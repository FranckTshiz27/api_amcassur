package com.amc.model.myamc;

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
  @Column(name = "code")
  private Integer code;
  @Column(name = "personne_moral")
  private String persMoral;
  @Column(name = "code_assure")
  private String code_assure;
  @Column(name = "photo_profil")
  private String photoProfil;
  @Column(name = "enabled")
  private boolean enabled;
  @Column(name = "totp")
  private boolean totp;
  @Column(name = "email_verified")
  private boolean emailVerified;
  @Column(name = "p_name")
  private String pname;
  @Column(name = "date_naiss")
  private String dateNaiss;
  @Column(name = "address")
  private String address;
  @Column(name = "lieu_naiss")
  private String lieuNaiss;
  @Column(name = "type_doc")
  private String typeDoc;
  @Column(name = "num_doc")
  private String numDoc;
  @Column(name = "doc_str")
  private String docStr;
  @Column(name = "type_file")
  private String typeFile;
  @Column(name = "date_exp")
  private String dateExp;
  @Column(name = "realm_id")
  private String realmId;
}
