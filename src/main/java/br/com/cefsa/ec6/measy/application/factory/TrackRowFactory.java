package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.TrackRowController;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.holder.FXMLLoaderHolder;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@Component
public class TrackRowFactory {

  @Autowired
  private FXMLLoaderFactory fxmlLoaderFactory;

  private Node create(String trackName, String trackUri, String albumName, String albumUri,
                      Collection<ArtistSimplified> artists, Integer durationMs) {

    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("TrackRow");
      final Node trackRow = loader.load();
      final TrackRowController trackRowController = loader.getController();

      trackRowController.setTitle(trackName);
      trackRowController.setTrackUri(trackUri);
      trackRowController.setAlbumName(albumName);
      trackRowController.setAlbumUri(albumUri);
      trackRowController.setArtists(artists);
      trackRowController.setDuration(durationMs);

      return trackRow;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  public Node create(Track track) {
    return create(
        track.getName(),
        track.getUri(),
        track.getAlbum().getName(),
        track.getAlbum().getUri(),
        Arrays.asList(track.getArtists()),
        track.getDurationMs()
    );
  }

  public Node create(TrackSimplified track) {
    return create(
        track.getName(),
        track.getUri(),
        "",
        "",
        Arrays.asList(track.getArtists()),
        track.getDurationMs()
    );
  }

}
