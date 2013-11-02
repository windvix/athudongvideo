package com.athudong.video.bean;

public class Msg {

	private String username;
	private String time;
	private String content;
	private boolean hasShare;
	private String headImg;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isHasShare() {
		return hasShare;
	}
	public void setHasShare(boolean hasShare) {
		this.hasShare = hasShare;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
}
