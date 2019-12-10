package com.everdune.competitive.di.dagger;

import com.everdune.competitive.ui.events.EventsFragment;
import com.everdune.competitive.ui.events.ParticipantsFragment;
import com.everdune.competitive.ui.persons.PersonsFragment;
import com.everdune.competitive.ui.teams.TeamsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = { ServiceModule.class, ViewModelModule.class, RepositoryModule.class} )
public interface ApplicationComponent {
  void inject(EventsFragment fragment);
  void inject(ParticipantsFragment fragment);
  void inject(TeamsFragment fragment);
  void inject(PersonsFragment fragment);
}
