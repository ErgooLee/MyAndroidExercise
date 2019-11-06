package com.example.test.base

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R
import kotlinx.android.synthetic.main.activity_test_flag.*

class TestFlag : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_flag)
        testFlag.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"))
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(intent)


        }
    }
}
