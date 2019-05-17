package com.northmeter.smartenergyregulation.Interface;

/**
 * Created by dyd on 2019/5/15.
 */

public interface I_HomePresenter {
    void getBuildList(int withMeter,String mtEquipmentTypeID,String buildingID);
    void getMonitor(String mtEquipmentTypeID);

    void getallleafmonitor(String buildingid);
}
