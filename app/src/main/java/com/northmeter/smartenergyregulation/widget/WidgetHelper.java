package com.northmeter.smartenergyregulation.widget;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.RelativeLayout;

/**
 * 获取、设置控件高度宽度
 */
public class WidgetHelper {
	/**
	 * 获取控件width
	 */
	public static int getWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return (view.getMeasuredWidth());
	}

	/**
	 * 获取控件Height
	 */
	public static int getHeight(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		return (view.getMeasuredHeight());
	}

	public static int getWindowWidth(Activity aty) {
		DisplayMetrics dm = new DisplayMetrics();
		aty.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;// 获取分辨率宽度
	}

	/**
	 * 设置控件的位置X，并且不改变宽高 X为绝对位置，此时Y可能0
	 */
	public static void setLayoutX(View view, int x) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(x, margin.topMargin, x + margin.width,
				margin.bottomMargin);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}

	/**
	 * 设置控件的位置Y，并且不改变宽高 Y为绝对位置，此时X可能0
	 */
	public static void setLayoutY(View view, int y) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(margin.leftMargin, y, margin.rightMargin, y
				+ margin.height);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}

	/**
	 * 设置控件的位置YY，并且不改变宽高XY为绝对位置
	 */
	public static void setLayout(View view, int x, int y) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(x, y, x + margin.width, y + margin.height);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}
}