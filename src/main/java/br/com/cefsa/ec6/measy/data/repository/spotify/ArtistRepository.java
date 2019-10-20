package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.data.repository.CachedRepository;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.Artist;
import java.util.Arrays;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepository extends CachedRepository<Artist>
    implements SpotifyRepository<Artist> {

  @Autowired private SpotifyClient spotifyClient;

  private Collection<Artist> encache(Collection<Artist> artists) {
    for (Artist artist : artists) {
      encache(artist.getId(), artist);
    }
    return artists;
  }

  @Override
  public Artist getById(@NotNull String artistId) {
    return getOrEncache(artistId, () -> spotifyClient.getArtist(artistId));
  }

  public Collection<Artist> search(@NotNull String query) {
    return encache(Arrays.asList(spotifyClient.searchArtists(query).getItems()));
  }
}
