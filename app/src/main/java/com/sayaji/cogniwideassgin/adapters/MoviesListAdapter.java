package com.sayaji.cogniwideassgin.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sayaji.cogniwideassgin.R;
import com.sayaji.cogniwideassgin.commonutils.AppConstants;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MyViewHolder>{

    private Context mContext;
    private ScrollAtLastPosition scrollAtLastPosition;

    public MoviesListAdapter(Context mContext, ScrollAtLastPosition scrollAtLastPosition) {
        this.mContext = mContext;
        this.scrollAtLastPosition = scrollAtLastPosition;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MoviesListAdapter.MyViewHolder viewHolder = new MoviesListAdapter.MyViewHolder(itemView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Glide.with(mContext).load(AppConstants.IMAGE_BASE_URL+AppConstants.movieItems.get(position).getPoster_path())
                .placeholder(R.drawable.loading)
                .into(holder.moviePhoto);
        holder.movieName.setText(AppConstants.movieItems.get(position).getTitle());
        if (position == (AppConstants.movieItems.size()-1)){
            if (AppConstants.pageCount < 10) {
                scrollAtLastPosition.scrollAtLastPosition();
            }
        }
    }

    @Override
    public int getItemCount() {
        return AppConstants.movieItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView moviePhoto;
        TextView movieName;
        public MyViewHolder(View view) {
            super(view);
            moviePhoto = (ImageView)view.findViewById(R.id.movie_photo);
            movieName = (TextView)view.findViewById(R.id.txt_movie_name);
        }
        @Override
        public void onClick(View v) {

        }
    }

    public interface ScrollAtLastPosition {
        void scrollAtLastPosition();
    }
}