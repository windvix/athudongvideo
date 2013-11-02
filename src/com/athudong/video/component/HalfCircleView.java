package com.athudong.video.component;

import com.athudong.video.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint("DrawAllocation")
public class HalfCircleView extends View {

	private int color;
	
	private int right = 1;
	private int left = 2;
	private int top = 3;
	private int bottom = 4;
	
	private int type = 1;
	
	public HalfCircleView(Context context, AttributeSet attrs) {

		super(context, attrs);
		
		TypedArray a = context.obtainStyledAttributes(attrs ,R.styleable.ShapeView);
		color = a.getColor(R.styleable.ShapeView_color, 0);
		type = a.getInteger(R.styleable.ShapeView_type, 1);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		//canvas.drawColor(Color.BLACK); // 画布为黑色
		
		Paint paint = new Paint();
		RectF oval = new RectF(); // 浮点型的

		paint.setColor(color);
		
		paint.setStyle(Style.FILL_AND_STROKE); // 空心的

		oval = new RectF();
		//从哪里开始画，即开始画的左边具体到左边的距离
		//oval.set(-(getRight()-getLeft()), getTop(), getRight(), getBottom());
		//开始的角度为270（逆时针）.画180度
		//canvas.drawArc(oval, 90, -180f, true, paint);
		
		
		
		
		float pX = getLeft();
		float pY = getTop()+(getHeight()/2.0f);
		float r = getHeight()/2.0f;
		
		if(type==top){
			pX = getLeft()+getWidth()/2.0f;
			pY = getBottom();
			r = getWidth()/2.0f;
		}else if(type==bottom){
			pX = getLeft()+getWidth()/2.0f;
			pY = getTop();
			r = getWidth()/2.0f;
		}
		
		
		
		
		canvas.drawCircle(pX, pY, r, paint);
	}

}
