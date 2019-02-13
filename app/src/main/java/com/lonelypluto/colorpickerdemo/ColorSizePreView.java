package com.lonelypluto.colorpickerdemo;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description: 颜色大小预览view
 * @author: ZhangYW
 * @time: 2019/2/13 15:34
 */
public class ColorSizePreView extends View {

	private int viewWidth;
	private int viewHeigt;
	private int size = 20;// 大小
	private Paint mPaint;// 画笔

	public ColorSizePreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.RED);

		viewWidth = (int) getResources()
				.getDimension(R.dimen.color_size_preview_width);
		viewHeigt = (int) getResources().getDimension(
				R.dimen.color_size_preview_height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制成一个圆
		canvas.drawCircle(viewWidth / 2, viewHeigt / 2, size, mPaint);
	}

	/**
	 * 设置view的颜色
	 * @param color
	 */
	public void setPaintColor(int color) {
		mPaint.setColor(color);
		invalidate();
	}
}
