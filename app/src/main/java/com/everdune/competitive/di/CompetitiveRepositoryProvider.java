package com.everdune.competitive.di;

import java.util.List;

import com.everdune.competitive.model.competitors.Person;
import com.everdune.competitive.model.competitors.Team;
import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.model.events.Participant;
import com.everdune.competitive.repository.CompetitiveRepository;
import com.everdune.competitive.repository.EventServiceRepository;
import com.everdune.competitive.repository.ParticipantsServiceRepository;

public class CompetitiveRepositoryProvider {
  private static CompetitiveRepository<List<Event>> eventRepositoryInstance;
  private static CompetitiveRepository<List<Team>> teamRepositoryInstance;
  private static CompetitiveRepository<List<Person>> personRepositoryInstance;
  private static CompetitiveRepository<List<Participant>> participantRepositoryInstance;

  public static CompetitiveRepository<List<Event>> getEventRepository() {
    if (null == eventRepositoryInstance) {
      eventRepositoryInstance = new EventServiceRepository(CompetitiveServiceProvider.getInstance());
    }
    return eventRepositoryInstance;
  }

  public static CompetitiveRepository<List<Participant>> getParticipantRepository() {
    if (null == participantRepositoryInstance) {
      participantRepositoryInstance = new ParticipantsServiceRepository(CompetitiveServiceProvider.getInstance());
    }
    return participantRepositoryInstance;
  }

}
