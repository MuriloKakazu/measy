package br.com.cefsa.ec6.measy.domain.repository.spotify;

import br.com.cefsa.ec6.measy.domain.repository.CachedRepository;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;
import java.util.Arrays;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrackRepository extends CachedRepository<Track> implements SpotifyRepository<Track> {

  @Autowired private SpotifyClient spotifyClient;

  private Paging<Track> encache(Paging<Track> paging) {
    encache(Arrays.asList(paging.getItems()));
    return paging;
  }

  private Collection<Track> encache(Collection<Track> tracks) {
    for (Track track : tracks) {
      encache(track.getId(), track);
    }
    return tracks;
  }

  @Override
  public Track getById(@NotNull String trackId) {
    return getOrEncache(trackId, () -> spotifyClient.getTrack(trackId));
  }

  public Paging<Track> search(@NotNull String query) {
    return encache(spotifyClient.searchTracks(query));
  }

  public Paging<TrackSimplified> getByAlbumId(@NotNull String albumId) {
    return spotifyClient.getAlbumTracks(albumId);
  }

  public Collection<Track> getMostPopularsFromArtistId(
      @NotNull String artistId, @NotNull CountryCode countryCode) {
    return encache(Arrays.asList(spotifyClient.getArtistTopTracks(artistId, countryCode)));
  }
}
