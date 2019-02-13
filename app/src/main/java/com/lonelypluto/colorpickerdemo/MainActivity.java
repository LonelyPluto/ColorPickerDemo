package com.lonelypluto.colorpickerdemo;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SeekBar sb_colorPicker;// 拖动条
    private ColorSizePreView colorSizePreView;// 预览图
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        sb_colorPicker = (SeekBar)findViewById(R.id.sb_color);
        //设置初始值
        sb_colorPicker.setProgress(10);
        Drawable thumb = sb_colorPicker.getThumb();
        thumb.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

        colorSizePreView = (ColorSizePreView)findViewById(R.id.colorsizepreview);
        tv = (TextView)findViewById(R.id.tv);

        // seekbar拖动监听事件
        sb_colorPicker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float radio = (float) progress / sb_colorPicker.getMax();

                int mColor = ColorPickerGradientUtil.getColor(radio);
                // 设置文字颜色
                tv.setTextColor(Color.parseColor("#"+ColorPickerGradientUtil.getHexString(mColor)));
                // 设置拖动按钮随颜色的改变而改变
                Drawable thumb = sb_colorPicker.getThumb();
                thumb.setColorFilter(mColor, PorterDuff.Mode.SRC_IN);
                // 设置预览view颜色
                colorSizePreView.setPaintColor(mColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ShapeDrawable.ShaderFactory shaderFactory = new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(int width, int height) {
                LinearGradient linearGradient = new LinearGradient(0, 0, width, height,
                        ColorPickerGradientUtil.PICKCOLORBAR_COLORS, ColorPickerGradientUtil.PICKCOLORBAR_POSITIONS, Shader.TileMode.REPEAT);
                return linearGradient;
            }
        };

        PaintDrawable paint = new PaintDrawable();
        paint.setShape(new RectShape());
        paint.setCornerRadius(10);
        paint.setShaderFactory(shaderFactory);
        sb_colorPicker.setProgressDrawable(paint);
    }
}
