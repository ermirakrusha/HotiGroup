package com.example.ermira.hotigroup;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MySingleton extends Application{
//    private static MySingleton mInstance;
//    private RequestQueue requestQueue;
//    private Context context;
//    private ImageLoader mImageLoader;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mInstance = this;
//    }
//
//    private MySingleton(Context context){
//        this.context = context;
//        requestQueue = getRequestQueue();
//    }
//
//
//    public RequestQueue getRequestQueue(){
//        if (requestQueue == null){
//            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
//        }
//        return requestQueue;
//    }
//
//    public static synchronized MySingleton getInstance(Context context){
//        if (mInstance == null){
//            mInstance = new MySingleton(context);
//        }
//        return mInstance;
//    }
//
//    public ImageLoader getImageLoader() {
//        getRequestQueue();
//        if (mImageLoader == null) {
//            mImageLoader = new ImageLoader(this.requestQueue,
//                    new LruBitmapCache());
//        }
//        return this.mImageLoader;
//    }
//
//
//    public <T>void addToRequestque(Request<T> request){
//        requestQueue.add(request);
//    }
//}

    public static final String TAG = MySingleton.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static MySingleton mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MySingleton getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}