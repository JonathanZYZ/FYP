package com.myapplicationdev.android.fyp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class HowToPlayActivity extends AppCompatActivity {
    ArrayList<Fragment> al;
    FragmentPagerAdapter adapter;
    ViewPager vPager;
    VideoView videoView;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        videoView = findViewById(R.id.videoView);
        btnHome = findViewById(R.id.btnHome);
//        vPager = findViewById(R.id.viewpager1);
//
//        FragmentManager fm = getSupportFragmentManager();
//        al = new ArrayList<>();
//        al.add(new HowToFrag1());
//        al.add(new HowToFrag2());
//
//        adapter = new FragmentPagerAdapter(fm, al);
//        vPager.setAdapter(adapter);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment f1 = new HowToFrag1();
        ft.replace(R.id.frame1, f1);

        ft.commit();
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}