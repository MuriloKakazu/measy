package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.HyperlinkFactory;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MultipleArtistsController {

  @Autowired private HyperlinkFactory hyperlinkFactory;

  @FXML private HBox container;

  public void addArtist(Artist artist) {
    if (!container.getChildren().isEmpty()) addSeparator();

    Node artistNode = hyperlinkFactory.create(artist.getName(), artist.getUri());
    container.getChildren().add(artistNode);
  }

  public void addArtist(ArtistSimplified artist) {
    if (!container.getChildren().isEmpty()) addSeparator();

    Node artistNode = hyperlinkFactory.create(artist.getName(), artist.getUri());
    container.getChildren().add(artistNode);
  }

  private void addSeparator() {
    Node separator = hyperlinkFactory.create(", ", "");
    container.getChildren().add(separator);
  }

  public void clear() {
    container.getChildren().clear();
  }
}
