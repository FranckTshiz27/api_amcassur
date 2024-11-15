package com.amc.services.amc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.amc.configs.Data.Constants;
import com.amc.dto.UpdateAccountDTO;
import com.amc.dto.keyclaok.AccountDTO;
import com.amc.dto.keyclaok.AccountKeycloakDTO;
import com.amc.dto.keyclaok.CredentialsDTO;
import com.amc.dto.keyclaok.TokenDTO;
import com.amc.dto.keyclaok.UserChangePassDTO;
import com.amc.dto.keyclaok.UserDTO;
import com.amc.enums.HttpStatusResponse;
import com.amc.httpResponse.HttpResponse;
import com.amc.model.myamc.Account;
import com.amc.model.myamc.Keyclaokcredential;
import com.amc.repository.myamc.AccountRepository;
import com.amc.routes.Routes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  Environment env;

  @Transactional
  public HttpResponse createAccount(AccountDTO accountDTO) {
    log.info("BEGIN CREATE ACCOUNT (SERVICE)");

    HttpResponse httpResponse = null;

    // convert string in objet

    // Gson gson = new Gson();
    // Account account = gson.fromJson(data, Account.class);
    Account account = this.accoutDtoToAccount(accountDTO);
    log.debug("ACCOUNT CREATE SUCCES");

    AccountKeycloakDTO accountKeycloakDTO = this.accoutKeycloakDtoToAccount(accountDTO);
    log.debug("ACCOUNTDTO CREATE SUCCES");

    try {
      // Get user by UserName or Email
      Account account_exist = this.accountRepository.findByUsername(account.getUsername()); // save

      // in
      // DB
      if (account_exist != null &&
          account_exist.getEmail().equals(account.getEmail())) {
        httpResponse = new HttpResponse(); // create instance HttpResponse
        httpResponse.setCode(400);
        httpResponse.setMessage("L'adresse email existe déjà.");
        httpResponse.setStatus(HttpStatusResponse.EMAIL_EXIST.name());
        return httpResponse;
      } else if (account_exist != null &&
          account_exist.getUsername().equals(account.getUsername())) {
        httpResponse = new HttpResponse(); // create instance HttpResponse
        httpResponse.setCode(400);
        httpResponse.setMessage("Le numéro de téléphone existe déjà");
        httpResponse.setStatus(HttpStatusResponse.USERNAME_EXIST.name());
        return httpResponse;
      }

      // insert in DB user-entity
      account.setRealmId(Constants.realmId);
      Account user = this.accountRepository.save(account); // save in DB
      log.debug("ACCOUNT SAVED");

      if (user.getId() != null) {
        // System.out.println("User create in DB");
        // Generate token keyclaok with admin account
        log.info("SAVE ACCOUNT OK");
      
        httpResponse = new HttpResponse(); // create instance HttpResponse
        // List<CKeyclaokCredentialApi> obj =
        // this.keyclaokCredentialApiRepository.findAll();

        Keyclaokcredential keyclaokcredential = this.readJsonFileConfig();
     

        if (keyclaokcredential != null) {
          String token = getToken(keyclaokcredential);
        
          // check response
          // log.info("TOKEN : " + token);
          if (token != null) {
            // Create user in keycloak Rest API
            RestTemplate template = new RestTemplate();
            HttpHeaders httpHeader = new HttpHeaders();

            // MultiMap headers = new MultiValueMap();

            httpHeader.setContentType(MediaType.APPLICATION_JSON);
            httpHeader.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            log.debug("SET HEADER SUCCES");

            // build the request
            // HttpEntity<AccountKeycloakDTO> httpEntity = new
            // HttpEntity<>(accountKeycloakDTO,
            // httpHeader);
            HttpEntity<AccountKeycloakDTO> httpEntity = new HttpEntity<>(accountKeycloakDTO,
                httpHeader);

            log.debug("httpEntity (BODY) : " + httpEntity.getBody());
            log.debug("httpEntity (HEADER) : " + httpEntity.getHeaders());

            String url_create_user = Routes.CREATE_USER_KEYCLAOK;
            log.info("URL : " + url_create_user);

            // send POST request
            ResponseEntity<String> responseEntity = template.postForEntity(url_create_user, httpEntity,
                String.class);

            log.info("STATUS CODE : " + responseEntity.getStatusCode());

            if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
              accountKeycloakDTO.setCredentials(null);

              httpResponse.setCode(201);
              httpResponse.setMessage("Utilisateur créé avec succes");
              httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
              httpResponse.setData(accountKeycloakDTO);

            } else {

              // remove in DB last-insert
              this.accountRepository.deleteById(user.getId());

              httpResponse = new HttpResponse();
              httpResponse.setCode(500);
              httpResponse.setMessage(
                  "Le processus de création de compte n'a pas abouti jusqu'à la fin. Il y a une erreur qui s'est produite pendant le traitement.");
              httpResponse.setStatus(HttpStatusResponse.ERROR.name());

            }
          } else {
            // remove in DB last-insert
            this.accountRepository.deleteById(user.getId());
            httpResponse.setCode(500);
            httpResponse.setMessage("Authentification keyCloak echouée");
            httpResponse.setStatus(HttpStatusResponse.KEYCLOAK_NOT_AUTHENTIFICATION.name());
          }

        } else { //
          httpResponse.setCode(500);
          httpResponse.setMessage("Authentification keyCloak echouée");
          httpResponse.setStatus(HttpStatusResponse.KEYCLOAK_NOTHING_SETTING_AUTHENTIFICATION.name());
        }

      } else {

        httpResponse = new HttpResponse();
        httpResponse.setCode(500);
        httpResponse
            .setMessage("Le compte n'a pas été créé. Il y a une erreur qui s'est produite pendant le traitement.");
        httpResponse.setStatus(HttpStatusResponse.ERROR.name());
      }

    } catch (Exception e) {

      System.out.println(" EXCEPTION ".repeat(50));
      System.out.println(e.getMessage());
      // remove in DB last-insert
      this.accountRepository.deleteByUsername(account.getUsername());

      httpResponse = new HttpResponse();
      httpResponse.setCode(500);
      httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
      httpResponse.setStatus(HttpStatusResponse.ERROR.name());
      httpResponse.setData(e);
      return httpResponse;

    }

    return httpResponse;
  }

  @Transactional
  public HttpResponse resetPasswordUser(UserChangePassDTO account) {

    HttpResponse httpResponse = null;

    try {

      RestTemplate restTemplate = new RestTemplate();

      String url_auth = Routes.GET_USER + "v1/get-by-realm/" + Constants.realmId + "/" + account.getUsername();

      ResponseEntity<String> response = restTemplate.getForEntity(url_auth, String.class);

      // check response
      if (response.getStatusCode() == HttpStatus.OK) {

        httpResponse = new HttpResponse(); // create instance HttpResponse

        Gson gson_token = new Gson();

        // On recupere le user keycloak
        UserDTO user = gson_token.fromJson(response.getBody(), UserDTO.class);

        // On s'authentifie avec le compte keycloak Admin pour avoir l'autorisation
        // d'appeler l'API keycloak

        Keyclaokcredential keyclaokcredential = this.readJsonFileConfig();

        if (keyclaokcredential != null) {

          String token = getToken(keyclaokcredential);
          // check response
          if (token != null) {

            // Create user in keycloak Rest API
            RestTemplate restTemplate_create_user = new RestTemplate();

            HttpHeaders headers_create_user = new HttpHeaders();
            headers_create_user.setContentType(MediaType.APPLICATION_JSON);
            headers_create_user.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

            AccountKeycloakDTO accountDTO = new AccountKeycloakDTO();
            accountDTO.setEnabled(user.isEnabled()); // changera en : true lors de l'activation de compte

            // On remplace les mêmes informations de l'utilisateur keycloak trouvé ci-haut
            accountDTO.setUsername(account.getUsername());

            // construction de l'objet credentials (Type et temporary ont des valeurs par
            // defaut)
            CredentialsDTO credentialsDTO = new CredentialsDTO();
            credentialsDTO.setValue(account.getPassword());

            accountDTO.setCredentials(new CredentialsDTO[] { credentialsDTO });

            // request body parameters
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("username", account.getUsername());
            objectMap.put("credentials", accountDTO.getCredentials());

            // build the request
            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(objectMap, headers_create_user);

            String url_create_user = Routes.CREATE_USER_KEYCLAOK + "/{userId}";

            // send PUT request
            ResponseEntity<String> responseEntity = restTemplate_create_user.exchange(url_create_user, HttpMethod.PUT,
                httpEntity, String.class, user.getId());

            if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
              httpResponse.setCode(204); // La mise à jour de mot de passe s'est effectuée avec success
            } else {

              httpResponse = new HttpResponse();
              httpResponse.setCode(500);
              httpResponse.setMessage(
                  "Le processus de création de compte n'a pas abouti jusqu'à la fin. Il y a une erreur qui s'est produite pendant le traitement.");
              httpResponse.setStatus(HttpStatusResponse.ERROR.name());

            }

          } else {

            httpResponse.setCode(500);
            httpResponse.setMessage("Authentification keyCloak echouée");
            httpResponse.setStatus(HttpStatusResponse.ERROR.name());

          }

        } else {
          httpResponse.setCode(500);
          httpResponse.setMessage("Authentification keyCloak echouée");
          httpResponse.setStatus(HttpStatusResponse.KEYCLOAK_NOTHING_SETTING_AUTHENTIFICATION.name());

        }

      } else {

        httpResponse = new HttpResponse();
        httpResponse.setCode(404);
        httpResponse.setMessage("Le compte d'utilisateur inexistant");
        httpResponse.setStatus(HttpStatusResponse.USERNAME_NOT_EXIST.name());

      }

    } catch (Exception e) {

      // remove in DB last-insert
      // this.accountRepository.deleteByUsername(account.getUsername());

      httpResponse = new HttpResponse();
      httpResponse.setCode(500);
      httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
      httpResponse.setStatus(HttpStatusResponse.ERROR.name());
      httpResponse.setData(e);
      return httpResponse;
    }

    return httpResponse;
  }

  public String getToken(Keyclaokcredential obj) {
    log.info("BEGIN GET TOKEN");
    ResponseEntity<String> response = null;
    try {
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add("username", obj.getUsername());
      map.add("password", obj.getPassword());
      map.add("grant_type", obj.getGranttype());
      map.add("client_id", obj.getClientid());

      HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
      System.out.println("REQUEST ENTITY");
      System.out.println(entity);

      String url_auth = Routes.GET_TOKEN_KEYCLAOK;
      log.info(url_auth);
      response = restTemplate.postForEntity(url_auth, entity, String.class);
      System.out.println("==================GET TOKEN===============");
      System.out.println(response.getStatusCode());

      // check response
      if (response.getStatusCode() == HttpStatus.OK) {
        Gson gson_token = new Gson();
        TokenDTO tokenDTO = gson_token.fromJson(response.getBody(), TokenDTO.class); // token
        return tokenDTO.getAccess_token();
      } else {
        return null;
      }

    } catch (Exception e) {
      System.out.println("ERREUR");
      System.out.println(e.getMessage());
      System.out.println(response.getStatusCode());
      return null;
    }

  }

   public HttpResponse getProfilUser(String username) {

        HttpResponse httpResponse = null;
        try {

            // Update user
            Account user = this.accountRepository.findByUsername(username);
            if (user.getId() == null) {
                httpResponse = new HttpResponse();
                httpResponse.setCode(404);
                httpResponse.setMessage("Le compte d'utilisateur inexistant.");
                httpResponse.setStatus(HttpStatusResponse.ERROR.name());

            } else {

                if (user.getPhotoProfil() != null) {
                    httpResponse = new HttpResponse();
                    httpResponse.setCode(200);
                    httpResponse.setMessage("Le compte a été trouvé avec une photo de profil succès");
                    httpResponse.setData(user);
                    httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());

                } else {
                    httpResponse = new HttpResponse();
                    httpResponse.setCode(200);
                    user.setPhotoProfil("");
                    httpResponse.setData(user);
                    httpResponse.setMessage("Le compte a été trouvé avec une photo de profil succès");
                    httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            httpResponse = new HttpResponse();
            httpResponse.setCode(500);
            httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
            httpResponse.setStatus(HttpStatusResponse.ERROR.name());
            httpResponse.setData(e);
        }
        return httpResponse;

    }

  private Account accoutDtoToAccount(AccountDTO accountDTO) {
    Account account = new Account();

    account.setUsername(accountDTO.getUsername());
    account.setLastName(accountDTO.getLastName());
    account.setFirstName(accountDTO.getFirstName());
    account.setPhotoProfil(accountDTO.getPhotoProfil());
    // account.setEmail(accountDTO.getEmail());
    account.setEnabled(accountDTO.isEnabled());
    account.setEmailVerified(accountDTO.isEmailVerified());
    account.setTotp(accountDTO.isTotp());

    return account;
  }

  private AccountKeycloakDTO accoutKeycloakDtoToAccount(AccountDTO accountDTO) {
    AccountKeycloakDTO account = new AccountKeycloakDTO();

    account.setFirstName(accountDTO.getFirstName());
    account.setLastName(accountDTO.getLastName());
    account.setUsername(accountDTO.getUsername());
    // account.setEmail(accountDTO.getEmail());
    account.setEnabled(accountDTO.isEnabled());
    account.setEmailVerified(accountDTO.isEmailVerified());
    account.setTotp(accountDTO.isTotp());

    CredentialsDTO credentialsDTO = new CredentialsDTO();
    credentialsDTO.setValue(accountDTO.getPassword());

    account.setCredentials(new CredentialsDTO[] { credentialsDTO });

    return account;
  }

  public Keyclaokcredential readJsonFileConfig() {
    Keyclaokcredential keyclaokcredential = new Keyclaokcredential();
    ObjectMapper objectMapper = new ObjectMapper();
    // File file = Paths.get("C:/config/config.dev.json").toFile();
    File file = Paths.get("C:/config/keyclaokcredential.json").toFile();
    try {
      keyclaokcredential = objectMapper.readValue(file, Keyclaokcredential.class);

      return keyclaokcredential;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return keyclaokcredential;
  }

  @Transactional
  public String updateUserMyRawsur(UpdateAccountDTO dto) {
    String username = dto.getUsername();
   
    Account user = this.accountRepository.findByUsername(username);

    if (user == null)
      return "ERREUR";
    user.setAddress(dto.getAddress());
    user.setFirstName(dto.getFirstname());
    user.setLastName(dto.getLastname());
    user.setPname(dto.getPname());
    user.setDateNaiss(dto.getDatenaiss());
    user.setLieuNaiss(dto.getLieunaiss());
    user.setEmail(dto.getEmail());
    user.setTypeDoc(dto.getTypedoc());
    user.setNumDoc(dto.getNumdoc());
    user.setDocStr(dto.getDocstr());
    user.setTypeFile(dto.getTypeFile());
    user.setDateExp(dto.getDateExp());
    System.out.println(" HELLLLLLLLLLLO --------------- *******************");
    int code = dto.getCode();
    user.setCode(code == 0 ? null : code);
    user = this.accountRepository.save(user);

    return "OK";
  }

}
