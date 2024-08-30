package com.amc.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateStepDto {
  @NotEmpty(message = "L'identifiant d'une étape ne peut être nulle ou vide")
  private String id;
  @NotEmpty(message = "La désignation d'une étape ne peut être nulle")
  private String name;
}
