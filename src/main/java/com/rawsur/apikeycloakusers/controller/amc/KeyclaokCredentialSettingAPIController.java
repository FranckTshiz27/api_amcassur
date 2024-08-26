// package com.rawsur.apikeycloakusers.controller.amc;

// import com.rawsur.apikeycloakusers.dto.myrawsur.KeyclaokCredentialApiDTO;
// import com.rawsur.apikeycloakusers.httpResponse.HttpResponse;
// import com.rawsur.apikeycloakusers.routes.Routes;
// import com.rawsur.apikeycloakusers.services.myrawsur.KeyclaokCredentialAPIService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @CrossOrigin(origins = "*")
// @RequestMapping(path = Routes.BASE_URL_KEYCLOAK_CREDENTIAL)
// public class KeyclaokCredentialSettingAPIController {

//   @Autowired
//   private KeyclaokCredentialAPIService keyclaokCredentialAPIService;

//   /***
//    *
//    * @param body
//    * @return
//    */
//   @PostMapping(path = Routes.CREATE_SETTING, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//   public ResponseEntity<?> create(@RequestBody KeyclaokCredentialApiDTO body) {
//     HttpResponse httpResponse = this.keyclaokCredentialAPIService.create(body);
//     return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
//   }

//   /***
//    *
//    * @param body
//    * @return
//    */
//   @PostMapping(path = Routes.CHECK_SETTING, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//   public ResponseEntity<?> checkCredential(@RequestBody KeyclaokCredentialApiDTO body) {
//     HttpResponse httpResponse = this.keyclaokCredentialAPIService.checkCredential(body);
//     return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
//   }


//   @GetMapping(path = Routes.GET_SETTINGS, produces = MediaType.APPLICATION_JSON_VALUE)
//   public ResponseEntity<?> getSetting() {
//     HttpResponse httpResponse = this.keyclaokCredentialAPIService.getSetting();
//     return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
//   }

// }
