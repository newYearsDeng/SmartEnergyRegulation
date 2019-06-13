package com.northmeter.smartenergyregulation.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.northmeter.smartenergyregulation.R;

/**
 * Created by dyd on 2018/11/29.
 */

public class NavagationitemSelecteImpl implements NavigationView.OnNavigationItemSelectedListener {
    private final DrawerLayout mDrawerlayout;
    private final Activity mActivity;

    public NavagationitemSelecteImpl(Activity activity, DrawerLayout drawerlayout) {
        mActivity = activity;
        mDrawerlayout = drawerlayout;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_camera:
                Intent intent = new Intent(mActivity,HomeActivity.class);
                mActivity.startActivity(intent);
                break;

        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
