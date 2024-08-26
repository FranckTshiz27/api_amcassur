package com.rawsur.apikeycloakusers.controller.amc;

import com.rawsur.apikeycloakusers.dto.myrawsur.OtpDTO;
import com.rawsur.apikeycloakusers.httpResponse.HttpResponse;
import com.rawsur.apikeycloakusers.routes.Routes;
import com.rawsur.apikeycloakusers.services.amc.COtpService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping(path = Routes.BASE_URL_MY_AMC)
@RestController
public class OtpController {
  @Autowired
  private COtpService cOtpService;

  /***
   *
   * @param otp
   * @return
   * @throws Exception
   */
  @PostMapping(path = Routes.OTP_REQUEST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> sendOtp(@RequestBody OtpDTO.OtpCreateDTO otp) throws Exception {
    log.info("CONTROLLER SEND OTP");
    HttpResponse httpResponse = this.cOtpService.sendOtp(otp.getUsername(), otp.getPrefix_country());
    return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
  }

  /***
   *
   * @param otp
   * @return
   */
  @PostMapping(path = Routes.OTP_VERIFY, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> verifyOTP(@RequestBody OtpDTO.OtpVerifyDTO otp) {
   
    HttpResponse httpResponse = this.cOtpService.verifiyOtp(otp.getUsername(), otp.getCode());

    System.out.println(httpResponse.getCode());
    return ResponseEntity.status(httpResponse.getCode()).body(httpResponse);
  }

}
