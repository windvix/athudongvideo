package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.athudong.video.adapter.RecentActAdapter;
import com.athudong.video.bean.RecentAct;
import com.athudong.video.task.BaseTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class RecentActivity extends BaseActivity implements OnClickListener, OnRefreshListener<ListView> {

	private PullToRefreshListView listView;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_recent);

		findViewById(R.id.back_btn).setOnClickListener(this);

		listView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

		List<RecentAct> list = new ArrayList<RecentAct>();

		for (int i = 0; i < 10; i++) {
			list.add(new RecentAct());
		}

		RecentActAdapter adapter = new RecentActAdapter(this, R.layout.list_recent_template, list);

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
