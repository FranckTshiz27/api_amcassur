package com.amc.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateStepDto {
  @NotEmpty(message = "La désignation d'une étape ne peut être nulle")
  private String name;
}
