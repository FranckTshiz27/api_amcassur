// package com.rawsur.apikeycloakusers.services.amc;

// import com.google.gson.Gson;
// import com.rawsur.apikeycloakusers.dto.keyclaok.TokenDTO;
// import com.rawsur.apikeycloakusers.dto.myrawsur.InfoBipDTO;
// import com.rawsur.apikeycloakusers.dto.myrawsur.KeyclaokCredentialApiDTO;
// import com.rawsur.apikeycloakusers.enums.HttpStatusResponse;
// import com.rawsur.apikeycloakusers.httpResponse.HttpResponse;
// import com.rawsur.apikeycloakusers.model.myrawsur.CInfoBip;
// import com.rawsur.apikeycloakusers.repository.mongo.CInfoBipRepository;
// import com.rawsur.apikeycloakusers.routes.Routes;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.env.Environment;
// import org.springframework.http.*;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;
// import org.springframework.web.client.RestTemplate;

// import java.util.List;
// import java.util.logging.Logger;

// @Service
// public class KeyclaokCredentialAPIService {
//   @Autowired
//   private CKeyclaokCredentialApiRepository keyclaokCredentialApiRepository;
//   private static final Logger logger = Logger.getLogger(KeyclaokCredentialAPIService.class.getName());

//   @Autowired
//   Environment env;

//   // @Transactional
//   // public HttpResponse create(KeyclaokCredentialApiDTO keyclaokCredentialApiDTO) {

//   //   HttpResponse httpResponse = null;

//   //   try {

//   //     boolean b = testCredential(keyclaokCredentialApiDTO);

//   //     if (b) {

//   //       List<CKeyclaokCredentialApi> cKeyclaokCredentialApi = this.keyclaokCredentialApiRepository.findAll();

//   //       if (cKeyclaokCredentialApi.size() > 0) {
//   //         // on fait l'update
//   //         cKeyclaokCredentialApi.get(0).setUsername(keyclaokCredentialApiDTO.getUsername());
//   //         cKeyclaokCredentialApi.get(0).setPassword(keyclaokCredentialApiDTO.getPassword());
//   //         cKeyclaokCredentialApi.get(0).setGranttype(keyclaokCredentialApiDTO.getGranttype());
//   //         cKeyclaokCredentialApi.get(0).setClientid(keyclaokCredentialApiDTO.getClientid());

//   //         CKeyclaokCredentialApi save = this.keyclaokCredentialApiRepository.save(cKeyclaokCredentialApi.get(0));

//   //         if (save.getUsername().isEmpty()) {
//   //           httpResponse = new HttpResponse(); // create instance HttpResponse
//   //           httpResponse.setCode(500);
//   //           httpResponse.setMessage("Les nouveaux paramètres keyclaok credential access API n'ont pas été enregistrés");
//   //           httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//   //         } else {
//   //           httpResponse = new HttpResponse(); // create instance HttpResponse
//   //           httpResponse.setCode(201);
//   //           httpResponse.setMessage("Les nouveaux paramètres keyclaok credential access API ont été mise à jour");
//   //           httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
//   //         }

//   //       } else {
//   //         // on crée le nouveau parametre

//   //         CKeyclaokCredentialApi obj = new CKeyclaokCredentialApi();
//   //         obj.setUsername(keyclaokCredentialApiDTO.getUsername());
//   //         obj.setPassword(keyclaokCredentialApiDTO.getPassword());
//   //         obj.setGranttype(keyclaokCredentialApiDTO.getGranttype());
//   //         obj.setClientid(keyclaokCredentialApiDTO.getClientid());

//   //         CKeyclaokCredentialApi save = this.keyclaokCredentialApiRepository.insert(obj);

//   //         if (save.getUsername().isEmpty()) {
//   //           httpResponse = new HttpResponse(); // create instance HttpResponse
//   //           httpResponse.setCode(500);
//   //           httpResponse.setMessage("Les nouveaux paramètres keyclaok credential access API n'ont pas été enregistrés");
//   //           httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//   //         } else {
//   //           httpResponse = new HttpResponse(); // create instance HttpResponse
//   //           httpResponse.setCode(201);
//   //           httpResponse.setMessage("Les nouveaux paramètres keyclaok credential access API ont été enregistrés");
//   //           httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
//   //         }

//   //       }

//   //     } else {
//   //       httpResponse = new HttpResponse(); // create instance HttpResponse
//   //       httpResponse.setCode(200);
//   //       httpResponse.setStatus(HttpStatusResponse.EMPTY.name());
//   //     }

//   //   } catch (Exception e) {

//   //     httpResponse = new HttpResponse();
//   //     httpResponse.setCode(500);
//   //     httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
//   //     httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//   //     httpResponse.setData(e);
//   //     return httpResponse;

//   //   }

//   //   return httpResponse;
//   // }

//   // @Transactional
//   // public HttpResponse checkCredential(KeyclaokCredentialApiDTO obj) {

//   //   HttpResponse httpResponse = null;

//   //   try {

//   //     RestTemplate restTemplate = new RestTemplate();

//   //     HttpHeaders headers = new HttpHeaders();
//   //     headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//   //     MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//   //     map.add("username", obj.getUsername());
//   //     map.add("password", obj.getPassword());
//   //     map.add("grant_type", obj.getGranttype());
//   //     map.add("client_id", obj.getClientid());

//   //     HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

//   //     String url_auth = Routes.GET_TOKEN_KEYCLAOK;
//   //     ResponseEntity<String> response = restTemplate.postForEntity(url_auth, entity, String.class);

//   //     // check response
//   //     if (response.getStatusCode() == HttpStatus.OK) {
//   //       httpResponse = new HttpResponse(); // create instance HttpResponse
//   //       httpResponse.setCode(200);
//   //       httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
//   //     } else {
//   //       httpResponse = new HttpResponse(); // create instance HttpResponse
//   //       httpResponse.setCode(500);
//   //       httpResponse.setMessage("Authentification keyCloak echouée");
//   //       httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//   //     }

//   //   } catch (Exception e) {
//   //     httpResponse = new HttpResponse();
//   //     httpResponse.setCode(500);
//   //     httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
//   //     httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//   //     httpResponse.setData(e);
//   //     return httpResponse;
//   //   }

//   //   return httpResponse;
//   // }

//   // @Transactional
//   // public HttpResponse getSetting() {

//   //   HttpResponse httpResponse = null;

//   //   try {

//   //     List<CKeyclaokCredentialApi> obj = this.keyclaokCredentialApiRepository.findAll();

//   //     if (obj.size() > 0) {

//   //       httpResponse = new HttpResponse(); // create instance HttpResponse
//   //       httpResponse.setCode(200);
//   //       httpResponse.setData(obj.get(0));
//   //       httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());

//   //     } else {

//   //       httpResponse = new HttpResponse(); // create instance HttpResponse
//   //       httpResponse.setCode(200);
//   //       httpResponse.setStatus(HttpStatusResponse.EMPTY.name());

//   //     }

//   //   } catch (Exception e) {

//   //     httpResponse = new HttpResponse();
//   //     httpResponse.setCode(500);
//   //     httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
//   //     httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//   //     httpResponse.setData(e);
//   //     return httpResponse;

//   //   }

//   //   return httpResponse;
//   // }

//   // public boolean testCredential(KeyclaokCredentialApiDTO obj) {

//   //   try {

//   //     RestTemplate restTemplate = new RestTemplate();

//   //     HttpHeaders headers = new HttpHeaders();
//   //     headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

//   //     MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//   //     map.add("username", obj.getUsername());
//   //     map.add("password", obj.getPassword());
//   //     map.add("grant_type", obj.getGranttype());
//   //     map.add("client_id", obj.getClientid());

//   //     HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

//   //     String url_auth = Routes.GET_TOKEN_KEYCLAOK;
//   //     ResponseEntity<String> response = restTemplate.postForEntity(url_auth, entity, String.class);

//   //     // check response
//   //     if (response.getStatusCode() == HttpStatus.OK) {
//   //       return true;
//   //     } else {
//   //       return false;
//   //     }

//   //   } catch (Exception e) {
//   //     return false;
//   //   }
//   // }

// }
