package com.example.test.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R

class testCoroutine : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coroutine)
//        Coroutine().postItem()
//        Coroutine().testRunBlocking()
        Coroutine().testRunBlocking2()
    }
}
