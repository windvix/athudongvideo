package com.athudong.video;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.athudong.video.task.BaseTask;

public class FansActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_fans);
		findViewById(R.id.back_btn).setOnClickListener(this);
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

}
