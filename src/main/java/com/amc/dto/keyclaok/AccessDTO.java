package com.amc.dto.keyclaok;

import lombok.Data;

@Data
public class AccessDTO {
  private boolean manageGroupMembership = false;
  private boolean view = true;
  private boolean mapRoles = true;
  private boolean impersonate = false;
  private boolean manage = false;
}
