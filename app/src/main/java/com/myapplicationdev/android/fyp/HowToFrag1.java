package com.myapplicationdev.android.fyp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;


public class HowToFrag1 extends Fragment {

    ImageView ivImage;
    VideoView videoView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_how_to_frag1, container, false);
        // Inflate the layout for this fragment

//        ivImage = v.findViewById(R.id.ivHowTo1);
//        ivImage.setImageResource(R.drawable.howto1);

        videoView = v.findViewById(R.id.videoView);

        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.quizgame;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();
        return v;
    }
}