package com.athudong.video.component;

import java.util.List;

import com.athudong.video.MainActivity;
import com.athudong.video.R;
import com.athudong.video.bean.Msg;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MsgAdapter extends ArrayAdapter<Msg> {

	private MainActivity act;
	private int viewId;

	private List<Msg> objects;

	private int size;

	public MsgAdapter(MainActivity act, int viewResourceId, List<Msg> objects) {
		super(act, viewResourceId, objects);
		this.act = act;
		this.viewId = viewResourceId;
		this.objects = objects;
		this.size = objects.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// 如果位置的视图不为空，那么就要知道它是哪个模版的视图
		// 以下为当前是最后一个位置的视图。且其位置为空时
		if (convertView != null) {
			if (position == (size - 1)) {
				// 判断是不是结尾视图，不是结尾视图，则要生成结尾视图
				if (convertView.findViewById(R.id.listEnd) == null) {
					convertView = act.createView(R.layout.one_msg_end_template);
				}
			} else if (position == 0) {
				if (convertView.findViewById(R.id.listHead) == null) {
					convertView = act.createView(R.layout.one_msg_head_template);
				}
			} else {
				if (convertView.findViewById(R.id.listEnd) != null || convertView.findViewById(R.id.listHead)!=null) {
					convertView = act.createView(viewId);
				}
			}
		} else {
			if (position == (size - 1)) { 
				convertView = act.createView(R.layout.one_msg_end_template);
			} else if (position == 0) {
				convertView = act.createView(R.layout.one_msg_head_template);
			}

			else {
				convertView = act.createView(viewId);
			}
		}

		return convertView;
	}

}
