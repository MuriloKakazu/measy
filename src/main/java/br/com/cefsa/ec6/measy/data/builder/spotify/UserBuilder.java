package br.com.cefsa.ec6.measy.data.builder.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.User;
import br.com.cefsa.ec6.measy.infrastructure.builder.Builder;

public class UserBuilder implements Builder<User> {
  private User user = new User();

  @Override
  public User build() {
    return user;
  }
}
