package com.athudong.video.dialog;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;

public class UploadResDialog extends Dialog implements android.view.View.OnClickListener {

	private BaseActivity activity;

	public UploadResDialog(BaseActivity activity) {
		super(activity, R.style.BottomDialogStyle);
		this.activity = activity;

		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.dialog_uploadres, null);

		addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		setContentView(layout);

		layout.findViewById(R.id.btn_01).setOnClickListener(this);
		layout.findViewById(R.id.btn_02).setOnClickListener(this);
		layout.findViewById(R.id.btn_03).setOnClickListener(this);
		layout.findViewById(R.id.btn_04).setOnClickListener(this);

		Window win = getWindow();
		win.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams lp = win.getAttributes();

		lp.width = activity.getScreenWidth();

		onWindowAttributesChanged(lp);

		setCancelable(true);
		setCanceledOnTouchOutside(true);
		
		findViewById(R.id.clickLayout).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		dismiss();
		if (id == R.id.btn_04) {
		} else if (id == R.id.btn_01) {
			new SelectResDialog(activity, SelectResDialog.TYPE_PIC).show();
		} else if (id == R.id.btn_02) {
			new SelectResDialog(activity, SelectResDialog.TYPE_VIDEO).show();
		} else if (id == R.id.btn_03) {
			new SelectResDialog(activity, SelectResDialog.TYPE_AUDIO).show();
		}
	}
}
