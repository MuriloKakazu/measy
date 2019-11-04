package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.ArtistController;
import br.com.cefsa.ec6.measy.domain.repository.spotify.ArtistRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Artist;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public class ArtistComponentFactory {

  @Autowired
  private ArtistRepository artistRepository;

  public Node fromArtistId(@NotNull String artistId) {
    return fromArtist(artistRepository.getById(artistId));
  }

  public Node fromArtist(@NotNull Artist artist) {
    try {

      final FXMLLoader artistLoader = FXMLLoaderFactory.create("Artist");
      final Node artistNode = artistLoader.load();
      final ArtistController artistController = artistLoader.getController();

      artistController.setArtist(artist);
      return artistNode;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
