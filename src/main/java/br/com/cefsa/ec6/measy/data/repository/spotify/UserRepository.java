package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.infrastructure.client.rest.SpotifyClient;
import com.wrapper.spotify.model_objects.specification.User;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements SpotifyRepository<User> {

  @Autowired private SpotifyClient spotifyClient;

  @Override
  public User getById(@NotNull String id) {
    return spotifyClient.getUser(id);
  }

  public User getCurrentUser() {
    return spotifyClient.getUser();
  }
}
