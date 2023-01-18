package com.adrian.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NextApplication extends SpringBootServletInitializer {
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
         return app.sources(NextApplication.class);
     }
	
	
	public static void main(String[] args) {

		SpringApplication.run(NextApplication.class, args);
	}
	
}
