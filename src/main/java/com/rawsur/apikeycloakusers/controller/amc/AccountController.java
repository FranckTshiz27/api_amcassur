package com.rawsur.apikeycloakusers.controller.amc;

import com.rawsur.apikeycloakusers.dto.keyclaok.AccountDTO;
import com.rawsur.apikeycloakusers.dto.keyclaok.UserChangePassDTO;
import com.rawsur.apikeycloakusers.httpResponse.HttpResponse;
import com.rawsur.apikeycloakusers.routes.Routes;
import com.rawsur.apikeycloakusers.services.amc.AccountService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
