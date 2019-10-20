package br.com.cefsa.ec6.measy.infrastructure.cache;

import br.com.cefsa.ec6.measy.infrastructure.async.Job;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Cache {
  private Map<String, Cacheable> cachedData;
  private long ttlMillis;

  private Cache() {
    this.cachedData = new HashMap<>();
    this.ttlMillis = 10000;
  }

  public Cache put(String key, Cacheable value) {
    cachedData.put(key, value);
    return this;
  }

  public <T> Cache put(String key, T value) {
    return put(key, new CacheableValue<T>(value));
  }

  public Cache remove(String key) {
    cachedData.remove(key);
    return this;
  }

  public Boolean contains(String key) {
    return cachedData.containsKey(key);
  }

  public <T> Cacheable get(String key) {
    return cachedData.get(key);
  }

  public Cache setTTL(Long millis) {
    ttlMillis = millis;
    return this;
  }

  public Cache expire() {
    cachedData.clear();
    scheduleExpiration();
    return this;
  }

  private void scheduleExpiration() {
    new Job(() -> expire()).runAfter(ttlMillis).repeat(0).run();
  }
}
