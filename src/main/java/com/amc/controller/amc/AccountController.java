package com.amc.controller.amc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amc.dto.UpdateAccountDTO;
import com.amc.dto.keyclaok.AccountDTO;
import com.amc.dto.keyclaok.UserChangePassDTO;
import com.amc.httpResponse.HttpResponse;
import com.amc.routes.Routes;
import com.amc.services.amc.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Routes.BASE_URL_MY_AMC)
@Slf4j
public class AccountController {

  @Autowired
  private AccountService accountService;

  /***
   *
   * @param body
   * @return
   */
  @PostMapping(path = Routes.CREATE_USER, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@RequestBody AccountDTO accountDTO) {
    log.info("CONTROLLER CREATE ACCOUNT");
    HttpResponse httpResponse = this.accountService.createAccount(accountDTO);
    return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
  }

  /***
   *
   * @param body
   * @return
   */
  @PostMapping(path = Routes.CHANGE_PASSWORD_USER, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> resetPasswordUser(@RequestBody UserChangePassDTO body) {
    HttpResponse httpResponse = this.accountService.resetPasswordUser(body);
    System.out.println(httpResponse);
    return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
  }

   @PostMapping(path = Routes.UPDATE_USER_MYRAWSUR)
   public ResponseEntity<String> updateUserMyRawsur(@RequestBody @Valid UpdateAccountDTO dto) {
     log.info("UPDATE USER CONTROLLER");
     System.out.println(dto.getDatenaiss());
     String response = this.accountService.updateUserMyRawsur(dto);
     return ResponseEntity.status(HttpStatus.CREATED).body(response);
   }
  
   /***
   *
   * @param username
   * @return
   */
  @GetMapping(path = Routes.GET_ACCOUNT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getProfilUser(@PathVariable String username) {
    HttpResponse httpResponse = this.accountService.getProfilUser(username);
    return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
  }

}
