package com.example.test.dagger;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, SubcomponentsModule.class})

public interface ApplicationComponent {
    // This function exposes the LoginComponent Factory out of the graph so consumers
    // can use it to obtain new instances of LoginComponent
    LoginComponent.Factory loginComponent();
}
