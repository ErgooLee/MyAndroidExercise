package com.example.test.window

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import com.example.test.R
import kotlinx.android.synthetic.main.activity_window.*

class TestWindow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //test0 home 可保持
        window.addFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_window)


        //test1 状态栏 导航栏变暗效果、点击后出现，出现后变暗消失
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE

        //test2 home 返回不可保持，点击界面元素退出全屏，
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //test3 home 返回不可保持，点击界面元素退出全屏，
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

//        //test4 home 返回不可保持，点击界面元素退出全屏，
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//
//        //test5 home 返回不可保持，点击界面元素退出全屏，
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
//                View.SYSTEM_UI_FLAG_IMMERSIVE

//        //test6 home 返回不可保持，点击界面元素退出全屏，
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Handler().postDelayed({
//            Toast.makeText(this@TestWindow, "test", Toast.LENGTH_SHORT).show()
            root.addView(WebView(this))
        }, 3000)

    }

}
