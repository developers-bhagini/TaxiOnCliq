package com.idroid.app.taxioncliq.network;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleTon {
    private static SingleTon mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private SingleTon(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }


    public static synchronized SingleTon getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingleTon(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
