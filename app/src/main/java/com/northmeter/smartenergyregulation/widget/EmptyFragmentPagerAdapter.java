package com.northmeter.smartenergyregulation.widget;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */
public class EmptyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[]  titles;
    public EmptyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[]  titles ) {
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
