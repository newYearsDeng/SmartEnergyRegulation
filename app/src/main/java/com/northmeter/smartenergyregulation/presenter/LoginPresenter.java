package com.northmeter.smartenergyregulation.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.northmeter.smartenergyregulation.Interface.I_LoginPresenter;
import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.base.API;
import com.northmeter.smartenergyregulation.bean.LoginResponse;
import com.northmeter.smartenergyregulation.bean.UserInfo;
import com.northmeter.smartenergyregulation.http.DialogCallback;
import com.northmeter.smartenergyregulation.utils.EncryptRSA;
import com.northmeter.smartenergyregulation.utils.MD5;
import com.northmeter.smartenergyregulation.utils.RsaUtils;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by dyd on 2019/5/13.
 */

public class LoginPresenter implements I_LoginPresenter {
    Context context;
    I_ShowData iShowData;
    public LoginPresenter(Context context){
        this.context = context;
        iShowData = (I_ShowData) context;
    }



    @Override
    public void toLogin(final String userName, final String passWord) {
        String md5Str = MD5.getMessageDigest(MD5.getMessageDigest(MD5.getMessageDigest(passWord.getBytes()).getBytes()).getBytes());
        System.out.println("RsaUtils-------"+ RsaUtils.encrypt(md5Str));
        System.out.println("EncryptRSA-------"+ EncryptRSA.encrypt(md5Str));

        Map mapList = new HashMap();
        mapList.put("username",userName);
        mapList.put("password",RsaUtils.encrypt(md5Str));
        OkGo.<LoginResponse>post(API.sysLogin)
                .tag(this)
                .upJson(new Gson().toJson(mapList))
                .execute(new DialogCallback<LoginResponse>((Activity) context,LoginResponse.class) {
                    @Override
                    public void onSuccess(Response<LoginResponse> response) {
                        int code = response.body().getCode();
                        if(code == 0){
                            UserInfo userInfo = new UserInfo();
                            userInfo.setUserName(userName);
                            userInfo.setPassWord(passWord);
                            userInfo.setExpire(response.body().getExpire());
                            userInfo.setToken(response.body().getToken());
                            SaveUserInfo.saveLoginUser(context,userInfo);
                            iShowData.showData(response.body());
                        }else{
                            iShowData.returnMessage(response.body().getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<LoginResponse> response) {
                        super.onError(response);
                        iShowData.returnMessage("连接失败，请稍后重试");
                    }
                });
    }
}
