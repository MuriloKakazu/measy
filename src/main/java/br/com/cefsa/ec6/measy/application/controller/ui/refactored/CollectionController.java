package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CollectionController {

  @FXML private VBox items;

  public void addChild(Node node) {
    items.getChildren().add(node);
  }

  public void addChildren(Node... nodes) {
    items.getChildren().addAll(nodes);
  }

  public void clear() {
    items.getChildren().clear();
  }
}
