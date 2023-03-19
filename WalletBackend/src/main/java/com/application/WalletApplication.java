package com.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(WalletApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		WalletDto wallet = new WalletDto();
		System.out.println(wallet.getBalance());
	}



}
