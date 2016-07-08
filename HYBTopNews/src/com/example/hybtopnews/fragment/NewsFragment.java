package com.example.hybtopnews.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.hybtopnews.R;
import com.example.hybtopnews.model.NewsEntity;
import com.example.hybtopnews.tools.Constants;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NewsFragment extends Fragment {
	Activity activity;
	String text;
	List<NewsEntity> newsList=new ArrayList<NewsEntity>();
	ListView mListView;
	ImageView detail_loading;
	public final static int SET_NEWSLIST=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle data=getArguments();
		text=data!=null?data.getString("text"):"";
		initData();
	}
	private void initData(){
		newsList=Constants.getNewsList();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=View.inflate(getActivity(), R.layout.news_fragment, null);
		mListView=(ListView)view.findViewById(R.id.mListView);
		detail_loading=(ImageView)view.findViewById(R.id.detail_loading);
		TextView tView=(TextView)view.findViewById(R.id.item_textview);
		tView.setText(text);
		return view;
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		this.activity=activity;
		super.onAttach(activity);
	}
	//fragment可见时才加载数据
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		if(isVisibleToUser){
			//fragment可见时加载数据
			//if()
		}
		super.setUserVisibleHint(isVisibleToUser);
	}
}
