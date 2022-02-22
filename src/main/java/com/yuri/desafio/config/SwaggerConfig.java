package com.yuri.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yuri.desafio.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responseMessageForGET())
                .globalResponses(HttpMethod.POST, responseMessageForGET())
                .globalResponses(HttpMethod.PATCH, responseMessageForGET())
                .globalResponses(HttpMethod.DELETE, responseMessageForGET())
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(apiInfo());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DESAFIO SOGO TECNOLOGIA")
                .description("Essa é a documentação da API da empresa SOGO TECNOLOGIA")
                .version("1.0.0")
                .contact(new Contact("SOGO", "https://sogo.com.br/", "suporte@sogo.com.br"))
                .build();
    }

    private List<Response> responseMessageForGET()
    {
        return new ArrayList<Response>() {{
            add(new ResponseBuilder()
                    .code("500")
                    .description("Internal Server Error")
                    .build());
            add(new ResponseBuilder()
                    .code("403")
                    .description("Forbidden")
                    .build());
            add(new ResponseBuilder()
                    .code("401")
                    .description("Unauthorized")
                    .build());
            add(new ResponseBuilder()
                    .code("400")
                    .description("Bad Request")
                    .build());
            add(new ResponseBuilder()
                    .code("200")
                    .description("Ok")
                    .build());
        }};
    }
}

