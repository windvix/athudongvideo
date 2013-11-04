package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.component.CircleAnim;
import com.athudong.video.component.PKAnim;
import com.athudong.video.task.BaseTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

/**
 * PK界面
 */
public class MainActivity extends BaseActivity implements OnClickListener {

	private ViewPager viewpager;

	private List<View> list;

	private List<View> bottomBtnList;

	private MainActivityPK mainPk;

	private MainActivityCircle mainCircle;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);

		viewpager = (ViewPager) findViewById(R.id.viewpager);
		list = new ArrayList<View>();

		View pkView = createView(R.layout.main_pk);
		View circleView = createView(R.layout.main_circle);
		View msgView = createView(R.layout.main_msg);
		View settingView = createView(R.layout.main_setting);
		View videoView = createView(R.layout.main_video);

		list.add(pkView);
		list.add(circleView);
		list.add(msgView);
		list.add(videoView);
		list.add(settingView);

		viewpager.setAdapter(new ViewPagerAdapter(list));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(0);

		mainPk = new MainActivityPK(this, pkView);
		mainCircle = new MainActivityCircle(this, circleView);
		new MainActivityMsg(this, msgView);
		new MainActivitySettings(this, settingView);
		new MainActivityVideo(this, videoView);

		bottomBtnList = new ArrayList<View>();
		bottomBtnList.add(findViewById(R.id.bottomBarBtn1));
		bottomBtnList.add(findViewById(R.id.bottomBarBtn2));
		bottomBtnList.add(findViewById(R.id.bottomBarBtn3));
		bottomBtnList.add(findViewById(R.id.bottomBarBtn4));
		bottomBtnList.add(findViewById(R.id.bottomBarBtn5));

		for (View v : bottomBtnList) {
			v.setOnClickListener(this);
		}
	}

	@Override
	protected void beforeDestory() {

	}

	private PKAnim anim;

	@Override
	protected void onResume() {
		super.onResume();
		if (anim == null && viewpager.getCurrentItem() == 0) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					anim = new PKAnim(MainActivity.this);
					mainCircle.resetLoc();
				}
			}, 500);
		}
	}

	@Override
	protected void beforeEveryVisable() {
	}

	@Override
	protected void afterEveryInVisable() {

	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {

	}

	// 重写Activity中onKeyDown（）方法
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 当keyCode等于退出事件值时
			ToQuitTheApp();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	private boolean isExit = false;

	// 封装ToQuitTheApp方法
	private void ToQuitTheApp() {
		if (isExit) {
			// ACTION_MAIN with category CATEGORY_HOME 启动主屏幕
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					MainActivity.this.finish();
					System.exit(0);// 使虚拟机停止运行并退出程序
				}
			}, 1500);
		} else {
			isExit = true;
			toast("再按一次退出");
			mHandler.sendEmptyMessageDelayed(0, 3000);// 3秒后发送消息
		}
	}

	// 创建Handler对象，用来处理消息
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {// 处理消息
			super.handleMessage(msg);
			isExit = false;
		}
	};

	@Override
	public void onClick(View v) {

		int id = v.getId();
		if (id == R.id.bottomBarBtn1) {
			if (viewpager.getCurrentItem() != 0) {
				viewpager.setCurrentItem(0);
			}
		}
		if (id == R.id.bottomBarBtn2) {
			if (viewpager.getCurrentItem() != 1) {
				viewpager.setCurrentItem(1);
			}
		}
		if (id == R.id.bottomBarBtn3) {
			if (viewpager.getCurrentItem() != 2) {
				viewpager.setCurrentItem(2);
			}
		}
		if (id == R.id.bottomBarBtn4) {
			if (viewpager.getCurrentItem() != 3) {
				viewpager.setCurrentItem(3);
			}
		}
		if (id == R.id.bottomBarBtn5) {
			if (viewpager.getCurrentItem() != 4) {
				viewpager.setCurrentItem(4);
			}
		}
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
			for (int i = 0; i < bottomBtnList.size(); i++) {
				View v = bottomBtnList.get(i);
				if (i == page) {
					v.setBackgroundResource(R.drawable.bottom_bar_item_select_bg);
				} else {
					v.setBackgroundResource(R.drawable.transparent);
				}
				if (page == 0) {
					anim = new PKAnim(MainActivity.this);
					mainCircle.resetLoc();
				} else if (page == 1) {
					mainPk.reset();
					new CircleAnim(MainActivity.this);
				} else if (page == 2) {
					mainPk.reset();
					mainCircle.resetLoc();
				} else if (page == 3) {
					mainPk.reset();
					mainCircle.resetLoc();
				}
			}
		}
	}
}
