package com.amc.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateSinitreDTO {
  private String num_police;
  private String immatriculation;
  private List<String> img_sinstres;
  private List<String> img_doc;
  private double latitude;
  private double longitude;
  private String phoneNumber;
  private String decSinistre;
}
