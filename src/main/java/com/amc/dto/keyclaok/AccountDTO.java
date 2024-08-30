package com.amc.dto.keyclaok;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

// import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class AccountDTO {
  private String photoProfil;
  @NotBlank
  @NotEmpty
  private String username;
  private String firstName;
  private String lastName;
  @Email(message = "Entrez une email valide")
  private String email;
  private String password;
  private boolean enabled = false;
  private boolean totp = false;
  private boolean emailVerified = false;
  private long createdTimestamp = new Date().getTime();
  private String realmId;
}
