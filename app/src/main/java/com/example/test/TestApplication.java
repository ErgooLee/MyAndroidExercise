package com.example.test;

import android.app.Application;

import com.example.test.dagger.ApplicationComponent;
import com.example.test.dagger.DaggerApplicationComponent;

public class TestApplication extends Application {

    public ApplicationComponent appComponent = DaggerApplicationComponent.create();

    @Override
    public void onCreate() {
        super.onCreate();


    }
}
