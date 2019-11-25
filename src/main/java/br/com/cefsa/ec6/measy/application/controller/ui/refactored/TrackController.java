package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.domain.model.youtube.Video;
import br.com.cefsa.ec6.measy.domain.repository.musixmatch.LyricsRepository;
import br.com.cefsa.ec6.measy.domain.repository.youtube.VideoRepository;
import br.com.cefsa.ec6.measy.infrastructure.util.ArtistPicker;
import br.com.cefsa.ec6.measy.infrastructure.util.ImagePicker;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.ArtistFormatter;
import com.wrapper.spotify.model_objects.specification.Track;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.jmusixmatch.entity.lyrics.Lyrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Optional;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TrackController {

  @Autowired private VideoRepository videoRepository;
  @Autowired private LyricsRepository lyricsRepository;

  @FXML private Label lyricsLabel;
  @FXML private TrackHeaderController headerController;

  public void setTrack(Track track) {
    final String trackName = track.getName();
    final String artistName = ArtistPicker.pickMain(track.getArtists()).getName();

    final Lyrics lyrics = lyricsRepository.getLyricsFromTrack(trackName, artistName);
    final Video video = videoRepository.getVideo(trackName, artistName);

    lyricsLabel.setText(Optional.ofNullable(lyrics).orElse(new Lyrics()).getLyricsBody());
    headerController.loadVideo(video);
    headerController.setTrack(track);
  }

}
