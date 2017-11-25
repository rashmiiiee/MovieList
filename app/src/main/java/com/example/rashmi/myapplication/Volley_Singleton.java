package com.example.rashmi.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

/**
 * Created by QCS2015 on 25-11-2017.
 */

public class Volley_Singleton {
    private RequestQueue requestQueue;
    private static Context context;
    private static Volley_Singleton mInstance;

    private Volley_Singleton(Context mcontext){
        this.context=mcontext;
        requestQueue=getRequestQueue();

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

}

