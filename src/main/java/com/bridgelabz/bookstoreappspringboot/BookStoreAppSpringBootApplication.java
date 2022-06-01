package com.bridgelabz.bookstoreappspringboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class BookStoreAppSpringBootApplication {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(BookStoreAppSpringBootApplication.class, args);
    log.info("Book Store Application Started in {} Environment",
            context.getEnvironment().getProperty("environment"));
    System.out.println("Welcome to Book Store!!");
  }
}
