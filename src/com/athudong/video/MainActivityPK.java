package com.athudong.video;

import com.athudong.video.component.PKAnim;
import com.athudong.video.component.PKAnim2;
import com.athudong.video.dialog.LoginDialog;
import com.nineoldandroids.animation.ObjectAnimator;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class MainActivityPK implements OnClickListener {

	private MainActivity act;

	private View root;

	private View addOneLeft;
	private View addOneRight;

	private ImageView leftHeadImg;
	private ImageView rightHeadImg;
	
	private TextView topNameTv;
	private TextView bottomNameTv;

	public MainActivityPK(MainActivity act, View root) {
		this.act = act;
		this.root = root;

		root.findViewById(R.id.realTopBtn01).setOnClickListener(this);
		root.findViewById(R.id.realTopBtn02).setOnClickListener(this);
		root.findViewById(R.id.realTopBtn03).setOnClickListener(this);
		root.findViewById(R.id.realTopBtn04).setOnClickListener(this);

		root.findViewById(R.id.realBottomBtn01).setOnClickListener(this);
		root.findViewById(R.id.realBottomBtn02).setOnClickListener(this);
		root.findViewById(R.id.realBottomBtn03).setOnClickListener(this);
		root.findViewById(R.id.realBottomBtn04).setOnClickListener(this);

		root.findViewById(R.id.leftThumbLayout).setOnClickListener(this);
		root.findViewById(R.id.rightThumbLayout).setOnClickListener(this);

		root.findViewById(R.id.start_thumb_btn).setOnClickListener(this);

		root.findViewById(R.id.menuBtn).setOnClickListener(this);

		addOneLeft = root.findViewById(R.id.leftAddOneTv);
		addOneRight = root.findViewById(R.id.rightAddOneTv);
		leftHeadImg = (ImageView) root.findViewById(R.id.leftHeadImg);
		rightHeadImg = (ImageView)root.findViewById(R.id.rightHeadImg);
		
		topNameTv = (TextView)root.findViewById(R.id.topNameTv);
		bottomNameTv = (TextView)root.findViewById(R.id.bottomNameTv);
		reset();
	}

	/**
	 * 重置
	 */
	public void reset() {
		addOneLeft.setVisibility(View.INVISIBLE);
		addOneRight.setVisibility(View.INVISIBLE);
		root.findViewById(R.id.realTopBtn01).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.realTopBtn02).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.realTopBtn03).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.realTopBtn04).setVisibility(View.INVISIBLE);

		root.findViewById(R.id.realBottomBtn01).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.realBottomBtn02).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.realBottomBtn03).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.realBottomBtn04).setVisibility(View.INVISIBLE);

		root.findViewById(R.id.leftThumbLayout).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.rightThumbLayout).setVisibility(View.INVISIBLE);
	}

	private boolean add = false;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.start_thumb_btn) {
			View left = act.findViewById(R.id.rightThumbLayout);
			View right = act.findViewById(R.id.leftThumbLayout);
			if (left.getVisibility() == View.INVISIBLE) {
				thumbAnimShow(left, 2.0f);
			}
			if (right.getVisibility() == View.INVISIBLE) {
				thumbAnimShow(right, -2.0f);
			}
		} else if (id == R.id.realBottomBtn02 || id == R.id.realTopBtn03) {
			Intent intent = new Intent(act, PersonResourceActivity.class);
			act.startActivity(intent);
		} else if (id == R.id.realTopBtn02 || id == R.id.realBottomBtn03) {
			Intent intent = new Intent(act, PersonPhotoActivity.class);
			act.startActivity(intent);
		} else if (id == R.id.realBottomBtn04 || id == R.id.realTopBtn01) {
			if (add) {
				act.toast("已关注");
			} else {
				act.toast("关注+1");
				add = true;
			}
		} else if (id == R.id.realBottomBtn01 || id == R.id.realTopBtn04) {
			Intent intent = new Intent(act, ProfileActivity.class);
			act.startActivity(intent);
		} else if (id == R.id.menuBtn) {
			LoginDialog dialog = new LoginDialog(act);
			dialog.show();
		} else if (id == R.id.leftThumbLayout) {
			if(!isThumbing){
				thumbLeft();
			}else{
				act.toast("稍等一会儿~");
			}
		} else if (id == R.id.rightThumbLayout) {
			if(!isThumbing){
				thumbRight();
			}else{
				act.toast("稍等一会儿~");
			}
		}

		else {
			act.toast(v.getId() + "内测中");
		}
	}

	private boolean isThumbing = false;
	
	/**
	 * 投左
	 */
	private void thumbLeft() {
		isThumbing = true;
		anim(addOneLeft);
		addOneRight.setVisibility(View.INVISIBLE);
		hideRound(topNameTv, rightHeadImg);
	}

	/**
	 * 投右
	 */
	private void thumbRight() {
		isThumbing = true;
		anim(addOneRight);
		addOneLeft.setVisibility(View.INVISIBLE);
		hideRound(bottomNameTv, leftHeadImg);
	}
	
	private Handler handler;
	
	private void hideRound(final TextView nameView, final ImageView headImg){
		
		handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				new PKAnim2(act);
				
				nameView.setText("抽取中");
				animate(headImg).rotationYBy(100*360f).start();

				
				
				final ObjectAnimator animObj = ObjectAnimator.ofFloat(headImg, "rotationY", 0, 360).setDuration(200);
				animObj.setRepeatCount(100);
				animObj.start();
				
				handler.postDelayed(new Runnable() {
					@Override
					public void run() {
						nameView.setText("小晓");
						animObj.end();
						headImg.setVisibility(View.GONE);
						headImg.invalidate();
						headImg.invalidate(0, 0, 8000, 8000);
						headImg.setImageResource(R.drawable.head_right);
						headImg.setVisibility(View.VISIBLE);
						headImg.invalidate();
						headImg.getLayoutParams();
						headImg.invalidate(0, 0, 8000, 8000);
						animate(nameView).rotationYBy(360f).start();
						new PKAnim(act);
						isThumbing = false;
					}
				}, 3000);
				
			}
		}, ZOOM_TIME * 2);
	}
	
	
	private static final int ZOOM_TIME = 1000;

	private void anim(View view) {
		int height = -leftHeadImg.getHeight() + view.getHeight();
		view.setVisibility(View.VISIBLE);
		ObjectAnimator.ofFloat(view, "scaleX", 0.3f, 1, 1).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(view, "scaleY", 0.3f, 1, 1).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(view, "translationY", 0, height).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(view, "alpha", 0.3f, 1, 1, 0).setDuration(ZOOM_TIME * 3).start();
	}

	private void thumbAnimShow(View view, float fromX) {
		Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, fromX, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		anim.setDuration(250);
		view.setAnimation(anim);
		view.setVisibility(View.VISIBLE);
	}

}
