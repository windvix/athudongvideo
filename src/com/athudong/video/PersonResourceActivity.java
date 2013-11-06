package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Environment;
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
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.athudong.video.component.AnimatorPath;
import com.athudong.video.component.PathEvaluator;
import com.athudong.video.component.PathPoint;
import com.athudong.video.component.VideoHelper;
import com.athudong.video.task.BaseTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.animation.AnimatorProxy;

/**
 * 个人视频，排名、资源界面
 */
public class PersonResourceActivity extends BaseActivity implements OnRefreshListener<ScrollView>, OnClickListener {

	private PullToRefreshScrollView scrollView;

	private LinearLayout contentLayout;

	private View vpView;

	private ViewPager viewpager;

	private List<View> listView;

	private ImageView cursor;

	
	private View playAudioLayout;
	
	private View page1;
	private View page2;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_personres);

		scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollview);

		contentLayout = (LinearLayout) findViewById(R.id.contentLayout);
		scrollView.setOnRefreshListener(this);

		contentLayout.addView(createView(R.layout.personres_head));

		vpView = createView(R.layout.personres_body);

		viewpager = (ViewPager) vpView.findViewById(R.id.viewpager);
		listView = new ArrayList<View>();

		
		page1 = createView(R.layout.personres_page1);
		page2 = createView(R.layout.personres_page2);
		
		
		
		listView.add(page1);
		listView.add(page2);
		listView.add(createView(R.layout.personres_page3));

		viewpager.setAdapter(new ViewPagerAdapter(listView));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(0);
		currIndex = 0;

		findViewById(R.id.back_btn).setOnClickListener(this);

		vpView.findViewById(R.id.page_btn_01).setOnClickListener(this);
		vpView.findViewById(R.id.page_btn_02).setOnClickListener(this);
		vpView.findViewById(R.id.page_btn_03).setOnClickListener(this);

		viewpager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});

		contentLayout.addView(vpView);

		cursor = (ImageView) findViewById(R.id.cursor);
		
		
		page1.findViewById(R.id.video_01).setOnClickListener(this);
		page1.findViewById(R.id.video_02).setOnClickListener(this);
		page1.findViewById(R.id.video_03).setOnClickListener(this);
		page1.findViewById(R.id.video_04).setOnClickListener(this);
		
		playAudioLayout = findViewById(R.id.play_audio_layout);
		playAudioLayout.setVisibility(View.GONE);
		
		
		page2.findViewById(R.id.audio_01).setOnClickListener(this);
		page2.findViewById(R.id.audio_02).setOnClickListener(this);
		page2.findViewById(R.id.audio_03).setOnClickListener(this);
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
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				scrollView.onRefreshComplete();
			}
		}, 2000);

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.back_btn) {
			finish();
		} else if (id == R.id.page_btn_01) {
			playAudioLayout.setVisibility(View.GONE);
			viewpager.setCurrentItem(0);

		} else if (id == R.id.page_btn_02) {
			playAudioLayout.setVisibility(View.VISIBLE);
			viewpager.setCurrentItem(1);
		} else if (id == R.id.page_btn_03) {
			playAudioLayout.setVisibility(View.GONE);
			viewpager.setCurrentItem(2);
		} else if(id==R.id.video_01){
			String url = Environment.getExternalStorageDirectory() + "/test.3gp";
			new VideoHelper(this,url);
		} else if(id==R.id.video_02){
			String url = Environment.getExternalStorageDirectory() + "/test.3gp";
			new VideoHelper(this,url);
		} else if(id==R.id.video_03){
			String url = Environment.getExternalStorageDirectory() + "/test.3gp";
			new VideoHelper(this,url);
		} else if(id==R.id.video_04){
			String url = Environment.getExternalStorageDirectory() + "/test.3gp";
			new VideoHelper(this,url);
		}else if(id==R.id.audio_01){
			v.setBackgroundResource(R.drawable.audio_list_bg_press);
		}
		else{
			toast("内测中，敬请期待");
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

	private int currIndex = 0;

	class ViewPagerPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {

		}

		@Override
		public void onPageScrolled(int page, float positionOffset, int positionOffsetPixels) {
		}

		private AnimatorProxy proxy;

		@Override
		public void onPageSelected(int page) {
			proxy = AnimatorProxy.wrap(cursor);
			AnimatorPath path = new AnimatorPath();
			if (page == 0) {
				((ImageView)vpView.findViewById(R.id.page_btn_01)).setImageResource(R.drawable.res_btn_01_press);
				((ImageView)vpView.findViewById(R.id.page_btn_02)).setImageResource(R.drawable.res_btn_02_default);
				((ImageView)vpView.findViewById(R.id.page_btn_03)).setImageResource(R.drawable.res_btn_03_default);
				
				if (currIndex == 1) {
					path.moveTo(1 * getScreenWidth() / 3, 0);
				} else if (currIndex == 2) {
					path.moveTo(2 * getScreenWidth() / 3, 0);
				}
				path.lineTo(0, 0);
			} else if (page == 1) {
				((ImageView)vpView.findViewById(R.id.page_btn_01)).setImageResource(R.drawable.res_btn_01_default);
				((ImageView)vpView.findViewById(R.id.page_btn_02)).setImageResource(R.drawable.res_btn_02_press);
				((ImageView)vpView.findViewById(R.id.page_btn_03)).setImageResource(R.drawable.res_btn_03_default);
				if (currIndex == 0) {
					path.moveTo(0, 0);
				} else if (currIndex == 2) {
					path.moveTo(2 * getScreenWidth() / 3, 0);
				}
				path.lineTo(1 * getScreenWidth() / 3, 0);
			} else if (page == 2) {
				((ImageView)vpView.findViewById(R.id.page_btn_01)).setImageResource(R.drawable.res_btn_01_default);
				((ImageView)vpView.findViewById(R.id.page_btn_02)).setImageResource(R.drawable.res_btn_02_default);
				((ImageView)vpView.findViewById(R.id.page_btn_03)).setImageResource(R.drawable.res_btn_03_press);
				if (currIndex == 0) {
					path.moveTo(0, 0);
				} else if (currIndex == 1) {
					path.moveTo(1 * getScreenWidth() / 3, 0);
				}
				path.lineTo(2 * getScreenWidth() / 3, 0);
			}

			currIndex = page;
			final ObjectAnimator anim = ObjectAnimator.ofObject(this, "Loc", new PathEvaluator(), path.getPoints().toArray());
			startAnim(anim);
		}

		public void setLoc(PathPoint newLoc) {
			proxy.setTranslationX(newLoc.mX);
			proxy.setTranslationY(newLoc.mY);
		}

		private void startAnim(ObjectAnimator anim) {
			if (anim != null) {
				anim.setDuration(200);
				anim.start();
			}
		}
	}

}
