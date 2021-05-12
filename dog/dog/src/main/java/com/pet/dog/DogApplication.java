package com.pet.dog;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)  
@EnableJms
@EnableSwagger2
public class DogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogApplication.class, args);
	}

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("java2blog.queue");
    }
    
    @Bean
    public Docket productApi() {
       return new Docket(DocumentationType.SWAGGER_2).select()
          .apis(RequestHandlerSelectors.basePackage("com.pet.dog")).build();
    }
}
