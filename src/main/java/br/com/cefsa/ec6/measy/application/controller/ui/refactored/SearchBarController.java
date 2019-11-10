package br.com.cefsa.ec6.measy.application.controller.ui.refactored;

import br.com.cefsa.ec6.measy.application.builder.SearchResultBuilder;
import br.com.cefsa.ec6.measy.domain.repository.spotify.AlbumRepository;
import br.com.cefsa.ec6.measy.domain.repository.spotify.ArtistRepository;
import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.domain.repository.spotify.TrackRepository;
import com.wrapper.spotify.model_objects.specification.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.CustomTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchBarController {

  @Autowired
  private TrackRepository trackRepository;
  @Autowired
  private ArtistRepository artistRepository;
  @Autowired
  private AlbumRepository albumRepository;
  @Autowired
  private PlaylistRepository playlistRepository;
  @Autowired
  private ClientController clientController;
  @Autowired
  private SearchResultBuilder searchResultBuilder;

  @FXML
  private CustomTextField searchField;

  @FXML
  private void onAction(ActionEvent event) {
    search(searchField.getText());
  }

  private void search(String query) {

    final Collection<Track> tracks = Arrays.stream(trackRepository.search(query).getItems()).limit(8).collect(Collectors.toList());
    final Collection<Artist> artists = Arrays.stream(artistRepository.search(query).getItems()).limit(8).collect(Collectors.toList());
    final Collection<AlbumSimplified> albums = Arrays.stream(albumRepository.search(query).getItems()).limit(8).collect(Collectors.toList());
    final Collection<PlaylistSimplified> playlists = Arrays.stream(playlistRepository.search(query).getItems()).limit(8).collect(Collectors.toList());

    final Node searchResultPage = searchResultBuilder
        .withTracks(tracks)
        .withArtists(artists)
        .withAlbums(albums)
        .withPlaylists(playlists)
        .build();

    clientController.setContent((AnchorPane) searchResultPage);
  }
}
