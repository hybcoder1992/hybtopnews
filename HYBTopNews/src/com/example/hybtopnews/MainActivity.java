package com.example.hybtopnews;

import java.util.ArrayList;

import com.example.hybtopnews.adapter.NewsFragmentPagerAdapter;
import com.example.hybtopnews.fragment.NewsFragment;
import com.example.hybtopnews.model.NewsClassify;
import com.example.hybtopnews.tools.BaseTools;
import com.example.hybtopnews.tools.Constants;
import com.example.hybtopnews.view.ColumnHorizontalScrollView;
import com.example.hybtopnews.view.DrawerView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	LinearLayout mRadioGroup_content;
	LinearLayout ll_more_columns;
	RelativeLayout rl_column;
	ViewPager viewPager;
	ImageView button_more_columns;
	//新闻分类列表
	ArrayList<NewsClassify> newsClassify=new ArrayList<NewsClassify>();
	//当前选中的栏目
	int columnSelectIndex=0;
	//左阴影
	ImageView shade_left;
	//右阴影
	ImageView shade_right;
	//屏幕宽度
	int mScreenWidth=0;
	//item宽度
	int mItemWidth=0;
	//侧滑菜单
	SlidingMenu slide_drawer;
	ArrayList<Fragment> fragments=new ArrayList<Fragment>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mScreenWidth=BaseTools.getWindowsWidth(this);
		mItemWidth=mScreenWidth / 7;
		initView();
		initSlidingMenu();
	}
	private void initSlidingMenu(){
		slide_drawer=new DrawerView(this).initSlidingMenu();
	}
	private void initView(){
		mColumnHorizontalScrollView=(ColumnHorizontalScrollView)findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content=(LinearLayout)findViewById(R.id.mRadioGroup_content);
		ll_more_columns=(LinearLayout)findViewById(R.id.ll_more_columns);
		rl_column=(RelativeLayout)findViewById(R.id.rl_column);
		button_more_columns=(ImageView)findViewById(R.id.button_more_columns);
		viewPager=(ViewPager)findViewById(R.id.mViewPager);
		shade_left=(ImageView)findViewById(R.id.shade_left);
		shade_right=(ImageView)findViewById(R.id.shade_right);
		setChangeView();
	}
	/**
	 * 当栏目组发生变化时调用
	 * */
	private void setChangeView(){
		initColumnData();
		initTabColumn();
		initFragment();
	}
	private void initFragment()
	{
		int count=newsClassify.size();
		for(int i=0;i<count;i++){
			Bundle data=new Bundle();
			data.putString("text", newsClassify.get(i).getTitle());
			NewsFragment fragment=new NewsFragment();
			fragment.setArguments(data);
			fragments.add(fragment);
		}
		NewsFragmentPagerAdapter mAdapter=new NewsFragmentPagerAdapter(getSupportFragmentManager(),fragments);
		viewPager.setAdapter(mAdapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(position);
				selectTab(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	/** 
	 *  选择的Column里面的Tab
	 * */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
			View checkView = mRadioGroup_content.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
			
		}
		//判断是否选中
		for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}
	/**
	 * 获取栏目数据
	 * */
	private void initColumnData(){
		newsClassify=Constants.getData();
	}
	/**
	 * 初始化栏目项
	 * */
	@SuppressLint("NewApi")
	private void initTabColumn(){
		mRadioGroup_content.removeAllViews();
		int count=newsClassify.size();
		mColumnHorizontalScrollView.setParameters(this, this.mScreenWidth, this.mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
		for(int i=0;i<count;i++)
		{
			LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(mItemWidth, LayoutParams.WRAP_CONTENT);
			params.leftMargin=5;
			params.rightMargin=5;
			TextView localTextView=new TextView(this);
			localTextView.setTextAppearance(this,R.style.top_category_scroll_view_item_text);
			localTextView.setBackgroundResource(R.drawable.radio_buttong_bg);//设置栏目每个item的背景颜色,选中时为红色,没选中时透明
			localTextView.setGravity(Gravity.CENTER);
			localTextView.setPadding(5, 0,5, 0);
			localTextView.setId(i);
			localTextView.setText(newsClassify.get(i).getTitle());
			localTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
			if(this.columnSelectIndex==i){
				localTextView.setSelected(true);
			}
			localTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					for(int i=0;i<mRadioGroup_content.getChildCount();i++){
						View localView=mRadioGroup_content.getChildAt(i);
						if(v!=localView){
							localView.setSelected(false);
						}else {
							localView.setSelected(true);
							viewPager.setCurrentItem(i);
						}
					}
				}
			});
			mRadioGroup_content.addView(localTextView,i,params);
		}
	}
}
