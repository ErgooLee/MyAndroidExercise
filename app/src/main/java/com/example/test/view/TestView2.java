package com.example.test.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test.R;

public class TestView2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_2);

        findViewById(R.id.btn)
                .animate()
                .translationY(500f)
                .setDuration(50_000);
    }
}