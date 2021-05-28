package com.example.movieappapi.adapters.movie;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieappapi.R;
import com.example.movieappapi.helper.OnItemClickListener;
import com.example.movieappapi.models.movie.NowPlaying;
import com.example.movieappapi.networks.Consts;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {

    private List<NowPlaying> nowPlayingList;

    private OnItemClickListener<Integer> clickListener;

    public void setClickListener(OnItemClickListener<Integer> clickListener){
        this.clickListener = clickListener;
    }

    public void setNowPlayingList(List<NowPlaying> nowPlayingList){
        this.nowPlayingList = nowPlayingList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.onBind(nowPlayingList.get(position));
    }

    @Override
    public int getItemCount() {
        return nowPlayingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        NowPlaying nowPlaying;
        TextView tvTitle, tvOverview;
        ImageView photo;
        Integer tvId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = itemView.findViewById(R.id.tvJudul);
            photo = itemView.findViewById(R.id.photo);

        }
        public void onBind(NowPlaying nowPlaying){
            String uri = Consts.IMAGEBASEURL + nowPlaying.getPosterImage();
            this.nowPlaying = nowPlaying;
            tvId = nowPlaying.getId();
            tvTitle.setText(nowPlaying.getTitle());
            Glide.with(this.itemView.getContext()).load(uri).into(photo);
        }

        @Override
        public void onClick(View view) {
            Log.d("DEBUG", tvId.toString());
            clickListener.onClick(tvId);
        }
    }
}
