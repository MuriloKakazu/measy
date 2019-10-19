package br.com.cefsa.ec6.measy.data.repository.spotify

import br.com.cefsa.ec6.measy.domain.model.spotify.Track
import br.com.six2six.fixturefactory.Fixture
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.mockito.Mockito.*

@SpringBootTest(classes = [
  TrackRepository.class
])
class TrackRepositoryTest extends Specification {

  @Autowired TrackRepository repository

  def setup() {
    FixtureFactoryLoader.loadTemplates("br.com.cefsa.ec6.measy.data.model.fixtures")
  }
}
