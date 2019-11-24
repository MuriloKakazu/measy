package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.navigation.LoadUrlParams;
import com.teamdev.jxbrowser.net.HttpHeader;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class YoutubeEmbeddedVideoController {

  @Autowired private Browser browser;
  @Autowired private BrowserView browserView;

  @FXML
  private StackPane container;

  public void load(String url) {
    browser.navigation().loadUrl(
        LoadUrlParams.newBuilder(url)
            .addExtraHeader(HttpHeader.of("Referer", "https://www.youtube.com"))
            .build());

    container.getChildren().clear();
    container.getChildren().add(browserView);
  }

}
