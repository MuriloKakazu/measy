package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import br.com.cefsa.ec6.measy.infrastructure.exception.NavigationException;
import br.com.cefsa.ec6.measy.infrastructure.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

  @Autowired private SpotifyClient spotifyClient;

  @FXML
  private void onMouseClickedAuth(MouseEvent event) {
    try {
      spotifyClient.requestAuthToken();
    } catch (NavigationException e) {
      AlertUtil.showGenericServiceError();
    }
  }
}
