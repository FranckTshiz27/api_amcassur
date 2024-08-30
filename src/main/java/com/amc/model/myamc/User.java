package com.amc.model.myamc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@Table(name = "user_entity")
@NoArgsConstructor
public class User {
  @Id
  private UUID id;

  @Column()
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "email_constraint")
  private String email_constraint;

  @Column(name = "email_verified")
  private boolean email_verified;

  @Column(name = "realm_id")
  private String realmId;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "last_name")
  private String lastname;

  @Column(name = "first_name")
  private String firstname;

  @Column(name = "created_timestamp")
  private Long created_timestamp;

  @Column(name = "service_account_client_link")
  private String service_account_client_link;

  @Column(name = "federation_link")
  private String federation_link;

  @Column(name = "not_before")
  private Integer not_before;
}
