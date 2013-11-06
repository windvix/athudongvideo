package com.athudong.video.component;

import java.io.IOException;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView.FindListener;

import com.athudong.video.BaseActivity;
import com.athudong.video.VideoViewPlayingActivity;
import com.athudong.video.dialog.ConfirmDialog;
import com.athudong.video.dialog.LoadingDialog;
import com.athudong.video.util.StringUtil;
import com.baidu.cyberplayer.sdk.BCyberPlayerFactory;
import com.baidu.cyberplayer.sdk.BEngineManager;
import com.baidu.cyberplayer.sdk.BEngineManager.OnEngineListener;

/**
 * 视频助手
 */
public class VideoHelper {

	// 您的ak
	private String AK = "OR0DIumPj7taq3GZjSiEZvNk";
	// 您的sk的前16位
	private String SK = "kVVuHW1dNvsrX6gg0TuKPFmCRfapG9Bi";

	private BaseActivity act;

	private String url;

	private final int UPDATE_INFO = 0;
	private final int FINISH_INFO = 1;

	// 返回值对应的含义
	private String[] mRetInfo = new String[] { "RET_NEW_PACKAGE_INSTALLED", "RET_NO_NEW_PACKAGE", "RET_STOPPED", "RET_CANCELED", "RET_FAILED_STORAGE_IO", "RET_FAILED_NETWORK", "RET_FAILED_ALREADY_RUNNING", "RET_FAILED_OTHERS", "RET_FAILED_ALREADY_INSTALLED", "RET_FAILED_INVALID_APK" };

	public VideoHelper(BaseActivity act, String url) {
		this.act = act;
		this.url = url;
		// 初始化BCyberPlayerFactory, 在其他任何接口调用前需要先对BCyberPlayerFactory进行初始化
		BCyberPlayerFactory.init(act);
		if (isEngineInstalled()) {
			playVideo();
		} else {
			showInstallDialog();
		}
	}

	private Handler mUIHandler = new Handler() {
		public void handleMessage(Message msg) {
			int what = msg.what;
			if (what == UPDATE_INFO) {
				if (loading != null && loading.isShowing()) {
					loading.setMessage((String) msg.obj);
				}
			} else if (what == FINISH_INFO) {
				if (loading != null ) {
					loading.dismiss();
				}
			}

		}
	};
	
	MediaPlayer mp =null;

	private void playVideo() {
		if (StringUtil.isEmpty(url)) {
			act.toast("视频不存在");
		} else {
			/*
			 * act.toast(url); BEngineManager mgr =
			 * BCyberPlayerFactory.createEngineManager();
			 * mgr.initCyberPlayerEngine(AK, SK); Intent intent = new
			 * Intent(act, VideoViewPlayingActivity.class);
			 * intent.setData(Uri.parse(act.getServerAddr()+url));
			 * act.startActivity(intent);
			 */
			
			// 通过Intent的调用CyberPlayerEngine中的PlayingActivity进行播放
			Intent it = new Intent(Intent.ACTION_VIEW);
			Uri uri = Uri.parse(url);
			it.setClassName("com.baidu.cyberplayer.engine", "com.baidu.cyberplayer.engine.PlayingActivity");
			it.setData(uri);
			act.startActivity(it);
			
			/*
			mp = new MediaPlayer();
			try {
				act.toast(url);
				mp.setDataSource(url);
				mp.prepare();
				mp.start();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
	}

	private LoadingDialog loading;

	/**
	 * 显示下载引擎对话框
	 */
	private void showInstallDialog() {
		loading = new LoadingDialog(act, "初始化中", true);
		loading.show();
		installEngine();
	}

	/**
	 * 检测engine是否安装
	 */
	private boolean isEngineInstalled() {
		BEngineManager mgr = BCyberPlayerFactory.createEngineManager();
		return mgr.EngineInstalled();
	}

	private void installEngine() {
		BEngineManager mgr = BCyberPlayerFactory.createEngineManager();
		mgr.installAsync(mEngineListener);
	}

	private OnEngineListener mEngineListener = new OnEngineListener() {
		@Override
		public boolean onPrepare() {

			setInfo("启动加速引擎", UPDATE_INFO);

			return true;
		}

		@Override
		public int onDownload(int total, int current) {
			setInfo("启动加速引擎\n" + (100 * current / total) + "%", UPDATE_INFO);
			return DOWNLOAD_CONTINUE;
		}

		@Override
		public int onPreInstall() {
			setInfo("启动加速引擎\n0%", UPDATE_INFO);
			return DOWNLOAD_CONTINUE;
		}

		@Override
		public void onInstalled(int result) {
			setInfo("下载完成了"+mRetInfo[result], FINISH_INFO);
			if (result == OnEngineListener.RET_NEW_PACKAGE_INSTALLED) {

			}
		}
	};

	private void setInfo(String info, int type) {
		Message msg = new Message();
		msg.what = type;
		msg.obj = info;
		mUIHandler.sendMessage(msg);
	}
}
