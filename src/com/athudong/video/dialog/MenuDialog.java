package com.athudong.video.dialog;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;
import com.nineoldandroids.animation.ObjectAnimator;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuDialog  extends Dialog implements android.view.View.OnClickListener{
	
	
	private View root;
	
	public MenuDialog(BaseActivity act) {
		super(act, R.style.MenuDialog);

		setContentView(R.layout.dialog_menu);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		Window win = getWindow();
		win.setGravity(Gravity.TOP);

		WindowManager.LayoutParams lp = win.getAttributes();
		lp.width = (act.getScreenWidth()); // 设置宽度
		lp.y = lp.y+act.getResources().getDimensionPixelSize(R.dimen.actionbar_height)+10;
		onWindowAttributesChanged(lp);
		
		
		findViewById(R.id.loginBtn_01).setOnClickListener(this);
		findViewById(R.id.loginBtn_02).setOnClickListener(this);
		findViewById(R.id.loginBtn_03).setOnClickListener(this);
		
		root = findViewById(R.id.menuLayout);
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}
	
	
	@Override
	public void dismiss() {
		ObjectAnimator.ofFloat(root, "scaleY", 1, 0, 0).setDuration(300).start();
		root.setVisibility(View.INVISIBLE);
		super.dismiss();
	}
	
	@Override
	public void show() {
		super.show();
		ObjectAnimator.ofFloat(root, "scaleY", 0, 1, 1).setDuration(300).start();
		root.setVisibility(View.VISIBLE);
	}
}
