package br.com.cefsa.ec6.measy;

import br.com.cefsa.ec6.measy.infrastructure.builder.FXMLLoaderBuilder;
import java.util.Arrays;
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

@SpringBootApplication
public class App extends Application {

  private static FXMLLoader FXML_LOADER;
  private static ConfigurableApplicationContext APP_CONTEXT;
  private static Stage PRIMARY_STAGE;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {

    APP_CONTEXT = SpringApplication.run(App.class);

    FXML_LOADER =
        new FXMLLoaderBuilder()
            .springManaged(APP_CONTEXT)
            .withUrl(App.class.getResource("/fxml/Auth.fxml"))
            .build();
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {

      PRIMARY_STAGE = stage;
      final Parent primaryNode = FXML_LOADER.load();
      final Scene scene = new Scene(primaryNode, 983, 628, false, SceneAntialiasing.BALANCED);

      setScene(scene);

      stage.setTitle("Measy");
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void setScene(Scene scene) {
    PRIMARY_STAGE.setScene(scene);
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
