package br.com.cefsa.ec6.measy.infrastructure.mapper;

import br.com.cefsa.ec6.measy.domain.model.youtube.Video;
import br.com.cefsa.ec6.measy.infrastructure.util.formatter.YoutubeUrlFormatter;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YoutubeSearchResultToVideoMapper {

  @Autowired private YoutubeUrlFormatter youtubeUrlFormatter;

  public Video fromSearchResult(SearchResult searchResult) {
    return new Video.Builder()
        .withId(searchResult.getId().getVideoId())
        .withThumbnailUrl(searchResult.getSnippet().getThumbnails().getHigh().getUrl())
        .withUrl(youtubeUrlFormatter.getVideoUrlFromId(searchResult.getId().getVideoId()))
        .build();
  }
}
