package com.northmeter.smartenergyregulation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.adapter.CommonAdapter;
import com.northmeter.smartenergyregulation.adapter.ViewHolder;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.BuildListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/5/10.
 * 建筑列表
 */

public class BuildListActivity extends BaseActivity implements XRefreshView.XRefreshViewListener,TextWatcher {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.et_search_name)
    EditText etSearchName;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.x_refresh_view)
    XRefreshView xRefreshView;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    private CommonAdapter commonAdapter;
    private List<BuildListBean> datas = new ArrayList();
    private List<BuildListBean> datasBack = new ArrayList();
    private List<BuildListBean> searchDatas = new ArrayList();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_build_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    String progectName;
    @Override
    public void initIntentData() {
        super.initIntentData();
        progectName = getIntent().getStringExtra("Name");
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tvToolbarTitle.setText(progectName);
    }

    @Override
    public void initData() {
        super.initData();
        initRefresh();
        initListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String searchName = etSearchName.getText().toString();
//        if (!datasBack.isEmpty()) {
//            searchDatas.clear();
//            for (BuildListBean item : datasBack) {
//                if (item.getBuildName().indexOf(searchName) >= 0) {
//                    searchDatas.add(item);
//                }
//            }
//            datas.clear();
//            datas.addAll(searchDatas);
//            commonAdapter.notifyDataSetChanged();
//        }
    }


    @OnClick(R.id.btn_tb_back)
    public void onViewClicked(View v) {
        switch(v.getId()){
            case R.id.btn_tb_back:
                this.finish();
                break;
        }
    }


    private void initListView() {
        commonAdapter = new CommonAdapter<BuildListBean>(this, datas, R.layout.item_build_list) {
            @Override
            public void convert(ViewHolder helper, final BuildListBean item) {
                helper.getTextViewSet(R.id.tv_build_name, "");
            }
        };
        listview.setAdapter(commonAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
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


}
