package com.northmeter.smartenergyregulation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.adapter.MainBuildList_RvAdapter;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.BuildListBean;
import com.northmeter.smartenergyregulation.bean.CommonResponse;
import com.northmeter.smartenergyregulation.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/5/9.
 * 主页面，建筑列表
 */

public class HomeActivity extends BaseActivity implements XRefreshView.XRefreshViewListener,I_ShowData {
    @BindView(R.id.listview)
    RecyclerView listview;
    @BindView(R.id.x_refresh_view)
    XRefreshView xRefreshView;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.btn_tb_back)
    ImageView btnTbBack;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    private MainBuildList_RvAdapter adapter;
    private List<BuildListBean.BuildList> datas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
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
        tvRightText.setText("筛选");
        btnTbBack.setVisibility(View.GONE);
        tvToolbarTitle.setText(R.string.app_name);
    }

    @Override
    public void initData() {
        super.initData();
        initListView();
        initRefresh();
        //new HomePresenter(this).getBuildList(1,"001008","");
        new HomePresenter(this).getMonitor("001008");
        //new HomePresenter(this).getallleafmonitor("001000001000001000005");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.tv_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_text:
                goActivity(SelectBuildActivity.class);
                break;
        }
    }

    private void initListView() {
        adapter = new MainBuildList_RvAdapter(this, datas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        listview.setLayoutManager(gridLayoutManager);
        listview.setAdapter(adapter);
    }

    private void initRefresh() {
        // 设置是否可以下拉刷新
        xRefreshView.setPullRefreshEnable(true);
        // 设置是否可以上拉加载
        xRefreshView.setPullLoadEnable(false);
        // 设置上次刷新的时间
        xRefreshView.restoreLastRefreshTime(xRefreshView.getLastRefreshTime());
        // 设置时候可以自动刷新
        xRefreshView.setAutoRefresh(false);
        xRefreshView.setXRefreshViewListener(this);
        //xRefreshView.startRefresh();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRefresh(boolean isPullDown) {

    }

    @Override
    public void onLoadMore(boolean isSilence) {

    }

    @Override
    public void onRelease(float direction) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {

    }



    @Override
    public void returnMessage(String message) {

    }

    @Override
    public void showData(CommonResponse commonResponse) {
        BuildListBean buildListBean = (BuildListBean)commonResponse;

        datas.addAll(buildListBean.getList());
        adapter.notifyDataSetChanged();
    }
}
