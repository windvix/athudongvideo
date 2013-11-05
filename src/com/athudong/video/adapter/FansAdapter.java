package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.FansActivity;
import com.athudong.video.R;
import com.athudong.video.bean.FansTun;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		ImageView img = (ImageView)convertView.findViewById(R.id.headImg);
		TextView name = (TextView)convertView.findViewById(R.id.nameTv);
		TextView size = (TextView)convertView.findViewById(R.id.sizeTv);
		TextView msg = (TextView) convertView.findViewById(R.id.msgTv);
		
		FansTun f = getItem(position);
		img.setImageResource(f.getId());
		name.setText(f.getName());
		size.setText(f.getSize());
		msg.setText(f.getMsg());
		
		return convertView;
	}
}
