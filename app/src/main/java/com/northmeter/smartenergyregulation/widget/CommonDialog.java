package com.northmeter.smartenergyregulation.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.northmeter.smartenergyregulation.R;


/**
 * Created by dyd on 2018/7/20.
 */

public class CommonDialog<T> extends Dialog{

    private Context context;
    private int layoutID;
    private CallBack callBack;
    private String title;

    public CommonDialog(Context context, int layoutID, String title, CallBack callBack) {
        super(context,R.style.dialog);
        this.context = context;
        this.layoutID = layoutID;
        this.callBack = callBack;
        this.title = title;
    }
    public CommonDialog(Context context) {
        super(context, R.style.dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showConfirm();
    }


    private void showConfirm() {
        View dialogView = LayoutInflater.from(context).inflate(layoutID, null);
        //获得dialog的window窗口
        Window window = getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.CENTER);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.PopupAnimation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        setContentView(dialogView);

        TextView dialog_title = dialogView.findViewById(R.id.tv_dialog_title);
        dialog_title.setText(title);

        Button summit = dialogView.findViewById(R.id.btn_summit);
        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onConfirm();
            }
        });
        Button cancel = dialogView.findViewById(R.id.btn_cancle);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onCancel();
            }
        });
    }

    public interface CallBack {
         void onConfirm();
         void onCancel();
    }



//    private void showConfirm() {
//        final Dialog dialog = new Dialog(mContext, R.style.dialog);
//        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_update_version, null);
//        //获得dialog的window窗口
//        Window window = dialog.getWindow();
//        //设置dialog在屏幕底部
//        window.setGravity(Gravity.CENTER);
//        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
//        window.setWindowAnimations(R.style.dialog_from_bottom);
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        //获得window窗口的属性
//        WindowManager.LayoutParams lp = window.getAttributes();
//        //设置窗口宽度为充满全屏
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        //设置窗口高度为包裹内容
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        //将设置好的属性set回去
//        window.setAttributes(lp);
//        //将自定义布局加载到dialog上
//        dialog.setContentView(dialogView);
//
//        Button summit = dialogView.findViewById(R.id.btn_summit);
//        summit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent version_intent = new Intent();
//                version_intent.putExtra("ACTION", 1);
//                goActivity(VersionActivity.class,version_intent);
//                dialog.dismiss();
//            }
//        });
//        Button cancel = dialogView.findViewById(R.id.btn_cancel);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }

}
