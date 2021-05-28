package com.example.movieappapi.models.tv;

import com.google.gson.annotations.SerializedName;

public class TvShow {
    @SerializedName("poster_path")
    private String posterImage;

    @SerializedName("original_name")
    private String name;

    int id;

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
