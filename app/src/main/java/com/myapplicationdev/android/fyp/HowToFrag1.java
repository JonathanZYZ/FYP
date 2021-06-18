package com.myapplicationdev.android.fyp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


public class HowToFrag1 extends Fragment {

    ImageView ivImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_how_to_frag1, container, false);
        // Inflate the layout for this fragment

        ivImage = v.findViewById(R.id.ivHowTo1);
        //ivImage.setImageResource(R.drawable.);
        return v;
    }
}