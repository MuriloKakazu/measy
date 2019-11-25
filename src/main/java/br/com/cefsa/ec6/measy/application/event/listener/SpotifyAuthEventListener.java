package br.com.cefsa.ec6.measy.application.event.listener;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.*;
import br.com.cefsa.ec6.measy.application.event.SpotifyAuthEvent;
import br.com.cefsa.ec6.measy.application.factory.HomeComponentFactory;
import br.com.cefsa.ec6.measy.domain.model.youtube.Video;
import br.com.cefsa.ec6.measy.domain.repository.musixmatch.LyricsRepository;
import br.com.cefsa.ec6.measy.domain.repository.youtube.VideoRepository;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.CacheHelper;
import br.com.cefsa.ec6.measy.infrastructure.util.FXThreadHelper;
import com.google.common.eventbus.Subscribe;
import java.io.IOException;
import java.util.EventListener;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

@Component
public class SpotifyAuthEventListener implements EventListener {

  @Autowired private HomeComponentFactory homeComponentFactory;
  @Autowired private ClientController clientController;
  @Autowired private SidePanelController sidePanelController;
  @Autowired private CacheHelper cacheHelper;

  @Subscribe
  private void onSpotifyAuth(SpotifyAuthEvent event) throws IOException {
    FXThreadHelper.run(() -> {
      try {

        cacheHelper.refreshUserPlaylists();
        sidePanelController.fetchPlaylists();
        clientController.setContent(homeComponentFactory.create());

      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }
}
