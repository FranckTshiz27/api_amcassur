package com.rawsur.apikeycloakusers.dto.myrawsur;

import lombok.Data;


public class OtpDTO {

  @Data
  public static class OtpCreateDTO {
    private String username;
    private String prefix_country;
  }

  @Data
  public static class OtpVerifyDTO {
    private String username;
    private String code;
  }

}
