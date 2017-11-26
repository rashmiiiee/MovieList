package com.example.rashmi.myapplication;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by QCS2015 on 25-11-2017.
 */

public class MovieItem {
    String dp_id,dp_imdb,dp_title,dp_year,dp_synopsis,dp_runtime;
    private String posterUrl;

    public String getPosterUrl() {
        return posterUrl;
    }

    public MovieItem(String dp_id, String dp_imdb, String dp_title, String dp_year, String dp_synopsis, String dp_runtime, String pPosterUrl) {
        this.dp_id = dp_id;
        this.dp_imdb = dp_imdb;
        this.dp_title = dp_title;
        this.dp_year = dp_year;
        this.dp_synopsis = dp_synopsis;
        this.dp_runtime = dp_runtime;
        this.posterUrl = pPosterUrl;
    }


    public String getDp_id() {
        return dp_id;
    }

    public String getDp_imdb() {
        return dp_imdb;
    }

    public String getDp_title() {
        return dp_title;
    }

    public String getDp_year() {
        return dp_year;
    }

    public String getDp_synopsis() {
        return dp_synopsis;
    }


    public String getDp_runtime() {
        return dp_runtime;
    }
}
