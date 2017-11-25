package com.example.rashmi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url="https://tv-v2.api-fetch.website/movies/1";
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    List<DataProvider> data_list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue= Volley_Singleton.getInstance(this).getRequestQueue();
        requestQueue.start();
        JsonArrayRequest request= new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject obj= response.getJSONObject(i);
                        DataProvider provider= new DataProvider(obj.getString("_id"),obj.getString("imdb_id"),obj.getString("title"),obj.getString("year"),obj.getString("synopsis"),obj.getString("runtime"));
                        data_list.add(provider);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    myAdapter=new MyAdapter(data_list,getApplicationContext());
                    recyclerView.setAdapter(myAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("error");

            }
        });
        requestQueue.add(request);
    }
}
