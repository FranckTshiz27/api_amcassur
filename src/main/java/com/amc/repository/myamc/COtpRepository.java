package com.amc.repository.myamc;


import org.springframework.data.jpa.repository.JpaRepository;

import com.amc.model.myamc.COtp;

public interface COtpRepository extends JpaRepository<COtp, String> {

    COtp findByUsernameAndCode(String username,String code);
    COtp findByUsername(String username);
    COtp findByUsernameAndCodeAndEnabled(String username,String code,boolean enabled);
    void deleteAllByUsername(String username);


}
