package br.com.cefsa.ec6.measy.application.factory;

import br.com.cefsa.ec6.measy.application.controller.ui.refactored.RecordCardController;
import br.com.cefsa.ec6.measy.infrastructure.factory.FXMLLoaderFactory;
import br.com.cefsa.ec6.measy.infrastructure.holder.FXMLLoaderHolder;
import br.com.cefsa.ec6.measy.infrastructure.util.ImagePicker;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.TrackFormatter;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.Track;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RecordCardFactory {

  @Autowired
  private FXMLLoaderFactory fxmlLoaderFactory;

  private Node create(String uri, String title, String imageUrl) {
    try {

      final FXMLLoader loader = fxmlLoaderFactory.create("RecordCard");
      Node trackCard = loader.load();
      RecordCardController trackController = loader.getController();

      trackController.setContextUri(uri);
      trackController.setTitle(title);
      trackController.setImage(imageUrl);

      return trackCard;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Node fromTrack(Track track) {
    return create(
        track.getUri(),
        TrackFormatter.formatTrackArtistAndTitle(track),
        ImagePicker.pickHighestResolutionImage(track.getAlbum().getImages()).getUrl()
    );
  }

  public Node fromArtist(Artist artist) {
    return create(
        artist.getUri(),
        artist.getName(),
        ImagePicker.pickHighestResolutionImage(artist.getImages()).getUrl()
    );
  }

  public Node fromAlbum(Album album) {
    return create(
        album.getUri(),
        album.getName(),
        ImagePicker.pickHighestResolutionImage(album.getImages()).getUrl()
    );
  }

  public Node fromPlaylist(Playlist playlist) {
    return create(
        playlist.getUri(),
        playlist.getName(),
        ImagePicker.pickHighestResolutionImage(playlist.getImages()).getUrl()
    );
  }

}
