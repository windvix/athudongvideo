package com.athudong.video;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import com.athudong.video.bean.User;
import com.athudong.video.task.BaseTask;
import com.athudong.video.util.AppConst;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


/**
 * 公共基类Activity，封装各activity常用到的方法
 */
public abstract class BaseActivity extends Activity {

	/**
	 * 当前界面的任务列表
	 */
	private List<BaseTask> taskList = new ArrayList<BaseTask>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initView(savedInstanceState);
		super.onCreate(savedInstanceState);
	}
	
	/**
	 * 初始化视图
	 */
	protected abstract void initView(Bundle savedInstanceState);

	/**
	 * 增加任务
	 */
	public void addTask(BaseTask task){
		if(task!=null){
			taskList.add(task);
		}
	}
	
	/**
	 * 清除所有任务
	 */
	public void cleanAllTask(){
		if(taskList!=null && taskList.size()>0){
			for(BaseTask task:taskList){
				if(task!=null){
					task.stopTask();
				}
			}
			taskList.clear();
		}
	}
	
	/**
	 * 用于标识当前界面的可见状态
	 */
	private boolean isVisiable = false;
	
	private boolean isFinished = false;
	
	
	/**
	 * 判断当前界面是否销毁了
	 */
	public boolean isFinished(){
		return isFinished;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		isVisiable  =true;
	}
	
	@Override
	protected void onStart() {
		beforeEveryVisable();
		super.onStart();
	}
	
	/**
	 * 视图在每次可见之前调用
	 */
	protected abstract void beforeEveryVisable();
	
	
	/**
	 * 视图在每次不可见之后调用 ，如果有不需要的资源，最好在此方法中将资源释放
	 */
	protected abstract void afterEveryInVisable();
	
	
	@Override
	protected void onStop(){
		super.onStop();
		isVisiable  = false;
		afterEveryInVisable();
	}
	
	/**
	 * 判断当前界面是否可见
	 */
	public boolean isVisiable(){
		return isVisiable;
	}
	
	/**
	 * 获取当前登录的用户
	 */
	public User getUser(){
		User user = null;
		return user;
	}
	
	
	/**
	 * toast显示text字符串
	 */
	public void toast(String text) {
		if (text == null) {
			text = "";
		}
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 输出日志信息
	 */
	public void log(String text){
		if(text==null){
			text = "";
		}
		Log.d(this.getClass().getName(), text);
	}
	
	/**
	 * 返回动画
	 */
	protected void backAnim() {
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
	}

	/**
	 * 获取软件当前的版本号
	 */
	public int getVersionCode() {
		int version = 0;
		PackageManager pm = getPackageManager();
		PackageInfo pi;
		try {
			pi = pm.getPackageInfo(getPackageName(), 0);
			version = pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}
	
	
	/**
	 * 启动activity的动画效果
	 */
	public void startAnim() {
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
	}

	/**
	 * 根据资源ID，生成对应的view
	 */
	public View createView(int resourceId) {
		View view = getInflater().inflate(resourceId, null);
		return view;
	}

	/**
	 * 获取inflater
	 */
	private LayoutInflater getInflater() {
		return  getLayoutInflater();
	}
	
	/**
	 * 获取屏幕高度
	 */
	@SuppressWarnings("deprecation")
	public int getScreenHeight() {
		return getWindowManager().getDefaultDisplay().getHeight();
	}

	/**
	 * 获取屏幕宽度
	 */
	@SuppressWarnings("deprecation")
	public int getScreenWidth() {
		return getWindowManager().getDefaultDisplay().getWidth();
	}

	/**
	 * 获取资源中的字符串
	 */
	public String getResString(int resourceId) {
		return getResources().getString(resourceId);
	}

	// 从assets 文件夹中获取文件并读取数据
	protected String getFromAssets(String fileName) {
		String result = "";
		try {
			InputStream in = getResources().getAssets().open(fileName);
			// 获取文件的字节数
			int lenght = in.available();
			// 创建byte数组
			byte[] buffer = new byte[lenght];
			// 将文件中的数据读到byte数组中
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		startAnim();
	}

	@Override
	public void finish() {
		cleanAllTask();
		isVisiable = false;
		super.finish();
		backAnim();
		isFinished = true;
	}

	/**
	 * 保存字符串数据
	 */
	public void saveDataString(String key, String data) {
		SharedPreferences pre = getPreference();
		Editor editor = pre.edit();
		editor.putString(key, data);
		editor.commit();
	}

	
	/**
	 * 保存数字数据
	 */
	public void saveDataInt(String key, int val){
		SharedPreferences pre = getPreference();
		Editor editor = pre.edit();
		editor.putInt(key, val);
		editor.commit();
	}
	
	/**
	 * 获取保存的数字数据
	 */
	public int getDataInt(String key){
		SharedPreferences pre = getPreference();
		return pre.getInt(key, -1);
	}
	
	
	/**
	 * 获取字符串数据
	 */
	public String getDataString(String key) {
		SharedPreferences pre = getPreference();
		return pre.getString(key, "");
	}

	
	/**
	 * 清除保存的数据
	 */
	protected void cleanPreData() {
		SharedPreferences pre = getPreference();
		Editor editor = pre.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 获取Preference对象
	 */
	protected SharedPreferences getPreference() {
		return getSharedPreferences(AppConst.PRE_ROOT_KEY, 0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			//触摸主界面时，使键盘隐藏起来
			if (this.getCurrentFocus() != null) {
				if (this.getCurrentFocus().getWindowToken() != null) {
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onDestroy() {
		System.gc();
		cleanAllTask();
		beforeDestory();
		super.onDestroy();
		this.isFinished = true;
	}
	
	
	public String getServerAddr(){
		return getString(R.string.server_addr);
	}
	
	
	/**
	 * 界面销毁之前的做的事情 
	 */
	protected abstract void beforeDestory();
	
	/**
	 * 执行一个任务后返回的结果
	 */
	public abstract void executeTaskResult(BaseTask task, Object data);
}
