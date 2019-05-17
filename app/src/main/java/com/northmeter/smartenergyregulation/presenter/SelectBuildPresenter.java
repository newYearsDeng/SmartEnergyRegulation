package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_SelectBuildPresenter;
import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.BuildListBean;
import com.northmeter.smartenergyregulation.bean.SelectBuildBean;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

/**
 * Created by dyd on 2019/5/17.
 */

public class SelectBuildPresenter implements I_SelectBuildPresenter {
    private Context context;
    private I_ShowData iShowData;

    public SelectBuildPresenter(Context context){
        this.context = context;
        this.iShowData = (I_ShowData) context;
    }


    @Override
    public void getTreeBuild(int withMeter, String mtEquipmentTypeID, String buildingID) {
        OkGo.<SelectBuildBean>get(API.getTreeBuild)
                .tag(this)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .headers("token", SaveUserInfo.getLoginUser(context).getToken())
                .params("withMeter",1)
                .params("mtEquipmentTypeID",mtEquipmentTypeID)
                .params("buildingID",buildingID)
                .execute(new DialogCallback<SelectBuildBean>((Activity) context,SelectBuildBean.class) {
                    @Override
                    public void onSuccess(Response<SelectBuildBean> response) {
                        if (response.body().getCode()==0){
                            iShowData.showData(response.body());
                        }else{
                            iShowData.returnMessage(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<SelectBuildBean> response) {
                        super.onError(response);
                        iShowData.returnMessage("连接失败，请稍后重试");
                    }
                });
    }
}
