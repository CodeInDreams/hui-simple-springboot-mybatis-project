package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@EnableAutoConfiguration
@Configuration
@ComponentScan(value="com.example.demo")
@MapperScan("com.example.demo.mapper")
@SpringBootApplication
public class Test3Application {
	private static Logger logger = LogManager.getLogger(Test3Application.class);

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource datasource(){
        return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	public static void main(String[] args) {
		SpringApplication.run(Test3Application.class, args);
	}

}