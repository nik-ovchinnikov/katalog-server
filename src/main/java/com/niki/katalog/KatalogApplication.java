package com.niki.katalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class KatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(KatalogApplication.class, args);
	}

}
