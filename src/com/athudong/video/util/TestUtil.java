package com.athudong.video.util;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.R;

public class TestUtil {

	private static List<Integer> a = new ArrayList<Integer>();

	private static boolean isInA(int i) {
		for (int b : a) {
			if (b == i) {
				return true;
			}
		}
		return false;
	}

	public static int getRandomHeadImgId() {
		int i = 0;

		int t = getRandom(1, 31);

		if (isInA(t)) {
			t = getRandom(1, 31);
		}

		if (isInA(t)) {
			t = getRandom(1, 31);
		}
		if (isInA(t)) {
			t = getRandom(1, 31);
		}
		if (isInA(t)) {
			t = getRandom(1, 31);
		}
		if (!isInA(t)) {
			a.add(t);
		}

		if (t == 1) {
			i = R.drawable.test_head_01;
		}
		if (t == 2) {
			i = R.drawable.test_head_02;
		}
		if (t == 3) {
			i = R.drawable.test_head_03;
		}
		if (t == 4) {
			i = R.drawable.test_head_04;
		}
		if (t == 5) {
			i = R.drawable.test_head_05;
		}
		if (t == 6) {
			i = R.drawable.test_head_06;
		}
		if (t == 7) {
			i = R.drawable.test_head_07;
		}
		if (t == 8) {
			i = R.drawable.test_head_08;
		}
		if (t == 9) {
			i = R.drawable.test_head_09;
		}
		if (t == 10) {
			i = R.drawable.test_head_10;
		}
		if (t == 11) {
			i = R.drawable.test_head_11;
		}
		if (t == 12) {
			i = R.drawable.test_head_12;
		}
		if (t == 13) {
			i = R.drawable.test_head_13;
		}
		if (t == 14) {
			i = R.drawable.test_head_14;
		}
		if (t == 15) {
			i = R.drawable.test_head_15;
		}
		if (t == 16) {
			i = R.drawable.test_head_16;
		}
		if (t == 17) {
			i = R.drawable.test_head_17;
		}
		if (t == 18) {
			i = R.drawable.test_head_18;
		}
		if (t == 19) {
			i = R.drawable.test_head_19;
		}
		if (t == 20) {
			i = R.drawable.test_head_20;
		}
		if (t == 21) {
			i = R.drawable.test_head_21;
		}
		if (t == 22) {
			i = R.drawable.test_head_22;
		}
		if (t == 23) {
			i = R.drawable.test_head_23;
		}
		if (t == 24) {
			i = R.drawable.test_head_24;
		}
		if (t == 25) {
			i = R.drawable.test_head_25;
		}
		if (t == 26) {
			i = R.drawable.test_head_26;
		}
		if (t == 27) {
			i = R.drawable.test_head_27;
		}
		if (t == 28) {
			i = R.drawable.test_head_28;
		}
		if (t == 29) {
			i = R.drawable.test_head_29;
		}
		if (t == 30) {
			i = R.drawable.test_head_30;
		}
		if (t == 31) {
			i = R.drawable.test_head_31;
		}
		return i;
	}

	private static String[] names = new String[] { "阿Li", "维以不永伤", 
		"/xj爱鸭肉", "●﹏●", "°○o牆角見", "皮卡 ", "Chocolate ice", "角隅的呵 ", 
		"( ⊙ o ⊙ )", "毕业前的雨季", "903268978", "小酒量", "≮回到过去≯ ", "s. 碧海蓝天 ",
		"来来", "左边驰仔", "liangcan", "～范蔸～", "灵感落差", "梅子", 
		"AsionL_only", "Rita_zhong", "聆叮叮", "康诺斯", "天角", "cole", 
		"ewolf", "惠小陆",
		"ヤMaybe.o", "夏浅忆", "JinJun_儀", "♫ ", "SKY", "Find My Way",
		"kira", "Crazy about", "银河泻影", "小小情意 ",
		"尹天仇", "爵·千百度", "东方使者", "唉聲歎氣",
		"L-Tune", "在河", "雨", "安  chincely",
		"baobao", "Max~谦.Ra", "宇辰", "風之痕",
		"芝麻糊", "~神留下の遗物", "开始懂了", "吥信愛情",
		"LeKSun", "Iatcontinue~", "幽光步蒙", "雪月灵枫",
		"Eve", "羊 ", "刹那永恒", "Larry灬 ",
		"无心", "知秋一叶", "（原 味。"
	};
	
	
	private static String[] contents = new String[] {
		"你唱歌真是越来越好听了",
		
		"必须顶！",
		"o(∩_∩)o 哈哈",
		"--如果有一天我不再主动找你，不是因为你不重要了，而是我不知道我还重不重要。",
		"嘿嘿.终于找到你了。",
		"加我QQ251582940",
		"稳如泰山，方能稳操胜券！",
		"人靓声甜，good~",
		"貌似我错过了很多美好的东西",
		"一人回复一句：我的天~",
		"5201314",
		"不管未来如何，活在当下，开心过好每一天，多点宽容多点感恩",
		"15807850660",
		"冠军！",
		"坑爹的。。",
		"become a star",
		"善待自己，注意身体",
		"喜欢你",
		"感觉有点像哪位明星，那谁了？",
		"来一首粤语歌吧，没见你唱过~",
		"那些年，我们一起追过的女孩",
		"真心不错。阿妹加油！！！",
		"这是我见过最棒的..",
		"人过留名, 多谢支持",
		"膜拜了"
	};
	
	
	
	public static String[] times = new String[]{
		"46分钟前",
		"今天06:51",
		"今天01:00",
		"昨天18:23",
		"昨天14:05",
		"昨天13:05",
		"昨天13:00",
		"昨天08:44",
		"前天22:11",
		"前天20:08",
		"前天17:43",
		"前天16:01",
		"前天16:00",
		"前天14:51",
		"前天10:33",
		"前天09:38",
		"2013-11-11 17:43",
		"2013-11-11 17:43",
		"2013-11-11 06:51",
		"2013-11-11 01:00",
		"2013-11-11 14:05",
		"2013-11-11 13:05",
		"2013-11-11 13:00",
		"2013-11-11 08:44"
}
	;
	

	public static String getRandomContent(){
		int i = getRandom(0, contents.length);
		String name = contents[i];
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		if(name.equals("")){
			i = getRandom(0, contents.length);
		    name = contents[i];
		}
		
		contents[i]  ="";
		return name;
	}
	
	public static String getRandomName() {
		int i = getRandom(0, names.length);
	    String name = names[i];
	    if(name.equals("")){
	    	i = getRandom(0, names.length);
		    name = names[i];
	    }
	    if(name.equals("")){
	    	i = getRandom(0, names.length);
		    name = names[i];
	    }
	    if(name.equals("")){
	    	i = getRandom(0, names.length);
		    name = names[i];
	    }
	    if(name.equals("")){
	    	i = getRandom(0, names.length);
		    name = names[i];
	    }
	    if(name.equals("")){
	    	i = getRandom(0, names.length);
		    name = names[i];
	    }
	    if(name.equals("")){
	    	i = getRandom(0, names.length);
		    name = names[i];
	    }
	    names[i] = "";
	    return name;
	}

	public static boolean getRandomShare(){
		int i = getRandom(0, 100);
		if(i%3==0){
			return true;
		}
		return false;
	}
	
	private static int getRandom(int start, int end) {
		return (int) (Math.random() * end + start);
	}
}
