package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NewPlaylistButtonController {
  @Autowired private FXMLLoaderFactory fxmlLoaderFactory;
  @Autowired private ClientController clientController;

  @FXML
  private void onMouseClicked(MouseEvent event) {
    try {
      Stage dialogStage = new Stage();
      dialogStage.initModality(Modality.WINDOW_MODAL);

      FXMLLoader pageLoader = fxmlLoaderFactory.create("NewPlaylistPage");

      dialogStage.setScene(new Scene(pageLoader.load()));
      dialogStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
