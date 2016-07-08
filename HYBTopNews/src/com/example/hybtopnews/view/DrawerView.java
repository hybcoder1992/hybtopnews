package com.example.hybtopnews.view;

import com.example.hybtopnews.R;
import com.example.hybtopnews.SettingActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 自定义slidemenu侧拉菜单类
 * */
public class DrawerView implements OnClickListener {
	Activity activity;
	SlidingMenu localSlidingMenu;
	TextView night_mode_text;
	RelativeLayout setting_btn;
	SwitchButton night_mode_btn;
	public DrawerView(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setting_btn:
			activity.startActivity(new Intent(activity,SettingActivity.class));
			activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;

		default:
			break;
		}
	}
	public SlidingMenu initSlidingMenu(){
		localSlidingMenu=new SlidingMenu(activity);
		localSlidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//设置左右滑菜单
		localSlidingMenu.setTouchModeAbove(SlidingMenu.SLIDING_WINDOW);//设置要使菜单滑动,触碰屏幕的范围,SLIDING_WINDOW是触摸边缘才会触发
		localSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片宽度
		localSlidingMenu.setShadowDrawable(R.drawable.shadow);//设置阴影图片
		localSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//slidingmenu滑出时主页面显示的剩余宽度
		localSlidingMenu.setFadeDegree(0.35f);//slidingmenu滑动时的渐变程度
		localSlidingMenu.attachToActivity(activity, SlidingMenu.RIGHT);//使slidingmenu附加在activity的右边
		localSlidingMenu.setMenu(R.layout.left_drawer_fragment);//设置menu布局文件
		localSlidingMenu.setSecondaryMenu(R.layout.profile_drawer_right);
		localSlidingMenu.setSecondaryShadowDrawable(R.drawable.shadowright);
		initView();
		return localSlidingMenu;
	}
	private void initView(){
		night_mode_btn=(SwitchButton)localSlidingMenu.findViewById(R.id.night_mode_btn);
		night_mode_text=(TextView)localSlidingMenu.findViewById(R.id.night_mode_text);
		night_mode_btn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
				}else {
					night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
				}
			}
		});
		night_mode_btn.setChecked(false);
		if(night_mode_btn.isChecked()){
			night_mode_text.setText(activity.getResources().getString(R.string.action_night_mode));
		}else {
			night_mode_text.setText(activity.getResources().getString(R.string.action_day_mode));
		}
		setting_btn=(RelativeLayout)localSlidingMenu.findViewById(R.id.setting_btn);
		setting_btn.setOnClickListener(this);
	}

}
