package br.com.cefsa.ec6.measy.application.event.listener;

import br.com.cefsa.ec6.measy.App;
import br.com.cefsa.ec6.measy.application.controller.ui.refactored.*;
import br.com.cefsa.ec6.measy.application.event.SpotifyAuthEvent;
import br.com.cefsa.ec6.measy.application.factory.ClientSceneFactory;
import br.com.cefsa.ec6.measy.application.factory.HomeComponentFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.CacheHelper;
import br.com.cefsa.ec6.measy.infrastructure.util.FXThreadHelper;
import com.google.common.eventbus.Subscribe;
import java.io.IOException;
import java.util.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotifyAuthEventListener implements EventListener {

  @Autowired private ClientSceneFactory clientSceneFactory;
  @Autowired private HomeComponentFactory homeComponentFactory;
  @Autowired private ClientController clientController;
  @Autowired private SidePanelController sidePanelController;
  @Autowired private CacheHelper cacheHelper;
  @Autowired private PlayerPanelController playerPanelController;

  @Subscribe
  private void onSpotifyAuth(SpotifyAuthEvent event) throws IOException {
    FXThreadHelper.run(
        () -> {
          try {

            App.setScene(clientSceneFactory.create());

            cacheHelper.refreshUserPlaylists();
            cacheHelper.refreshGlobalCharts();
            cacheHelper.refreshUserTopTracks();
            sidePanelController.fetchPlaylists();
            clientController.setContent(homeComponentFactory.create());
            playerPanelController.refresh();

          } catch (Exception e) {
            e.printStackTrace();
          }
        });
  }
}
