package br.com.cefsa.ec6.measy.infrastructure.cache;

public class CacheableValue<T> implements Cacheable {
  private T value;

  public CacheableValue(T value) {
    setValue(value);
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }
}
