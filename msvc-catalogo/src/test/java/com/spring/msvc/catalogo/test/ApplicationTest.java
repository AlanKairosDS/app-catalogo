package com.spring.msvc.catalogo.test;

import com.spring.msvc.catalogo.MsvcCatalogoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

  @Test
  public void applicationContextTest () {
    MsvcCatalogoApplication.main(new String[]{});
  }
}
