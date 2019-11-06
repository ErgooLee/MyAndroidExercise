package com.example.test.executors;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class BrowserOption implements Parcelable {

    public static final Creator<BrowserOption> CREATOR = new Creator<BrowserOption>() {

        @Override
        public BrowserOption createFromParcel(Parcel source) {
            return new BrowserOption(source);
        }


        @Override
        public BrowserOption[] newArray(int size) {
            return new BrowserOption[size];
        }
    };


    public final
    int downloadInquiry;
    public final
    String url;
    public final
    String url2;


    public BrowserOption(int downloadInquiry, String url, String url2) {

        this.downloadInquiry = downloadInquiry;
        this.url = url;
        this.url2 = url2;

    }

    public BrowserOption(Parcel source) {
        Log.d("chao", "deparse");
        this.downloadInquiry = source.readInt();
        this.url = source.readString();
        this.url2 = source.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d("chao", "parse");
        dest.writeInt(downloadInquiry);
        dest.writeString(url);
        dest.writeString(url2);

    }

    @Override
    public String toString() {
        return "BrowserOption{" +
                "downloadInquiry=" + downloadInquiry +
                ", url='" + url + '\'' +
                ", url2='" + url2 + '\'' +
                '}';
    }
}
