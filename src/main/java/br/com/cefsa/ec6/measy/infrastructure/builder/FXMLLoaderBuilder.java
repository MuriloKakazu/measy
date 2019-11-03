package br.com.cefsa.ec6.measy.infrastructure.builder;

import br.com.cefsa.ec6.measy.infrastructure.configuration.FXMLScannerConfiguration;
import br.com.cefsa.ec6.measy.infrastructure.util.SpringContextLocator;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FXMLLoaderBuilder implements Builder<FXMLLoader> {

  private FXMLLoader fxmlLoader = new FXMLLoader();
  private String fxmlBasePath = new FXMLScannerConfiguration().fxmlBasePath();

  public FXMLLoaderBuilder withFileName(String fileName) {
    final String filePath = fxmlBasePath.concat(fileName);
    return withFilePath(filePath);
  }

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
    fxmlLoader.setControllerFactory(SpringContextLocator.getApplicationContext()::getBean);
    return this;
  }

  @Override
  public FXMLLoader build() {
    return fxmlLoader;
  }
}
