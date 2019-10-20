package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.data.repository.CachedRepository;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

  public List<AlbumSimplified> search(@NotNull String query) {
    return Arrays.asList(spotifyClient.searchAlbums(query).getItems());
  }

  public Collection<AlbumSimplified> getByArtistId(@NotNull String artistId) {
    return Arrays.asList(spotifyClient.getArtistAlbums(artistId).getItems());
  }
}
