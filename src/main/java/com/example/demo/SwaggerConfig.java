package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 张彦辉 on 2017/7/12.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "list",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                true,         // enableJsonEditor      => true | false
                true,        // showRequestHeaders    => true | false
                null
        );
    }

    @Bean
    public Docket userAPI() {
        Set<String> setProtocol = new HashSet<>();
        setProtocol.add("http");
        Set<String> setProduce = new HashSet<>();
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
                .contact(new Contact("hui", "http://172.28.4.12/600220/hui-simple-springboot-mybatis-project", "zhangyanhui@syswin.com"))
                .version("1.0.0")
                .build();
    }
}
