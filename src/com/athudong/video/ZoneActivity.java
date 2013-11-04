package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.athudong.video.MainActivity.ViewPagerPageChangeListener;
import com.athudong.video.component.CircleAnim;
import com.athudong.video.component.PKAnim;
import com.athudong.video.task.BaseTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nineoldandroids.animation.ObjectAnimator;

import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class ZoneActivity extends BaseActivity implements OnClickListener,  OnRefreshListener<ScrollView> {

	private ImageView leftImg;
	private ImageView rightImg;
	private TextView leftTv;
	private TextView rightTv;
	private ImageView midImg;

	private ViewPager viewpager;

	private List<View> viewList;

	private boolean isCurrentRight = false;
	
	
	private PullToRefreshScrollView scrollView;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_zone);
		findViewById(R.id.back_btn).setOnClickListener(this);

		leftImg = (ImageView) findViewById(R.id.left_Img);
		rightImg = (ImageView) findViewById(R.id.right_Img);
		leftTv = (TextView) findViewById(R.id.left_Tv);
		rightTv = (TextView) findViewById(R.id.right_Tv);
		midImg = (ImageView) findViewById(R.id.mid_Img);

		viewpager = (ViewPager) findViewById(R.id.viewpager);
		viewList = new ArrayList<View>();

		
		
		
		findViewById(R.id.leftLayout).setOnClickListener(this);
		findViewById(R.id.rightLayout).setOnClickListener(this);

		viewList.add(createView(R.layout.zone_page_01));
		viewList.add(createView(R.layout.zone_page_02));

		viewpager.setAdapter(new ViewPagerAdapter(viewList));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(0);

		isCurrentRight = false;
		animate(midImg).rotationYBy(180f);
		ObjectAnimator.ofFloat(midImg, "translationX", 0,-100, -100).setDuration(0).start();
		ObjectAnimator.ofFloat(rightImg, "scaleX", 1, 0, 0).setDuration(0).start();
		ObjectAnimator.ofFloat(rightImg, "scaleY", 1, 0, 0).setDuration(0).start();
		ObjectAnimator.ofFloat(leftImg, "scaleX", 0, 1, 1).setDuration(0).start();
		ObjectAnimator.ofFloat(leftImg, "scaleY", 0, 1, 1).setDuration(0).start();
		rightImg.setVisibility(View.INVISIBLE);
		leftImg.setVisibility(View.VISIBLE);
		ObjectAnimator.ofFloat(rightTv, "scaleX", 1, 0.8f, 0.8f).setDuration(0).start();
		ObjectAnimator.ofFloat(rightTv, "scaleY", 1, 0.8f, 0.8f).setDuration(0).start();
		ObjectAnimator.ofFloat(leftTv, "scaleX", 0.8f, 1, 1).setDuration(0).start();
		ObjectAnimator.ofFloat(leftTv, "scaleY", 0.8f, 1, 1).setDuration(0).start();


		viewpager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		
		
		scrollView = (PullToRefreshScrollView)findViewById(R.id.scrollview);
		scrollView.setOnRefreshListener(this);
	}

	@Override
	protected void beforeEveryVisable() {

	}

	@Override
	protected void afterEveryInVisable() {

	}

	@Override
	protected void beforeDestory() {

	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {

	}

	private static final int ZOOM_TIME = 300;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.back_btn) {
			finish();
		} else if (R.id.leftLayout == id) {
			if (isCurrentRight) {
				showRight();
			}
			viewpager.setCurrentItem(0);
			isCurrentRight = false;
		} else if (R.id.rightLayout == id) {
			if (!isCurrentRight) {
				showLeft();
			}
			isCurrentRight = true;
			viewpager.setCurrentItem(1);
		}
	}

	private void showLeft() {
		animate(midImg).rotationYBy(180f);
		ObjectAnimator.ofFloat(midImg, "translationX", 0, 100, 100).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(leftImg, "scaleX", 1, 0, 0).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(leftImg, "scaleY", 1, 0, 0).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(rightImg, "scaleX", 0, 1, 1).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(rightImg, "scaleY", 0, 1, 1).setDuration(ZOOM_TIME).start();
		leftImg.setVisibility(View.INVISIBLE);
		rightImg.setVisibility(View.VISIBLE);

		ObjectAnimator.ofFloat(leftTv, "scaleX", 1, 0.8f, 0.8f).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(leftTv, "scaleY", 1, 0.8f, 0.8f).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(rightTv, "scaleX", 0.8f, 1, 1).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(rightTv, "scaleY", 0.8f, 1, 1).setDuration(ZOOM_TIME).start();
		isCurrentRight = false;
	}

	private void showRight() {
		animate(midImg).rotationYBy(180f);
		ObjectAnimator.ofFloat(midImg, "translationX", 0, -100, -100).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(rightImg, "scaleX", 1, 0, 0).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(rightImg, "scaleY", 1, 0, 0).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(leftImg, "scaleX", 0, 1, 1).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(leftImg, "scaleY", 0, 1, 1).setDuration(ZOOM_TIME).start();
		rightImg.setVisibility(View.INVISIBLE);
		leftImg.setVisibility(View.VISIBLE);
		ObjectAnimator.ofFloat(rightTv, "scaleX", 1, 0.8f, 0.8f).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(rightTv, "scaleY", 1, 0.8f, 0.8f).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(leftTv, "scaleX", 0.8f, 1, 1).setDuration(ZOOM_TIME).start();
		ObjectAnimator.ofFloat(leftTv, "scaleY", 0.8f, 1, 1).setDuration(ZOOM_TIME).start();
		isCurrentRight = false;
	}

	class ViewPagerAdapter extends PagerAdapter {
		private List<View> list = null;

		public ViewPagerAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(list.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	class ViewPagerPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int state) {

		}

		@Override
		public void onPageScrolled(int page, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int page) {
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				scrollView.onRefreshComplete();
			}
		}, 2000);
	}
}
