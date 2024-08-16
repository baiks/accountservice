package com.account.service.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String apiTitle = "Account Service API";
        final String description = "Handles the management of bank accounts, including account creation, balance inquiries, and account closure.";
        String apiVersion = "1.0.0";
        Contact contact = new Contact();
        contact.setName("Paul");
        contact.setEmail("paulkabaiku023@gmail.com");
        contact.setUrl("paulkabaiku.website");
        return new OpenAPI()
                .info(new Info().title(apiTitle).version(apiVersion).description(description).contact(contact)).
                addServersItem(new io.swagger.v3.oas.models.servers.Server()
                        .url("http://localhost:8002")
                        .description("Development server"))
                .addServersItem(new io.swagger.v3.oas.models.servers.Server()
                        .url("https://api.example.com")
                        .description("Production server"));
    }
}
