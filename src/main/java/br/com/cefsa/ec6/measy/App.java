package br.com.cefsa.ec6.measy;

import java.util.Arrays;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.CollectionController;
import br.com.cefsa.ec6.measy.application.controller.ui.refactored.PlaylistController;
import br.com.cefsa.ec6.measy.application.controller.ui.refactored.RecordHeaderController;
import br.com.cefsa.ec6.measy.application.controller.ui.refactored.TrackCollectionController;
import br.com.cefsa.ec6.measy.infrastructure.builder.FXMLLoaderBuilder;
import br.com.cefsa.ec6.measy.infrastructure.configuration.FXMLScannerConfiguration;
import br.com.cefsa.ec6.measy.infrastructure.util.SpringContextLocator;
import com.wrapper.spotify.model_objects.specification.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App extends Application {

  private static FXMLLoader FXML_LOADER;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void init() throws Exception {
    SpringContextLocator.setApplicationContext(SpringApplication.run(App.class));

    FXML_LOADER = new FXMLLoaderBuilder()
          .springManaged()
          .withFileName("refactored/Playlist.fxml")
          .build();
  }

  @Override
  public void start(Stage stage) throws Exception {
    try {
      final Parent rootNode = FXML_LOADER.load();
      PlaylistController controller = FXML_LOADER.getController();

      ArtistSimplified artist0 = new ArtistSimplified.Builder()
              .setName("Pogo")
              .build();

      ArtistSimplified artist1 = new ArtistSimplified.Builder()
              .setName("Test Second Artist")
              .build();

      AlbumSimplified album = new AlbumSimplified.Builder()
              .setName("Weightless")
              .build();

      Track thereYouAre = new Track.Builder()
              .setName("There You Are")
              .setAlbum(album)
              .setArtists(artist0)
              .setDurationMs(217000)
              .setUri("spotify:track:381kdUGNobxuJ7fmXH7SiA")
              .build();

      Track dataAndPicard = new Track.Builder()
              .setName("Data & Picard")
              .setAlbum(album)
              .setArtists(artist0, artist1)
              .setDurationMs(185000)
              .setUri("spotify:track:5VcOZYRlC02MDPD95bi0rs")
              .build();

      Image image = new Image.Builder()
              .setUrl("https://i.pinimg.com/564x/90/a5/4e/90a54e7aa8c8b8e813f90f2e3f7dd3a7.jpg")
              .setHeight(250)
              .setWidth(250)
              .build();

      PlaylistTrack playlistTrack0 = new PlaylistTrack.Builder()
              .setTrack(thereYouAre)
              .build();

      PlaylistTrack playlistTrack1 = new PlaylistTrack.Builder()
              .setTrack(dataAndPicard)
              .build();

      Paging<PlaylistTrack> trackPaging = new Paging.Builder<PlaylistTrack>()
              .setItems(new PlaylistTrack[] { playlistTrack0, playlistTrack1 })
              .build();

      Playlist playlist = new Playlist.Builder()
              .setName("pogo")
              .setImages(image)
              .setTracks(trackPaging)
              .setUri("spotify:playlist:4tmTHbSOlHIYel3bA2LQc8")
              .build();

      controller.setPlaylist(playlist);

      stage.setTitle("Measy");
      Scene scene = new Scene(rootNode, 800, 600);
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Override
  public void stop() {
    SpringContextLocator.getApplicationContext().stop();
    System.exit(0);
  }

  //@Bean
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
