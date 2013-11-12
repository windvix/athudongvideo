package com.athudong.video;

import android.os.Bundle;
import android.widget.VideoView;

import com.athudong.video.task.BaseTask;

public class VideoActivity extends BaseActivity{

	private VideoView videoview;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_video);
		
		videoview = (VideoView)findViewById(R.id.videoview);
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
