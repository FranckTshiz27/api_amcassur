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
@Table(name = "roles_user_view")
@NoArgsConstructor
public class UserRole {
  @Id
  private UUID id;

  @Column(name = "realm_id")
  private String realmId;

  @Column(name = "client")
  private String client;

  @Column(name = "id_user")
  private String userId;

  @Column(name = "name")
  private String name;

}
