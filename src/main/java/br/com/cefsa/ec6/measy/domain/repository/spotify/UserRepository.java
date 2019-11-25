package br.com.cefsa.ec6.measy.domain.repository.spotify;

import br.com.cefsa.ec6.measy.infrastructure.cache.Cache;
import br.com.cefsa.ec6.measy.infrastructure.cache.CacheableValue;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.User;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements SpotifyRepository<User> {

  @Autowired private Cache cache;
  @Autowired private SpotifyClient spotifyClient;

  @Override
  public User getById(@NotNull String id) {
    return spotifyClient.getUser(id);
  }

  public User getCurrentUser() {
    if (cache.contains("current_user")) return ((CacheableValue<User>) cache.get("current_user")).getValue();

    User user = spotifyClient.getUser();
    cache.put("current_user", user);

    return user;
  }
}
