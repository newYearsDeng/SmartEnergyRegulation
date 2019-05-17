/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.northmeter.smartenergyregulation.http;

import android.app.Activity;
import android.app.ProgressDialog;

import com.lzy.okgo.request.base.Request;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.lang.reflect.Type;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    private ProgressDialog dialog;
    private LoadingDialog mLoadingDialog;

    private void initDialog(Activity activity) {
        /*dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");*/

        mLoadingDialog=new LoadingDialog(activity);
        mLoadingDialog.setLoadingText("加载中,请稍后...");
    }

    public DialogCallback(Activity activity, Class<T> clazz) {
        super(clazz);
        initDialog(activity);
    }

    public DialogCallback(Activity activity, Type type) {
        super(type);
        initDialog(activity);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        /*if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }*/
        if (mLoadingDialog!=null){
            mLoadingDialog.show();
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        /*if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }*/
        if (mLoadingDialog!=null){
            mLoadingDialog.close();
        }
    }
}
