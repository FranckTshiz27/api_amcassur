package com.amc.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateParametreDTO {
  @NotNull
  @Size(min = 13, max = 13, message = "Le numero de téléphone doit avoir 13 caractères")
  private String phone_call_center;
}
