package com.amc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.amc.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ApiAmcAssur implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiAmcAssur.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Server run");
    }

     

}
