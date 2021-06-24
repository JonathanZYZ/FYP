package com.myapplicationdev.android.fyp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {
    ArrayList<Fragment> fragments;

    public FragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> al) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragments = al;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
