package com.matrimony.codewithnitin;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MatrimonyBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatrimonyBackendApplication.class, args);


		System.out.println("Program running finely : \t\n\n");
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/*")
//				.allowedHeaders("*")
//				.allowedOrigins("*")
//				.allowCredentials(true);
//			}
//		};
//	}

}
