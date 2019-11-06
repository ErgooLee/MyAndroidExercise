package com.example.test.pattern.mvp.model;

import com.example.test.pattern.mvp.bean.UserInfo;
import com.example.test.pattern.mvp.callback.Callback1;
import com.example.test.pattern.mvp.http.HttpUtil;

public class SampleModel implements BaseModel{

    public void getUserInfo(String uid, Callback1<UserInfo> callback1) {
        UserInfo userInfo = new HttpUtil<UserInfo>().get(uid);
        if (callback1 != null) {
            callback1.onCallback(userInfo);
        }
    }

}
