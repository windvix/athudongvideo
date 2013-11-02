package com.athudong.video;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.athudong.video.task.BaseTask;
import com.athudong.video.util.AppConst;

/**
 * 每次启动显示的欢迎界面
 */
public class WelcomeActivity extends BaseActivity{

	
	/**
	 * 因为在欢迎界面中，要判断是否是第一次启动，
	 * 从而决定是否跳转到引导界面。
	 * 所以必须重写onCreate方法
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//先判断Preference中保存的版本号是否小于当前的版本号。如果版本号小于当前的，则说明需要显示引导界面
		boolean isGuide = getDataInt(AppConst.PRE_VERSION_KEY)<getVersionCode();
		
		
		isGuide = false;
		if(isGuide){
			startGuide();
		}else{
			setContentView(R.layout.activity_welcome);
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					startMain();
				}
			}, 2000);
		}
	}
	
	/**
	 * 启动主界面
	 */
	private void startMain(){
		Intent intent  = new Intent(this, MainActivity.class);
		finish();
		startActivity(intent);
	}
	
	
	/**
	 * 启动引导界面
	 */
	private void startGuide(){
		Intent intent = new Intent(this, GuideActivity.class);
		finish();
		startActivity(intent);
	}
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		
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
