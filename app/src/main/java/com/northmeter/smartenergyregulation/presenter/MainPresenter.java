package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_MainPresenter;
import com.northmeter.smartenergyregulation.Interface.I_ShowMain;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.EquipmentBean;
import com.northmeter.smartenergyregulation.bean.MainResponse;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by dyd on 2019/5/20.
 */

public class MainPresenter implements I_MainPresenter {
    private Context context;
    private I_ShowMain iShowMain;

    public MainPresenter(Context context){
        this.context = context;
        this.iShowMain  = (I_ShowMain) context;
    }


    /**
     * 年度实收入总计
     * {"msg":"success","code":0,"annuncharge":
     * {"waterReturn":"0.00","powerReturn":"1023.86","waterCharge":"205.01","powerCharge":"11844.12"}}
     */
    @Override
    public void getAnnunCharge(String starttime, String endtime) {
        Map mapList = new HashMap();
        mapList.put("starttime",starttime);
        mapList.put("endtime",endtime);

        OkGo.<MainResponse>post(API.getAnnunCharge)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new DialogCallback<MainResponse>((Activity) context,MainResponse.class) {
                    @Override
                    public void onSuccess(Response<MainResponse> response) {
                        if (response.body().getCode()==0){
                            iShowMain.showAnnunCharge(response.body().getAnnuncharge());
                        }else{
                            iShowMain.showReturnMsg(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<MainResponse> response) {
                        super.onError(response);
                        iShowMain.showReturnMsg("网络连接失败，请稍后重试");
                    }
                });
    }

    /**
     * 年度实支出总计*/
    @Override
    public void getYearlyMount(String yeartime) {
        Map mapList = new HashMap();
        mapList.put("yeartime",yeartime);

        OkGo.<MainResponse>post(API.getYearlyMount)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new DialogCallback<MainResponse>((Activity) context,MainResponse.class) {
                    @Override
                    public void onSuccess(Response<MainResponse> response) {
                        if (response.body().getCode()==0){
                            iShowMain.showYearlyMount(response.body().getYearlyMount());
                        }else{
                            iShowMain.showReturnMsg(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<MainResponse> response) {
                        super.onError(response);
                        iShowMain.showReturnMsg("网络连接失败，请稍后重试");
                    }
                });
    }

    /***设备汇总*/
    @Override
    public void getEquipment() {
        OkGo.<EquipmentBean>post(API.getEquipment)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .execute(new DialogCallback<EquipmentBean>((Activity) context,EquipmentBean.class) {
                    @Override
                    public void onSuccess(Response<EquipmentBean> response) {
                        if (response.body().getCode()==0){
                            iShowMain.showEquipment(response.body().getEquipment());
                        }else{
                            iShowMain.showReturnMsg(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<EquipmentBean> response) {
                        super.onError(response);
                        iShowMain.showReturnMsg("网络连接失败，请稍后重试");
                    }
                });
    }







    private void initPieChart(PieChart pieChart) {
        pieChart.getDescription().setEnabled(false);    //设置pieChart图表的描述
        pieChart.setRotationEnabled(true);//可以手动旋转
        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]
        pieChart.setHighlightPerTapEnabled(true);       //设置piecahrt图表点击Item高亮是否可用
        pieChart.setUsePercentValues(true);

        pieChart.animateXY(1000, 1000);// 设置pieChart图表展示动画效果
        Legend l = pieChart.getLegend();
        l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setForm(Legend.LegendForm.CIRCLE); //设置图例的形状
        l.setFormSize(10);                      //设置图例的大小
        l.setFormToTextSpace(10f);              //设置每个图例实体中标签和形状之间的间距
        l.setDrawInside(false);
        l.setWordWrapEnabled(true);              //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
        l.setXEntrySpace(10f);                  //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
        l.setYEntrySpace(8f);                  //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
        l.setYOffset(0f);                      //设置比例块Y轴偏移量
        l.setTextSize(7f);                      //设置图例标签文本的大小
        l.setTextColor(Color.WHITE);//设置图例标签文本的颜色
    }

    private void setPieData(PieChart pieChart, float power, float water) {
        ArrayList<PieEntry> pieEntryList = new ArrayList();//数据列表
        ArrayList<Integer> colors = new ArrayList();//颜色列表


        pieEntryList.add(new PieEntry(power, "电费"));
        pieEntryList.add(new PieEntry(water, "水费"));


        for (int i = 0; i < 2; i++) {//list为数据列表
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            colors.add(Color.rgb(r, g, b));
            //pieEntryList.add(new PieEntry(i, "标签"));
        }


        //饼状图数据集 PieDataSet
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "");
        pieDataSet.setSliceSpace(3f);           //设置饼状Item之间的间隙
        pieDataSet.setSelectionShift(20f);      //设置饼状Item被选中时变化的距离
        pieDataSet.setColors(colors);           //为DataSet中的数据匹配上颜色集(饼图Item颜色)
        //最终数据 PieData
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);            //设置是否显示数据实体(百分比，true:以下属性才有意义)
        pieData.setValueTextColor(Color.BLUE);  //设置所有DataSet内数据实体（百分比）的文本颜色
        pieData.setValueTextSize(10f);          //设置所有DataSet内数据实体（百分比）的文本字体大小
        pieData.setValueFormatter(new PercentFormatter());//设置所有DataSet内数据实体（百分比）的文本字体格式

        //将数据设置在mChart中，图表完成
        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
}
