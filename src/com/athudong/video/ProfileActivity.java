package com.athudong.video;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;

import com.athudong.video.task.BaseTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;


/**
 * 个人资料界面
 */
public class ProfileActivity extends BaseActivity implements OnClickListener, OnRefreshListener<ScrollView>{

	
	private PullToRefreshScrollView scrollView;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_profile);
		findViewById(R.id.back_btn).setOnClickListener(this);
		findViewById(R.id.sendGiftBtn).setOnClickListener(this);
		scrollView = (PullToRefreshScrollView)findViewById(R.id.scrollview);
		scrollView.setOnRefreshListener(this);
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
		if(id==R.id.back_btn){
			finish();
		}else if(id==R.id.sendGiftBtn){
			toast("内测中");
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				scrollView.onRefreshComplete();
			}
		},2000);
	}

}
