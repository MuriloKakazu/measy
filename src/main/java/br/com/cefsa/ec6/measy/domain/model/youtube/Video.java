package br.com.cefsa.ec6.measy.domain.model.youtube;

public class Video {

  private String id;
  private String url;
  private String thumbnailUrl;

  public static class Builder {
    private Video video = new Video();

    public Builder withId(String id) {
      video.setId(id);
      return this;
    }

    public Builder withUrl(String url) {
      video.setUrl(url);
      return this;
    }

    public Builder withThumbnailUrl(String url) {
      video.setThumbnailUrl(url);
      return this;
    }

    public Video build() {
      return video;
    }
  }

  public String getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  private void setId(String id) {
    this.id = id;
  }

  private void setUrl(String url) {
    this.url = url;
  }

  private void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }
}
