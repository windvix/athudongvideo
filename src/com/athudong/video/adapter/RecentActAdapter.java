package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.RecentActivity;
import com.athudong.video.bean.RecentAct;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RecentActAdapter extends ArrayAdapter<RecentAct> {

	private RecentActivity act;

	private int viewId;

	public RecentActAdapter(RecentActivity act, int viewResourceId, List<RecentAct> objects) {
		super(act, viewResourceId, objects);
		this.act = act;
		this.viewId = viewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		if(convertView==null){
			convertView  = act.createView(viewId);
		}
		
		RecentAct a = getItem(position);
		
		
		
		
		return convertView;
	}

}
