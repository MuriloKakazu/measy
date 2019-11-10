package br.com.cefsa.ec6.measy.infrastructure.factory;

import br.com.cefsa.ec6.measy.infrastructure.builder.FXMLLoaderBuilder;
import br.com.cefsa.ec6.measy.infrastructure.util.ResourceFinder;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FXMLLoaderFactory implements Factory<FXMLLoader> {

  @Autowired private ApplicationContext appContext;

  public FXMLLoader create(String componentName) {
    return appContext
        .getBean(FXMLLoaderBuilder.class)
        .springManaged()
        .withUrl(ResourceFinder.fxml(componentName))
        .build();
  }
}
