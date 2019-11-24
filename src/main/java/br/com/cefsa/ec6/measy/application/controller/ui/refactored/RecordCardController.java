package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.validation.constraints.NotNull;

import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RecordCardController {

  private String contextUri;
  @Autowired private ClientController clientController;

  @FXML private Label title;
  @FXML private ImageView image;
  @FXML private SpotifyPlayButtonController playButtonController;

  public void setImage(@NotNull String imageUrl) {
    this.image.setImage(new Image(imageUrl));
  }

  public void setTitle(@NotNull String title) {
    this.title.setText(title);
  }

  public void setContextUri(@NotNull String contextUri) {
    this.contextUri = contextUri;
    this.playButtonController.setSpotifyUri(contextUri);
  }

  @FXML
  private void onMouseClickedOnTitle(MouseEvent event) {
    clientController.navigateToUri(contextUri);
  }
}
