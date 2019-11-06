package com.example.test.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.utils.Files
import com.example.test.utils.Logs
import com.example.test.utils.Packages
import kotlinx.android.synthetic.main.activity_test_anti_hi_jack.*
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class TestAntiHiJack : AppCompatActivity() {


    private val testList = listOf(
        "baidu.apk",
        "zuoyebang.apk"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_anti_hi_jack)
        init()

        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = TestAdapter()
        mockedCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AntiHiJackUtils.setDynamicProxy(1)
                mockedCb.isEnabled = false
                mockedCb2.isEnabled = false
            }
        }

        mockedCb2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AntiHiJackUtils.setDynamicProxy(2)
                mockedCb.isEnabled = false
                mockedCb2.isEnabled = false
            }
        }

        Logs.d("source dir is " + Packages.getSourceDir(this, "com.baidu.browser.apps"))

    }

    private fun init() {
        Thread(Runnable {
            testList.forEach {
                val apkFile = buildFile(it)
                if (apkFile.exists()) {
                    Logs.d("$it exists")
                } else {
                    val inputStream: InputStream = assets.open(it)
                    Files.copyFileUsingStreams(inputStream, FileOutputStream(apkFile))
                    Logs.d("$it exists")
                }
            }
        }).start()
    }

    private fun buildFile(it: String) =
        File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), it)


    private inner class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var apkFileName: String? = null

        init {
            itemView.setOnClickListener {
                val file = buildFile (apkFileName!!)
                if (file.exists()) {
                    val uri = Files.buildFileUri(this@TestAntiHiJack, file)
                    val intent = Packages.getInstallApkIntent(uri, this@TestAntiHiJack)
                    startActivity(intent)
                    Logs.d("start install $apkFileName")
                } else {
                    Logs.d("start install failed file not exists")
                }
            }
        }

        val textTv: TextView = itemView.findViewById(android.R.id.text1)
        @SuppressLint("SetTextI18n")
        fun bindData(apkFileName: String) {
            this.apkFileName = apkFileName
            textTv.text = "install $apkFileName"
        }
    }

    private inner class TestAdapter : RecyclerView.Adapter<TestViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return TestViewHolder(view)
        }

        override fun getItemCount(): Int {
            return testList.size
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
            holder.bindData(testList[position])
        }
    }


}
