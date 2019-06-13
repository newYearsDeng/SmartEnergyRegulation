package com.northmeter.smartenergyregulation.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.northmeter.smartenergyregulation.Interface.I_ShowDevice;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.adapter.CommonAdapter;
import com.northmeter.smartenergyregulation.adapter.ViewHolder;
import com.northmeter.smartenergyregulation.base.BaseFragment;
import com.northmeter.smartenergyregulation.bean.AllleafmonitorBean;
import com.northmeter.smartenergyregulation.bean.MonitorHistoryBean;
import com.northmeter.smartenergyregulation.formatter.LineChartMarkerView;
import com.northmeter.smartenergyregulation.formatter.XAxisValueFormatter;
import com.northmeter.smartenergyregulation.presenter.DeviceMainPresenter;
import com.northmeter.smartenergyregulation.widget.Date.PromptHelper;
import com.northmeter.smartenergyregulation.widget.PopupHelper;
import com.northmeter.smartenergyregulation.widget.WidgetHelper;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by dyd on 2019/5/10.
 */

public class Fragment_Socket_Chart extends BaseFragment implements I_ShowDevice {

    private static Fragment_Socket_Chart fragment;
    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.bar_chart)
    BarChart barChart;
    @BindView(R.id.tv_type_action)
    TextView tvTypeAction;
    @BindView(R.id.tv_time_action_star)
    TextView tvTimeActionStar;
    @BindView(R.id.tv_time_action_end)
    TextView tvTimeActionEnd;

    Unbinder unbinder;

    int chartColor = Color.parseColor("#585de7");
    int textColor = Color.parseColor("#5A5959");

    int barColor = Color.parseColor("#e54243");
    int barTopColor = Color.parseColor("#f97c7c");

    AllleafmonitorBean.MeterList meterData;
    DeviceMainPresenter deviceMainPresenter;


    private View view;
    private ListView listview;
    private PopupWindow popupWindow;
    private CommonAdapter commonAdapter;
    private String type="功率(kW)";
    private int showType=0;
    private List<MonitorHistoryBean.HistoryList> datas = new ArrayList<>();


    public static Fragment_Socket_Chart newInstance(int getType) {
        fragment = new Fragment_Socket_Chart();
        Bundle bundle = new Bundle();
        bundle.putInt("getType", getType);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_socket_chart;
    }

    @Override
    protected void startGetArgument(Bundle savedInstanceState) {
        meterData = (AllleafmonitorBean.MeterList) getActivity().getIntent().getSerializableExtra("meterData");
    }

    @Override
    protected void finishCreateView(Bundle savedInstanceState) {
        initpopupWindow();
        deviceMainPresenter = new DeviceMainPresenter(getActivity(), Fragment_Socket_Chart.this);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        tvTimeActionStar.setText(simpleDateFormat.format(date));
        tvTimeActionEnd.setText(simpleDateFormat.format(date));


        init_lineChart();
        //setLineChartData();

        initBarChart();
        setLegend();
        setYAxis();
        setXAxis();
        //setChartData();


        deviceMainPresenter.getMonitorHistory(meterData.getMeterid(), simpleDateFormat.format(date)+" 00:00:00", simpleDateFormat.format(date)+" 23:59:59");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showData(List<MonitorHistoryBean.HistoryList> dataList) {
        this.datas.addAll(dataList);
        setLineChartData(datas,type,showType);
        setChartData(datas,type,showType);
    }

    @Override
    public void returnMessage(int code, String msg) {

    }


    @OnClick({R.id.tv_type_action, R.id.tv_time_action_star,R.id.tv_time_action_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_type_action:
                popupWindow.showAsDropDown(view,0,0,Gravity.CENTER);
                break;
            case R.id.tv_time_action_star:
                PromptHelper.createTimePicker(getActivity(), true, true, true, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year +"-"+String.format("%02d", (monthOfYear+1))+"-"+String.format("%02d", dayOfMonth);
                        tvTimeActionStar.setText(date);

                        deviceMainPresenter.getMonitorHistory(meterData.getMeterid() , tvTimeActionStar.getText()+" 00:00:00", tvTimeActionEnd.getText()+" 23:59:59");
                    }
                }, new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                });
                break;
            case R.id.tv_time_action_end:
                PromptHelper.createTimePicker(getActivity(), true, true, true, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year +"-"+String.format("%02d", (monthOfYear+1))+"-"+String.format("%02d", dayOfMonth);
                        tvTimeActionEnd.setText(date);
                        deviceMainPresenter.getMonitorHistory(meterData.getMeterid() , tvTimeActionStar.getText()+" 00:00:00", tvTimeActionEnd.getText()+" 23:59:59");
                    }
                }, new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                });
                break;
        }
    }


    /**
     * 类型选择
     */
    private void initpopupWindow() {
        view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(
                R.layout.item_progect_choose_type, null);
        popupWindow = new PopupHelper().getWindow_ALLWRAP(view, getActivity().getApplicationContext());
        popupWindow.setWidth((int) (WidgetHelper.getWindowWidth(getActivity())/4));

        final List<String[]> list = new ArrayList<>();
        list.add(new String[]{"功率", "0","kW"});
        list.add(new String[]{"电能", "1","kWh"});
        list.add(new String[]{"电压", "2","V"});
        list.add(new String[]{"电流", "3","A"});
        list.add(new String[]{"温度", "4","℃"});

        listview = view.findViewById(R.id.listview);
        commonAdapter = new CommonAdapter<String[]>(getActivity(), list, R.layout.item_progect_choose_type_view) {
            @Override
            public void convert(ViewHolder helper, String[] item) {
                helper.getTextViewSet(R.id.tv_type_name, item[0]);
            }
        };
        listview.setAdapter(commonAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                type = list.get(position)[0];
                tvTypeAction.setText(type);
                showType = Integer.parseInt(list.get(position)[1]);
                setLineChartData(datas,type+"("+list.get(position)[2]+")",showType);
                setChartData(datas,type+"("+list.get(position)[2]+")",showType);
            }
        });
    }

    /**
     * 折线图
     */
    private void init_lineChart() {
        Description description = new Description();
        description.setText("测试图表");
        description.setTextColor(Color.BLACK);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.getDescription().setEnabled(false);//设置描述不显示
        lineChart.setNoDataText("没有数据");//没有数据时显示的文字
        lineChart.setNoDataTextColor(textColor);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        lineChart.setBorderColor(textColor); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘
        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        lineChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        lineChart.setScaleYEnabled(true); //是否可以缩放 仅y轴
        lineChart.setPinchZoom(false);  //设置x轴和y轴能否同时缩放。默认是否
        lineChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        lineChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        lineChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        lineChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        lineChart.animateXY(1000, 1000);
        lineChart.setGridBackgroundColor(textColor);
        lineChart.setAlpha(1f);

        //获取此图表的x轴
        XAxis linexAxis = lineChart.getXAxis();
        linexAxis.setGranularity(1f);//禁止放大后x轴标签重绘
        linexAxis.setTextColor(textColor);//设置字体颜色
        linexAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        linexAxis.setDrawAxisLine(true);//是否绘制轴线
        linexAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        linexAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        linexAxis.setLabelCount(2);//开始时显示的x标签个数
        linexAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        //linexAxis.setTextSize(20f);//设置字体
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度 spaceLength控制线之间的空间
        linexAxis.enableGridDashedLine(10f, 10f, 0f);
//        linexAxis.setAxisMinimum(0f);//设置x轴的最小值
//        linexAxis.setAxisMaximum(10f);//设置最大值
        linexAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        //linexAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度

//        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
//        linexAxis.setLabelCount(5);
//        linexAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        linexAxis.setTextSize(24f);//设置轴标签的大小
//        linexAxis.setGridLineWidth(10f);//设置竖线大小
//        linexAxis.setGridColor(Color.RED);//设置竖线颜色
//        linexAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        linexAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        linexAxis.setValueFormatter();//格式化x轴标签显示字符

        /**
         * Y轴默认显示左右两个轴线
         */
        //获取右边的轴线
        YAxis rightAxis = lineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChart.getAxisLeft();
        //设置y轴坐标值颜色
        leftAxis.setTextColor(textColor);
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);


        Legend legend = lineChart.getLegend();//图例
        legend.setTextColor(chartColor);
        legend.setTextSize(10f);//设置文字大小
        legend.setForm(Legend.LegendForm.DEFAULT);//正方形，圆形或线
        legend.setFormSize(10f); // 设置Form的大小
        legend.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        legend.setFormLineWidth(10f);//设置Form的宽度

    }

    private void setLineChartData(List<MonitorHistoryBean.HistoryList> datas,String type,int showType) {
        ArrayList<Entry> values1 = new ArrayList<>();

        for(int i=0;i<datas.size();i++){
            switch(showType){
                case 0:
                    values1.add(new Entry(i, datas.get(i).getPowertotal()));
                    break;
                case 1:
                    values1.add(new Entry(i, datas.get(i).getWatts()));
                    break;
                case 2:
                    values1.add(new Entry(i, datas.get(i).getVoltage()));
                    break;
                case 3:
                    values1.add(new Entry(i, datas.get(i).getCurrent()));
                    break;
                case 4:
                    values1.add(new Entry(i, datas.get(i).getTemperature()));
                    break;
            }
        }



        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;


        //判断图表中原来是否有数据
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, type);
            set1.setColor(chartColor);
            set1.setCircleColor(chartColor);
            set1.setValueTextColor(chartColor);
            set1.setLineWidth(3f);//设置线宽
            set1.setCircleRadius(3f);//设置焦点圆心的大小
            //set1.enableDashedHighlightLine(10f, 1f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(0.5f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(true);//是否禁用点击高亮线
            set1.setHighLightColor(textColor);//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小

            set1.setDrawFilled(false);//设置禁用范围背景填充
            set1.setDrawValues(false);//在点上显示数值 默认true
            set1.setDrawCircles(false);//在点上画圆 默认true

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
            set1.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return super.getFormattedValue(value, axis);
                }
            });
            lineChart.getXAxis().setValueFormatter(new XAxisValueFormatter(datas));

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);

            lineChart.setMarker(new LineChartMarkerView(getActivity(), datas));
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
            lineChart.notifyDataSetChanged();
        }
    }

    /**
     * 柱形图
     */
    private void initBarChart() {
        barChart.getDescription().setEnabled(false);//设置描述不显示
        barChart.setHighlightFullBarEnabled(false);
        barChart.setNoDataText("没有数据");//没有数据时显示的文字
        barChart.setNoDataTextColor(textColor);//没有数据时显示文字的颜色
        barChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        barChart.setDrawBorders(false);//禁止绘制图表边框的线
        barChart.setTouchEnabled(true); // 设置是否可以触摸
        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        barChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        barChart.setScaleYEnabled(false); //是否可以缩放 仅y轴
        barChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        barChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        barChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        barChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        barChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止。
        barChart.animateXY(1000, 1000);
        //barChart.setVisibleXRangeMaximum(10);

        //显示的时候是按照多大的比率缩放显示，1f表示不放大缩小
        barChart.zoom((float) 1.5, 1f, 0, 0);

    }

    private void setLegend() {
        /***图例 标签 设置***/
        Legend legend = barChart.getLegend();
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        legend.setDrawInside(false);
        legend.setTextSize(10f);//设置文字大小
        legend.setForm(Legend.LegendForm.DEFAULT);//正方形，圆形或线
        legend.setFormSize(10f); // 设置Form的大小
        legend.setWordWrapEnabled(true);//是否支持自动换行 目前只支持BelowChartLeft, BelowChartRight, BelowChartCenter
        legend.setFormLineWidth(10f);//设置Form的宽度
        legend.setXOffset(24f);
        legend.setTextColor(textColor);

    }

    private void setYAxis() {
        //获取右边的轴线
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);
        rightAxis.setAxisMinimum(0f);

        //获取左边的轴线
        YAxis leftAxis = barChart.getAxisLeft();
        //设置y轴坐标值颜色
        leftAxis.setTextColor(textColor);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        //设置网格线为虚线效果
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);

    }

    private void setXAxis() {
        // X轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelCount(2);//开始时显示的x标签个数
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 位于底部
        xAxis.setDrawGridLines(false); // 不绘制X轴网格线
        xAxis.setAxisMinimum(-0.3f); // 最小值-0.3f，为了使左侧留出点空间
        xAxis.setGranularity(1f); // 间隔尺寸1
        xAxis.setTextSize(10f); // 文本大小14
        xAxis.setTextColor(textColor);
        //xAxis.setTypeface(Typeface.DEFAULT_BOLD); // 加粗字体
        xAxis.setValueFormatter(new ValueFormatter() { // 自定义值格式
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return 9 - (int) value + "0后";

            }
        });
    }

    private void setChartData(List<MonitorHistoryBean.HistoryList> datas,String type,int showType) {
        final List<BarEntry> yVals = new ArrayList<>();

        for(int i=0;i<datas.size();i++){
            switch(showType){
                case 0:
                    yVals.add(new BarEntry(i, datas.get(i).getPowertotal()));
                    break;
                case 1:
                    yVals.add(new BarEntry(i, datas.get(i).getWatts()));
                    break;
                case 2:
                    yVals.add(new BarEntry(i, datas.get(i).getVoltage()));
                    break;
                case 3:
                    yVals.add(new BarEntry(i, datas.get(i).getCurrent()));
                    break;
                case 4:
                    yVals.add(new BarEntry(i, datas.get(i).getTemperature()));
                    break;
            }

        }

        BarDataSet barDataSet;
        if (barChart.getData() != null && barChart.getData().getDataSetCount() > 0) {
            //获取数据1
            barDataSet = (BarDataSet) barChart.getData().getDataSetByIndex(0);
            barDataSet.setValues(yVals);
            //刷新数据
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
            barChart.invalidate();
        }else{
            barDataSet = new BarDataSet(yVals, type);
            barDataSet.setColor(barColor);
            barDataSet.setValueTextSize(10f);
            barDataSet.setBarBorderColor(barColor);
            barDataSet.setValueTextColor(barColor);
            barDataSet.setDrawValues(false);//设置柱状图顶部是否显示数值
            barDataSet.setBarBorderWidth(0.5f);//单条柱状图宽度

            barDataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return new DecimalFormat("##.0").format(value / (yVals.get((int) entry.getX()).getY() + value) * 100) + "%";
                }
            });

            barChart.getXAxis().setValueFormatter(new XAxisValueFormatter(datas));

            List<IBarDataSet> dataSets = new ArrayList<>();

            dataSets.add(barDataSet);

            BarData bardata = new BarData(dataSets);
            bardata.setBarWidth(0.4f);
            barChart.setMarker(new LineChartMarkerView(getActivity(), datas));
            barChart.setData(bardata);
            barChart.invalidate();
        }
    }


}
