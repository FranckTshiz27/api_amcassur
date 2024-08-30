package com.amc.model.orass;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Date;


@Immutable
@Entity
@Table(name = "V_MOBILE_POLICY")
@Subselect("select * from V_MOBILE_POLICY")
@Data
public class Policy implements Serializable {

    @Id
    private String id;

    @Column(name = "CODEINTE")
    @JsonProperty("CODEINTE")
    private String codeint;
    @Column(name = "NUMEPOLI")
    @JsonProperty("NUMEPOLI")
    private String numpoli;

    @Column(name = "NUMARCA")
    @JsonProperty("NUMARCA")
    private String numearca;

    @Column(name = "NUMEAVEN")
    @JsonProperty("NUMEAVEN")
    private int numeave;

    @Column(name = "TYPE_AVENANT")
    @JsonProperty("TYPE_AVENANT")
    private String type_avenant;

    @Column(name = "DATEEMI")
    @JsonProperty("DATEEMI")
    private Date emissionDate;

    @Column(name = "DATEEFFE")
    @JsonProperty("DATEEFFE")
    private Date effectDate;

    @Column(name = "DATEECHE")
    @JsonProperty("DATEECHE")
    private Date issueDate;

    @Column(name = "CIVILITE")
    @JsonProperty("CIVILITE")
    private String civilite;

    @Column(name = "CODE_ASSURE")
    @JsonProperty("CODE_ASSURE")
    private int code_assure;

    @Column(name = "ASSURE")
    @JsonProperty("ASSURE")
    private String assure;

    @Column(name = "PRIMNETT")
    @JsonProperty("PRIMNETT")
    private double prime_nette;

    @Column(name = "ACCEQUIT")
    @JsonProperty("ACCEQUIT")
    private double accessoires;

    @Column(name = "ARCA")
    @JsonProperty("ARCA")
    private double arca;

    @Column(name = "TVA_PRIMNETT")
    @JsonProperty("TVA_PRIMNETT")
    private double tva_prime_nette;

    @Column(name = "TVA_ACCEQUIT")
    @JsonProperty("TVA_ACCEQUIT")
    private double tva_accessoires;

    @Column(name = "PRIMTOTA")
    @JsonProperty("PRIMTOTA")
    private double total;

    @Column(name = "PHONE")
    @JsonProperty("PHONE")
    private String phone;

    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @Column(name = "PERS_MORALE")
    @JsonProperty("PERS_MORALE")
    private String pers_morale;

    @Transient
    private String pays_etablissement;

    @Transient
    String adresse;

    @Transient
    double taxe;

    @Transient
    boolean status;

}
