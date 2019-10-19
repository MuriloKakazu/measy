package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.Track;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class TrackRepository implements SpotifyRepository<Track> {

  @Override
  public Track getById(String id) {
    return null;
  }

  public Collection<Track> getByName(String name) {
    return null;
  }

  public Collection<Track> getByAlbumId(String albumId) {
    return null;
  }

  public Collection<Track> getByArtistId(String artistId) {
    return null;
  }
}
