package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ClientController {

  @FXML private ScrollPane scrollableContent;

  public void setContent(AnchorPane panel) {
    scrollableContent.setContent(panel);
  }
}
