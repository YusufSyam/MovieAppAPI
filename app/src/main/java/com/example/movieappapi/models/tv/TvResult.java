package com.example.movieappapi.models.tv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResult {
    @SerializedName("results")
    @Expose
    List<TvShow> tvShowList;

    public List<TvShow> getTvShowList() {
        return tvShowList;
    }
}
