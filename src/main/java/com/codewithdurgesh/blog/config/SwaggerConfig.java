package com.codewithdurgesh.blog.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER="Authorization";
	
	// method for use JWT in Swagger
	
	private ApiKey apiKeys() {
		
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	// method for use JWT in Swagger
	
	private List<SecurityContext> securityContext(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
		
		
	}

	// method for use JWT in Swagger
	private List<SecurityReference> sf(){
		
		AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}
	
	@Bean
	public Docket api() {
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContext())  //This is for security implement in swagger (implement JWT in Swagger)
				.securitySchemes(Arrays.asList(apiKeys())) //This is for security implement in swagger (implement JWT in Swagger)
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
				
	}

	private ApiInfo getInfo() {

		
		return new ApiInfo("Blogging Application Back-End Course", "This Project is developed by Learn Code with Durgesh",
				"1.0", "Terms Of Service", new Contact("Durgesh", "https://learncodewithdurgesh.com", "learncodewithdurgesh@gmail.com"), 
				"Licence Of API", "URL",Collections.EMPTY_LIST);
	}
}
