package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.adapter.MsgAdapter;
import com.athudong.video.bean.Msg;
import com.athudong.video.dialog.MenuDialog;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivityMsg implements OnRefreshListener<ListView>, OnLastItemVisibleListener , OnClickListener{

	private MainActivity act;
	private View root;

	private PullToRefreshListView mPullRefreshListView;

	private ListView actualListView;

	private List<Msg> msgList;

	private MsgAdapter adapter;

	public MainActivityMsg(MainActivity act, View root) {
		this.act = act;
		this.root = root;

		mPullRefreshListView = (PullToRefreshListView) root.findViewById(R.id.pull_refresh_list);

		// 点击下拉刷新要做的事情
		mPullRefreshListView.setOnRefreshListener(this);

		// 拉到最底部时监听
		mPullRefreshListView.setOnLastItemVisibleListener(this);

		// 得到实际的listView
		actualListView = mPullRefreshListView.getRefreshableView();
		act.registerForContextMenu(actualListView);

		// 最后就是给其listView加入数据了
		msgList = new ArrayList<Msg>();
		for (int i = 0; i < 20; i++) {
			msgList.add(new Msg());
		}
		adapter = new MsgAdapter(act, R.layout.one_msg_template, msgList);
		actualListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		root.findViewById(R.id.menuBtn).setOnClickListener(this);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				mPullRefreshListView.onRefreshComplete();
				adapter.notifyDataSetChanged();
			}
		}, 2000);
	}

	@Override
	public void onLastItemVisible() {
		MainActivityMsg.this.act.toast("已经到最底了");
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.menuBtn){
			MenuDialog dialog = new MenuDialog(act);
			dialog.show();
		}
	}

}
