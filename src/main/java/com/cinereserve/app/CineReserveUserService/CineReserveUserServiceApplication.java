package com.cinereserve.app.CineReserveUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CineReserveUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineReserveUserServiceApplication.class, args);
	}

}
