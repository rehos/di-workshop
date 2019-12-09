package com.everdune.competitive.di;

import com.everdune.competitive.BuildConfig;
import com.everdune.competitive.model.competitors.Competitor;
import com.everdune.competitive.service.CompetitiveService;
import com.everdune.competitive.service.CompetitorAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class CompetitiveServiceProvider {
  private static CompetitiveService instance;

  public static CompetitiveService getInstance() {
    if (null == instance) {
      Moshi moshi = new Moshi.Builder()
        .add(Date.class, new Rfc3339DateJsonAdapter().nullSafe())
        .add(Competitor.class, new CompetitorAdapter().nullSafe())
        .build();

      Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BuildConfig.COMPETITIVE_API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build();

      instance = retrofit.create(CompetitiveService.class);
    }
    return instance;
  }
}
