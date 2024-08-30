package com.amc.dto;


import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ContratAssuranceDto {
    private int codeinte;
    private int numepoli;
    private int numequit;
    @NotBlank(message = "Le type de couverture ne peut être nul ou vide")
    private String formcouv;
    private int codecate;
    private int codeassu;
    private Integer numeaven;
    @NotNull(message = "La date de compensation ne peut être nulle")
    private String datecomp;
    @NotNull(message = "La date d'émission ne peut être nulle")
    private String dateemis;
    @NotNull(message = "La date d'effet ne peut être nulle")
    private String dateeffe;
    @NotNull(message = "La date d'échéance ne peut être nulle")
    private String dateeche;
    private String codedure;
    private Integer valedure;
    private String unitdure;
    private String libvaldu;
    @NotBlank(message = "La dénomination de la formule de couverture ne peut être nulle ou vide")
    private String libforco;
    private double primnett;
    private double taxeprim;
    private double accequit;
    private double taxeacce;
    private double primtota;
    @NotBlank(message = "Le montant total TTC ne peut être nul ou vide")
    private String prim_ttc;
    private double primtota_de;
    @NotBlank(message = "Le montant total libellé en devises étrangères ne peut être nul ou vide")
    private String primtota_lde;
    @NotBlank(message = "Le statut de paiement ne peut être nul ou vide")
    private int statut_paiement;
    @NotBlank(message = "Le statut de validation ne peut être nul ou vide")
    private boolean statut_validation;
    private int numero_police;
    @NotBlank(message = "L'opérateur ne peut être nul ou vide")
    private String operateur;
    @NotBlank(message = "Le numéro de téléphone ne peut être nul ou vide")
    private String telephone;
    private String orderId;
}
