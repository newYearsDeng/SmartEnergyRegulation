package com.northmeter.smartenergyregulation.Interface;

/**
 * Created by dyd on 2019/6/5.
 */

public interface I_WarnPresenter {
    void getNowWarn();
    void getHistoryWarn(int limit,int page,String eventName,String meterName,String startTime,String endTime,int isRead);
}
