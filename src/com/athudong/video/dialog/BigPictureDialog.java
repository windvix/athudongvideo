package com.athudong.video.dialog;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;
import com.athudong.video.component.LoadingCircleView;
import com.athudong.video.component.ZoomImageView;
import com.athudong.video.task.BaseTask;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

@SuppressLint("HandlerLeak")
public class BigPictureDialog extends Dialog implements OnClickListener {

	private String bigImgUrl;
	private LoadingCircleView circle;
	private LinearLayout layoutView;
	private String fileName;
	private BaseActivity activity;

	private LoadImgTask task;

	private Handler handler;

	public BigPictureDialog(BaseActivity activity, String bigImgUrl, String fileName) {
		super(activity, R.style.DimDialog);
		this.fileName = fileName;
		this.bigImgUrl = bigImgUrl;
		this.activity = activity;
		View layout = activity.createView(R.layout.dialog_big_picture);

		addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		setContentView(layout);

		layoutView = (LinearLayout) layout.findViewById(R.id.big_pic_iv);
		circle = (LoadingCircleView) layout.findViewById(R.id.big_pic_loading);
		circle.setOnClickListener(this);

		Window win = getWindow();
		win.setGravity(Gravity.CENTER);

		WindowManager.LayoutParams lp = win.getAttributes();

		lp.width = activity.getScreenWidth();
		lp.height = activity.getScreenHeight();

		setCancelable(true);
		setCanceledOnTouchOutside(true);
		circle.setVisibility(View.GONE);
		onWindowAttributesChanged(lp);

	}

	private int downloadSize = 0;
	private int fileSize = 0;

	private boolean isStop = false;
	
	@Override
	public void show() {
		super.show();
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int m = msg.what;
				if(m==1){
					if(!isStop){
						circle.setVisibility(View.VISIBLE);
					}
				}else if(m==50){
					if(!isStop){
						int per = (downloadSize/fileSize)*100;
						circle.setProgress(per);
					}
				}else if(m==100){
					circle.setProgress(100);
					circle.setVisibility(View.GONE);
				}else if(m==-1){
					if(task!=null){
						task.stopTask();
						isStop = true;
					}
				}
			}
		};
		layoutView.setTag(fileName);
		task = new LoadImgTask(activity, layoutView, false, bigImgUrl);
		activity.addTask(task);
	}

	@Override
	public void onClick(View v) {
		task.stopTask();
		dismiss();
	}

	private class LoadImgTask extends BaseTask {

		private LinearLayout view;
		private boolean forceRefresh = false;
		private String url;
		private Bitmap bitmap;

		public LoadImgTask(BaseActivity activity, LinearLayout view, boolean forceRefresh, String url) {
			super(activity);
			this.view = view;
			this.forceRefresh = forceRefresh;
			this.url = url;
			this.execute();
		}


		@Override
		protected void onProgressUpdate() {
			
		}

		@Override
		protected Object onPostExecute() {
			
			return null;
		}

		
		
		@Override
		protected void doInBackground() {
			if (view != null && url != null && view.getTag() != null && !isCancelled()) {
				String id = view.getTag().toString();
				File root = new File(getActivity().getAppCacheDir());
				try {
					if (!root.exists()) {
						root.mkdir();
					}
				} catch (Exception e) {
				}
				String fileName = getActivity().getAppCacheDir() + id;
				File file = new File(fileName);
				try {
					if (file.exists()) {
						if (forceRefresh) {
							file.delete();
							try {
								file.createNewFile();
							} catch (Exception e) {
							}
							bitmap = byteToBitmap(getImage(url));
							saveFile(bitmap, file);
						} else {
							bitmap = getActivity().readBitmapAutoSize(fileName, view.getWidth(), view.getHeight());
							if (bitmap == null) {
								try {
									file.delete();
									file.createNewFile();
								} catch (Exception e) {

								}
								bitmap = byteToBitmap(getImage(url));
								saveFile(bitmap, file);
							}
						}
					} else {
						try {
							file.createNewFile();
						} catch (Exception e) {

						}
						bitmap = byteToBitmap(getImage(url));
						saveFile(bitmap, file);
					}
				} catch (Exception e) {
				}
			}
		}

		@Override
		protected void onPostExecute(Void result) {
			if (getActivity().isVisiable() && !isCancelled()) {
				if (bitmap != null && view != null) {
					ZoomImageView imgView = new ZoomImageView(getContext(), bitmap);
					view.addView(imgView);
				}
			}
		}

		/**
		 */
		private byte[] readStream(InputStream inStream) {
			if (!isCancelled()) {
				try {
					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
					sendMessage(1);
					while ((len = inStream.read(buffer)) != -1) {
						if (!isCancelled()) {
							outStream.write(buffer, 0, len);
							downloadSize = downloadSize+len;
							sendMessage(50);
						} else {
							len = -1;
							sendMessage(-1);
						}
					}
					outStream.close();
					inStream.close();
					sendMessage(100);
					return outStream.toByteArray();
				} catch (Exception e) {

				}
			}
			return null;
		}

		/**
		 * 保存文件
		 */
		private void saveFile(Bitmap bm, File file) {
			if (!isCancelled()) {
				try {
					if (bm == null) {
						file.delete();
						return;
					}
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
					bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
					bos.flush();
					bos.close();
				} catch (Exception e) {

				}
			}
		}

		private Bitmap byteToBitmap(byte[] data) {
			if (data != null) {
				return BitmapFactory.decodeByteArray(data, 0, data.length);// bitmap
			}
			return null;
		}

		private byte[] getImage(String path) {
			if (!isCancelled()) {
				try {
					URL url = new URL(path);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5 * 1000);
					conn.setRequestMethod("GET");
					InputStream inStream = conn.getInputStream();
					fileSize = conn.getContentLength();
					if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
						return readStream(inStream);
					}
				} catch (Exception e) {

				}
			}
			return null;
		}

	}

	/**
	 * 给hand发送消息
	 * 
	 * @param what
	 */
	private void sendMessage(int what) {
		Message m = new Message();
		m.what = what;
		handler.sendMessage(m);
	}
}
