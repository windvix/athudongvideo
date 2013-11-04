package com.athudong.video;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.athudong.video.task.BaseTask;

public class RecentActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_recent);

		findViewById(R.id.back_btn).setOnClickListener(this);
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
	public void executeTaskResult(BaseTask task, Object data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.back_btn) {
			finish();
		}

	}

}
