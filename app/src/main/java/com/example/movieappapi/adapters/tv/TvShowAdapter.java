package com.example.movieappapi.adapters.tv;

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
import com.example.movieappapi.models.tv.TvShow;
import com.example.movieappapi.networks.Consts;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {
    private List<TvShow> tvShowList;

    private OnItemClickListener<Integer> clickListener;

    public void setClickListener(OnItemClickListener<Integer> clickListener){
        this.clickListener = clickListener;
    }

    public void setTvShowList(List<TvShow> tvShowList){
        this.tvShowList = tvShowList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.ViewHolder holder, int position) {

        holder.onBind(tvShowList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TvShow tvShow;
        TextView tvTitle;
        ImageView photo;
        Integer tvId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = itemView.findViewById(R.id.tvJudul);
            photo = itemView.findViewById(R.id.photo);
        }

        public void onBind(TvShow tvShow){
            String uri = Consts.IMAGEBASEURL + tvShow.getPosterImage();
            this.tvShow = tvShow;
            tvId = tvShow.getId();
            tvTitle.setText(tvShow.getName());
            Glide.with(this.itemView.getContext()).load(uri).into(photo);
        }

        @Override
        public void onClick(View view) {
            Log.d("DEBUG", tvId.toString());
            clickListener.onClick(tvId);
        }
    }
}
