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

		for (int i = 0; i < 10; i++) {
			list.add(new FansTun());
		}

		FansAdapter adapter = new FansAdapter(this, R.layout.list_fans_template, list);

		listView.setAdapter(adapter);
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
