package com.example.test.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by chao.li on 2019-08-08.
 */
public final class NetStatusReceiver {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void registerAfter21(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkRequest request = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();
        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(request, new NetworkCallbackImpl());
        }
    }

    public static void registerBefore21(@NonNull Context context) {

    }

    public static void register(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            registerAfter21(context);
        }
    }





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(@NonNull Network network) {
            Log.d("chao", "onAvailable " + network);
        }

        @Override
        public void onLost(@NonNull Network network) {
            Log.d("chao", "onLost " + network);
        }

        @Override
        public void onUnavailable() {
            Log.d("chao", "onUnavailable ");
        }


    }

}
