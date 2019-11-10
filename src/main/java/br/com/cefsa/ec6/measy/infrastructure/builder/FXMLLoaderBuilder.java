package br.com.cefsa.ec6.measy.infrastructure.builder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FXMLLoaderBuilder implements Builder<FXMLLoader> {

  @Autowired private ApplicationContext appContext;

  private FXMLLoader fxmlLoader = new FXMLLoader();

  public FXMLLoaderBuilder withFilePath(String filePath) {
    try {

      withUrl(new File(filePath).toURL());
      return this;

    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public FXMLLoaderBuilder withUrl(URL url) {
    fxmlLoader.setLocation(url);
    return this;
  }

  public FXMLLoaderBuilder springManaged() {
    return springManaged((ConfigurableApplicationContext) appContext);
  }

  public FXMLLoaderBuilder springManaged(ConfigurableApplicationContext appContext) {
    fxmlLoader.setControllerFactory(appContext::getBean);
    return this;
  }

  @Override
  public FXMLLoader build() {
    return fxmlLoader;
  }
}
