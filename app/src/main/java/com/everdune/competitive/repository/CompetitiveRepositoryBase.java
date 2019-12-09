package com.everdune.competitive.repository;

import android.os.Bundle;

import com.everdune.competitive.service.CompetitiveService;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public abstract class CompetitiveRepositoryBase<T> implements CompetitiveRepository<T> {
  private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
  private final MutableLiveData<Boolean> isFetching = new MutableLiveData<>();
  private final MutableLiveData<Boolean> isLoaded = new MutableLiveData<>();
  private final MutableLiveData<Boolean> canFetchMore = new MutableLiveData<>();
  private final MutableLiveData<String> error = new MutableLiveData<>();

  // Note: this is poor mans cache
  private final MutableLiveData<T> data = new MutableLiveData<>();

  public CompetitiveRepositoryBase() {
    isLoading.setValue(false);
    isFetching.setValue(false);
    isLoaded.setValue(false);
    canFetchMore.setValue(false);
  }

  @Override
  public LiveData<Boolean> isLoading() {
    return isLoading;
  }

  protected void updateIsLoading(boolean value) {
    isLoading.setValue(value);
  }

  @Override
  public LiveData<Boolean> isFetching() {
    return isFetching;
  }

  protected void updateIsFetching(boolean value) {
    isFetching.setValue(value);
  }

  @Override
  public LiveData<Boolean> isLoaded() {
    return isLoaded;
  }

  protected void updateIsLoaded(boolean value) {
    isLoaded.setValue(value);
  }

  @Override
  public LiveData<Boolean> canFetchMore() {
    return canFetchMore;
  }

  protected void updateCanFetchMore(boolean value) {
    canFetchMore.setValue(value);
  }

  @Override
  public LiveData<String> getError() {
    return error;
  }

  protected void updateError(@Nullable String value) {
    error.setValue(value);
  }

  @Override
  public LiveData<T> getData() {
    return getData(null);
  }

  @Override
  public LiveData<T> getData(@Nullable Bundle bundle) {
    if (isLoaded.getValue()) {
      if (!shouldReloadData(bundle)) return data;
      isLoaded.setValue(false);
      data.setValue(null);
    }

    isLoading.setValue(true);

    loadData(bundle);

    return data;
  }

  protected void updateData(@Nullable T value) {
    data.setValue(value);
  }

  abstract protected boolean shouldReloadData(@Nullable Bundle bundle);

  abstract protected void loadData(@Nullable Bundle bundle);
}
