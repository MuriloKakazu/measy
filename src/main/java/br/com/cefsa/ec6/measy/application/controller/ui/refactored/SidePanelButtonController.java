package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.enums.Icon;
import br.com.cefsa.ec6.measy.infrastructure.util.IconURLFinder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SidePanelButtonController {

  @Autowired private IconURLFinder iconURLFinder;

  @FXML private ImageView icon;

  @FXML private Label label;

  private String resourceUri;

  public void setText(String text) {
    label.setText(text);
  }

  public void setIcon(Icon icon) {
    this.icon.setImage(new Image(iconURLFinder.findRelativePath(icon)));
  }

  public void setResourceUri(String uri) {
    resourceUri = uri;
  }
}
