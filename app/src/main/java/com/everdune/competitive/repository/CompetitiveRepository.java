package com.everdune.competitive.repository;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public interface CompetitiveRepository<T> {
  LiveData<Boolean> isLoading();
  LiveData<Boolean> isFetching();
  LiveData<Boolean> isLoaded();
  LiveData<Boolean> canFetchMore();
  LiveData<String> getError();
  LiveData<T> getData();
  LiveData<T> getData(@Nullable Bundle bundle);
}
