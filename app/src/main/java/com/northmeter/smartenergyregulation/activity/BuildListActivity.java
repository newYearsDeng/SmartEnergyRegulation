package com.northmeter.smartenergyregulation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.andview.refreshview.XRefreshView;
import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.adapter.CommonAdapter;
import com.northmeter.smartenergyregulation.adapter.ViewHolder;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.AllleafmonitorBean;
import com.northmeter.smartenergyregulation.bean.CommonResponse;
import com.northmeter.smartenergyregulation.presenter.BuildListPresenter;
import com.northmeter.smartenergyregulation.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/5/10.
 * 建筑列表
 */

public class BuildListActivity extends BaseActivity implements XRefreshView.XRefreshViewListener, TextWatcher,I_ShowData {
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
    ImageView tvEmpty;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;

    private CommonAdapter commonAdapter;
    private List<AllleafmonitorBean.MeterList> datas = new ArrayList();
    private List<AllleafmonitorBean.MeterList> datasBack = new ArrayList();
    private List<AllleafmonitorBean.MeterList> searchDatas = new ArrayList();

    private BuildListPresenter buildListPresenter;
    private String checkBuildId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_build_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initIntentData() {
        super.initIntentData();
        checkBuildId = SharedPreferencesUtil.getPrefString(this,"checkBuildId","001");
    }

    @Override
    public void setTitle() {
        super.setTitle();
        tvRightText.setText("建筑筛选");
        tvToolbarTitle.setText("");

    }

    @Override
    public void initData() {
        super.initData();
        etSearchName.addTextChangedListener(this);
        buildListPresenter = new BuildListPresenter(this);
        buildListPresenter.getallleafmonitor(checkBuildId);
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
        if (!datasBack.isEmpty()) {
            searchDatas.clear();
            for (AllleafmonitorBean.MeterList item : datasBack) {
                if (item.getMetername().indexOf(searchName) >= 0 || item.getComaddress().indexOf(searchName) >= 0) {
                    searchDatas.add(item);
                }
            }
            datas.clear();
            datas.addAll(searchDatas);
            commonAdapter.notifyDataSetChanged();
        }
    }


    private final static int GREQUEST_CODE = 1;
    @OnClick({R.id.btn_tb_back,R.id.tv_right_text})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_tb_back:
                this.finish();
                break;
            case R.id.tv_right_text:
                Intent intent = new Intent(this, SelectBuildActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, GREQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case GREQUEST_CODE:
                if(resultCode==RESULT_OK){
                    checkBuildId = data.getStringExtra("result").toString();
                    SharedPreferencesUtil.setPrefString(this,"checkBuildId",checkBuildId);
                    buildListPresenter.getallleafmonitor(checkBuildId);
                }
                break;
        }
    }

    private void initListView() {
        commonAdapter = new CommonAdapter<AllleafmonitorBean.MeterList>(this, datas, R.layout.item_build_list) {
            @Override
            public void convert(ViewHolder helper, final AllleafmonitorBean.MeterList item) {
                helper.getTextViewSet(R.id.tv_build_name,item.getMetername());
                helper.getTextViewSet(R.id.tv_device_num,item.getComaddress());
            }
        };
        listview.setAdapter(commonAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("meterData",datas.get(position));
                goActivity(DeviceMainActivity.class,intent);
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
        buildListPresenter.getallleafmonitor(checkBuildId);
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
        xRefreshView.stopRefresh();
    }

    @Override
    public void showData(CommonResponse commonResponse) {
        xRefreshView.stopRefresh();
        AllleafmonitorBean data = (AllleafmonitorBean) commonResponse;
        this.datas.clear();
        this.datas.addAll(data.getList());
        this.datasBack.clear();
        this.datasBack.addAll(data.getList());
        commonAdapter.notifyDataSetChanged();
        if (datas.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.GONE);
        }
    }



}
