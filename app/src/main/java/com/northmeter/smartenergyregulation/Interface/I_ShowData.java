package com.northmeter.smartenergyregulation.Interface;

import com.northmeter.smartenergyregulation.bean.CommonResponse;

/**
 * Created by dyd on 2019/5/13.
 */

public interface I_ShowData<T extends CommonResponse> {
    void returnMessage(String message);
    void showData(T t);
}
