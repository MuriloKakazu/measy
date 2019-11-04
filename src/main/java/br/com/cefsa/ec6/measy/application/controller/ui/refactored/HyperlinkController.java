package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HyperlinkController {

  @Autowired
  private ClientController clientController;

  @FXML
  private Label label;
  private String uri;

  @FXML private void onMouseClick(MouseEvent event) {
    clientController.navigateToUri(getUri());
  }

  public Label getLabel() {
    return label;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

}
