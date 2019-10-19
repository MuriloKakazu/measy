package br.com.cefsa.ec6.measy.data.repository.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.BaseModel;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotifyRepository<T extends BaseModel> {
  T getById(String id);
}
