package com.northmeter.smartenergyregulation.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
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
