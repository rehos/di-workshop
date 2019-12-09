package com.everdune.competitive.repository;

import android.os.Bundle;

import com.everdune.competitive.BuildConfig;
import com.everdune.competitive.model.events.Event;
import com.everdune.competitive.service.CompetitiveService;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class EventServiceRepository extends CompetitiveRepositoryBase<List<Event>> {
  private CompetitiveService api;

  public EventServiceRepository() {
    Moshi moshi = new Moshi.Builder()
      .add(Date.class, new Rfc3339DateJsonAdapter().nullSafe())
      .build();

    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BuildConfig.COMPETITIVE_API_BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build();

    this.api = retrofit.create(CompetitiveService.class);
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

