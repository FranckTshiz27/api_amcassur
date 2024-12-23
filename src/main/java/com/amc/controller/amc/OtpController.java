package com.amc.controller.amc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amc.dto.myrawsur.OtpDTO;
import com.amc.httpResponse.HttpResponse;
import com.amc.routes.Routes;
import com.amc.services.amc.COtpService;

import lombok.extern.slf4j.Slf4j;

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
    System.out.println("USERNAME : "+otp.getUsername());
    System.out.println("PREFIX : "+otp.getPrefix_country());
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
