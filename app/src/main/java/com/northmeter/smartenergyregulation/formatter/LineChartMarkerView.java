package com.northmeter.smartenergyregulation.formatter;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.bean.MonitorHistoryBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dyd on 2019/6/6.
 */

public class LineChartMarkerView extends MarkerView {
    private TextView text_value;
    private TextView text_date;
    private List<MonitorHistoryBean.HistoryList> datas;
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public LineChartMarkerView(Context context, List<MonitorHistoryBean.HistoryList> datas) {
        super(context, R.layout.item_marker_view);
        text_value = findViewById(R.id.text_value);
        text_date = findViewById(R.id.text_date);
        this.datas = datas;
    }

    //回调函数每次MarkerView重绘,可以用来更新内容(用户界面)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        text_value.setText(e.getY()+"");
        text_date.setText(datas.get((int) e.getX()).getUpdatetime());
        super.refreshContent(e, highlight);
    }
    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
