package br.com.cefsa.ec6.measy.domain.repository;

import br.com.cefsa.ec6.measy.infrastructure.cache.Cache;
import br.com.cefsa.ec6.measy.infrastructure.cache.CacheableValue;
import java.util.function.Supplier;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CachedRepository<T> {

  @Autowired private Cache cache;

  protected Boolean isCached(@NotNull String key) {
    return cache.contains(key);
  }

  protected T getCachedValue(@NotNull String key) {
    return ((CacheableValue<T>) cache.get(key)).getValue();
  }

  protected T encache(@NotNull String key, @NotNull T value) {
    cache.put(key, value);
    return value;
  }

  protected T getOrEncache(@NotNull String key, @NotNull Supplier<T> dataToEncache) {
    return isCached(key) ? getCachedValue(key) : encache(key, dataToEncache.get());
  }
}
