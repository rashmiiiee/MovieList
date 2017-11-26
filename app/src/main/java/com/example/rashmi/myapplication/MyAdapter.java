package com.example.rashmi.myapplication;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QCS2015 on 25-11-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<MovieItem> mList;
    private Context mcontext;
    private LayoutInflater inflater;
    ImageLoader imageLoader;
    ArrayList<Image> images;

    public MyAdapter(List<MovieItem> mList, Context mcontext) {
        this.mList = mList;
        this.mcontext = mcontext;
        inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        MovieItem provider = mList.get(position);
        holder._id.setText(provider.getDp_id());
        holder.imdb_id.setText(provider.getDp_imdb());
        holder.title.setText(provider.getDp_title());
        holder.year.setText(provider.getDp_year());
        holder.synopsis.setText(provider.getDp_synopsis());
        holder.runtime.setText(provider.getDp_runtime());
        holder.networkImageView.setImageUrl(provider.getPosterUrl(),Volley_Singleton.getInstance(mcontext).getImageLoader());
      //  Picasso.with(mcontext).load(provider.getDp_image()).into(holder.networkImageView);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }




public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView _id,imdb_id,title,year,synopsis,runtime;
    private NetworkImageView networkImageView;

    public MyViewHolder(View itemView) {
        super(itemView);

        _id= (TextView)itemView.findViewById(R.id.tv_id);
        imdb_id=(TextView)itemView.findViewById(R.id.tv_imdb);
        title= (TextView)itemView.findViewById(R.id.tv_title);
        year=(TextView)itemView.findViewById(R.id.tv_year);
        synopsis=(TextView)itemView.findViewById(R.id.tv_synopsis);
        runtime=(TextView)itemView.findViewById(R.id.tv_runtime);
        networkImageView=(NetworkImageView)itemView.findViewById(R.id.iv_image);


    }
}
}
