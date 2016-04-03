package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan
@SpringBootApplication
@EnableTransactionManagement
// Only use the below if you want to rely on application.properties under resources
//@PropertySource("classpath:application.properties")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
