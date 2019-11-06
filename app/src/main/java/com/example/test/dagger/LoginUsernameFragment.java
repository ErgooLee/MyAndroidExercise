package com.example.test.dagger;

import android.content.Context;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;

public class LoginUsernameFragment extends Fragment {

    // Fields that need to be injected by the login graph
    @Inject
    LoginViewModel loginViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Obtaining the login graph from LoginActivity and instantiate
        // the @Inject fields with objects from the graph
        ((LoginActivity) getActivity()).loginComponent.inject(this);

        // Now you can access loginViewModel here and onCreateView too
        // (shared instance with the Activity and the other Fragment)
    }
}