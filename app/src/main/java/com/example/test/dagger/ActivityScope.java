package com.example.test.dagger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

// Definition of a custom scope called ActivityScope
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
