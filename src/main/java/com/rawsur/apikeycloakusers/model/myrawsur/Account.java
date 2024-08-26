package com.rawsur.apikeycloakusers.model.myrawsur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "user_entity")
@NoArgsConstructor
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID id;
  @Column(name = "username")
  private String username;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "email")
  private String email;
  @Column(name = "photo_profil")
  private String photoProfil;
  @Column(name = "enabled")
  private boolean enabled;
  @Column(name = "totp")
  private boolean totp;
  @Column(name = "email_verified")
  private boolean emailVerified;
  @Column(name = "realm_id")
  private String realmId;

}
