package com.northmeter.smartenergyregulation.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.northmeter.smartenergyregulation.Interface.I_ShowData;
import com.northmeter.smartenergyregulation.R;
import com.northmeter.smartenergyregulation.base.BaseActivity;
import com.northmeter.smartenergyregulation.bean.CommonResponse;
import com.northmeter.smartenergyregulation.bean.LoginResponse;
import com.northmeter.smartenergyregulation.presenter.LoginPresenter;
import com.northmeter.smartenergyregulation.utils.SaveUserInfo;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by dyd on 2019/5/9.
 * 用户登录
 */

public class LoginActivity extends BaseActivity implements I_ShowData {
    @BindView(R.id.et_login_name)
    EditText etLoginName;
    @BindView(R.id.et_login_passwd)
    EditText etLoginPasswd;
    private boolean showOrHide = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTitle() {
        super.setTitle();
    }

    @Override
    public void initData() {
        super.initData();
        etLoginName.setText(SaveUserInfo.getLoginUser(this).getUserName());
        etLoginPasswd.setText(SaveUserInfo.getLoginUser(this).getPassWord());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.iv_delete, R.id.iv_show, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_delete://清空输入框
                etLoginName.setText("");
                etLoginPasswd.setText("");
                break;
            case R.id.iv_show://密码显示或隐藏
                if (showOrHide){
                    etLoginPasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//密码
                    showOrHide = false;
                }else{
                    etLoginPasswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//明文
                    showOrHide = true;
                }
                break;
            case R.id.btn_login://登录 密码需要先使用MD5加密三次，再使用RAS加密一次
                new LoginPresenter(this).toLogin(
                        etLoginName.getText().toString(),etLoginPasswd.getText().toString());
                break;
        }
    }

    @Override
    public void returnMessage(String message) {
        showTosatMsg(message);
    }

    @Override
    public void showData(CommonResponse commonResponse) {
        LoginResponse loginResponse = (LoginResponse) commonResponse;
        if (loginResponse.getCode()==0){
            goActivity(HomeActivity.class);
        }
    }

}
