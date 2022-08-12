package com.crud.prices;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PricesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}

	  @Bean public GroupedOpenApi pricesApi() {
		  return GroupedOpenApi.builder()
				  .group("prices-apis")
				  .packagesToScan("com.crud")
				  .build();
	  }
	  
	  
}
