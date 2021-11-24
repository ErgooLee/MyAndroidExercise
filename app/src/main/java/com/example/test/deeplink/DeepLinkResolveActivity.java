package com.example.test.deeplink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.test.R;

public class DeepLinkResolveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link_resolve);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("chao", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("chao", "onStop");
    }
}