package com.everdune.competitive.di.dagger;

import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.model.events.Participant;
import com.everdune.competitive.repository.CompetitiveRepository;
import com.everdune.competitive.ui.common.ViewModelFactory;
import com.everdune.competitive.ui.events.EventsViewModel;
import com.everdune.competitive.ui.events.ParticipantsViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;

import androidx.lifecycle.ViewModel;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @MapKey
  @interface ViewModelKey {
    Class<? extends ViewModel> value();
  }

  @Provides
  ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    return new ViewModelFactory(providerMap);
  }

  @Provides
  @IntoMap
  @ViewModelKey(EventsViewModel.class)
  ViewModel eventsViewModel(CompetitiveRepository<List<Event>> repository) {
    return new EventsViewModel(repository);
  }

  @Provides
  @IntoMap
  @ViewModelKey(ParticipantsViewModel.class)
  ViewModel participantViewModel(CompetitiveRepository<List<Participant>> repository) {
    return new ParticipantsViewModel(repository);
  }
}
