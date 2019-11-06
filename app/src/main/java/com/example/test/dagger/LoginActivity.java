package com.example.test.dagger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test.R;
import com.example.test.TestApplication;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    LoginComponent loginComponent;

    // You want Dagger to provide an instance of LoginViewModel from the graph
    @Inject
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Make Dagger instantiate @Inject fields in LoginActivity
        loginComponent = ((TestApplication) getApplicationContext()).appComponent.loginComponent().create();
        loginComponent.inject(this);

        // Now loginViewModel is available

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

}
