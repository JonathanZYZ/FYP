package com.myapplicationdev.android.fyp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class HowToFrag2 extends Fragment {

ImageView ivImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_how_to_frag2, container, false);

        ivImage = v.findViewById(R.id.ivHowTo2);
        //ivImage.setImageResource();
        return v;
    }
}