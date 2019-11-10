package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.domain.repository.spotify.TrackRepository;
import br.com.cefsa.ec6.measy.infrastructure.util.ImagePicker;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Track;
import java.util.Arrays;
import java.util.Collection;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ArtistController {

  @FXML private RecordHeaderController headerController;
  @FXML private TrackCollectionController tracksController;

  @Autowired private TrackRepository trackRepository;

  public void setArtist(Artist artist) {
    headerController.setRecordType(Artist.class.getSimpleName());
    headerController.setTitle(artist.getName());
    headerController.setSubtitle(String.join(", ", artist.getGenres()));
    headerController.setImage(
        ImagePicker.pickHighestResolutionImage(Arrays.asList(artist.getImages())).getUrl());
    headerController.setContextUri(artist.getUri());

    Collection<Track> artistTracks =
        trackRepository.getMostPopularsFromArtistId(artist.getId(), CountryCode.BR);
    artistTracks.forEach(track -> tracksController.addTrack(track));
  }
}
