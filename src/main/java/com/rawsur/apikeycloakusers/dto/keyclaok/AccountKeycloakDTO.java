package com.rawsur.apikeycloakusers.dto.keyclaok;

import lombok.Data;

@Data
public class AccountKeycloakDTO {

    private String username;
    private String firstName;
    private String lastName;
    // private String email;
    private boolean enabled;
    private boolean totp;
    private boolean emailVerified;
    private String[] disableableCredentialTypes = new String[] {};
    private String[] requiredActions = new String[] {};
    private int notBefore = 0;
    private AccessDTO access = new AccessDTO();
    private String[] realmRoles = new String[] {};
    private CredentialsDTO[] credentials = new CredentialsDTO[] {};
    // private String password;
}
