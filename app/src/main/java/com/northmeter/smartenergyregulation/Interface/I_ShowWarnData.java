package com.northmeter.smartenergyregulation.Interface;

import com.northmeter.smartenergyregulation.bean.NowWarnBean;

import java.util.List;

/**
 * Created by dyd on 2019/6/5.
 */

public interface I_ShowWarnData {
    void returnMessage(String message);
    void showWarnList(List<NowWarnBean.WarnBean> list);
}
