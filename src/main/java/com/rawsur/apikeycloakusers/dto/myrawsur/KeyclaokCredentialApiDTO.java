package com.rawsur.apikeycloakusers.dto.myrawsur;

import lombok.Data;

@Data
public class KeyclaokCredentialApiDTO {
    private String username;
    private String password;
    private String granttype;
    private String clientid;
}
