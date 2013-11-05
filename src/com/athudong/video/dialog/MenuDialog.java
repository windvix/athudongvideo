package com.athudong.video.dialog;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MenuDialog  extends Dialog implements android.view.View.OnClickListener{
	
	public MenuDialog(BaseActivity act) {
		super(act, R.style.Dialog);

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
	}

	@Override
	public void onClick(View v) {
		dismiss();
	}
}
