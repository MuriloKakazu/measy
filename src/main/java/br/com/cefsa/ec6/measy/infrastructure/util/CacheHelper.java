package br.com.cefsa.ec6.measy.infrastructure.util;

import br.com.cefsa.ec6.measy.domain.repository.spotify.PlaylistRepository;
import br.com.cefsa.ec6.measy.infrastructure.cache.Cache;
import br.com.cefsa.ec6.measy.infrastructure.cache.CacheableValue;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheHelper {

  @Autowired private Cache cache;
  @Autowired private PlaylistRepository playlistRepository;

  public void refreshUserPlaylists() {
    cache.remove("user_playlists");
    cache.put("user_playlists", playlistRepository.getCurrentUserPlaylists());
  }

  public Paging<PlaylistSimplified> getUserPlaylists() {
    Object cachedResult = cache.get("user_playlists");

    if (cachedResult == null) return null;

    CacheableValue<Paging<PlaylistSimplified>> cachedValue =
        (CacheableValue<Paging<PlaylistSimplified>>) cachedResult;

    return ((CacheableValue<Paging<PlaylistSimplified>>) cachedResult).getValue();
  }

}
