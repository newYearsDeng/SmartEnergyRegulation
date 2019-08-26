package com.northmeter.smartenergyregulation.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.andview.refreshview.XRefreshView;
import com.northmeter.smartenergyregulation.Interface.I_ShowWarnData;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.adapter.CommonAdapter;
import com.northmeter.smartenergyregulation.adapter.ViewHolder;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.NowWarnBean;
import com.northmeter.smartenergyregulation.presenter.WarnPresenter;
import com.northmeter.smartenergyregulation.widget.Date.PromptHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/6/5.
 * 告警信息
 */

public class WarnActivity extends BaseActivity implements XRefreshView.XRefreshViewListener, I_ShowWarnData {
    @BindView(R.id.btn_tb_back)
    ImageView btnTbBack;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.x_refresh_view)
    XRefreshView xRefreshView;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_time_action_star)
    TextView tvTimeActionStar;
    @BindView(R.id.tv_time_action_end)
    TextView tvTimeActionEnd;
    @BindView(R.id.ll_show_date)
    LinearLayout llShowDate;

    private CommonAdapter commonAdapter;
    private List<NowWarnBean.WarnBean> datas = new ArrayList<>();
    private int warnType;//告警类型，当前告警或者历史告警
    private WarnPresenter warnPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_warn;
    }

    @Override
    public void initIntentData() {
        super.initIntentData();
        warnType = getIntent().getIntExtra("warnType", 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTitle() {
        super.setTitle();
        if (warnType == 0) {
            tvToolbarTitle.setText("当前告警");
        } else {
            tvToolbarTitle.setText("历史告警");
            llShowDate.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void initData() {
        super.initData();
        warnPresenter = new WarnPresenter(this);
        initRefresh();
        initListView();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        tvTimeActionStar.setText(simpleDateFormat.format(date));
        tvTimeActionEnd.setText(simpleDateFormat.format(date));

        if (warnType == 0) {
            warnPresenter.getNowWarn();
        } else {
            warnPresenter.getHistoryWarn(0, 0, "", "",
                    simpleDateFormat.format(date)+" 00:00:00", simpleDateFormat.format(date)+" 23:59:59", 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btn_tb_back, R.id.tv_time_action_star, R.id.tv_time_action_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_tb_back:
                this.finish();
                break;
            case R.id.tv_time_action_star:
                PromptHelper.createTimePicker(this, true, true, true, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year +"-"+String.format("%02d", (monthOfYear+1))+"-"+String.format("%02d", dayOfMonth);
                        tvTimeActionStar.setText(date);
                        warnPresenter.getHistoryWarn(0, 0, "", "",
                                date+" 00:00:00", tvTimeActionEnd.getText()+" 23:59:59", 0);
                    }
                }, new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                });
                break;
            case R.id.tv_time_action_end:
                PromptHelper.createTimePicker(this, true, true, true, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year +"-"+String.format("%02d", (monthOfYear+1))+"-"+String.format("%02d", dayOfMonth);
                        tvTimeActionStar.setText(date);
                        warnPresenter.getHistoryWarn(0, 0, "", "",
                                tvTimeActionStar.getText()+" 00:00:00", date+" 23:59:59", 0);
                    }
                }, new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                });
                break;
        }
    }

    private void initListView() {
        commonAdapter = new CommonAdapter<NowWarnBean.WarnBean>(this, datas, R.layout.item_warn_list) {
            @Override
            public void convert(ViewHolder helper, NowWarnBean.WarnBean item) {
                if (item.getIsread() == 0) {
                    helper.getImageViewSet(R.id.iv_left_item, R.color.color_noalready);
                } else {
                    helper.getImageViewSet(R.id.iv_left_item, R.color.color_already);
                }
                helper.getTextViewSet(R.id.tv_warn_eventname, item.getEventname());
                helper.getTextViewSet(R.id.tv_warn_metername, item.getMetername());
                helper.getTextViewSet(R.id.tv_warn_eventdata, item.getEventdata());
                helper.getTextViewSet(R.id.tv_warn_eventtime, item.getEventtime());
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
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onRefresh(boolean isPullDown) {
        if (warnType == 0) {
            warnPresenter.getNowWarn();
        } else {
            warnPresenter.getHistoryWarn(0, 0, "", "",
                    tvTimeActionStar.getText()+" 00:00:00", tvTimeActionEnd.getText()+" 23:59:59", 0);
        }
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
        showTosatMsg(message);
    }

    @Override
    public void showWarnList(List<NowWarnBean.WarnBean> list) {
        xRefreshView.stopRefresh();
        this.datas.clear();
        this.datas.addAll(datas);
        commonAdapter.notifyDataSetChanged();
        if (datas.isEmpty()) {
            ivEmpty.setVisibility(View.VISIBLE);
        } else {
            ivEmpty.setVisibility(View.GONE);
        }

    }
}
