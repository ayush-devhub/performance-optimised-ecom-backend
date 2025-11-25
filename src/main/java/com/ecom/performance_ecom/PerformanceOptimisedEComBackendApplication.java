package com.ecom.performance_ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PerformanceOptimisedEComBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceOptimisedEComBackendApplication.class, args);
	}

}
