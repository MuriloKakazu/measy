package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.HyperlinkController;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.holder.FXMLLoaderHolder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HyperlinkFactory {

  @Autowired
  private FXMLLoaderFactory fxmlLoaderFactory;

  public Node create(String text, String uri) {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("Hyperlink");
      final Node hyperlinkNode = loader.load();
      final HyperlinkController hyperlinkController = loader.getController();

      hyperlinkController.setUri(uri);
      hyperlinkController.setText(text);
      return hyperlinkNode;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
