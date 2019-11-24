package br.com.cefsa.ec6.measy.infrastructure.client.rest;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class YoutubeClient {

  @Value("${youtube.api.key}") private String apiKey;
  @Value("${youtube.client.settings.default-page-size}") private Long pageSize;

  @Autowired private YouTube youtubeApi;

  public Collection<SearchResult> searchVideo(String query) {
    try {

      YouTube.Search.List search = youtubeApi.search()
          .list("id,snippet")
          .setKey(apiKey)
          .setQ(query)
          .setType("video")
          .setVideoSyndicated("true")
          .setVideoEmbeddable("true")
          .setSafeSearch("strict")
          .setOrder("relevance")
          .setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/high/url)")
          .setMaxResults(pageSize);

      SearchListResponse searchResponse = search.execute();
      return searchResponse.getItems();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
