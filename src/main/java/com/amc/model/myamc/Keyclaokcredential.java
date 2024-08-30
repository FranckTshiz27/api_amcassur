package com.amc.model.myamc;

import lombok.Data;

@Data
public class Keyclaokcredential {
    private String username;
    private String password;
    private String granttype;
    private String clientid;
}
