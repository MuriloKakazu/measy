package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistRepository implements SpotifyRepository<Playlist> {

  @Autowired private SpotifyClient spotifyClient;

  @Override
  public Playlist getById(@NotNull String playlistId) {
    return spotifyClient.getPlaylist(playlistId);
  }

  public List<PlaylistSimplified> search(@NotNull String query) {
    return Arrays.asList(spotifyClient.searchPlaylists(query).getItems());
  }

  public Collection<PlaylistSimplified> getCurrentUserPlaylists() {
    return Arrays.asList(spotifyClient.getUserSavedPlaylists().getItems());
  }
}
