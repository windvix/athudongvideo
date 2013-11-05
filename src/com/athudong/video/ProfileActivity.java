package com.athudong.video;

import android.os.Bundle;

import com.athudong.video.task.BaseTask;


/**
 * 个人资料界面
 */
public class ProfileActivity extends BaseActivity{

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_profile);
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

}
