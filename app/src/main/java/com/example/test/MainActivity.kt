package com.example.test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.anim.TestAnim
import com.example.test.annotation.TestAnnotation
import com.example.test.base.TestAntiHiJack
import com.example.test.base.TestFlag
import com.example.test.dagger.testDagger
import com.example.test.dialog.TestDialog
import com.example.test.event.testEvent
import com.example.test.executors.TestThread
import com.example.test.file.TestFile
import com.example.test.kotlin.testCoroutine
import com.example.test.life.TestLife
import com.example.test.network.NetStatusReceiver
import com.example.test.retrofit.TestRetrofit
import com.example.test.theme.TestTheme
import com.example.test.view.TestView
import com.example.test.view.TestView2
import com.example.test.window.TestWindow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NetStatusReceiver.register(this)

        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = TestAdapter(this)

    }

    private class TestViewHolder(itemView: View, activity: Activity) : RecyclerView.ViewHolder(itemView) {

        private var activityClass: Class<out Activity>? = null

        init {
            itemView.setOnClickListener {
                if (activityClass == TestThread::class.java) {
                    TestThread.start(it.context)
                } else {
                    it.context.startActivity(Intent(activity, activityClass))
//                    activity.finish()

                }

            }
        }

        val textTv: TextView = itemView.findViewById(android.R.id.text1)
        fun bindData(activityClass: Class<out Activity>) {
            this.activityClass = activityClass
            textTv.text = activityClass.simpleName
        }
    }

    private class TestAdapter(val activity: Activity) : RecyclerView.Adapter<TestViewHolder>() {
        private val testList = listOf<Class<out Activity>>(
            TestView::class.java,
            TestView2::class.java,
            TestWindow::class.java,
            TestTheme::class.java,
            TestDialog::class.java,
            TestAnim::class.java,
            TestThread::class.java,
            TestFlag::class.java,
            testDagger::class.java,
            TestAntiHiJack::class.java,
            testCoroutine::class.java,
            TestRetrofit::class.java,
            testEvent::class.java,
            TestAnnotation::class.java,
            TestFile::class.java,
            TestLife::class.java
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return TestViewHolder(view, activity)
        }

        override fun getItemCount(): Int {
            return testList.size
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
            holder.bindData(testList[position])
        }
    }


    override fun onPause() {
        super.onPause()
        Log.d("chao", "main pause")
    }


    override fun onStop() {
        super.onStop()
        Log.d("chao", "main onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d("chao", "main onResume: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("chao", "main onRestart: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("chao", "main onStart: ")
    }

}
