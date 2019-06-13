package com.northmeter.smartenergyregulation.Interface;

import com.northmeter.smartenergyregulation.bean.EquipmentBean;
import com.northmeter.smartenergyregulation.bean.MainResponse;

/**
 * Created by dyd on 2019/5/24.
 */

public interface I_ShowMain {
    void showReturnMsg(String msg);
    void showYearlyMount(MainResponse.YearlyMount yearlyMount);
    void showAnnunCharge(MainResponse.AnnunCharge annunCharge);
    void showEquipment(EquipmentBean.Equipment equipmentBean);
}
