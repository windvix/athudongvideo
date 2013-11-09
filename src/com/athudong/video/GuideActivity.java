package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.athudong.video.task.BaseTask;

/**
 * 引导界面（软件第一次安装运行时显示）
 */
public class GuideActivity extends BaseActivity implements OnClickListener {

	private ViewPager viewpager = null;
	private List<View> list = null;
	private ImageView[] img = null;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_guide);

		viewpager = (ViewPager) findViewById(R.id.viewpager);
		list = new ArrayList<View>();

		/**
		 * 创建多个具体的引导内容
		 */
		View view4 = createView(R.layout.guide_04);
		view4.findViewById(R.id.guide_btn).setOnClickListener(this);
		list.add(createView(R.layout.guide_01));
		//list.add(createView(R.layout.guide_02));
		//list.add(createView(R.layout.guide_03));
		list.add(view4);

		img = new ImageView[list.size()];
		LinearLayout layout = (LinearLayout) findViewById(R.id.viewGroup);

		/**
		 * 根据引导页面的数量，创建多个导航原点
		 */
		for (int i = 0; i < list.size(); i++) {
			img[i] = new ImageView(this);
			int size = getResources().getDimensionPixelSize(R.dimen.nav_round_size);
			LayoutParams lp = new LayoutParams(size, size);
			int margin = getResources().getDimensionPixelSize(R.dimen.nav_round_padding);
			lp.setMargins(margin, 0, margin, 0);
			img[i].setLayoutParams(lp);
			if (0 == i) {
				img[i].setBackgroundResource(R.drawable.guide_circle_select);
			} else {
				img[i].setBackgroundResource(R.drawable.guide_circle_default);
			}
			img[i].getBackground().setAlpha(150);

			layout.addView(img[i]);
		}
		viewpager.setAdapter(new ViewPagerAdapter(list));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

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

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.guide_btn) {
			finish();
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
			// 更新图标
			for (int i = 0; i < list.size(); i++) {
				if (page == i) {
					img[i].setBackgroundResource(R.drawable.guide_circle_select);
				} else {
					img[i].setBackgroundResource(R.drawable.guide_circle_default);
				}
				img[i].getBackground().setAlpha(150);
			}
		}
	}

}
