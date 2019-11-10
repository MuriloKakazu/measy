package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.TrackRowFactory;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackCollectionController {

  @Autowired private TrackRowFactory trackRowFactory;

  @FXML private CollectionController tracksController;

  public void addTrack(Track track) {
    final Node trackRow = trackRowFactory.create(track);
    tracksController.addChild(trackRow);
  }

  public void addTrack(TrackSimplified track) {
    final Node trackRow = trackRowFactory.create(track);
    tracksController.addChild(trackRow);
  }
}
