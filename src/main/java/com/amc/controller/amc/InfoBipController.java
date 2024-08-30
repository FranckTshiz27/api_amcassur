package com.amc.controller.amc;
// package com.rawsur.apikeycloakusers.controller.amc;

// import com.rawsur.apikeycloakusers.dto.myrawsur.InfoBipDTO;
// import com.rawsur.apikeycloakusers.httpResponse.HttpResponse;
// import com.rawsur.apikeycloakusers.routes.Routes;
// import com.rawsur.apikeycloakusers.services.amc.InfoBipService;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.logging.Logger;

// @RestController
// @CrossOrigin(origins = "*")
// @RequestMapping(path = Routes.BASE_URL_INFO_BIP)
// public class InfoBipController {

//   private static final Logger logger = Logger.getLogger(InfoBipController.class.getName());

//   @Autowired
//   private InfoBipService infoBipService;

//   /***
//    *
//    * @param body
//    * @return
//    */
//   @PostMapping(path = Routes.CREATE_SETTING, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//   public ResponseEntity<?> create(@RequestBody InfoBipDTO body) {
//     logger.info("*********************************");
//     HttpResponse httpResponse = this.infoBipService.create(body);
//     return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
//   }


//   @GetMapping(path = Routes.GET_SETTINGS, produces = MediaType.APPLICATION_JSON_VALUE)
//   public ResponseEntity<?> getSetting() {
//     HttpResponse httpResponse = this.infoBipService.getSetting();
//     return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
//   }

// }
