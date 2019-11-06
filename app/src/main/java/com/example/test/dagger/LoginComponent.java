package com.example.test.dagger;

import dagger.Subcomponent;

// Classes annotated with @ActivityScope are scoped to the graph and the same
// instance of that type is provided every time the type is requested.

@ActivityScope
@Subcomponent
public interface LoginComponent {
    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        LoginComponent create();
    }

    // All LoginActivity, LoginUsernameFragment and LoginPasswordFragment
    // request injection from LoginComponent. The graph needs to satisfy
    // all the dependencies of the fields those classes are injecting
    void inject(LoginActivity loginActivity);

    void inject(LoginUsernameFragment loginUsernameFragment);

    void inject(LoginPasswordFragment loginPasswordFragment);
}
