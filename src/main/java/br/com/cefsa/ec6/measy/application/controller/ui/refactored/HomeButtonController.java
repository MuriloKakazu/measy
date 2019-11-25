package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.HomeComponentFactory;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HomeButtonController {

  @Autowired private HomeComponentFactory homeComponentFactory;
  @Autowired private ClientController clientController;

  @FXML
  private void onMouseClicked(MouseEvent event) {
    clientController.setContent(homeComponentFactory.create());
  }
}
