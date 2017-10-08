package com.reconciliation.demo.data.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by FJGMATEU
 */

@ComponentScan(basePackages = "com.reconciliation.demo.data")
public class DataConfig {
    public static void main(String[] args) {
        SpringApplication.run(DataConfig.class, args);
    }
}
