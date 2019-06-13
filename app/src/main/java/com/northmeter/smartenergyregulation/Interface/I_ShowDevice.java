package com.northmeter.smartenergyregulation.Interface;

import com.northmeter.smartenergyregulation.bean.MonitorHistoryBean;

import java.util.List;

/**
 * Created by dyd on 2019/5/23.
 */

public interface I_ShowDevice {
    void showData(List<MonitorHistoryBean.HistoryList> datas);
    void returnMessage(int code,String msg);
}
