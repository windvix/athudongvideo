package com.athudong.video.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;

public class AudioHelper implements OnSeekBarChangeListener {

	private LinearLayout root;

	private MediaPlayer player;

	private View currentMusicView;

	private List<View> audioViewList;

	private SeekBar seekbar;
	private BaseActivity act;

	private Timer timer;

	private TimerTask timerTask;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			int val = (Integer) msg.obj;
			seekbar.setProgress(val);
		};
	};

	public AudioHelper(BaseActivity act, View root, SeekBar seekbar) {
		this.act = act;
		this.root = (LinearLayout) root;
		this.seekbar = seekbar;
		timer = new Timer();
		audioViewList = new ArrayList<View>();
		for (int i = 0; i < 6; i++) {
			View oneAudioView = act.createView(R.layout.list_audio_template);
			String url = Environment.getExternalStorageDirectory() + "/test.mp3";
			oneAudioView.setTag(url);
			audioViewList.add(oneAudioView);
			this.root.addView(oneAudioView);
			oneAudioView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					playMusic(v,0);
				}
			});
		}
		seekbar.setProgress(0);
		seekbar.setEnabled(false);
		seekbar.setOnSeekBarChangeListener(this);
		if (timerTask == null) {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					if (player != null) {

						boolean isPlaying = false;
						int val = -1;
						try {
							isPlaying = player.isPlaying();
							val = player.getCurrentPosition();
						} catch (Exception e) {

						}
						if (val < 0) {
							val = 0;
						}
						if (isPlaying) {
							setProgress(val);
						}
					}
				}
			};
			timer.schedule(timerTask, 0, 50);
		}
	}
	
	private void playMusic(View currenView, int where_pause) {
		try {
			this.where_pause = where_pause;
			for (View one : audioViewList) {
				if (one == currenView) {
					one.setBackgroundResource(R.drawable.audio_list_bg_press);
				} else {
					one.setBackgroundResource(R.drawable.audio_list_bg);
					((ImageView) one.findViewById(R.id.audio_img)).setImageResource(R.drawable.audio_play_btn);
				}
			}
			seekbar.setEnabled(true);
			String path = currenView.getTag().toString();
			if (currentMusicView != currenView) {
				if (player != null) {
					player.release();
				}

				Uri uri = Uri.parse(path);
				player = MediaPlayer.create(act, uri);
				seekbar.setMax(player.getDuration());
				((ImageView) currenView.findViewById(R.id.audio_img)).setImageResource(R.drawable.audio_pause_btn);
				this.currentMusicView = currenView;
				player.start();
				player.seekTo(where_pause);
			} else {
				if (player != null) {
					if (player.isPlaying()) {
						player.pause();
						((ImageView) currenView.findViewById(R.id.audio_img)).setImageResource(R.drawable.audio_play_btn);
					} else {
						player.start();
						((ImageView) currenView.findViewById(R.id.audio_img)).setImageResource(R.drawable.audio_pause_btn);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int where_pause = 0;
	
	public void pause() {
		if (player != null && player.isPlaying()) {
			player.pause();
			where_pause = player.getCurrentPosition();
		}
	}

	public void stop() {
		if (player != null) {
			seekbar.setEnabled(false);
			player.release();
		}
	}

	public void continuePlay() {
		if (player != null) {
			player.release();
			View tempView = currentMusicView ;
			if(tempView!=null){
				currentMusicView = null;
				playMusic(tempView,where_pause);
			}
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		if (player != null && seekBar.isEnabled()) {
			player.pause();
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (player != null && seekBar.isEnabled()) {
			player.seekTo(seekBar.getProgress());
			player.start();
		}
	}

	public void setProgress(int progress) {
		Message msg = new Message();
		msg.obj = progress;
		handler.sendMessage(msg);
	}
}
