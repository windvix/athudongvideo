package com.athudong.video.component;

import android.view.View;

import com.athudong.video.MainActivity;
import com.athudong.video.R;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.animation.AnimatorProxy;

public class CircleAnim {

	private MainActivity act;

	public static final int DUR_TIME = 350;

	public CircleAnim(MainActivity act) {
		this.act = act;
		if (act.findViewById(R.id.rootAbsLayout) != null) {
			move1();
			move2();
			move3();
			move4();
		}
	}

	private void move1() {
		prx1 = AnimatorProxy.wrap(act.findViewById(R.id.circle_layout_01));
		AnimatorPath path = new AnimatorPath();
		int h1 = act.findViewById(R.id.rootAbsLayout).getBottom() - act.findViewById(R.id.circle_layout_01).getBottom();
		int w1 = act.findViewById(R.id.circle_layout_01).getLeft() - act.findViewById(R.id.rootAbsLayout).getLeft();

		int w2 = act.findViewById(R.id.circle_layout_04).getLeft() - act.findViewById(R.id.circle_layout_01).getLeft();
		int h2 = act.findViewById(R.id.circle_layout_04).getBottom() - act.findViewById(R.id.circle_layout_01).getBottom();

		int w3 = act.findViewById(R.id.circle_layout_03).getLeft() - act.findViewById(R.id.circle_layout_01).getLeft();
		int h3 = act.findViewById(R.id.circle_layout_03).getBottom() - act.findViewById(R.id.circle_layout_01).getBottom();

		path.moveTo(-w1 - act.findViewById(R.id.circle_layout_01).getWidth(), h1);
		path.lineTo(-w1, h1);
		path.lineTo(w2, h2);
		path.lineTo(w3, h3);
		path.lineTo(0, 0);
		final ObjectAnimator anim = ObjectAnimator.ofObject(CircleAnim.this, "loc1", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.circle_layout_01).setVisibility(View.VISIBLE);
		startAnim(anim);
	}

	private void move2() {
		prx2 = AnimatorProxy.wrap(act.findViewById(R.id.circle_layout_02));
		AnimatorPath path = new AnimatorPath();
		int h1 = act.findViewById(R.id.rootAbsLayout).getBottom() - act.findViewById(R.id.circle_layout_02).getBottom();
		int w1 = act.findViewById(R.id.circle_layout_02).getLeft() - act.findViewById(R.id.rootAbsLayout).getLeft();

		int w2 = act.findViewById(R.id.circle_layout_04).getLeft() - act.findViewById(R.id.circle_layout_02).getLeft();
		int h2 = act.findViewById(R.id.circle_layout_04).getBottom() - act.findViewById(R.id.circle_layout_02).getBottom();

		int w3 = act.findViewById(R.id.circle_layout_03).getLeft() - act.findViewById(R.id.circle_layout_02).getLeft();
		int h3 = act.findViewById(R.id.circle_layout_03).getBottom() - act.findViewById(R.id.circle_layout_02).getBottom();

		path.moveTo(-w1 - act.findViewById(R.id.circle_layout_02).getWidth(), h1);

		path.lineTo(-w1, h1);
		path.lineTo(w2, h2);
		path.lineTo(w3, h3);
		path.lineTo(0, 0);
		final ObjectAnimator anim = ObjectAnimator.ofObject(CircleAnim.this, "loc2", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.circle_layout_02).setVisibility(View.VISIBLE);
		startAnim(anim);
	}

	private void move3() {
		prx3 = AnimatorProxy.wrap(act.findViewById(R.id.circle_layout_03));
		AnimatorPath path = new AnimatorPath();
		int h1 = act.findViewById(R.id.rootAbsLayout).getBottom() - act.findViewById(R.id.circle_layout_03).getBottom();
		int w1 = act.findViewById(R.id.circle_layout_03).getLeft() - act.findViewById(R.id.rootAbsLayout).getLeft();

		int w2 = act.findViewById(R.id.circle_layout_04).getLeft() - act.findViewById(R.id.circle_layout_03).getLeft();
		int h2 = act.findViewById(R.id.circle_layout_04).getBottom() - act.findViewById(R.id.circle_layout_03).getBottom();

		path.moveTo(-w1 - act.findViewById(R.id.circle_layout_02).getWidth(), h1);
		path.lineTo(-w1, h1);
		path.lineTo(w2, h2);
		path.lineTo(0, 0);

		final ObjectAnimator anim = ObjectAnimator.ofObject(CircleAnim.this, "loc3", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.circle_layout_03).setVisibility(View.VISIBLE);
		startAnim(anim);
	}

	private void move4() {
		prx4 = AnimatorProxy.wrap(act.findViewById(R.id.circle_layout_04));
		AnimatorPath path = new AnimatorPath();
		int h1 = act.findViewById(R.id.rootAbsLayout).getBottom() - act.findViewById(R.id.circle_layout_04).getBottom();
		int w1 = act.findViewById(R.id.circle_layout_04).getLeft() - act.findViewById(R.id.rootAbsLayout).getLeft();
		path.moveTo(-w1 - act.findViewById(R.id.circle_layout_04).getWidth(), h1);
		path.lineTo(-w1, h1);
		path.lineTo(0, 0);

		final ObjectAnimator anim = ObjectAnimator.ofObject(CircleAnim.this, "loc4", new PathEvaluator(), path.getPoints().toArray());
		act.findViewById(R.id.circle_layout_04).setVisibility(View.VISIBLE);
		startAnim(anim);
	}

	private AnimatorProxy prx1;
	private AnimatorProxy prx2;
	private AnimatorProxy prx3;
	private AnimatorProxy prx4;

	public void setLoc1(PathPoint newLoc) {
		prx1.setTranslationX(newLoc.mX);
		prx1.setTranslationY(newLoc.mY);
	}

	public void setLoc2(PathPoint newLoc) {
		prx2.setTranslationX(newLoc.mX);
		prx2.setTranslationY(newLoc.mY);
	}

	public void setLoc3(PathPoint newLoc) {
		prx3.setTranslationX(newLoc.mX);
		prx3.setTranslationY(newLoc.mY);
	}

	public void setLoc4(PathPoint newLoc) {
		prx4.setTranslationX(newLoc.mX);
		prx4.setTranslationY(newLoc.mY);
	}

	private void startAnim(ObjectAnimator anim) {
		if (anim != null) {
			anim.setDuration(DUR_TIME);
			anim.start();
		}
	}
}
