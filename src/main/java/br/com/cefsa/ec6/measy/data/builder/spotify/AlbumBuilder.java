package br.com.cefsa.ec6.measy.data.builder.spotify;

import br.com.cefsa.ec6.measy.domain.model.spotify.Album;
import br.com.cefsa.ec6.measy.infrastructure.builder.Builder;

public class AlbumBuilder implements Builder<Album> {
  private Album album = new Album();

  @Override
  public Album build() {
    return album;
  }
}
