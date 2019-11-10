package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.RecordCardFactory;
import br.com.cefsa.ec6.measy.domain.repository.spotify.TrackRepository;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.ImagePicker;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.TrackFormatter;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomeController {

  @Autowired private TrackRepository trackRepository;
  @Autowired private RecordCardFactory recordCardFactory;

  @FXML private FlowPane userFavoriteTracks;

  public void addFavoriteTrack(Track track) {
    Node trackCard = recordCardFactory.fromTrack(track);
    userFavoriteTracks.getChildren().add(trackCard);
  }

  public void addFavoriteTrack(TrackSimplified track) {
    Track fullTrack = trackRepository.getById(track.getId());
    addFavoriteTrack(fullTrack);
  }
}
