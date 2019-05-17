package com.northmeter.smartenergyregulation.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.northmeter.smartenergyregulation.R;


/** PopuHelper */
@SuppressLint("NewApi")
public class PopupHelper {
	public PopupMenu popupMenu = null;
	public PopupWindow popupWindow = null;

	public void OnPopuClick(View view, Context context, Integer layout,
			PopupMenu.OnMenuItemClickListener listener) {
		popupMenu = new PopupMenu(context, view);
		popupMenu.getMenuInflater().inflate(layout, popupMenu.getMenu());
		popupMenu.setOnMenuItemClickListener(listener);
		popupMenu.show();
	}

	/**
	 * @param view
	 *            触发视图
	 * @param activity
	 *            设置背景
	 * @param touchListener
	 *            点击消失
	 * @param keyListener
	 *            按键消失
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public PopupWindow getWindow_WRAP(View view, Context context) {
		getWindow(view, context, ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		return popupWindow;
	}

	public PopupWindow getWindow_ALLWRAP(View view, Context context) {
		getWindow(view, context, ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		return popupWindow;
	}

	/**
	 * @param view
	 *            触发视图
	 * @param activity
	 *            设置背景
	 * @param touchListener
	 *            点击消失
	 * @param keyListener
	 *            按键消失
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public PopupWindow getWindow_FILL(View view, Context context) {
		getWindow(view, context, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		return popupWindow;
	}

	@SuppressWarnings("deprecation")
	private PopupWindow getWindow(View view, Context context, int width,
			int height) {
		popupWindow = new PopupWindow(view, width, height);
		popupWindow.setAnimationStyle(R.style.PopupAnimation);
		// popupWindow.setBackgroundDrawable(context.getResources().getDrawable(
		// R.drawable.noselected_white));// 这句是关键，响应返回键必须的语句
		popupWindow.setFocusable(true);
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);// 点击边框外不消失
		view.setFocusableInTouchMode(true);
		// ColorDrawable dw = new ColorDrawable(0xb0000000);
		// 设置SelectPicPopupWindow弹出窗体的背景0xb0000000
		// popupWindow.setBackgroundDrawable(dw);
		TouchListener listener = new TouchListener(popupWindow);
		popupWindow.setTouchInterceptor(listener);
		view.setOnKeyListener(listener);
		return popupWindow;
	}
}
