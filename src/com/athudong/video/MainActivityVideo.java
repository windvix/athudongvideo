package com.athudong.video;

import com.athudong.video.dialog.LoginDialog;

import android.view.View;
import android.view.View.OnClickListener;

public class MainActivityVideo implements OnClickListener{

	private MainActivity act;
	private View root;
	
	
	public MainActivityVideo(MainActivity act, View root ){
		this.act = act;
		this.root = root;
		root.findViewById(R.id.menuBtn).setOnClickListener(this);
		
	
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.menuBtn){
			LoginDialog dialog = new LoginDialog(act);
			dialog.show();
		}
	}
	
}
