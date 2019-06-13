package com.northmeter.smartenergyregulation.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.northmeter.smartenergyregulation.Interface.I_ShowMain;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.EquipmentBean;
import com.northmeter.smartenergyregulation.bean.MainResponse;
import com.northmeter.smartenergyregulation.presenter.MainPresenter;
import com.northmeter.smartenergyregulation.view.CirclePercentView;
import com.northmeter.smartenergyregulation.widget.PopupHelper;
import com.northmeter.smartenergyregulation.widget.WidgetHelper;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/5/20.
 */

public class MainActivity extends BaseActivity implements I_ShowMain {
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.iv_left_menu)
    ImageView ivLeftMenu;
    @BindView(R.id.iv_right_menu)
    ImageView ivRightMenu;
    @BindView(R.id.tv_content_zyd)
    TextView tvContentZyd;
    @BindView(R.id.tv_content_zydje)
    TextView tvContentZydje;
    @BindView(R.id.tv_content_zsyje)
    TextView tvContentZsyje;
    @BindView(R.id.tv_content_sjsr)
    TextView tvContentSjsr;
    @BindView(R.id.tv_content_czzj)
    TextView tvContentCzzj;
    @BindView(R.id.tv_content_tfzj)
    TextView tvContentTfzj;
    @BindView(R.id.tv_power_ss)
    TextView tvPowerSs;
    @BindView(R.id.tv_power_cz)
    TextView tvPowerCz;
    @BindView(R.id.tv_power_tf)
    TextView tvPowerTf;
    @BindView(R.id.tv_water_ss)
    TextView tvWaterSs;
    @BindView(R.id.tv_water_cz)
    TextView tvWaterCz;
    @BindView(R.id.tv_water_tf)
    TextView tvWaterTf;
    @BindView(R.id.tv_concentrator_total)
    TextView tvConcentratorTotal;
    @BindView(R.id.tv_concentrator_online)
    TextView tvConcentratorOnline;
    @BindView(R.id.tv_concentrator_unonline)
    TextView tvConcentratorUnonline;
    @BindView(R.id.tv_concentrator_use)
    TextView tvConcentratorUse;
    @BindView(R.id.tv_concentrator_percentage)
    TextView tvConcentratorPercentage;
    @BindView(R.id.tv_meter_total)
    TextView tvMeterTotal;
    @BindView(R.id.tv_meter_normal)
    TextView tvMeterNormal;
    @BindView(R.id.tv_meter_unnormal)
    TextView tvMeterUnnormal;
    @BindView(R.id.tv_meter_use)
    TextView tvMeterUse;
    @BindView(R.id.tv_meter_percentage)
    TextView tvMeterPercentage;
    @BindView(R.id.circle_concentrator)
    CirclePercentView circleConcentrator;
    @BindView(R.id.circle_meter)
    CirclePercentView circleMeter;


    private MainPresenter mainPresenter;
    private View view;
    private PopupWindow popupWindow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigationView.setNavigationItemSelectedListener(new NavagationitemSelecteImpl(this, drawerLayout));
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
        init_popup();
        mainPresenter = new MainPresenter(this);
        mainPresenter.getYearlyMount("2019");
        mainPresenter.getAnnunCharge("2019-01-01 00:00:00", "2019-12-12 00:00:00");
        mainPresenter.getEquipment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @OnClick({R.id.iv_left_menu, R.id.iv_right_menu, R.id.ll_history, R.id.ll_warn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_right_menu:
                goActivity(HomeActivity.class);
                //popupWindow.showAsDropDown(view, 0, 0, Gravity.RIGHT);
                break;
            case R.id.ll_warn://当前告警
                goActivity(WarnActivity.class,new Intent().putExtra("warnType",0));
                break;
            case R.id.ll_history://历史告警
                goActivity(WarnActivity.class,new Intent().putExtra("warnType",1));
                break;
        }
    }


    private void init_popup() {
        view = LayoutInflater.from(getApplicationContext()).inflate(
                R.layout.item_main_poput, null);
        popupWindow = new PopupHelper().getWindow_ALLWRAP(view, getApplicationContext());
        popupWindow.setWidth((int) (WidgetHelper.getWindowWidth(this)));
    }


    @Override
    public void showReturnMsg(String msg) {

    }

    @Override
    public void showYearlyMount(MainResponse.YearlyMount yearlyMount) {
        tvContentZyd.setText(String.valueOf(yearlyMount.getPowerNum()));
        tvContentZydje.setText(String.valueOf(yearlyMount.getPowerMount()));
        tvContentZsyje.setText(String.valueOf(yearlyMount.getWaterNum()));

    }

    @Override
    public void showAnnunCharge(MainResponse.AnnunCharge annunCharge) {
        BigDecimal powerCharge = new BigDecimal(Double.toString(annunCharge.getPowerCharge()));
        BigDecimal powerReturn = new BigDecimal(Double.toString(annunCharge.getPowerReturn()));
        BigDecimal waterCharge = new BigDecimal(Double.toString(annunCharge.getWaterCharge()));
        BigDecimal waterReturn = new BigDecimal(Double.toString(annunCharge.getWaterReturn()));

        tvContentSjsr.setText(String.valueOf(powerCharge.add(waterCharge).subtract(powerReturn).subtract(waterReturn)));//总实收入总计 = 充值总计 - 退费总计

        tvContentCzzj.setText(String.valueOf(powerCharge.add(waterCharge)));//充值总计 = 用电充值 + 用水充值
        tvContentTfzj.setText(String.valueOf(powerReturn.add(waterReturn)));//退费总计 = 用电退费 + 用水退费

        tvPowerSs.setText(String.valueOf(powerCharge.subtract(powerReturn)));//电实收入总计 = 用电充值 - 用电退费
        tvPowerCz.setText(String.valueOf(powerCharge));//电费充值
        tvPowerTf.setText(String.valueOf(powerReturn));//电费退费

        tvWaterSs.setText(String.valueOf(waterCharge.subtract(waterReturn)));//水实收入总计 = 用水充值 - 用水退费
        tvWaterCz.setText(String.valueOf(waterCharge));//水充值
        tvWaterTf.setText(String.valueOf(waterReturn));//水退费

    }

    @Override
    public void showEquipment(EquipmentBean.Equipment equipmentBean) {
        BigDecimal concentratorInUser = new BigDecimal(equipmentBean.getConcentrator().getInuse());//使用中
        BigDecimal concentratorOnLine = new BigDecimal(equipmentBean.getConcentrator().getOnline());// 在线数
        BigDecimal concentratorSum = new BigDecimal(equipmentBean.getConcentrator().getSum()); // 总数

        BigDecimal meterInUser = new BigDecimal(equipmentBean.getMeter().getInuse());// 使用中
        BigDecimal meterNormal = new BigDecimal(equipmentBean.getMeter().getNormal());// 通讯正常数
        BigDecimal meterSum = new BigDecimal(equipmentBean.getMeter().getSum());  // 总数


        tvConcentratorTotal.setText(String.valueOf(concentratorSum));
        tvConcentratorOnline.setText(String.valueOf(concentratorOnLine));
        tvConcentratorUnonline.setText(String.valueOf(concentratorInUser.subtract(concentratorOnLine)));
        tvConcentratorUse.setText(String.valueOf(concentratorInUser));

        tvConcentratorPercentage.setText(String.valueOf(concentratorOnLine.multiply(new BigDecimal(100)).divide(concentratorInUser, 1, BigDecimal.ROUND_HALF_UP)) + "%");
        setCircleConcentrator(equipmentBean.getConcentrator().getInuse(),equipmentBean.getConcentrator().getOnline());


        tvMeterTotal.setText(String.valueOf(meterSum));
        tvMeterNormal.setText(String.valueOf(meterNormal));
        tvMeterUnnormal.setText(String.valueOf(meterInUser.subtract(meterNormal)));
        tvMeterUse.setText(String.valueOf(meterInUser));

        tvMeterPercentage.setText(String.valueOf(meterNormal.multiply(new BigDecimal(100)).divide(meterInUser, 1, BigDecimal.ROUND_HALF_UP)) + "%");
        setCircleMeter(equipmentBean.getMeter().getInuse(),equipmentBean.getMeter().getNormal());
    }

    /**
     * 设置百分比
     *
     * @param max     最大值
     * @param current 占比
     */
    private void setCircleConcentrator(int max, float current) {
        circleConcentrator.setBgColor(Color.parseColor("#e9edf5"));
        circleConcentrator.setProgressColor(Color.parseColor("#5bd1b1"));
        float percentage = (100f * current) / max;
        ObjectAnimator animator = ObjectAnimator.ofFloat(circleConcentrator, "percentage", 0, percentage);
        animator.setDuration(1000);
        animator.start();

    }

    private void setCircleMeter(int max, float current) {
        circleMeter.setBgColor(Color.parseColor("#e9edf5"));
        circleMeter.setProgressColor(Color.parseColor("#49a2f9"));
        float percentage = (100f * current) / max;
        ObjectAnimator animator = ObjectAnimator.ofFloat(circleMeter, "percentage", 0, percentage);
        animator.setDuration(1000);
        animator.start();

    }

}
