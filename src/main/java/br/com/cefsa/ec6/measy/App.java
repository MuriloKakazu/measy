package br.com.cefsa.ec6.measy;

import java.io.File;
import java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App extends Application {

  private ConfigurableApplicationContext springContext;
  private Parent rootNode;
  private FXMLLoader fxmlLoader;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    springContext = SpringApplication.run(App.class);
    fxmlLoader = new FXMLLoader();
    fxmlLoader.setControllerFactory(springContext::getBean);
  }

  @Override
  public void start(Stage stage) throws Exception {
    fxmlLoader.setLocation(new File("./src/main/java/br/com/cefsa/ec6/measy/presentation/component/fxml/Client.fxml").toURL());
    rootNode = fxmlLoader.load();

    stage.setTitle("Measy");
    Scene scene = new Scene(rootNode, 800, 600);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() {
    springContext.stop();
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
