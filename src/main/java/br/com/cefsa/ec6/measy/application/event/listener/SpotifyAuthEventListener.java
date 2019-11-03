package br.com.cefsa.ec6.measy.application.event.listener;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.*;
import br.com.cefsa.ec6.measy.application.event.SpotifyAuthEvent;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.util.FXThreadHelper;
import com.google.common.eventbus.Subscribe;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventListener;

import com.wrapper.spotify.model_objects.specification.Track;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotifyAuthEventListener implements EventListener {

  @Autowired private ClientController clientController;

  @Autowired private SidePanelController sidePanelController;

  @Autowired private SpotifyClient spotifyClient;

  @Subscribe
  private void onSpotifyAuth(SpotifyAuthEvent event) throws IOException {
    FXThreadHelper.run(
        () -> {
          try {

            FXMLLoader homeLoader = FXMLLoaderFactory.create("Home");
            Node homeNode = homeLoader.load();
            HomeController homeController = homeLoader.getController();
            Collection<Track> tracks = Arrays.asList(spotifyClient.getUserTopTracks().getItems());
            tracks.forEach(track -> homeController.addFavoriteTrack(track));

//            FXMLLoader playlistLoader = FXMLLoaderFactory.create("Playlist");
//            Node playlist = playlistLoader.load();
//            PlaylistController playlistController = playlistLoader.getController();
//
//            playlistController.setPlaylist(spotifyClient.getPlaylist("0PW1vmagj613BHpxEAvlFI"));

            Node nodeToSet = homeNode;

            clientController.setContent((AnchorPane) nodeToSet);

            sidePanelController.fetchPlaylists();

          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }
}
