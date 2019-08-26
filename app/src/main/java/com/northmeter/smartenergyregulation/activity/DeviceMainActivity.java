package com.northmeter.smartenergyregulation.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.fragment.Fragment_Socket_Chart;
import com.northmeter.smartenergyregulation.fragment.Fragment_Socket_Info;
import com.northmeter.smartenergyregulation.widget.EmptyFragmentPagerAdapter;
import com.northmeter.smartenergyregulation.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/5/10.
 */

public class DeviceMainActivity extends BaseActivity {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_empty)
    TabLayout tlEmpty;
    @BindView(R.id.vp_empty)
    NoScrollViewPager vpEmpty;

    private List<Fragment> fragments = new ArrayList<>();
    private String[] mTitles;
    private EmptyFragmentPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_device;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initIntentData() {
        super.initIntentData();
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tvToolbarTitle.setText("智能插座");
    }

    @Override
    public void initData() {
        super.initData();
        fragments.add(Fragment_Socket_Info.newInstance(0));
        fragments.add(Fragment_Socket_Chart.newInstance(0));
        mTitles = new String[]{"设备状态", "视图分析"};
        adapter = new EmptyFragmentPagerAdapter(getSupportFragmentManager(), fragments, mTitles);
        vpEmpty.setAdapter(adapter);
        tlEmpty.setupWithViewPager(vpEmpty);
        vpEmpty.setOffscreenPageLimit(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.btn_tb_back)
    public void onViewClicked(View v) {
        switch(v.getId()){
            case R.id.btn_tb_back:
                this.finish();
                break;
        }
    }
}
