package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.AlbumController;
import br.com.cefsa.ec6.measy.domain.repository.spotify.AlbumRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Album;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public class AlbumComponentFactory {

  @Autowired private AlbumRepository albumRepository;

  @Autowired
  private FXMLLoaderFactory fxmlLoaderFactory;

  public Node fromAlbumId(@NotNull String albumId) {
    return fromAlbum(albumRepository.getById(albumId));
  }

  public Node fromAlbum(@NotNull Album album) {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("Album");
      final Node albumNode = loader.load();
      final AlbumController albumController = loader.getController();

      albumController.setAlbum(album);
      return albumNode;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
