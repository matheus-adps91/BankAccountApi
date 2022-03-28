package com.neptum.BankAccountApi.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.neptum.BankAccountApi"))
			.build()
			.apiInfo(apiInfo())
				.select().paths(pathRegex()).build();
	}
	
	private Predicate<String> pathRegex(){
		return PathSelectors.regex("/api/v1/.*");
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Bank Account API", 
			"Api que simula o funcionamento de uma conta", 
			"1.0.0", "http://apache.org", 
			new Contact("Matheus", "http://teste.api.com", "matheus_adps@hotmail.com"),
			"Licença da API", 
			"http://www.apache.org/license.html",
			new ArrayList<VendorExtension>());
	}
}