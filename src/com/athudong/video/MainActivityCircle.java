package com.athudong.video;

import com.athudong.video.dialog.MenuDialog;

import android.content.Intent;
import android.sax.StartElementListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.LinearLayout;

public class MainActivityCircle implements OnClickListener {

	private MainActivity act;
	private View root;

	public MainActivityCircle(MainActivity act, View root) {
		this.act = act;
		this.root = root;
		root.findViewById(R.id.circle_layout_01).setOnClickListener(this);
		root.findViewById(R.id.circle_layout_02).setOnClickListener(this);
		root.findViewById(R.id.circle_layout_03).setOnClickListener(this);
		root.findViewById(R.id.circle_layout_04).setOnClickListener(this);
		root.findViewById(R.id.menuBtn).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();

		if (id == R.id.circle_layout_01) {
			Intent intent = new Intent(act, ZoneActivity.class);
			act.startActivity(intent);
		} else if (id == R.id.circle_layout_02) {
			Intent intent = new Intent(act, FansActivity.class);
			act.startActivity(intent);
		} else if (id == R.id.circle_layout_03) {
			Intent intent = new Intent(act, RecentActivity.class);
			act.startActivity(intent);
		} else if (id == R.id.circle_layout_04) {
			Intent intent = new Intent(act, MsgBoardActivity.class);
			act.startActivity(intent);
		}else if(id==R.id.menuBtn){
			MenuDialog dialog = new MenuDialog(act);
			dialog.show();
		}

		else {

			act.toast(id + "内测中");
		}

	}

	private int top;

	private float r;

	public void resetLoc() {

		View bigCircleLayout = root.findViewById(R.id.bigCircleLayout);
		top = bigCircleLayout.getTop();
		r = bigCircleLayout.getHeight() / 2.0f;
		smallCircleR = root.findViewById(R.id.circle_layout_01).getHeight() / 2.0f;
		mainWidth1 = sin36 * r;
		mainHeight1 = cos36 * r;
		mainWidth2 = cos18 * r;
		mainHeight2 = sin18 * r;
		setCircle1();
		setCircle2();
		setCircle3();
		setCircle4();
		// 设置为不可见
		root.findViewById(R.id.circle_layout_01).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.circle_layout_02).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.circle_layout_03).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.circle_layout_04).setVisibility(View.INVISIBLE);

	}

	private float smallCircleR = 0;
	private float mainWidth1 = 0;
	private float mainHeight1 = 0;

	private float mainWidth2 = 0;
	private float mainHeight2 = 0;

	private float sin36 = 0.587785f;
	private float sin18 = 0.309017f;
	private float cos36 = 0.80901699f;
	private float cos18 = 0.9510565f;

	private void setCircle1() {
		final View view = root.findViewById(R.id.circle_layout_01);
		view.post(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("deprecation")
				LayoutParams lp = (LayoutParams) view.getLayoutParams();
				lp.x = (int) (mainWidth1 - smallCircleR);
				lp.y = (int) (top + r - mainHeight1 - smallCircleR);
				view.setLayoutParams(lp);
			}
		});
	}

	private void setCircle2() {
		final View view = root.findViewById(R.id.circle_layout_02);
		view.post(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("deprecation")
				LayoutParams lp = (LayoutParams) view.getLayoutParams();
				lp.x = (int) (mainWidth2 - smallCircleR);
				lp.y = (int) (top + r - mainHeight2 - smallCircleR);
				view.setLayoutParams(lp);
			}
		});
	}

	private void setCircle3() {
		final View view = root.findViewById(R.id.circle_layout_03);
		view.post(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("deprecation")
				LayoutParams lp = (LayoutParams) view.getLayoutParams();
				lp.x = (int) (mainWidth2 - smallCircleR);
				lp.y = (int) (top + r + mainHeight2 - smallCircleR);
				;
				view.setLayoutParams(lp);
			}
		});
	}

	private void setCircle4() {
		final View view = root.findViewById(R.id.circle_layout_04);
		view.post(new Runnable() {
			@Override
			public void run() {
				@SuppressWarnings("deprecation")
				LayoutParams lp = (LayoutParams) view.getLayoutParams();
				lp.x = (int) (mainWidth1 - smallCircleR);
				lp.y = (int) (top + r + mainHeight1 - smallCircleR);
				view.setLayoutParams(lp);
			}
		});
	}

}
