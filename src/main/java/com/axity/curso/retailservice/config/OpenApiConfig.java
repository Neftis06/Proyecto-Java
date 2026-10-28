package com.axity.curso.retailservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI retailServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Retail Order Service API")
                        .description("Documentación oficial del microservicio de órdenes y productos para Axity")
                        .version("v1.0.0"));
    }
}