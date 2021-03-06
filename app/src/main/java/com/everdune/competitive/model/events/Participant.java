package com.everdune.competitive.model.events;

import com.everdune.competitive.model.competitors.Competitor;
import com.squareup.moshi.Json;


public class Participant {
  private Competitor competitor;
  private Integer number;
  private Integer result;
  private String formattedResult;

  public Participant(Competitor competitor, Integer number, Integer result, String formattedResult) {
    this.competitor = competitor;
    this.number = number;
    this.result = result;
    this.formattedResult = formattedResult;
  }

  public Competitor getCompetitor() {
    return competitor;
  }

  public String getName() {
    return getCompetitor().getName();
  };

  public Integer getNumber() {
    return number;
  };

  public Integer getResult() {
    return result;
  }

  public String getFormattedResult() {
    return formattedResult;
  }

  boolean isIndividual() {
    return getCompetitor().isIndividual();
  }
}
