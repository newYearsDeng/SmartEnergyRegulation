package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_HomePresenter;
import com.northmeter.smartenergyregulation.Interface.I_ShowHome;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.AllbuildingmonitorBean;
import com.northmeter.smartenergyregulation.bean.AllleafmonitorBean;
import com.northmeter.smartenergyregulation.bean.MonitorBean;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.http.JsonCallback;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyd on 2019/5/15.
 */

public class HomePresenter implements I_HomePresenter {
    private Context context;
    private I_ShowHome iShowData;

    public HomePresenter(Context context){
        this.context = context;
        this.iShowData = (I_ShowHome) context;
    }

    @Override
    public void getMonitor(String mtEquipmentTypeID) {
        Map mapList = new HashMap();
        mapList.put("typeid",mtEquipmentTypeID);
        OkGo.<MonitorBean>post(API.getmonitor)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new DialogCallback<MonitorBean>((Activity) context,MonitorBean.class) {
                    @Override
                    public void onSuccess(Response<MonitorBean> response) {
                        if(response.body().getCode()==0){
                            iShowData.showBuildList(response.body());
                        }else{
                            iShowData.returnMessage(response.body().getMsg());
                        }

                    }

                    @Override
                    public void onError(Response<MonitorBean> response) {
                        super.onError(response);
                        iShowData.returnMessage("连接失败，请稍后重试");
                    }
                });
    }



    @Override
    public void getallleafmonitor(String buildingid) {
        Map mapList = new HashMap();
        mapList.put("buildingid",buildingid);
        OkGo.<AllleafmonitorBean>post(API.getallleafmonitor)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new JsonCallback<AllleafmonitorBean>(AllleafmonitorBean.class) {
                    @Override
                    public void onSuccess(Response<AllleafmonitorBean> response) {
                        if(response.body().getCode()==0){
                            iShowData.showAllleafmonitor(response.body().getList());
                        }else{
                            iShowData.returnMessage(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<AllleafmonitorBean> response) {
                        super.onError(response);
                    }
                });
    }


    public void getallbuildingmonitor(String buildingId) {
        Map mapList = new HashMap();
        mapList.put("buildingId",buildingId);
        OkGo.<AllbuildingmonitorBean>post(API.getallbuildingmonitor)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new JsonCallback<AllbuildingmonitorBean>(AllbuildingmonitorBean.class) {
                    @Override
                    public void onSuccess(Response<AllbuildingmonitorBean> response) {
                        if(response.body().getCode()==0){
                            iShowData.showAllbuildingmonitor(response.body().getList());
                        }else{
                            iShowData.returnMessage(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<AllbuildingmonitorBean> response) {
                        super.onError(response);
                    }
                });
    }
}
