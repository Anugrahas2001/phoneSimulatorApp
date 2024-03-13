package com.ff.phonesimulatorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Phone Simulator Application OPEN-API", version = "1.0.0", description = "Phone Simulator Application API with Spring boot"), servers = {
		@Server(url = "http://localhost:8080", description = " Development Phone Simulator Application OPEN API url"),
		@Server(url = "http://localhost:8081", description = "Testing Phone Simulator Application OPEN API url") })
public class PhoneSimulatorAppApplication {

	private static ConfigurableApplicationContext applicationContext;													

	public static void main(String[] args) {
		SpringApplication.run(PhoneSimulatorAppApplication.class, args);
//		SpringApplication.exit(applicationContext, ()->0);

	}

}
