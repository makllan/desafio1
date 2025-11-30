package com.checkemploy.in_out.swaggerconfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API CHECK-IN CHECK-OUT")
                        .version("0.1")
                        .description("Documentação da API REST de Exemplo para o projeto Java."));
    }
}