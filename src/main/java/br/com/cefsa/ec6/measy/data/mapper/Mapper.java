package br.com.cefsa.ec6.measy.data.mapper;

public interface Mapper<T, U> {
  U map(T instance);
}
