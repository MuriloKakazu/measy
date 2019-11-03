package br.com.cefsa.ec6.measy;

import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.SpringContextLocator;
import com.wrapper.spotify.model_objects.specification.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App extends Application {

  private static FXMLLoader FXML_LOADER;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    SpringContextLocator.setApplicationContext(SpringApplication.run(App.class));
    FXML_LOADER = FXMLLoaderFactory.create("Client");
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {

      final Parent client = FXML_LOADER.load();
      final Scene scene = new Scene(client, 800, 600);

      stage.setTitle("Measy");
      stage.setScene(scene);
      stage.show();

    } catch (Exception e) {

      System.out.println(e);
      System.out.println(e.getMessage());
      System.out.println(e.getCause());
      System.out.println(e.getStackTrace());
    }
  }

  @Override
  public void stop() {
    SpringContextLocator.getApplicationContext().stop();
    System.exit(0);
  }
}
