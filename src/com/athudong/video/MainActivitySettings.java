package com.athudong.video;

import com.athudong.video.dialog.ConfirmDialog;

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

		root.findViewById(R.id.uploadLayout).setVisibility(View.VISIBLE);

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
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.headImg) {
			toggleUploadLayout();
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
			
		}
	}

	private void toggleUploadLayout() {
		View view = root.findViewById(R.id.uploadLayout);

		if (view.getVisibility() == View.GONE) {

			Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -2.0f, Animation.RELATIVE_TO_SELF, 0.0f);
			anim.setDuration(250);
			view.setAnimation(anim);
			view.setVisibility(View.VISIBLE);
		}else{
			Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -2.0f);
			anim.setDuration(250);
			view.setAnimation(anim);
			view.setVisibility(View.GONE);
		}
	}
}
