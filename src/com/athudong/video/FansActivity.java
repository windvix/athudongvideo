package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.athudong.video.adapter.FansAdapter;
import com.athudong.video.bean.FansTun;
import com.athudong.video.task.BaseTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class FansActivity extends BaseActivity implements OnClickListener, OnRefreshListener<ListView> {

	private PullToRefreshListView listView;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_fans);
		findViewById(R.id.back_btn).setOnClickListener(this);

		listView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

		List<FansTun> list = new ArrayList<FansTun>();

		for (int i = 0; i < 2; i++) {
			FansTun f1 = new FansTun();
			FansTun f2 = new FansTun();
			FansTun f3 = new FansTun();
			FansTun f4 = new FansTun();
			FansTun f5 = new FansTun();
			f1.setId(R.drawable.fans_01);
			f1.setName("Jone Li声援会");
			f1.setSize("300/300");
			f1.setMsg("本群已满,请加Jone Li第二个声援会");
			
			f2.setId(R.drawable.fans_05);
			f2.setName("妮妮粉");
			f2.setSize("300/300");
			f2.setMsg("本群已满,请加Jone Li第二个声援会");
			
			f3.setId(R.drawable.fans_03);
			f3.setName("Fans for Lily");
			f3.setSize("267/300");
			f3.setMsg("支持Lily");
			
			f4.setId(R.drawable.fans_04);
			f4.setName("姚子蜜罐");
			f4.setSize("268/300");
			f4.setMsg("姚子的蜜蜜,可爱的姚蜜");
			
			f5.setId(R.drawable.fans_02);
			f5.setName("熊猫人");
			f5.setSize("190/200");
			f5.setMsg("熊猫熊猫,顶尖的熊猫人");
			
			list.add(f1);
			list.add(f2);
			list.add(f3);
			list.add(f4);
			list.add(f5);
		}
		FansAdapter adapter = new FansAdapter(this, R.layout.list_fans_template, list);

		listView.setAdapter(adapter);
		listView.setOnRefreshListener(this);
	}

	@Override
	protected void beforeEveryVisable() {

	}

	@Override
	protected void afterEveryInVisable() {

	}

	@Override
	protected void beforeDestory() {

	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.back_btn) {
			finish();
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				listView.onRefreshComplete();
			}
		}, 2000);
	}

}
