package com.myapplicationdev.android.fyp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class HowToPlayActivity extends AppCompatActivity {
    ArrayList<Fragment> al;
    FragmentPagerAdapter adapter;
    ViewPager vPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        vPager = findViewById(R.id.viewpager1);

        FragmentManager fm = getSupportFragmentManager();
        al = new ArrayList<Fragment>();
        al.add(new HowToFrag1());
        al.add(new HowToFrag2());

        adapter = new FragmentPagerAdapter(fm, al);
        vPager.setAdapter(adapter);
    }
}