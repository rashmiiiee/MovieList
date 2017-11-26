package com.example.rashmi.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
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
    List<MovieItem> movieList =new ArrayList<>();
    MovieItem provider;
    ImageLoader imageLoader;
    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestQueue = Volley_Singleton.getInstance(this).getRequestQueue();
        requestQueue.start();
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        JSONObject jsonObject = obj.getJSONObject("images");
                        String posterUrl = jsonObject.getString("poster");
                        MovieItem provider = new MovieItem(obj.getString("_id"),
                                obj.getString("imdb_id"),
                                obj.getString("title"),
                                obj.getString("year"),
                                obj.getString("synopsis"),
                                obj.getString("runtime"),posterUrl);
                        movieList.add(provider);
                        Log.d(TAG, "Movie Item " + provider.getDp_title());
                    } catch (JSONException e) {
                       Log.e(TAG,e.toString());
                    }
                    myAdapter = new MyAdapter(movieList, getApplicationContext());
                    recyclerView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    imageLoader=Volley_Singleton.getInstance(getApplicationContext()).getImageLoader();
                }
                Log.d(TAG, "Totla movies" + movieList.size());
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.toString());

            }
        });
        requestQueue.add(request);
    }
    }

