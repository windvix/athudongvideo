package com.athudong.video.component;

import android.view.View;

import com.athudong.video.MainActivity;
import com.athudong.video.R;
import com.athudong.video.R.id;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.animation.AnimatorProxy;

/**
 * 主界面的动画处理
 */
public class PKAnim2 {

	private MainActivity act;
	private int topLayoutHeight;

	private int bottomLayoutHeight;

	private int btnWidth;

	public static final int DUR_TIME = 500;

	public PKAnim2(MainActivity act) {
		this.act = act;
		btnWidth = act.findViewById(R.id.realTopBtn01).getWidth();

		moveTop();
		moveBottom();
	}

	private AnimatorProxy topProxy1;
	private AnimatorProxy topProxy2;
	private AnimatorProxy topProxy3;
	private AnimatorProxy topProxy4;

	private AnimatorProxy bottomProxy1;
	private AnimatorProxy bottomProxy2;
	private AnimatorProxy bottomProxy3;
	private AnimatorProxy bottomProxy4;

	private void moveTop() {
		topLayoutHeight = act.findViewById(R.id.topMoveLayout).getHeight();
		realTopBtnHeight = act.findViewById(R.id.realTopBtn01).getHeight();

		mTop1();
		mTop2();
		mTop3();
		mTop4();
	}

	private int realTopBtnHeight;

	private void mTop2() {
		topProxy2 = AnimatorProxy.wrap(act.findViewById(R.id.realTopBtn02));
		int w1 = act.findViewById(R.id.realTopBtn04).getLeft() - act.findViewById(R.id.realTopBtn02).getLeft();
		int w2 = act.findViewById(R.id.realTopBtn03).getLeft() - act.findViewById(R.id.realTopBtn02).getLeft();
		int h1 = act.findViewById(R.id.realTopBtn04).getTop() - act.findViewById(R.id.realTopBtn02).getTop();
		AnimatorPath path = new AnimatorPath();
		path.moveTo(0, 0);
		path.lineTo(w2, 0);
		path.lineTo(w1, h1);
		path.lineTo(w1, (topLayoutHeight));
		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "topLoc2", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realTopBtn02).setVisibility(View.INVISIBLE);
		startAnim(anim);

	}

	private void mTop3() {
		topProxy3 = AnimatorProxy.wrap(act.findViewById(R.id.realTopBtn03));

		AnimatorPath path = new AnimatorPath();
		int w1 = act.findViewById(R.id.realTopBtn04).getLeft() - act.findViewById(R.id.realTopBtn03).getLeft();
		int h1 = act.findViewById(R.id.realTopBtn04).getTop() - act.findViewById(R.id.realTopBtn03).getTop();
		path.moveTo(0, 0);
		path.lineTo(w1, h1);
		path.lineTo(w1, topLayoutHeight);

		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "topLoc3", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realTopBtn03).setVisibility(View.INVISIBLE);
		startAnim(anim);
	}

	private void mTop4() {
		topProxy4 = AnimatorProxy.wrap(act.findViewById(R.id.realTopBtn04));
		AnimatorPath path = new AnimatorPath();
		path.moveTo(0, 0);
		path.lineTo(0, realTopBtnHeight);
		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "topLoc4", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realTopBtn04).setVisibility(View.INVISIBLE);
		startAnim(anim);
	}

	private void mTop1() {
		topProxy1 = AnimatorProxy.wrap(act.findViewById(R.id.realTopBtn01));
		AnimatorPath path = new AnimatorPath();
		int w1 = act.findViewById(R.id.realTopBtn04).getLeft() - act.findViewById(R.id.realTopBtn01).getLeft();
		int w2 = act.findViewById(R.id.realTopBtn03).getLeft() - act.findViewById(R.id.realTopBtn01).getLeft();
		int w3 = act.findViewById(R.id.realTopBtn02).getLeft() - act.findViewById(R.id.realTopBtn01).getLeft();
		int h1 = act.findViewById(R.id.realTopBtn01).getTop() - act.findViewById(R.id.realTopBtn03).getTop();
		path.moveTo(0, 0);
		path.lineTo(w3, -h1);
		path.lineTo(w2, -h1);
		path.lineTo(w1, 0);
		path.lineTo(w1, realTopBtnHeight);
		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "topLoc1", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realTopBtn01).setVisibility(View.INVISIBLE);
		startAnim(anim);

	}

	private void moveBottom() {
		bottomLayoutHeight = act.findViewById(R.id.bottomMoveLayout).getHeight();
		bottom1();
		bottom2();
		bottom3();
		bottom4();
	}

	private void bottom1() {
		bottomProxy1 = AnimatorProxy.wrap(act.findViewById(R.id.realBottomBtn01));
		AnimatorPath path = new AnimatorPath();
		path.moveTo(0, 0);
		path.lineTo(0, -btnWidth);

		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "bottomLoc1", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realBottomBtn01).setVisibility(View.INVISIBLE);
		startAnim(anim);
	}

	private void bottom2() {
		bottomProxy2 = AnimatorProxy.wrap(act.findViewById(R.id.realBottomBtn02));
		int w1 = act.findViewById(R.id.realBottomBtn02).getLeft() - act.findViewById(R.id.realBottomBtn01).getLeft();
		int h1 = act.findViewById(R.id.realBottomBtn02).getTop() - act.findViewById(R.id.realBottomBtn01).getTop();

		AnimatorPath path = new AnimatorPath();
		path.moveTo(0, 0);
		path.lineTo(-w1, -h1);
		path.lineTo(-w1, -bottomLayoutHeight);
		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "bottomLoc2", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realBottomBtn02).setVisibility(View.INVISIBLE);
		startAnim(anim);
	}

	private void bottom3() {
		bottomProxy3 = AnimatorProxy.wrap(act.findViewById(R.id.realBottomBtn03));
		AnimatorPath path = new AnimatorPath();
		int w1 = act.findViewById(R.id.realBottomBtn03).getLeft() - act.findViewById(R.id.realBottomBtn01).getLeft();
		int h1 = act.findViewById(R.id.realBottomBtn03).getTop() - act.findViewById(R.id.realBottomBtn01).getTop();
		int w2 = act.findViewById(R.id.realBottomBtn03).getLeft() - act.findViewById(R.id.realBottomBtn02).getLeft();
		path.moveTo(0, 0);
		path.lineTo(-w2, 0);
		path.lineTo(-w1, -h1);
		path.lineTo(-w1, -bottomLayoutHeight);
		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "bottomLoc3", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realBottomBtn03).setVisibility(View.INVISIBLE);
		startAnim(anim);
	}

	private void bottom4() {
		bottomProxy4 = AnimatorProxy.wrap(act.findViewById(R.id.realBottomBtn04));
		AnimatorPath path = new AnimatorPath();
		int w1 = act.findViewById(R.id.realBottomBtn04).getLeft() - act.findViewById(R.id.realBottomBtn01).getLeft();
		int w2 = act.findViewById(R.id.realBottomBtn04).getLeft() - act.findViewById(R.id.realBottomBtn02).getLeft();
		int w3 = act.findViewById(R.id.realBottomBtn04).getLeft() - act.findViewById(R.id.realBottomBtn03).getLeft();
		int h = act.findViewById(R.id.realBottomBtn02).getTop() - act.findViewById(R.id.realBottomBtn04).getTop();
		path.moveTo(0, 0);
		path.lineTo(-w3, h);
		path.lineTo(-w2, h);
		path.lineTo(-w1, 0);
		path.lineTo(-w1, -bottomLayoutHeight);
		final ObjectAnimator anim = ObjectAnimator.ofObject(PKAnim2.this, "bottomLoc4", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.realBottomBtn04).setVisibility(View.INVISIBLE);
		startAnim(anim);
	}

	public void setBottomLoc1(PathPoint newLoc) {
		bottomProxy1.setTranslationX(newLoc.mX);
		bottomProxy1.setTranslationY(newLoc.mY);
	}

	public void setBottomLoc2(PathPoint newLoc) {
		bottomProxy2.setTranslationX(newLoc.mX);
		bottomProxy2.setTranslationY(newLoc.mY);
	}

	public void setBottomLoc3(PathPoint newLoc) {
		bottomProxy3.setTranslationX(newLoc.mX);
		bottomProxy3.setTranslationY(newLoc.mY);
	}

	public void setBottomLoc4(PathPoint newLoc) {
		bottomProxy4.setTranslationX(newLoc.mX);
		bottomProxy4.setTranslationY(newLoc.mY);
	}

	public void setTopLoc1(PathPoint newLoc) {
		topProxy1.setTranslationX(newLoc.mX);
		topProxy1.setTranslationY(newLoc.mY);
	}

	public void setTopLoc2(PathPoint newLoc) {
		topProxy2.setTranslationX(newLoc.mX);
		topProxy2.setTranslationY(newLoc.mY);
	}

	public void setTopLoc3(PathPoint newLoc) {
		topProxy3.setTranslationX(newLoc.mX);
		topProxy3.setTranslationY(newLoc.mY);
	}

	public void setTopLoc4(PathPoint newLoc) {
		topProxy4.setTranslationX(newLoc.mX);
		topProxy4.setTranslationY(newLoc.mY);
	}

	private void startAnim(ObjectAnimator anim) {
		if (anim != null) {
			anim.setDuration(DUR_TIME);
			anim.start();
		}
	}
}
