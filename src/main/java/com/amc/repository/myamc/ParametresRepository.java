package com.amc.repository.myamc;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amc.model.myamc.Parametres;

public interface ParametresRepository extends JpaRepository<Parametres, UUID> {

}
