package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.data.repository.CachedRepository;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.neovisionaries.i18n.CountryCode;
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

  public Collection<Track> search(@NotNull String query) {
    return encache(Arrays.asList(spotifyClient.searchTracks(query).getItems()));
  }

  public Collection<TrackSimplified> getByAlbumId(@NotNull String albumId) {
    return Arrays.asList(spotifyClient.getAlbumTracks(albumId).getItems());
  }

  public Collection<Track> getMostPopularsFromArtistId(
      @NotNull String artistId, @NotNull CountryCode countryCode) {
    return encache(Arrays.asList(spotifyClient.getArtistTopTracks(artistId, countryCode)));
  }
}
