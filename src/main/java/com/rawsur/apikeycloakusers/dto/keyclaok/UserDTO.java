package com.rawsur.apikeycloakusers.dto.keyclaok;

import lombok.Data;

@Data
public class UserDTO {

  private String id;
  private String username;
  private String email;
  private String email_constraint;
  private boolean email_verified;
  private String realmId;
  private boolean enabled;
  private String lastname;
  private String firstname;
  private Long created_timestamp;
  private String service_account_client_link;
  private String federation_link;
  private int not_before;
}
