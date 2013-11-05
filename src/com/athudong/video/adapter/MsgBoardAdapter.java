package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.MsgBoardActivity;
import com.athudong.video.bean.MsgBoard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MsgBoardAdapter extends ArrayAdapter<MsgBoard> {

	private MsgBoardActivity act;

	private int viewId;

	public MsgBoardAdapter(MsgBoardActivity act, int viewId, List<MsgBoard> objects) {
		super(act, viewId, objects);
		this.act = act;
		this.viewId = viewId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = act.createView(viewId);
		}

		return convertView;
	}
}
