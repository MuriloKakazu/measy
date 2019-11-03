package br.com.cefsa.ec6.measy.domain.repository.spotify;

import br.com.cefsa.ec6.measy.domain.repository.CachedRepository;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepository extends CachedRepository<Artist>
    implements SpotifyRepository<Artist> {

  @Autowired private SpotifyClient spotifyClient;

  private Paging<Artist> encache(Paging<Artist> paging) {
    for (Artist artist : paging.getItems()) {
      encache(artist.getId(), artist);
    }
    return paging;
  }

  @Override
  public Artist getById(@NotNull String artistId) {
    return getOrEncache(artistId, () -> spotifyClient.getArtist(artistId));
  }

  public Paging<Artist> search(@NotNull String query) {
    return encache(spotifyClient.searchArtists(query));
  }
}
