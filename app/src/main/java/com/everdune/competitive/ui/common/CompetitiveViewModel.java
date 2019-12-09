package com.everdune.competitive.ui.common;

import android.os.Bundle;

import com.everdune.competitive.repository.CompetitiveRepository;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CompetitiveViewModel<T> extends ViewModel {
  private final CompetitiveRepository<T> repository;

  public CompetitiveViewModel(CompetitiveRepository<T> repository) {
    this.repository = repository;
  }

  public LiveData<Boolean> isLoading() {
    return repository.isLoading() ;
  }
  public LiveData<Boolean> isFetching() {
    return repository.isFetching() ;
  }
  public LiveData<Boolean> isLoaded() {
    return repository.isLoaded() ;
  }
  public LiveData<Boolean> canFetchMore() {
    return repository.canFetchMore() ;
  }
  public LiveData<String> getError() {
    return repository.getError() ;
  }

  public LiveData<T> getData() {
    return repository.getData() ;
  }
  public LiveData<T> getData(@Nullable Bundle bundle) {
    return repository.getData(bundle) ;
  }
}
