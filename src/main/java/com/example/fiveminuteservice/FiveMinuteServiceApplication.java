package com.example.fiveminuteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.logging.Logger;

@SpringBootApplication
public class FiveMinuteServiceApplication {

    Logger logger = Logger.getLogger("c.e.f.ApplicationListener");

    public static void main(String[] args) {
        SpringApplication.run(FiveMinuteServiceApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationContextEvent> readyEventApplicationListener(final CustomerService cs) {
        return event -> cs.all().forEach(customer -> logger.info(customer.toString()));
    }

}
