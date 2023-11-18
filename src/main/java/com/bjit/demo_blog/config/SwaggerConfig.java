package com.bjit.demo_blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER="Authorization";

    @Bean
    public OpenAPI openAPI() {

    return new OpenAPI()
            .info(new Info().title("Blog API")
                    .description("Blogging Application: Backend Course\",\"This code is developed by-Md. Al Shariar")
                    .version("1.0")
                    .contact(new Contact().name("Md. Al Shariar").email("itmasjoy@gmail.com")));
    }

//    private ApiKey apiKeys(){
//        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
//    }
//
//    private List<SecurityContext> securityContexts(){
//        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
//    }
//
//    private List<SecurityReference> securityReferences(){
//        AuthorizationScope authorizationScope = new AuthorizationScope("global","access everything");
//        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[]{authorizationScope}));
//    }
//
//    @Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(getInfo())
//                .securityContexts(securityContexts())
//                .securitySchemes(Arrays.asList(apiKeys()))
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo getInfo(){
//        return new ApiInfo("Blogging Application: Backend Course","This code is developed by-Md. Al Shariar",
//                "1.0","Terms of service",
//                new Contact("Shariar","https://github.com/Shariarbup/", "itmasjoy@gmail.com"),
//                "License of APIs","API License URL", Collections.emptyList());
//    }
}
