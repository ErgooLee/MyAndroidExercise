package com.example.test;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.dagger.ApplicationComponent;
import com.example.test.dagger.DaggerApplicationComponent;

public class TestApplication extends Application {

    public ApplicationComponent appComponent = DaggerApplicationComponent.create();

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                Log.d("chao", "app created");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Log.d("chao", "app Started");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Log.d("chao", "app onResume");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Log.d("chao", "app onPause");
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Log.d("chao", "app onStop");
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
}
