package com.everdune.competitive.model.competitors;

import com.squareup.moshi.Json;

import java.util.List;

public class Team implements Competitor {
  private transient List<TeamMember> teamMembers;

  @Json(name = "id")
  private String id;
  @Json(name = "name")
  private String name;

  public Team(String id, String name) {
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
    return false;
  }


  public List<TeamMember> getTeamMembers() {
    return teamMembers;
  }

  public void setTeamMembers(List<TeamMember> teamMembers) {
    this.teamMembers = teamMembers;
  }

  public void addTeamMember(TeamMember teamMember) {
    teamMembers.add(teamMember);
  }

  public void removeTeamMember(TeamMember teamMember) {
    teamMembers.remove(teamMember);
  }

  public static class TeamMember {
    @Json(name = "person")
    private final Person person;
    @Json(name = "number")
    private final int number;

    public TeamMember(Person person, int number) {
      this.person = person;
      this.number = number;
    }

    public String getName() {
      return person.getName() + " (" + getNumber() + ")";
    }

    public int getNumber() {
      return number;
    }
  }
}
