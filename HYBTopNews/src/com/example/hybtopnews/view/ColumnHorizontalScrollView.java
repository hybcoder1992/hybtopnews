package com.example.hybtopnews.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;


public class ColumnHorizontalScrollView extends HorizontalScrollView {
	/** 传入整体布局  */
	private View ll_content;
	/** 传入更多栏目选择布局 */
	private View ll_more;
	/** 传入拖动栏布局 */
	private View rl_column;
	/** 左阴影图片 */
	private ImageView leftImage;
	/** 右阴影图片 */
	private ImageView rightImage;
	/** 屏幕宽度 */
	private int mScreenWitdh = 0;
	/** 父类的活动activity */
	private Activity activity;
	

	public ColumnHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public ColumnHorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ColumnHorizontalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param l Current horizontal scroll origin.
     * @param t Current vertical scroll origin.
     * @param oldl Previous horizontal scroll origin.
     * @param oldt Previous vertical scroll origin.
	 * */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		Log.d("hyb", "onScrollChanged");
		shade_ShowOrHide();
		if(!activity.isFinishing() && ll_content !=null && leftImage!=null && rightImage!=null && ll_more!=null && rl_column !=null){
			if(ll_content.getWidth() <= mScreenWitdh){
				leftImage.setVisibility(View.GONE);
				rightImage.setVisibility(View.GONE);
			}
		}else{
			return;
		}
		if(l ==0){
			leftImage.setVisibility(View.GONE);
			rightImage.setVisibility(View.VISIBLE);
			return;
		}
		if(ll_content.getWidth() - l + ll_more.getWidth() + rl_column.getLeft() == mScreenWitdh){
			leftImage.setVisibility(View.VISIBLE);
			rightImage.setVisibility(View.GONE);
			return;
		}
		leftImage.setVisibility(View.VISIBLE);
	   rightImage.setVisibility(View.VISIBLE);
	}
	/**
	 * 传入父类布局中的资源
	 * */
	public void setParameters(Activity activity,int mScreenWitdh,View ll_content,ImageView leftImage,ImageView rightImage,View ll_more,View rl_column){
		this.activity=activity;
		this.mScreenWitdh=mScreenWitdh;
		this.ll_content=ll_content;
		this.leftImage=leftImage;
		this.rightImage=rightImage;
		this.ll_more=ll_more;
		this.rl_column=rl_column;
	}
	/**
	 * 判断左右阴影的显示隐藏效果
	 * */
	public void shade_ShowOrHide(){
		if(!activity.isFinishing() && ll_content!=null){
			measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
			//如果整体宽度小于屏幕宽度,那左右阴影都隐藏
			if(mScreenWitdh>=getMeasuredWidth()){
				leftImage.setVisibility(View.GONE);
				rightImage.setVisibility(View.GONE);
			}else {
				return;
			}
			//如果滑动到最左边,左边阴影隐藏,右边显示
			if(getLeft()==0){
				leftImage.setVisibility(View.GONE);
				rightImage.setVisibility(View.VISIBLE);
				return;
			}
			//如果滑动到最右边时,左边阴影显示,右边隐藏
			if(getRight()==getMeasuredWidth()-mScreenWitdh){
				leftImage.setVisibility(View.VISIBLE);
				rightImage.setVisibility(View.GONE);
				return;
			}
			//否则说明在中间位置,左右阴影都显示
			leftImage.setVisibility(View.VISIBLE);
			rightImage.setVisibility(View.VISIBLE);
		}
	}
}
