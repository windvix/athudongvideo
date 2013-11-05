package com.athudong.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.athudong.video.task.BaseTask;

public class StarInfoActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_star_info);
		findViewById(R.id.back_btn).setOnClickListener(this);
		
		findViewById(R.id.star_main_btn_01).setOnClickListener(this);
		findViewById(R.id.star_main_btn_02).setOnClickListener(this);
		findViewById(R.id.star_main_btn_03).setOnClickListener(this);
		findViewById(R.id.star_main_btn_04).setOnClickListener(this);
		
		findViewById(R.id.star_msg_btn_01).setOnClickListener(this);
		findViewById(R.id.star_msg_btn_02).setOnClickListener(this);
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
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.back_btn) {
			finish();
		}else if(id==R.id.star_main_btn_01){
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
			
		}else if(id==R.id.star_main_btn_02){
			Intent intent = new Intent(this, PersonResourceActivity.class);
			startActivity(intent);
			
		}else if(id==R.id.star_main_btn_03){
			Intent intent = new Intent(this, PersonPhotoActivity.class);
			startActivity(intent);
			
		}else if(id==R.id.star_main_btn_04){
			toast("关注+1");
		}
	}

}
