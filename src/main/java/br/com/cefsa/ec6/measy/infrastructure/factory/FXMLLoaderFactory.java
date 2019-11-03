package br.com.cefsa.ec6.measy.infrastructure.factory;

import br.com.cefsa.ec6.measy.App;
import br.com.cefsa.ec6.measy.infrastructure.builder.FXMLLoaderBuilder;
import javafx.fxml.FXMLLoader;
import org.springframework.stereotype.Component;

@Component
public class FXMLLoaderFactory implements Factory<FXMLLoader> {

  public static FXMLLoader create(String componentName) {
    return new FXMLLoaderBuilder()
        .springManaged()
        .withUrl(App.class.getResource("/fxml/" + componentName + ".fxml"))
        .build();
  }
}
