package com.everdune.competitive.model.events;

import com.squareup.moshi.Json;

import java.util.Date;

public class Event {
  private String id;
  private String name;
  private Date date;

  public Event(String id, String name, Date date) {
    this.id = id;
    this.name = name;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Date getDate() {
    return date;
  }
}
