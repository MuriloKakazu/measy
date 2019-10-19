package br.com.cefsa.ec6.measy.domain.model.spotify;

import java.util.Date;

public class Album extends BaseModel {
  private String name;
  private Date releaseDate;

  private Artist artist;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Artist getArtist() {
    return artist;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }
}
