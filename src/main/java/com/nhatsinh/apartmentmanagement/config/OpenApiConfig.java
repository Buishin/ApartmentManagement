package com.nhatsinh.apartmentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig{

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
        .info(new Info()
                .title("Apartment API")
                .description("API Spring Boot: Auth, User CRUD, phân quyền")
                .version("1.0"))
        .components(new Components()
                .addSecuritySchemes("bearerAuth", 
                                    new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Nhập access token sau khi đăng nhập"))
        );
    }
}

 
