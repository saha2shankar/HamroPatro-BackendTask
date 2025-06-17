package com.hamropatro.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("school-api")
                .pathsToMatch("/api/**")
                .build();
    }
  @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hamropatro Backend API task")
                        .version("1.0.0")
                        .termsOfService("http://harishankar.info.np")
                        .description("Comprehensive API for managing students, classes, teachers, and subjects.")
                        .contact(new Contact()
                        		.email("sahharishankar11@gmail.com")
                                .name("Harishankar Sah")
                                .url("http://harishankar.info.np")));
    }
}
