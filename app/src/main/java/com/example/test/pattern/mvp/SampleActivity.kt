package com.example.test.pattern.mvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.R
import com.example.test.pattern.mvp.bean.UserInfo
import com.example.test.pattern.mvp.contract.SampleContract

class SampleActivity : AppCompatActivity(), SampleContract.SampleView {

    lateinit var mSamplePresenter: SampleContract.SamplePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_textview)
        mSamplePresenter = SampleContract.SamplePresenter(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        mSamplePresenter.onDestroy()
    }

    private fun getUserInfo(uid: String) {
        mSamplePresenter.loadData(uid)
    }

    override fun setDataToView(userInfo: UserInfo) {
        //update view
    }
}
