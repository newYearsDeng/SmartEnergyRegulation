package com.northmeter.smartenergyregulation.Interface;

/**
 * Created by dyd on 2019/5/23.
 */

public interface I_DeviceMainPresenter {

    void getMonitorHistory(int meterid,String startTime,String endTime);
    void controlRealSave(String commandID,String[] buildingIDs,String[] comAddress);
}
