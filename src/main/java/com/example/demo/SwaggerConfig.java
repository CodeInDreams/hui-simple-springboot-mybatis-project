package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 张彦辉 on 2017/7/12.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket userAPI() {
        Set<String> setProtocol = new HashSet<String>();
        setProtocol.add("http");
        Set<String> setProduce = new HashSet<String>();
        setProduce.add("application/json");

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("User")
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .protocols(setProtocol)
                .produces(setProduce);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("hui-simple-spring-boot-mybatis-project API")
                .contact("hui")
                .version("1.0.0")
                .build();
    }
}
