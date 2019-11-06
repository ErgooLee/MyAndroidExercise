package com.example.test.dagger;

import android.content.Context;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;

public class LoginPasswordFragment extends Fragment {

    // Fields that need to be injected by the login graph
    @Inject
    LoginViewModel loginViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((LoginActivity) getActivity()).loginComponent.inject(this);

        // Now you can access loginViewModel here and onCreateView too
        // (shared instance with the Activity and the other Fragment)
    }
}
