package com.example.test.executors

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test.R
import java.net.URL
import java.util.concurrent.TimeUnit

class TestThread : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_thread)
        for (i in 0..5) {
            NagaExecutors.io().execute {
                Log.d("chao_test_thread", "task $i start in thread ${Thread.currentThread().name}")
                TimeUnit.SECONDS.sleep(3)
                Log.d("chao_test_thread", "task $i done in thread ${Thread.currentThread().name}")
            }
        }




        val id = Uri.parse("market://details?id= com.cootek.crazyreader").getQueryParameter("id")
        Log.d("chao_test_thread", "id=$id")



        val option2 = intent.getParcelableExtra<BrowserOption>("option")
        Log.d("chao", " intent to string $option2")
    }


    companion object {

        fun start(context: Context) {
            val intent = Intent(context, TestThread::class.java)
            val option = BrowserOption(1, null, "2")
            intent.putExtra("option", option)
            context.startActivity(intent)
        }
    }
}
