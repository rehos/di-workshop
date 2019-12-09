package com.everdune.competitive.model.competitors;

import com.squareup.moshi.Json;

public class Competitor {
  public String id;
  private String name;

  public Competitor(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public boolean isIndividual() {
    return true;
  }
}
