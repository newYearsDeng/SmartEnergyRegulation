package com.northmeter.smartenergyregulation.widget;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;

/** PopupWindow TouchListener */
public class TouchListener implements OnTouchListener, OnKeyListener {
	private PopupWindow popupWindowtemp;

	public TouchListener(PopupWindow popupWindowtemp) {
		super();
		this.popupWindowtemp = popupWindowtemp;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
			popupWindowtemp.dismiss();
		return false;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& keyCode == KeyEvent.KEYCODE_MENU
				|| event.getAction() == KeyEvent.ACTION_DOWN
				&& keyCode == KeyEvent.KEYCODE_BACK) {
			popupWindowtemp.dismiss();
			return true;
		}
		return false;
	}
}