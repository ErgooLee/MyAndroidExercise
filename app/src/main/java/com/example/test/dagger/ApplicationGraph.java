package com.example.test.dagger;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface ApplicationGraph {
    UserRepository userRepository();
}
