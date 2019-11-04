package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.HyperlinkController;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HyperlinkFactory {

  public Node create(String text, String uri) {
    try {

      final FXMLLoader hyperlinkLoader = FXMLLoaderFactory.create("Hyperlink");
      final Node hyperlinkNode = hyperlinkLoader.load();
      final HyperlinkController hyperlinkController = hyperlinkLoader.getController();

      hyperlinkController.setUri(uri);
      hyperlinkController.getLabel().setText(text);
      return hyperlinkNode;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
