package com.example.rashmi.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.net.URL;

/**
 * Created by QCS2015 on 25-11-2017.
 */

public class Volley_Singleton {
    private RequestQueue requestQueue;
    private static Context context;
    private ImageLoader imageLoader;
    private static Volley_Singleton mInstance;

    private Volley_Singleton(Context mcontext){
        this.context=mcontext;
        requestQueue=getRequestQueue();
        requestQueue=getRequestQueue();
        imageLoader= new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap>
                    cache= new LruCache<String, Bitmap>(10);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);

            }
        });


    }
    public static synchronized Volley_Singleton getInstance(Context context){
        if(mInstance==null){
            mInstance= new Volley_Singleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext(), new HurlStack());
        }
        return requestQueue;
    }
    public<T> void addToRequestQueue(Request<T> request){
        requestQueue.add(request);
    }

   public ImageLoader getImageLoader(){
        getRequestQueue();
        if(imageLoader==null){
            imageLoader=new ImageLoader(this.requestQueue,new Bitmap_Cache(10));
        }
       return this.imageLoader;
   }
}

