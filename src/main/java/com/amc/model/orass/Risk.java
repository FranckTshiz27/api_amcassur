package com.amc.model.orass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
// @Immutable
@Data
@Entity
// @Table(name = "V_MOBILE_RISK")
@Table(name = "V_MOBILE_RISK")
// @Subselect("select * from V_MOBILE_RISK")
@Subselect("select * from V_MOBILE_RISK")
public class Risk implements Serializable {

    // @Id
    // @Transient
    // private String id = UUID.randomUUID().toString();

    // @Id
    private String cle; // ID policy

    @Column(name = "CODEINTE")
    private String codeint;
    @Column(name = "NUMEPOLI")
    private String numpoli;

    @Column(name = "NUMARCA")
    private String numearca;

    @Column(name = "NUMEAVEN")
    private int numave;

    @Column(name = "TYPE_AVENANT")
    private String typeave;

    @Column(name = "DATEEMI")
    private Date emissionDate;

    @Column(name = "DATEEFFE")
    private Date effectDate;

    @Column(name = "DATEECHE")
    private Date issueDate;

    @Column(name = "CODE_ASSURE")
    private int code_assure;

    @Column(name = "CIVILITE")
    private String civilite;

    // // @Column(name = "Assure")
    // // private String assure;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "CODERISQ")
    private int coderisq;

    @Id
    @Column(name = "NUMIMMAT")
    private String immatriculation;

    @Column(name = "TYPEVEHIC")
    private String typeVehicle;

    @Column(name = "MARQUE")
    private String mark;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "CAROSSERIE")
    private String caross;

    @Column(name = "PUISSANCE")
    private Integer power;

    @Column(name = "energie")
    private String energy;

    // // // @Column(name = "TONAGE")
    // // // private int tonage;

    // // @Column(name = "ANNEEFABRIC")
    // // private int yeafOfMgr;

    @Column(name = "DMC")
    private Date dmc;

    // // @Column(name = "VALEUR")
    // // private double value;

    @Column(name = "USAGEVEHIC")
    private String usage;

    @Column(name = "NUMMOTEUR")
    private String engineNumber;

    @Column(name = "CHASSI")
    private String chassis;

    @Column(name = "NBREPLACE")
    private Integer nbPlaces;

    // @Column(name = "REM")
    // private String rem;

    // @Column(name = "CODEGARA")
    // private String codeGarantie;

    // @Column(name = "LIBEGARA")
    // private String libGarantie;

    // @Column(name = "TYPE_GARA")
    // private String typeGarantie;

    // @Column(name = "OBSER")
    // private String observation;
    @Column(name = "ANNEEFABRIC")
    String mfgYear;

    @Transient
    private String remork;
}