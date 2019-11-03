package br.com.cefsa.ec6.measy.domain.repository.spotify;

import br.com.cefsa.ec6.measy.domain.repository.CachedRepository;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepository extends CachedRepository<Album> implements SpotifyRepository<Album> {

  @Autowired private SpotifyClient spotifyClient;

  @Override
  public Album getById(@NotNull String albumId) {
    return getOrEncache(albumId, () -> spotifyClient.getAlbum(albumId));
  }

  public Paging<AlbumSimplified> search(@NotNull String query) {
    return spotifyClient.searchAlbums(query);
  }

  public Paging<AlbumSimplified> getByArtistId(@NotNull String artistId) {
    return spotifyClient.getArtistAlbums(artistId);
  }
}
