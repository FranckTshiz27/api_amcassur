package com.amc.routes;


public interface Routes {

  public static String BASE_URL_KEYCLAOK = "api/keycloak/user/"; //
  public static String BASE_URL_KEYCLAOK_ROLE = "api/keycloak/role/"; //
  public static String BASE_URL_MY_AMC = "api/amcassur/"; //
  public static String BASE_URL_INFO_BIP = "api/amc/info-bip/"; //
  public static String BASE_URL_KEYCLOAK_CREDENTIAL = "api/keycloak/credential/"; //
  public static String BASE_URI_MOBILE = "api/mobile"; // Base url App Mobile
  public static String SINISTRE_URI ="sinistre";
  public static String policyUri = BASE_URI_MOBILE + "/policy";
  // URI KEYCLAOK
  public static String GET_USER_BY_REALM = "user/v1/get-by-realm/{realm}"; //
  public static String GET_USER_BY_REALM_AND_USERNAME = "user/v1/get-by-realm/{realm}/{username}"; //
    public static String GET_USERS_BY_REALM_AND_USERNAME = "v1/get-many-by-realm/{realm}/{username}"; //
  public static String GET_ROLES_BY_REALM_AND_CLIENT_AND_USER = "v1/get-by-realm-client-user/{realm}/{client}/{user}"; //
  public static String riskUri = BASE_URI_MOBILE + "/risk";
  // URI AMCASSUR
  public static String CREATE_USER = "user/v1/create/"; //
  public static String CREATE_SETTING = "v1/create/"; //
  public static String CHECK_SETTING = "v1/auth/"; //
  public static String GET_SETTINGS = "v1/find/all/"; //
  public static String CHANGE_PASSWORD_USER = "user/v1/reset-password/"; //

  public static String OTP_REQUEST = "user/v1/otp-send/"; //
  public static String OTP_VERIFY = "user/v1/otp-verify/"; //

  public static String CREATE_USER_KEYCLAOK = "http://192.168.88.82:8180/auth/admin/realms/rawsur/users";
  // public static String CREATE_USER_KEYCLAOK_TEST = "http://192.168.88.82:8180/auth/admin/realms/rawsur-test/users";
  public static String GET_TOKEN_KEYCLAOK = "http://192.168.88.82:8180/auth/realms/master/protocol/openid-connect/token";
  public static String GET_USER = "http://192.168.88.82:9006/api/keycloak/user/";
  public static String UPDATE_USER_MYRAWSUR = "v1/update-user-myrawsur/"; //

  // File file = Paths.get("C:/config/keyclaokcredential.json").toFile();


  
  public static String CREATE_USER_KEYCLAOK_TEST = "http://192.168.88.82:8180/auth/admin/realms/rawsur-test/users";

}
