package com.example.moviesandsongsapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.time.Instant;
import java.util.List;

public class MediaRecyclerViewAdapter extends RecyclerView.Adapter<MediaRecyclerViewAdapter.ViewHolder> {

    private List<MediaItem> mediaList;
    private Context context;

    public MediaRecyclerViewAdapter(Context context, List<MediaItem> mediaList) {
        this.context = context;
        this.mediaList = mediaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MediaItem mediaItem = mediaList.get(position);


        holder.titleTextView.setText(mediaItem.getTitle());
        holder.descriptionTextView.setText(mediaItem.getDescription());

        Glide.with(context).load(mediaItem.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
