package com.spring.msvc.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsvcCatalogoApplication {

  public static void main (String[] args) {
    SpringApplication application = new SpringApplication(MsvcCatalogoApplication.class);
    application.run(args);
  }
}
