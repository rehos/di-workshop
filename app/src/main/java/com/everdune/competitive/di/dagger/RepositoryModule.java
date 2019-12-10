package com.everdune.competitive.di.dagger;

import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.model.events.Participant;
import com.everdune.competitive.repository.CompetitiveRepository;
import com.everdune.competitive.repository.EventServiceRepository;
import com.everdune.competitive.repository.ParticipantsServiceRepository;
import com.everdune.competitive.service.CompetitiveService;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
  @Singleton
  @Provides
  public static CompetitiveRepository<List<Event>> getEventRepository(CompetitiveService api) {
    return new EventServiceRepository(api);
  }

  @Singleton
  @Provides
  public static CompetitiveRepository<List<Participant>> getParticipantRepository(CompetitiveService api) {
    return new ParticipantsServiceRepository(api);
  }
}
