package de.seuhd.campuscoffee.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger configuration for the CampusCoffee API.
 * Defines global API metadata such as title, version, and description.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CampusCoffee API",
                version = "0.0.1",
                description = "REST API for managing campus coffee points of sale, users, and reviews."
        )
)
public class OpenApiConfig {
    // This class serves as a configuration holder for OpenAPI annotations.
    // No additional beans or methods needed unless custom configuration is required.
}
