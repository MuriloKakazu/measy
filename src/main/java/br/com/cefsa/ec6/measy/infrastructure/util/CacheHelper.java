package br.com.cefsa.ec6.measy.infrastructure.util;

import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.domain.repository.spotify.TrackRepository;
import br.com.cefsa.ec6.measy.infrastructure.cache.Cache;
import br.com.cefsa.ec6.measy.infrastructure.cache.CacheableValue;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {

  @Autowired private Cache cache;
  @Autowired private PlaylistRepository playlistRepository;
  @Autowired private TrackRepository trackRepository;

  public void refreshUserPlaylists() {
    cache.remove("user_playlists");
    cache.put("user_playlists", playlistRepository.getCurrentUserPlaylists());
  }

  public void refreshUserTopTracks() {
    cache.remove("user_top_tracks");
    cache.put("user_top_tracks", trackRepository.getMostPopularsFromCurrentUser());
  }

  public void refreshGlobalCharts() {
    cache.remove("global_charts");
    cache.put("global_charts", trackRepository.getFromGlobalCharts());
  }

  public Paging<PlaylistSimplified> getUserPlaylists() {
    return ((CacheableValue<Paging<PlaylistSimplified>>) getCachedValue("user_playlists"))
        .getValue();
  }

  public Paging<Track> getUserTopTracks() {
    return ((CacheableValue<Paging<Track>>) getCachedValue("user_top_tracks")).getValue();
  }

  public Paging<PlaylistTrack> getGlobalCharts() {
    return ((CacheableValue<Paging<PlaylistTrack>>) getCachedValue("global_charts")).getValue();
  }

  private <T> T getCachedValue(String key) {
    Object cachedResult = cache.get(key);
    if (cachedResult == null) return null;
    return (T) cachedResult;
  }
}
