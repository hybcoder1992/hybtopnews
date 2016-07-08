package com.example.hybtopnews.adapter;

import java.util.List;

import com.example.hybtopnews.model.NewsEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NewsAdapter extends BaseAdapter {
	Activity activity;
	List<NewsEntity> newsList;
	protected ImageLoader imageLoader=ImageLoader.getInstance();
	public NewsAdapter(Activity activity, List<NewsEntity> newsList) {
		super();
		this.activity = activity;
		this.newsList = newsList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList!=null?newsList.size():0;
	}

	@Override
	public NewsEntity getItem(int position) {
		// TODO Auto-generated method stub
		if(newsList!=null && newsList.size()>0)
			return newsList.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}

}
