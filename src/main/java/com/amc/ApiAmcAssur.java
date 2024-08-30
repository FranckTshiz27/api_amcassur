package com.amc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		// CKeyclaokCredentialApi keyclaok = new CKeyclaokCredentialApi();
		// keyclaok.setId("63e12c6348c1786c7ce3638d");
		// keyclaok.setUsername("admin");
		// // keyclaok.setUsername("kevin");
		// keyclaok.setPassword("Rawsur2022$");
		// // keyclaok.setPassword("12345678");
		// keyclaok.setClientid("admin-cli");
		// keyclaok.setGranttype("password");
		// this.accountService.getToken(keyclaok);
	}

}
