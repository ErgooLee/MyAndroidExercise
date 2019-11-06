package com.example.test.pattern.mvp.contract

import com.example.test.pattern.mvp.bean.UserInfo
import com.example.test.pattern.mvp.model.SampleModel
import com.example.test.pattern.mvp.presenter.BasePresenter

class SampleContract {

    class SamplePresenter(private val sampleView: SampleView) : BasePresenter {

        var destroyed = false
        lateinit var sampleModel: SampleModel

        override fun onDestroy() {
            destroyed = true
        }

        fun loadData(uid: String) {
            sampleModel = SampleModel()
            sampleModel.getUserInfo(uid) {
                if (!destroyed) {
                    sampleView.setDataToView(it)
                }
            }

        }

    }

    interface SampleView {
        fun setDataToView(userInfo: UserInfo)
    }

}