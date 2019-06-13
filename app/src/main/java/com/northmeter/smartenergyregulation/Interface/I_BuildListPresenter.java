package com.northmeter.smartenergyregulation.Interface;

/**
 * Created by dyd on 2019/5/22.
 */

public interface I_BuildListPresenter {
    void getMonitor(String typeid);

    void getallleafmonitor(String buildingid);

    void getallbuildingmonitor(String buildingId);
}
