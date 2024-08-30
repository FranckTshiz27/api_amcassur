package com.amc.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateAgenceDto {  
    @NotBlank(message = "L'avenue d'une agence ne peut être nulle ou vide")
    private String avenue; 
    @NotBlank(message = "La commune d'une agence ne peut être nulle ou vide")
    private String commune;
    @NotBlank(message = "La dénomination d'une agence ne peut être nulle ou vide")
    private String denomination;
    @NotBlank(message = "Le numero de l'avenue d'une agence ne peut être nul ou vide")
    private String numero;
    @NotBlank(message = "La ville d'une agence ne peut être nulle ou vide")
    private String ville;
}
