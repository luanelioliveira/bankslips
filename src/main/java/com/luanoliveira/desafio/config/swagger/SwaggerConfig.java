package com.luanoliveira.desafio.config.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.luanoliveira.desafio.controllers.BankSlipController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackageClasses = BankSlipController.class)
@Configuration
public class SwaggerConfig {

	
	private static final String SWAGGER_API_VERSION = "1.0.0";
	private static final String TITLE = "REST API Documentation";
	private static final String DESCRIPTION = "Bank Slip REST API";
	
	private static final String CLIENT_ID = "";
	private static final String CLIENT_SECRET = "";
	
	
	private ApiInfo apiInfo() {
		 return new ApiInfo(
				 TITLE, 
				 DESCRIPTION, 
				 SWAGGER_API_VERSION, 
		         "Terms of service", 
		         new Contact("Luan Oliveira", "www.luanoliveira.com", "luannn@gmail.com"), 
		         "License of API", "API license URL", Collections.emptyList());
	}
	
	@Bean
	public Docket bankSlipApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("BankSlip")
				.select()	
				.apis(RequestHandlerSelectors.basePackage("com.luanoliveira.desafio.controllers"))
				.paths(PathSelectors.regex("/bankslips.*"))
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false);
	}

	
	@Bean
	public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder()
	        .clientId(CLIENT_ID)
	        .clientSecret(CLIENT_SECRET)
	        .scopeSeparator(" ")
	        .useBasicAuthenticationWithAccessCodeGrant(true)
	        .build();
	}
}
