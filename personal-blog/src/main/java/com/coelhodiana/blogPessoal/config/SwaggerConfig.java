package com.coelhodiana.blogPessoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				// Definir o package controllers para que o swagger mapeie os controllers para a documentação
				.apis(RequestHandlerSelectors.basePackage("com.coelhodiana.blogPessoal.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Blog Pessoal")
				.description("API do Projeto personal-blog")
				.version("1.0")
				.contact(contact()).build();
	}

	private Contact contact() {
		return new Contact("Diana Coelho", "https://github.com/coelhodiana",
				"Software developer Full Stack Developer Spring Angular");
	}

}
