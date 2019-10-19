package br.com.cefsa.ec6.measy.domain.model.spotify;

import br.com.cefsa.ec6.measy.infrastructure.cache.Cacheable;
import java.io.Serializable;

public abstract class BaseModel implements Cacheable, Serializable {
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
