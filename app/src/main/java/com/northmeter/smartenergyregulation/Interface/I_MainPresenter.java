package com.northmeter.smartenergyregulation.Interface;

/**
 * Created by dyd on 2019/5/20.
 */

public interface I_MainPresenter {
    void getAnnunCharge(String starttime,String endtime);
    void getYearlyMount(String yeartime);
    void getEquipment();
}
