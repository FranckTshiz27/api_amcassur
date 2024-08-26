package com.rawsur.apikeycloakusers.repository.keycloak;


import org.springframework.data.jpa.repository.JpaRepository;
import com.rawsur.apikeycloakusers.model.keyclaok.COtp;

public interface COtpRepository extends JpaRepository<COtp, String> {

    COtp findByUsernameAndCode(String username,String code);
    COtp findByUsername(String username);
    COtp findByUsernameAndCodeAndEnabled(String username,String code,boolean enabled);
    void deleteAllByUsername(String username);


}
