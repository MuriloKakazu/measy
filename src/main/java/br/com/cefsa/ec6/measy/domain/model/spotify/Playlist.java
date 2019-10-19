package br.com.cefsa.ec6.measy.domain.model.spotify;

public class Playlist extends BaseModel {

  private String name;

  private User owner;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
