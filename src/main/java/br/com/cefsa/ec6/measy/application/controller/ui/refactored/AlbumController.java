package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.infrastructure.util.formatter.ArtistFormatter;
import com.wrapper.spotify.model_objects.specification.Album;
import java.util.Arrays;
import javafx.fxml.FXML;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AlbumController {

  @FXML private RecordHeaderController headerController;
  @FXML private TrackCollectionController tracksController;

  public void setAlbum(Album album) {
    headerController.setRecordType(Album.class.getSimpleName());
    headerController.setTitle(album.getName());
    headerController.setSubtitle(
        "By "
            .concat(
                ArtistFormatter.formatSimplifiedArtistsNames(Arrays.asList(album.getArtists()))));
    headerController.setImage(Arrays.stream(album.getImages()).findFirst().get().getUrl());
    headerController.setContextUri(album.getUri());

    Arrays.stream(album.getTracks().getItems()).forEach(track -> tracksController.addTrack(track));
  }
}
