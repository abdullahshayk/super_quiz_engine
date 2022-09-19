package com.example.server_quiz_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class ServerQuizAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerQuizAppApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration()
	{

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.example.server_quiz_app.api")).build().apiInfo(apiDetails());

	}

	private ApiInfo apiDetails()
	{

		return new ApiInfo("Gameshow", "Api Documentation", "1.0", "Gameshow", new springfox.documentation.service.Contact("", "", ""), "", "", Collections.emptyList());
	}

}
