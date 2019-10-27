package br.com.cefsa.ec6.measy.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FXMLScannerConfiguration {

  @Bean
  public String fxmlBasePath() {
    return "./src/main/fxml/br/com/cefsa/ec6/measy/presentation/component/";
  }
}
