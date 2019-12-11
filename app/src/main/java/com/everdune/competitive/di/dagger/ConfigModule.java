package com.everdune.competitive.di.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {
  @Provides
  @Named("ServiceBaseUrl")
  String providesServiceBaseUrl() {
    return "https://rehos.github.io/di-workshop/api/v1/";
  }
}
