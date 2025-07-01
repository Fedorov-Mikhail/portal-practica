package com.example.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@EntityScan("com.example.portal.entities")
@EnableJpaRepositories("com.example.portal.repositories")
@SpringBootApplication
public class PortalApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
		System.out.println("Welcome to Portal");
	}

		@Override
		public void addResourceHandlers (ResourceHandlerRegistry registry){
			registry.addResourceHandler("/photo/**")
					.addResourceLocations("file:C:/photo/");
		}
}

