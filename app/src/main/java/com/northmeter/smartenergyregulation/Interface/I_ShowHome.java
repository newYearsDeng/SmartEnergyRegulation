package com.northmeter.smartenergyregulation.Interface;

import com.northmeter.smartenergyregulation.bean.AllbuildingmonitorBean;
import com.northmeter.smartenergyregulation.bean.AllleafmonitorBean;
import com.northmeter.smartenergyregulation.bean.MonitorBean;

import java.util.List;

/**
 * Created by dyd on 2019/5/29.
 */

public interface I_ShowHome {
    void returnMessage(String message);
    void showBuildList(MonitorBean monitorBean);//楼栋智能插座详细信息，包含建筑树
    void showAllleafmonitor(List<AllleafmonitorBean.MeterList> meterList);//房间内智能插座详细信息
    void showAllbuildingmonitor(AllbuildingmonitorBean.AllleafmonitorList allleafmonitorList);//非房间内智能插座统计信息
}
