package com.reconciliation.demo.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.reconciliation.demo.api")
@Import(ReconciliationServiceApplication.class)
public class ReconciliationApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReconciliationApiApplication.class, args);
    }
}
