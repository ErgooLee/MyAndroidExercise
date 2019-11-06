package com.example.test.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.binding.Binding;
import com.example.bindingannotation.BindView;
import com.example.test.R;

public class TestAnnotation extends AppCompatActivity {

    @BindView(R.id.helloAnnotationTv)
    public TextView helloAnnotationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_annotation);
        Binding.bind(this);

        if (helloAnnotationTv != null) {
            helloAnnotationTv.setText("hello annotation");
        }
    }
}