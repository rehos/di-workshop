package com.everdune.competitive.repository;

import android.os.Bundle;

import com.everdune.competitive.BuildConfig;
import com.everdune.competitive.di.CompetitiveServiceProvider;
import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.service.CompetitiveService;

import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventServiceRepository extends CompetitiveRepositoryBase<List<Event>> {
  private CompetitiveService api;

  public EventServiceRepository() {
    this.api = CompetitiveServiceProvider.getInstance();
  }

  @Override
  protected void loadData(Bundle bundle) {
    api.getEvents().enqueue(new Callback<List<Event>>() {
      @Override
      public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
        updateError(null);

        updateIsLoading(true);
        updateIsFetching(true);

        if (response.isSuccessful()) {
          updateData(response.body());
          updateIsLoaded(true);
        } else {
          updateError("HTTP status: " + response.code());
        }
      }

      @Override
      public void onFailure(Call<List<Event>> call, Throwable t) {
        updateIsLoading(true);
        updateIsFetching(true);
        updateError(t.getMessage());
      }
    });
  }

  @Override
  protected boolean shouldReloadData(@Nullable Bundle bundle) {
    return false;
  }
}

