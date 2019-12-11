package com.everdune.competitive.di.dagger;

import com.everdune.competitive.BuildConfig;
import com.everdune.competitive.model.competitors.Competitor;
import com.everdune.competitive.service.CompetitorAdapter;
import com.everdune.competitive.service.CompetitiveService;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import java.util.Date;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class ServiceModule {
  @Singleton
  @Provides
  public CompetitiveService providesService(@Named("ServiceBaseUrl") String baseUrl) {
    Moshi moshi = new Moshi.Builder()
      .add(Date.class, new Rfc3339DateJsonAdapter().nullSafe())
      .add(Competitor.class, new CompetitorAdapter().nullSafe())
      .build();

    Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build();

    return retrofit.create(CompetitiveService.class);
  }
}
