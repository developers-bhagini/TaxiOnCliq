package com.idroid.app.taxioncliq.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Aron on 19-11-2016.
 */

public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getSimpleName();

    public static boolean isNetworkConnected(Context aContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) aContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
