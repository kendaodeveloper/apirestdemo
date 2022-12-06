package com.example.apirestdemo.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class SwaggerConfiguration {
  @Bean
  public GroupedOpenApi getGroupedOpenApi() {
    return GroupedOpenApi.builder().group("default").pathsToMatch("/**").build();
  }

  @Bean
  public OpenAPI getOpenAPI() {
    return new OpenAPI().info(
        new Info()
            .title("Clean Arch")
            .description("API REST Demonstration")
            .version("v1.0.0")
    ).addSecurityItem(
        new SecurityRequirement().addList("apiKey")
    ).components(
        new Components()
            .addSecuritySchemes("apiKey",
                new SecurityScheme()
                    .name("Authorization")
                    .in(SecurityScheme.In.HEADER)
                    .type(SecurityScheme.Type.APIKEY)
            )
    );
  }
}