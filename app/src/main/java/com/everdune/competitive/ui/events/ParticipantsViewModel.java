package com.everdune.competitive.ui.events;

import com.everdune.competitive.model.events.Participant;
import com.everdune.competitive.repository.CompetitiveRepository;
import com.everdune.competitive.ui.common.CompetitiveViewModel;

import java.util.List;

public class ParticipantsViewModel extends CompetitiveViewModel<List<Participant>> {
  public ParticipantsViewModel(CompetitiveRepository<List<Participant>> repository) {
    super(repository);
  }
}