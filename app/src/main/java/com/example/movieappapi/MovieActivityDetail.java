package com.example.movieappapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movieappapi.models.movie.MovieDetail;
import com.example.movieappapi.models.tv.TvShowDetail;
import com.example.movieappapi.networks.Consts;
import com.example.movieappapi.networks.GetRetrofit;
import com.example.movieappapi.networks.MovieService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.KeyCharacterMap.load;

public class MovieActivityDetail extends AppCompatActivity {

    ImageView ivBackdrop;
    TextView tvTitle, tvGenre, tvOverview, tvReleaseDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ivBackdrop = findViewById(R.id.iv_backdrop);
        tvTitle = findViewById(R.id.tv_title);
        tvGenre = findViewById(R.id.tv_genre);
        tvOverview= findViewById(R.id.tvOverview);
        tvReleaseDate= findViewById(R.id.tv_release_date);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Integer tvId = getIntent().getIntExtra("TV ID", 0);
        load(tvId);
    }
    private void load(Integer id) {
        MovieService service = GetRetrofit.getInstance();

        Map<String, String> params = new HashMap<>();
        params.put("api_key", Consts.APIKEY);
        params.put("language", Consts.LANGUAGE);
        Call<MovieDetail> call = service.movieDetail(id, params);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful() && response.body() != null){
                    MovieDetail movieDetail = response.body();
                    tvTitle.setText(movieDetail.getTitle());
                    String getGenre = "";
                    for (int i = 0; i < movieDetail.getGenres().size(); i++){
                        getGenre += movieDetail.getGenres().get(i).getName() + (i==movieDetail.getGenres().size()-1 ? "." : ", ");
                    }
                    tvGenre.setText(getGenre);
                    tvOverview.setText(movieDetail.getOverview());
                    tvReleaseDate.setText(movieDetail.getReleaseDate());
                    String uri = Consts.IMAGEBASEURL + movieDetail.getBackdropImage();
                    Glide.with(MovieActivityDetail.this).load(uri).into(ivBackdrop);

                    getSupportActionBar().setTitle(tvTitle.getText());
                }else {
                    Log.d(Consts.APIERROR, "error");

                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.d(Consts.APIERROR, "error");
            }
        });

    }
}