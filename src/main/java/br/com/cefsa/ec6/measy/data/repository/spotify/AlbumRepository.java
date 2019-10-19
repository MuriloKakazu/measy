package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.Album;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepository implements SpotifyRepository<Album> {
  @Override
  public Album getById(String id) {
    return null;
  }

  public Collection<Album> getByName(String name) {
    return null;
  }

  public Collection<Album> getByArtistId(String artistId) {
    return null;
  }
}
