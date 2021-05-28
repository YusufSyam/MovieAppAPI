package com.example.movieappapi.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRetrofit {
    private static MovieService service;
    public static MovieService getInstance() {
        if(service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Consts.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(MovieService.class);
        }

        return service;
    }
}
