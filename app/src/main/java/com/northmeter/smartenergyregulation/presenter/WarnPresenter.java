package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_ShowWarnData;
import com.northmeter.smartenergyregulation.Interface.I_WarnPresenter;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.HistoryWarnBean;
import com.northmeter.smartenergyregulation.bean.NowWarnBean;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyd on 2019/6/5.
 */

public class WarnPresenter implements I_WarnPresenter {
    private Context context;
    private I_ShowWarnData showWarnData;
    public WarnPresenter(Context context){
        this.context = context;
        showWarnData = (I_ShowWarnData) context;
    }

    @Override
    public void getNowWarn() {
        OkGo.<NowWarnBean>get(API.getnowwarn)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .execute(new DialogCallback<NowWarnBean>((Activity) context,NowWarnBean.class) {
                    @Override
                    public void onSuccess(Response<NowWarnBean> response) {
                        if(response.body().getCode()==0){
                            showWarnData.showWarnList(response.body().getList());
                        }else{
                            showWarnData.returnMessage(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<NowWarnBean> response) {
                        super.onError(response);
                        showWarnData.returnMessage("网络出错了，请稍后重试");
                    }
                });

    }

    @Override
    public void getHistoryWarn(int limit, int page, String eventName, String meterName, String startTime, String endTime, int isRead) {

        Map mapList = new HashMap();
        mapList.put("limit",limit);
        mapList.put("page",page);
        //mapList.put("eventName",eventName);
        //mapList.put("meterName",meterName);
        mapList.put("startTime",startTime);
        mapList.put("endTime",endTime);
        //mapList.put("isRead",isRead);

        OkGo.<HistoryWarnBean>post(API.gethistorywarn)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .upJson(new Gson().toJson(mapList))
                .execute(new DialogCallback<HistoryWarnBean>((Activity) context,HistoryWarnBean.class) {
                    @Override
                    public void onSuccess(Response<HistoryWarnBean> response) {
                        if(response.body().getCode()==0){
                            showWarnData.showWarnList(response.body().getPage().getList());
                        }else{
                            showWarnData.returnMessage(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<HistoryWarnBean> response) {
                        super.onError(response);
                        showWarnData.returnMessage("网络出错了，请稍后重试");
                    }
                });
    }
}
