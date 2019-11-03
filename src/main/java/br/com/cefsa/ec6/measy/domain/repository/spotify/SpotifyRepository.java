package br.com.cefsa.ec6.measy.domain.repository.spotify;

import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyRepository<T> {
  T getById(@NotNull String id);
}
