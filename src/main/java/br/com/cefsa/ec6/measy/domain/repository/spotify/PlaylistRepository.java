package br.com.cefsa.ec6.measy.domain.repository.spotify;

import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistRepository implements SpotifyRepository<Playlist> {

  @Autowired private TrackRepository trackRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private SpotifyClient spotifyClient;

  @Override
  public Playlist getById(@NotNull String playlistId) {
    Playlist playlist = spotifyClient.getPlaylist(playlistId);
    trackRepository.encachePlaylistTracks(playlist.getTracks());
    return playlist;
  }

  public Paging<PlaylistSimplified> search(@NotNull String query) {
    return spotifyClient.searchPlaylists(query);
  }

  public Paging<PlaylistSimplified> getCurrentUserPlaylists() {
    return spotifyClient.getUserSavedPlaylists();
  }

  public void addTrackToPlaylist(@NotNull String playlistId, @NotNull String trackUri) {
    spotifyClient.addTrackToPlaylist(playlistId, trackUri);
  }

  public void createPlaylist(@NotNull String playlistName) {
    spotifyClient.createPlaylist(userRepository.getCurrentUser().getId(), playlistName);
  }
}
