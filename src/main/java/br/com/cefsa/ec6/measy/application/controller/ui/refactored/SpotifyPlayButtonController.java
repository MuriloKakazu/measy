package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.event.ChangePlaybackContextEvent;
import com.google.common.eventbus.EventBus;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpotifyPlayButtonController {

  @Autowired private EventBus eventBus;

  private String spotifyUri;

  public String getSpotifyUri() {
    return spotifyUri;
  }

  public void setSpotifyUri(String spotifyUri) {
    this.spotifyUri = spotifyUri;
  }

  @FXML
  private void onMouseClick(MouseEvent event) {
    eventBus.post(new ChangePlaybackContextEvent(getSpotifyUri()));
  }
}
