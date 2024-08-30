package com.amc.repository.myamc;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amc.model.myamc.File;

public interface FileRepository extends JpaRepository<File, UUID> {

}
