package com.example.test.anim

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R
import kotlinx.android.synthetic.main.activity_test_anim.*

class TestAnim : AppCompatActivity() {

    lateinit var frameAnimDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_anim)
        frameAnimDrawable = frameImage.drawable as AnimationDrawable
    }

    override fun onStart() {
        super.onStart()
        frameAnimDrawable.start()
    }

    override fun onStop() {
        super.onStop()
        frameAnimDrawable.stop()
    }

}
