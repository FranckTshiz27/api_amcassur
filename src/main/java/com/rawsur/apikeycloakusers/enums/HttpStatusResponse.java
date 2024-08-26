package com.rawsur.apikeycloakusers.enums;

public enum HttpStatusResponse {
  SUCCESS,
  ERROR,
  EMPTY,
  EMPTY_SETTING_INFO_BIP,
  NOT_USERNAME,
  KEYCLOAK_NOT_AUTHENTIFICATION,
  KEYCLOAK_NOTHING_SETTING_AUTHENTIFICATION,
  FORMAT_INCORRECT,
  NOT_EMAIL,
  NOT_LAST_NAME,
  NOT_FIRST_NAME,
  EMAIL_EXIST,
  USERNAME_EXIST,
  USERNAME_NOT_EXIST,

  OTP_ESPIRED,
  OTP_OK,
  OTP_NOT_EXIST,
  OTP_NOT_SAVE;

}
