package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.Playlist;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistRepository implements SpotifyRepository<Playlist> {
  @Override
  public Playlist getById(String id) {
    return null;
  }

  public Collection<Playlist> getByName(String name) {
    return null;
  }

  public Collection<Playlist> getCurrentUserPlaylists() {
    return null;
  }

  public Collection<Playlist> getByUserId(String userId) {
    return null;
  }
}
