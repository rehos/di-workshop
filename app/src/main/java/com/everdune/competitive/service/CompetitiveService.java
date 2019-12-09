package com.everdune.competitive.service;

import com.everdune.competitive.model.events.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompetitiveService {
  @GET("events")
  Call<List<Event>> getEvents();
}
