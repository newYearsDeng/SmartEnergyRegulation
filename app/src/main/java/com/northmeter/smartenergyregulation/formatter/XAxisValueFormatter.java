package com.northmeter.smartenergyregulation.formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.northmeter.smartenergyregulation.bean.MonitorHistoryBean;

import java.util.List;

/**
 * Created by dyd on 2019/5/23.
 */

public class XAxisValueFormatter extends ValueFormatter {
    private List<MonitorHistoryBean.HistoryList> datas;

    public XAxisValueFormatter(List<MonitorHistoryBean.HistoryList> datas){
        this.datas = datas;
    }


    @Override
    public String getFormattedValue(float value) {
        if (value < 0 || value > (datas.size() - 1)){//使得两侧柱子完全显示
            return "";
        }
        String tradeDate = datas.get((int) Math.abs(value)).getUpdatetime();
        return tradeDate;
    }
}
