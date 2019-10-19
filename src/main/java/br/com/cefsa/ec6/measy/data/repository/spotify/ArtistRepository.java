package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.Artist;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepository implements SpotifyRepository<Artist> {
  @Override
  public Artist getById(String spotifyId) {
    return null;
  }

  public Collection<Artist> getByName(String name) {
    return null;
  }
}
