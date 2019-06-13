package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_BuildListPresenter;
import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.AllleafmonitorBean;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyd on 2019/5/22.
 */

public class BuildListPresenter implements I_BuildListPresenter {
    private Context context;
    I_ShowData showData;
    public BuildListPresenter(Context context){
        this.context = context;
        showData = (I_ShowData) context;
    }


    @Override
    public void getMonitor(String typeid) {
        Map mapList = new HashMap();
        mapList.put("typeid",typeid);

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
                .execute(new DialogCallback<AllleafmonitorBean>((Activity) context,AllleafmonitorBean.class) {
                    @Override
                    public void onSuccess(Response<AllleafmonitorBean> response) {
                        showData.showData(response.body());
                    }

                    @Override
                    public void onError(Response<AllleafmonitorBean> response) {
                        super.onError(response);
                    }
                });
    }

    @Override
    public void getallbuildingmonitor(String buildingId) {
        Map mapList = new HashMap();
        mapList.put("buildingId",buildingId);
        OkGo.<String>post(API.getallbuildingmonitor)
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
                    }
                });
    }


}
