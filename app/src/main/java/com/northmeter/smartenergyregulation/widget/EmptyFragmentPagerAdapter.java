package com.northmeter.smartenergyregulation.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
