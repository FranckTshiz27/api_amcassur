package com.amc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountDTO {
  private String username;
  private String firstname;
  private String pname;
  private String lastname;
  private int code;

  private String datenaiss;
  private String lieunaiss;
  private String address;
  private String email;

  private String typedoc;
  private String numdoc;
  private String docstr;
  private String typeFile;
  private String dateExp;
}
