package com.athudong.video.dialog;

import com.athudong.video.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 确认对话框
 */
public class ConfirmDialog extends Dialog {

	
	public ConfirmDialog(Context context, String msg) {
		super(context, R.style.DimDialog);
		setCancelable(true);
		setCanceledOnTouchOutside(false);
		this.msg = msg;
	}

	private String msg;

	
	public void setMessage(CharSequence message) {
		this.msg = message.toString();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		}
		return super.onKeyDown(keyCode, event);
	}

	private View leftBtn;
	private View rightBtn;
	private TextView contentTv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_confirm);
		contentTv = (TextView)findViewById(R.id.confirm_dialog_content_tv);
		contentTv.setText(msg);
		leftBtn = findViewById(R.id.confirm_dialog_left_btn);
		rightBtn = findViewById(R.id.confirm_dialog_right_btn);
	}
	
	public View getLeftBtn(){
		return leftBtn;
	}
	
	public View getRightBtn(){
		return rightBtn;
	}
}
