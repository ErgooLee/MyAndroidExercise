package com.example.test.dagger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R
import kotlinx.android.synthetic.main.activity_test_dagger.*

class testDagger : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dagger)
        test1.setOnClickListener {
            val graph = DaggerApplicationGraph.create()
            val userRepository = graph.userRepository()
            val userRepository2 = graph.userRepository()
            assert(userRepository == userRepository2)
        }
    }
}
