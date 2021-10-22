package com.vetris.mastermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityScan("com.vetris.entity")
@ComponentScan("com.vetris")
@SpringBootApplication
//@EnableJpaAuditing
public class MasterManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(MasterManagementApplication.class, args);
	}
}