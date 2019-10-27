package br.com.cefsa.ec6.measy.infrastructure.util;

import org.springframework.context.ConfigurableApplicationContext;

public class SpringContextLocator {

  private static ConfigurableApplicationContext CONTEXT;

  public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
    CONTEXT = applicationContext;
  }

  public static ConfigurableApplicationContext getApplicationContext() {
    return CONTEXT;
  }
}
