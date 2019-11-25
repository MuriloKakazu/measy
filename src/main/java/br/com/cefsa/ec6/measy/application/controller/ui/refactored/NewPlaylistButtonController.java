package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.factory.HomeComponentFactory;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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
