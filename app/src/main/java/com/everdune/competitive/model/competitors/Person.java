package com.everdune.competitive.model.competitors;

import com.squareup.moshi.Json;

public class Person implements Competitor{
  @Json(name = "id")
  private String id;
  @Json(name = "name")
  private String name;

  public Person(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isIndividual() {
    return true;
  }
}
