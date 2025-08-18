package com.scientia.mystore;

import com.scientia.mystore.dto.ContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(ContactInfoDto.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class MystoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystoreApplication.class, args);
	}

}
