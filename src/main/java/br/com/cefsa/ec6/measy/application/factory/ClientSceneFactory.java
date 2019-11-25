package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientSceneFactory {

  @Autowired private FXMLLoaderFactory fxmlLoaderFactory;

  public Scene create() {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("Client");
      final Parent clientNode = loader.load();

      return new Scene(clientNode, 1280, 720, false, SceneAntialiasing.BALANCED);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
