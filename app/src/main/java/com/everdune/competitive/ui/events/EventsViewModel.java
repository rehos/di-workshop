package com.everdune.competitive.ui.events;

import java.util.List;

import com.everdune.competitive.repository.CompetitiveRepository;
import com.everdune.competitive.repository.EventServiceRepository;
import com.everdune.competitive.ui.common.CompetitiveViewModel;

import com.everdune.competitive.model.events.Event;

public class EventsViewModel extends CompetitiveViewModel<List<Event>> {
  public EventsViewModel() {
    super(new EventServiceRepository());
  }
}