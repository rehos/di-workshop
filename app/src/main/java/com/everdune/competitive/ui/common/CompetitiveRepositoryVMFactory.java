package com.everdune.competitive.ui.common;

import com.everdune.competitive.repository.CompetitiveRepository;

import java.lang.reflect.InvocationTargetException;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CompetitiveRepositoryVMFactory<T> implements ViewModelProvider.Factory {
  private CompetitiveRepository repository;

  public CompetitiveRepositoryVMFactory(CompetitiveRepository<T> repository) {
    this.repository = repository;
  }

  @NonNull
  @Override
  public <V extends ViewModel> V create(@NonNull Class<V> modelClass) {
    try {
      return modelClass.getConstructor(CompetitiveRepository.class).newInstance(repository);
    } catch (InvocationTargetException e) {
      throw new RuntimeException("Cannot create an instance of " + modelClass, e);
    } catch (InstantiationException e) {
      throw new RuntimeException("Cannot create an instance of " + modelClass, e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Cannot create an instance of " + modelClass, e);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Cannot create an instance of " + modelClass, e);
    }
  }
}
