package com.athudong.video;

import com.athudong.video.dialog.ConfirmDialog;
import com.athudong.video.dialog.MenuDialog;
import com.athudong.video.dialog.UploadResDialog;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class MainActivitySettings implements OnClickListener {
	private MainActivity act;
	private View root;

	public MainActivitySettings(MainActivity act, View root) {
		this.act = act;
		this.root = root;


		root.findViewById(R.id.headImg).setOnClickListener(this);
		root.findViewById(R.id.exitBtn).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_account).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_mall).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_qq).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_ren).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_sina).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_sohu).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_vip).setOnClickListener(this);
		root.findViewById(R.id.setting_btn_wallet).setOnClickListener(this);
		
		root.findViewById(R.id.menuBtn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.headImg) {
			new UploadResDialog(act).show();
		}else if (id==R.id.exitBtn){
			final ConfirmDialog dialog= new ConfirmDialog(act,"确定退出吗？");
			dialog.show();
			dialog.getLeftBtn().setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			dialog.getRightBtn().setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					act.finish();
					System.exit(0);
				}
			});
			
		}else if(id==R.id.menuBtn){
			MenuDialog dialog = new MenuDialog(act);
			dialog.show();
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		act.toast("上传完成");
	};
}
