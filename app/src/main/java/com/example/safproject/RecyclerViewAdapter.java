package com.example.safproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    int pos;
    RecyclerView.ViewHolder h;

    RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.files_list, parent, false);
        return new FileLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       ((FileLayoutHolder) holder).videoTitle.setText(Constant.allMediaList.get(position).getName());
        //we will load thumbnail using glid library
        Uri uri = Uri.fromFile(Constant.allMediaList.get(position));
        //Glide.with(mContext).load(uri).thumbnail(0.1f).into(((FileLayoutHolder)holder).imageView);
        Glide.with(mContext).load(uri)
                .apply(new RequestOptions()
                        .fitCenter()
                        .format(DecodeFormat.PREFER_ARGB_8888)
                        .override(Target.SIZE_ORIGINAL))
                .into(((FileLayoutHolder)holder).imageView);
        //Glide.with(mContext).load(uri).thumbnail(RequestBuilder<URI>).into(((FileLayoutHolder)holder).imageView);
       // ((FileLayoutHolder) holder).videoView.setVideoURI(uri);




    }

    @Override
    public int getItemCount() {
        return Constant.allMediaList.size();
    }

    class FileLayoutHolder extends RecyclerView.ViewHolder {
        //ImageView thumbnail;
        ImageView imageView;
        TextView videoTitle;
        ImageView ic_more_btn;

        public FileLayoutHolder(@NonNull View itemView) {
            super(itemView);
            //thumbnail = itemView.findViewById(R.id.thumbnail);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(v -> {
               // pos = getAdapterPosition();
               // int positionNew = 0;
                Intent intent = new Intent(v.getContext(), PlayVideo.class);

                //positionNew = pos;
                intent.putExtra("pos", getAdapterPosition());
                v.getContext().startActivity(intent);
            });
            videoTitle = itemView.findViewById(R.id.videotitle);
            ic_more_btn = itemView.findViewById(R.id.ic_more_btn);
        }
    }
}

