package br.com.cefsa.ec6.measy.infrastructure.builder;

import com.google.gson.JsonArray;

public class JsonArrayBuilder implements Builder<JsonArray> {

  private JsonArray result = new JsonArray();

  public JsonArrayBuilder with(String... values) {
    for (String value : values) {
      with(value);
    }
    return this;
  }

  public JsonArrayBuilder with(String value) {
    result.add(value);
    return this;
  }

  @Override
  public JsonArray build() {
    return result;
  }
}
