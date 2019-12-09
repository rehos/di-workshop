package com.everdune.competitive.service;

import com.everdune.competitive.model.competitors.Person;
import com.everdune.competitive.model.competitors.Team;
import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.model.events.Participant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CompetitiveService {
  @GET("events")
  Call<List<Event>> getEvents();

  @GET("events/{eventId}/participants")
  Call<List<Participant>> getParticipants(@Path("eventId") String eventId);

  @GET("teams")
  Call<List<Team>> getTeams();

  @GET("teams/{teamId}/members")
  Call<List<Team.TeamMember>> getTeamMembers(@Path("teamId") String teamId);

  @GET("persons")
  Call<List<Person>> getPersons();
}
