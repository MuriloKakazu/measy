package br.com.cefsa.ec6.measy.domain.repository.youtube;

import br.com.cefsa.ec6.measy.domain.model.youtube.Video;
import br.com.cefsa.ec6.measy.infrastructure.client.rest.YoutubeClient;
import br.com.cefsa.ec6.measy.infrastructure.mapper.YoutubeSearchResultToVideoMapper;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

@Repository
public class VideoRepository {

  @Autowired private YoutubeClient youtubeClient;
  @Autowired private YoutubeSearchResultToVideoMapper videoMapper;

  public Collection<Video> getVideos(String query) {
    Collection<SearchResult> results = youtubeClient.searchVideo(query);
    return results.stream().map(videoMapper::fromSearchResult).collect(Collectors.toList());
  }

  public Video getVideo(String query) {
    return getVideos(query).stream().findFirst().orElse(new Video.Builder().build());
  }

  public Video getVideo(String... query) {
    return getVideo(String.join(" ", query));
  }

}
