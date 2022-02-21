package com.example.safproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class PlayVideo extends AppCompatActivity {
    private VideoView videoView;
    private TextView textView;
    static int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        videoView = (VideoView) findViewById(R.id.vidView);
        textView = (TextView) findViewById(R.id.textView);
        //Uri uri = Uri.fromFile(Constant.allMediaList.get(position));
        //Glide.with(mContext).load(uri).thumbnail(0.1f).into(((FileLayoutHolder)holder).thumbnail);
        //((RecyclerViewAdapter.FileLayoutHolder)holder).videoView.setVideoURI(uri);
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();

        //Uri uri;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            pos = extras.getInt("pos");
            Uri uri = Uri.fromFile(Constant.allMediaList.get(pos));
            retriever.setDataSource(videoView.getContext(), uri);
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            metadataRetriever.setDataSource(videoView.getContext(), uri);
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR);
            String txt = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            videoView.setVideoURI(uri);
            textView.setText(txt);
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(new MediaController(videoView.getContext()));
            mediaController.setAnchorView(videoView);
            mediaController.setPrevNextListeners(v -> {

                videoView.seekTo(videoView.getCurrentPosition() + 5000);

                // v is mc
                // code for next
            }, v -> {

                videoView.seekTo(videoView.getCurrentPosition() - 5000);
                // v is mc
                // code for previous
            });


            videoView.start();

        }
    }
}