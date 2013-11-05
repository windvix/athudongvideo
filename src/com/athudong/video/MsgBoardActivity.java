package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.adapter.MsgBoardAdapter;
import com.athudong.video.bean.MsgBoard;
import com.athudong.video.task.BaseTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MsgBoardActivity extends BaseActivity implements OnClickListener, OnRefreshListener<ListView> {

	private PullToRefreshListView listView;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_msg_board);
		findViewById(R.id.back_btn).setOnClickListener(this);

		listView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

		List<MsgBoard> list = new ArrayList<MsgBoard>();

		for (int i = 0; i < 10; i++) {
			list.add(new MsgBoard());
		}

		MsgBoardAdapter adapter = new MsgBoardAdapter(this, R.layout.list_msg_board_template, list);

		listView.setAdapter(adapter);

		listView.setOnRefreshListener(this);
	}

	@Override
	protected void beforeEveryVisable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterEveryInVisable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeDestory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.back_btn) {
			finish();
		}
	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {
		// TODO Auto-generated method stub

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
