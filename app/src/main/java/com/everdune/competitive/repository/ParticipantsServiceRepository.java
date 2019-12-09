package com.everdune.competitive.repository;

import android.os.Bundle;
import android.text.TextUtils;

import com.everdune.competitive.model.events.Participant;
import com.everdune.competitive.service.CompetitiveService;

import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParticipantsServiceRepository extends CompetitiveRepositoryBase<List<Participant>> {
  public static String EVENT_ID = "EVENT_ID";

  private CompetitiveService api;
  private String loadedEventId;

  public ParticipantsServiceRepository(CompetitiveService api) {
    this.api = api;
  }

  @Override
  protected void loadData(Bundle bundle) {
    final String eventId = bundle.getString(EVENT_ID);

    if (null == eventId) {
      throw new IllegalArgumentException("No value for key EVENT_ID in bundle");
    }

    api.getParticipants(eventId).enqueue(new Callback<List<Participant>>() {
      @Override
      public void onResponse(Call<List<Participant>> call, Response<List<Participant>> response) {
        updateError(null);

        updateIsLoading(true);
        updateIsFetching(true);

        if (response.isSuccessful()) {
          loadedEventId = eventId;
          updateData(response.body());
          updateIsLoaded(true);
        } else {
          updateError("HTTP status: " + response.code());
        }
      }

      @Override
      public void onFailure(Call<List<Participant>> call, Throwable t) {
        updateIsLoading(true);
        updateIsFetching(true);
        updateError(t.getMessage());
      }
    });
  }

  @Override
  protected boolean shouldReloadData(@Nullable Bundle bundle) {
    String eventId = bundle.getString(EVENT_ID);

    return !TextUtils.equals(loadedEventId, eventId);
  }
}
