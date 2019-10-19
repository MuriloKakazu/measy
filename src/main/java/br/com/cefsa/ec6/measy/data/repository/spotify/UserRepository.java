package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.User;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements SpotifyRepository<User> {
  @Override
  public User getById(String id) {
    return null;
  }

  public Collection<User> getByName(String name) {
    return null;
  }

  public User getCurrentUser() {
    return null;
  }
}
