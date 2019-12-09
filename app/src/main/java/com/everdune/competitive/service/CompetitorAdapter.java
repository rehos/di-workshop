package com.everdune.competitive.service;

import com.everdune.competitive.model.competitors.Competitor;
import com.everdune.competitive.model.competitors.Person;
import com.everdune.competitive.model.competitors.Team;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;

import java.io.IOException;

public class CompetitorAdapter extends JsonAdapter<Competitor> {
  @Override
  public Competitor fromJson(JsonReader reader) throws IOException {
    if (reader.peek() != JsonReader.Token.BEGIN_OBJECT) {
      reader.skipValue();
      return null;
    }
    JsonReader.Options NAMES = JsonReader.Options.of("type", "id", "name");

    String type = null;
    String id = null;
    String name = null;

    reader.beginObject();
    while(reader.hasNext()) {
      switch(reader.selectName(NAMES)) {
        case 0: // type
          if (reader.peek() == JsonReader.Token.STRING) {
            type = reader.nextString();
          } else {
            reader.skipValue();
          }
          break;
        case 1: // id
          if (reader.peek() == JsonReader.Token.STRING) {
            id = reader.nextString();
          } else {
            reader.skipValue();
          }
          break;
        case 2: // name
          if (reader.peek() == JsonReader.Token.STRING) {
            name = reader.nextString();
          } else {
            reader.skipValue();
          }
          break;
        default:
          reader.skipName();
          reader.skipValue();
          break;
      }
    }
    reader.endObject();

    if (null == type || null == id || null == name) return null;

    switch(type) {
      case "person":
        return new Person(id, name);
      case "team":
        return new Team(id, name);
      default:
        return null;
    }
  }

  @Override
  public void toJson(JsonWriter writer, Competitor value) throws IOException {

  }
}
