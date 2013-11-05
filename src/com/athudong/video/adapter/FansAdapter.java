package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.FansActivity;
import com.athudong.video.bean.FansTun;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class FansAdapter extends ArrayAdapter<FansTun>{

	private int viewId;
	
	private FansActivity act;
	
	
	
	
	public FansAdapter(FansActivity act, int viewResourceId, List<FansTun> objects) {
		super(act, viewResourceId, objects);
		this.act = act;
		this.viewId = viewResourceId;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = act.createView(viewId);
		}
		return convertView;
	}
}
