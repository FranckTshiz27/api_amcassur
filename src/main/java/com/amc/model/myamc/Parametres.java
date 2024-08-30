package com.amc.model.myamc;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "parametres")
public class Parametres {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID id;

  private String phone_call_center;
  @JsonIgnore
  private String groupe_mail;
  @JsonIgnore
  private String mail_sender;
  @JsonIgnore
  private String password_mail_sender;
  // @JsonIgnore
  // private String token_souscription_orass;
}
