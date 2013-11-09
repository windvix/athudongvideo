package com.athudong.video;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.athudong.video.dialog.BigPictureDialog;
import com.athudong.video.task.BaseTask;


/**
 * 个人相册界面
 */
public class PersonPhotoActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_person_photo);
		findViewById(R.id.back_btn).setOnClickListener(this);
		findViewById(R.id.photo_01).setOnClickListener(this);
		findViewById(R.id.photo_02).setOnClickListener(this);
		findViewById(R.id.photo_03).setOnClickListener(this);
		findViewById(R.id.photo_04).setOnClickListener(this);
		findViewById(R.id.photo_05).setOnClickListener(this);
		findViewById(R.id.photo_06).setOnClickListener(this);
		findViewById(R.id.photo_07).setOnClickListener(this);
		findViewById(R.id.photo_08).setOnClickListener(this);
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
		if(id==R.id.back_btn){
			finish();
		}else{
			String url = "http://g.hiphotos.baidu.com/album/w%3D2048/sign=f946ec9f8b82b9013dadc43347b5a877/f3d3572c11dfa9ecbca991af63d0f703908fc187.jpg";
			
			
			String name = System.currentTimeMillis()+".jpg";
			
			//Intent intent = new Intent(this, BigPictureActivity.class);
			//intent.putExtra("url", url);
			//intent.putExtra(name, name);
			//startActivity(intent);
			BigPictureDialog dialog = new BigPictureDialog(this, url, name);
			dialog.show();	
		}
	}

}
