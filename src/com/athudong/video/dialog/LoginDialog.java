package com.athudong.video.dialog;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;
import com.baidu.frontia.Frontia;
import com.baidu.frontia.FrontiaUser;
import com.baidu.frontia.api.FrontiaAuthorization;
import com.baidu.frontia.api.FrontiaAuthorization.MediaType;
import com.baidu.frontia.api.FrontiaAuthorizationListener.AuthorizationListener;
import com.nineoldandroids.animation.ObjectAnimator;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class LoginDialog extends Dialog implements android.view.View.OnClickListener {

	private View root;

	private BaseActivity act;

	public LoginDialog(BaseActivity act) {
		super(act, R.style.MenuDialog);
		this.act = act;
		setContentView(R.layout.dialog_menu);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		Window win = getWindow();
		win.setGravity(Gravity.TOP);

		WindowManager.LayoutParams lp = win.getAttributes();
		lp.width = (act.getScreenWidth()); // 设置宽度
		lp.y = lp.y + act.getResources().getDimensionPixelSize(R.dimen.actionbar_height) + 10;
		onWindowAttributesChanged(lp);

		findViewById(R.id.loginBtn_01).setOnClickListener(this);
		findViewById(R.id.loginBtn_02).setOnClickListener(this);
		findViewById(R.id.loginBtn_03).setOnClickListener(this);

		root = findViewById(R.id.menuLayout);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		dismiss();
		FrontiaAuthorization authorization = Frontia.getAuthorization();
		String type = "";
		if (id == R.id.loginBtn_01 || id == R.id.loginBtn_02) {
			type = MediaType.QQWEIBO.toString();
		} else if (id == R.id.loginBtn_03) {
			type = MediaType.SINAWEIBO.toString();
		}

		authorization.authorize(act, type, new AuthorizationListener() {
			@Override
			public void onSuccess(FrontiaUser result) {
				Log.d("log", "social id: " + result.getId() + "\n" + "token: " + result.getAccessToken() + "\n" + "expired: " + result.getExpiresIn());
			}

			@Override
			public void onFailure(int errCode, String errMsg) {
				Log.d("log", "错误为" + errCode + errMsg);
			}

			@Override
			public void onCancel() {
				Log.d("log", "cancel");
			}
		});

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
