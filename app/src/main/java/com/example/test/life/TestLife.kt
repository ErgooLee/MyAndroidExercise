package com.example.test.life

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test.DialogActivity
import com.example.test.R

class TestLife : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_life)
        findViewById<Button>(R.id.testLife).setOnClickListener {
            Intent(this@TestLife, DialogActivity::class.java).apply {
                finish()

                startActivity(this)
            }
        }
    }
}