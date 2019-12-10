package com.everdune.competitive;

import com.everdune.competitive.di.dagger.ApplicationComponent;
import com.everdune.competitive.di.dagger.DaggerApplicationComponent;

public class Application extends android.app.Application {
  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    applicationComponent = DaggerApplicationComponent.builder()
      .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }}
