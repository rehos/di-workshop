package com.everdune.competitive.di;

import java.util.List;

import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.model.events.Participant;
import com.everdune.competitive.ui.common.CompetitiveRepositoryVMFactory;

public class CompetitiveRepositoryVMFactoryProvider {
  private static CompetitiveRepositoryVMFactory<List<Event>> eventsVMFactoryInstance;
  private static CompetitiveRepositoryVMFactory<List<Participant>> participantsVMFactoryInstance;

  public static CompetitiveRepositoryVMFactory<List<Event>> getEventsVMFactory() {
    if (null == eventsVMFactoryInstance) {
      eventsVMFactoryInstance = new CompetitiveRepositoryVMFactory<>(CompetitiveRepositoryProvider.getEventRepository());
    }
    return eventsVMFactoryInstance;
  }

  public static CompetitiveRepositoryVMFactory<List<Participant>> getParticipantsVMFactory() {
    if (null == participantsVMFactoryInstance) {
      participantsVMFactoryInstance = new CompetitiveRepositoryVMFactory<>(CompetitiveRepositoryProvider.getParticipantRepository());
    }
    return participantsVMFactoryInstance;
  }
}
