package com.example.hybtopnews.tools;

import android.app.Activity;
import android.util.DisplayMetrics;

public class BaseTools {
	/**
	 * 获取屏幕宽度
	 * */
	public final static int getWindowsWidth(Activity activity){
		DisplayMetrics dMetrics=new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		return dMetrics.widthPixels;
	}
}
