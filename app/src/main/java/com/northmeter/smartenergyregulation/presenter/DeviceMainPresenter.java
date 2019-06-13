package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_DeviceMainPresenter;
import com.northmeter.smartenergyregulation.Interface.I_ShowDevice;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.CommonResponse;
import com.northmeter.smartenergyregulation.bean.MonitorHistoryBean;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyd on 2019/5/23.
 */

public class DeviceMainPresenter implements I_DeviceMainPresenter {
    private Context context;
    private I_ShowDevice i_showDevice;

    public DeviceMainPresenter(Context context,I_ShowDevice i_showDevice){
        this.context = context;
        this.i_showDevice = i_showDevice;
    }


    /**单个插座历史数据曲线*/
    @Override
    public void getMonitorHistory(int meterid, String startTime, String endTime) {
        OkGo.<MonitorHistoryBean>get(API.getMonitorHistory)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .params("meterid",meterid)
                .params("startTime",startTime)
                .params("endTime",endTime)
                .execute(new DialogCallback<MonitorHistoryBean>((Activity) context,MonitorHistoryBean.class) {
                    @Override
                    public void onSuccess(Response<MonitorHistoryBean> response) {
                        if (response.body().getCode()==0){
                            i_showDevice.showData(response.body().getList());
                        }else{
                            i_showDevice.returnMessage(response.body().getCode(),response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<MonitorHistoryBean> response) {
                        super.onError(response);
                        i_showDevice.returnMessage(-1,"连接失败，请稍后重试");
                    }
                });
    }

    /**用电用水控制-操作*/
    @Override
    public void controlRealSave(String commandID,String[] buildingIDs,String[] comAddress) {
        Map mapList = new HashMap();
        mapList.put("commandID",commandID);
        mapList.put("buildingIDs",buildingIDs);
        mapList.put("comAddress",comAddress);
        mapList.put("pcomContentData","01");//// 规则： 一路-01  二路-02  三路-04  三路全控-07
        mapList.put("typeid","001008");
        //mapList.put("versionid","");
        OkGo.<CommonResponse>post(API.controlRealSave)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new DialogCallback<CommonResponse>((Activity) context,CommonResponse.class) {
                    @Override
                    public void onSuccess(Response<CommonResponse> response) {
                        i_showDevice.returnMessage(response.body().getCode(),response.body().getMsg());
                    }

                    @Override
                    public void onError(Response<CommonResponse> response) {
                        super.onError(response);
                        i_showDevice.returnMessage(-1,"连接失败，请稍后重试");
                    }
                });
    }
}
