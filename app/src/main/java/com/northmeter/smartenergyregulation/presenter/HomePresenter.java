package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_HomePresenter;
import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.activity.HomeActivity;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.BuildListBean;
import com.northmeter.smartenergyregulation.bean.LoginResponse;
import com.northmeter.smartenergyregulation.bean.UserInfo;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyd on 2019/5/15.
 */

public class HomePresenter implements I_HomePresenter {
    private Context context;
    private I_ShowData iShowData;

    public HomePresenter(Context context){
        this.context = context;
        this.iShowData = (I_ShowData) context;
    }

    @Override
    public void getBuildList(int withMeter,String mtEquipmentTypeID,String buildingID) {
        OkGo.<BuildListBean>get(API.getTreeBuild)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .params("withMeter",1)
                .params("mtEquipmentTypeID",mtEquipmentTypeID)
                .params("buildingID",buildingID)
                .execute(new DialogCallback<BuildListBean>((Activity) context,BuildListBean.class) {
                    @Override
                    public void onSuccess(Response<BuildListBean> response) {
                        if (response.body().getCode()==0){
                            iShowData.showData(response.body());
                        }else{
                            iShowData.returnMessage(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<BuildListBean> response) {
                        super.onError(response);
                        iShowData.returnMessage("连接失败，请稍后重试");
                    }
                });
    }

    @Override
    public void getMonitor(String mtEquipmentTypeID) {
        Map mapList = new HashMap();
        mapList.put("typeid",mtEquipmentTypeID);
        OkGo.<String>post(API.getmonitor)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iShowData.returnMessage("连接失败，请稍后重试");
                    }
                });
    }


    @Override
    public void getallleafmonitor(String buildingid) {
        Map mapList = new HashMap();
        mapList.put("buildingid",buildingid);
        OkGo.<String>post(API.getallleafmonitor)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iShowData.returnMessage("连接失败，请稍后重试");
                    }
                });
    }
}
