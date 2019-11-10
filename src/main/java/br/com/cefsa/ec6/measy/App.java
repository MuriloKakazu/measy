package br.com.cefsa.ec6.measy;

import br.com.cefsa.ec6.measy.infrastructure.builder.FXMLLoaderBuilder;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class App extends Application {

  private static FXMLLoader FXML_LOADER;
  private static ConfigurableApplicationContext APP_CONTEXT;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {

    APP_CONTEXT = SpringApplication.run(App.class);

    FXML_LOADER = new FXMLLoaderBuilder()
        .springManaged(APP_CONTEXT)
        .withUrl(App.class.getResource("/fxml/Client.fxml"))
        .build();

  }

  @Override
  public void start(Stage stage) throws Exception {
    try {

      final Parent client = FXML_LOADER.load();
      final Scene scene = new Scene(client, 1280, 720, false, SceneAntialiasing.BALANCED);

      stage.setTitle("Measy");
      stage.setScene(scene);
      stage.show();

    } catch (Exception e) {

      System.out.println(e);
      System.out.println(e.getMessage());
      System.out.println(e.getCause());
      System.out.println(e.getStackTrace());
      e.printStackTrace();
    }
  }

  @Override
  public void stop() {
    APP_CONTEXT.stop();
    System.exit(0);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext context) {
    return args -> {
      System.out.println("Injected beans:");

      String[] beanNames = context.getBeanDefinitionNames();
      Arrays.sort(beanNames);

      for (String beanName : beanNames) {
        System.out.println(beanName);
      }
    };
  }
}
