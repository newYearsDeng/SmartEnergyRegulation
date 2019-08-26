package com.northmeter.smartenergyregulation.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northmeter.smartenergyregulation.Interface.I_ShowHome;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.adapter.MainBuildList_RvAdapter;
import com.northmeter.smartenergyregulation.adapter.MainDevice_RvAdapter;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.AllbuildingmonitorBean;
import com.northmeter.smartenergyregulation.bean.AllleafmonitorBean;
import com.northmeter.smartenergyregulation.bean.MonitorBean;
import com.northmeter.smartenergyregulation.presenter.HomePresenter;
import com.northmeter.smartenergyregulation.utils.SharedPreferencesUtil;
import com.northmeter.smartenergyregulation.view.CirclePercentView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * Created by dyd on 2019/5/9.
 * 主页面，建筑列表
 */

public class HomeActivity extends BaseActivity implements I_ShowHome {
    @BindView(R.id.listview)
    RecyclerView listview;
    @BindView(R.id.listview_device)
    RecyclerView listviewDevice;
    @BindView(R.id.btn_tb_back)
    ImageView btnTbBack;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.linear_contain)
    LinearLayout linearContain;
    @BindView(R.id.tv_device_all)
    TextView tvDeviceAll;
    @BindView(R.id.tv_device_normalnum)
    TextView tvDeviceNormalnum;
    @BindView(R.id.tv_device_abnormalnum)
    TextView tvDeviceAbnormalnum;
    @BindView(R.id.circle_percent_view)
    CirclePercentView circlePercentView;
    @BindView(R.id.tv_percent_show)
    TextView tvShowPercent;
    @BindView(R.id.horizonscroll_view)
    HorizontalScrollView horizonscrollView;
    @BindView(R.id.iv_empty_build)
    ImageView ivEmptyBuild;
    @BindView(R.id.iv_empty_device)
    ImageView ivEmptyDevice;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;

    private HomePresenter homePresenter;
    private MainBuildList_RvAdapter adapter;
    private MainDevice_RvAdapter adapter_device;
    private List<MonitorBean.Page.BuildList> datas = new ArrayList<>();
    private List<MonitorBean.MonitorList> deviceDatas = new ArrayList<>();
    List<AllleafmonitorBean.MeterList> leafmonitorList = new ArrayList<>();

    private List<View> viewList;
    private List<ViewHolder> viewHolderList;
    private HashMap<Integer, MonitorBean.Page.BuildList> selectWithTreeList;//存储每个节点选择的建筑树
    private int mark = 0;

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
        tvToolbarTitle.setText(R.string.app_name);
    }

    @Override
    public void initData() {
        super.initData();
        homePresenter = new HomePresenter(this);
        viewList = new ArrayList<>();
        viewHolderList = new ArrayList<>();
        selectWithTreeList = new HashMap<>();

        initListView();
        //new HomePresenter(this).getBuildList(1,"001008","");
        //new HomePresenter(this).getallleafmonitor("001000001000001000005");

        //homePresenter.getMonitor("001008");

        //homePresenter.getTree("");
        homePresenter.getsametypeeq("004");

        /**
         * 001008  插座
         * 001006 分体空调控制器
         * */

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置百分比
     *
     * @param max     最大值
     * @param current 占比
     */
    private void setData(int max, float current) {
        float percentage = (100f * current) / max;
        ObjectAnimator animator = ObjectAnimator.ofFloat(circlePercentView, "percentage", 0, percentage);
        animator.setDuration(1000);
        animator.start();
    }


    private void initAddView(int mark, String buildName) {
        View addView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.item_home_build_view, null);
        addView.setId(mark);
        linearContain.addView(addView, mark);
        getViewInstance(addView, buildName);
    }

    private void getViewInstance(View view, String buildName) {
        ViewHolder vh = new ViewHolder();
        vh.id = view.getId();
        vh.relative_content = (RelativeLayout) view.findViewById(R.id.relative_content);
        vh.tv_progect_addr = (TextView) view.findViewById(R.id.tv_progect_addr);
        vh.tv_progect_addr.setText(buildName);
        // 设置监听
        vh.relative_content.setOnClickListener(selectProListener);
        viewHolderList.add(vh);
        viewList.add(view);

    }


    public class ViewHolder {
        private int id = -1;
        private RelativeLayout relative_content;
        private TextView tv_progect_addr;
    }

    View.OnClickListener selectProListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View view = (View) v.getParent();
            for (int i = 0; i < linearContain.getChildCount(); i++) {
                ViewHolder viewHolder = viewHolderList.get(i);
                Log.v("Import", "view.getId()==" + view.getId() + "  viewHolder.id==" + viewHolder.id);
                if (view.getId() == viewHolder.id) {
                    removeViews(i);
                    datas.clear();
                    datas.addAll(selectWithTreeList.get(i).getChild());
                    adapter.notifyDataSetChanged();

                    deviceDatas.clear();
                    deviceDatas.addAll(selectWithTreeList.get(i).getMonitor());
                    adapter_device.notifyDataSetChanged();

                    scrollView.post(new Runnable() {
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_UP);
                        }
                    });

                    //listview.setVisibility(datas.isEmpty() ? View.GONE : View.VISIBLE);
                    //listviewDevice.setVisibility(deviceDatas.isEmpty() ? View.GONE : View.VISIBLE);
                }
            }
        }
    };

    /**
     * 删除排在被点击按钮后面按钮
     */
    private void removeViews(int index) {
        int childNum = linearContain.getChildCount();
        for (int i = childNum - 1; i >= 0; i--) {
            if (i > index) {
                linearContain.removeViewAt(i);
                viewHolderList.remove(i);
                viewList.remove(i);
            }
        }
        mark = index + 1;
    }


    @OnClick({R.id.tv_right_text, R.id.btn_tb_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_text:
                goActivity(SelectBuildActivity.class);
                break;
            case R.id.btn_tb_back:
                this.finish();
                break;
        }
    }

    private void initListView() {
        adapter = new MainBuildList_RvAdapter(this, datas);
        listview.setLayoutManager(new GridLayoutManager(this, 1));
        listview.setAdapter(adapter);
        adapter.setOnMyClickListener(new MainBuildList_RvAdapter.OnMyClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                horizonscrollView.setVisibility(View.VISIBLE);
                initAddView(mark, datas.get(position).getBuidingname());
                selectWithTreeList.put(mark, datas.get(position));
                mark++;

                homePresenter.getallleafmonitor(datas.get(position).getBulidingid());
                homePresenter.getallbuildingmonitor(datas.get(position).getBulidingid());

                List<MonitorBean.MonitorList> deviceList = datas.get(position).getMonitor();
                deviceDatas.clear();
                deviceDatas.addAll(deviceList);
                adapter_device.notifyDataSetChanged();
                ivEmptyDevice.setVisibility(deviceList.isEmpty() ? View.VISIBLE : View.GONE);
                if(!deviceList.isEmpty()){
                    scrollView.post(new Runnable() {
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }


                List<MonitorBean.Page.BuildList> list = datas.get(position).getChild();
                datas.clear();
                datas.addAll(list);
                adapter.notifyDataSetChanged();

                //listview.setVisibility(datas.isEmpty() ? View.GONE : View.VISIBLE);
                //listviewDevice.setVisibility(deviceDatas.isEmpty() ? View.GONE : View.VISIBLE);


            }
        });


        adapter_device = new MainDevice_RvAdapter(this, deviceDatas);
        listviewDevice.setLayoutManager(new LinearLayoutManager(this));
        listviewDevice.setAdapter(adapter_device);
        adapter_device.setOnMyClickListener(new MainDevice_RvAdapter.OnMyClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("checkBuildId",deviceDatas.get(position).getBulidingid());
                for (AllleafmonitorBean.MeterList item : leafmonitorList) {
                    if (item.getComaddress().equals(deviceDatas.get(position).getComAddress())) {
                        intent.putExtra("meterData", item);
                    }
                }
                goActivity(DeviceMainActivity.class, intent);
            }
        });
    }


    @Override
    public void returnMessage(String message) {

    }

    @Override
    public void showBuildList(MonitorBean monitorBean) {
        datas.addAll(monitorBean.getPage().getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showAllleafmonitor(List<AllleafmonitorBean.MeterList> meterList) {
        leafmonitorList.clear();
        leafmonitorList.addAll(meterList);
    }

    @Override
    public void showAllbuildingmonitor(AllbuildingmonitorBean.AllleafmonitorList allleafmonitorList) {
        tvDeviceAll.setText(String.valueOf(allleafmonitorList.getAccounttotal()));
        tvDeviceNormalnum.setText(String.valueOf(allleafmonitorList.getNormalnum()));
        tvDeviceAbnormalnum.setText(String.valueOf(allleafmonitorList.getAbnormalnum()));

        BigDecimal allNum = new BigDecimal(allleafmonitorList.getAccounttotal());
        BigDecimal normalNum = new BigDecimal(allleafmonitorList.getNormalnum());

        setData(allleafmonitorList.getAccounttotal(), allleafmonitorList.getNormalnum());
        String percentage = normalNum.multiply(new BigDecimal(100)).divide(allNum,1,BigDecimal.ROUND_HALF_UP).toString();
        tvShowPercent.setText(percentage + "%");
    }

}
