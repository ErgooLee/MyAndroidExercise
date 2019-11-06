package com.example.test.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R
import kotlinx.android.synthetic.main.activity_test_event.*

class testEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_event)
        eventView.setOnClickListener {

        }
    }
}
