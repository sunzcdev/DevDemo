package com.cnjaj.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ClockView extends ImageView {
    private Paint mPaint;
    private int mWidth = 500, mHeight = 500;
    private int mPadding = 3;
    private int mScaleLength = 60;
    private Calendar mCalendar;
    private int mSecond;
    private int mHour;
    private int mMinutes;
    private int mDegrees;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

    public ClockView(Context context) {
        super(context);
        initPaint();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画表圈
        drawCircle(canvas);
        //画可堵
        drawScale(canvas);
        //画表针
        drawPointer(canvas);
        mHandler.sendEmptyMessage(0);
    }

    private void drawPointer(Canvas canvas) {
        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR);
        mMinutes = mCalendar.get(Calendar.MINUTE);
        mSecond = mCalendar.get(Calendar.SECOND);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mDegrees = mHour * 30 + mMinutes / 2;
        canvas.save();
        canvas.rotate(mDegrees, getWidth() / 2, getHeight() / 2);

        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, (float) (getClockRadius() * 0.8), mPaint);
        canvas.restore();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
        mDegrees = mMinutes * 6 + mSecond / 10;
        canvas.save();
        canvas.rotate(mDegrees, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, (float) (getClockRadius() * 0.7), mPaint);
        canvas.restore();
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(4);

        mDegrees = mSecond * 6;
        canvas.save();
        canvas.rotate(mDegrees, getWidth() / 2, getHeight() / 2);
        canvas.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2, (float) (getClockRadius() * 0.6), mPaint);
        canvas.restore();
    }

    private void drawScale(Canvas canvas) {
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.FILL);
        for (int position = 0; position < 60; position++) {
            if (position % 15 == 0) {
                canvas.drawLine(getClockRadius(), getHeight() / 2 - getClockRadius(), getClockRadius(), getHeight() / 2 - getClockRadius() + (mScaleLength), mPaint);
            } else if (position % 5 == 0) {
                canvas.drawLine(getClockRadius(), getHeight() / 2 - getClockRadius(), getClockRadius(), getHeight() / 2 - getClockRadius() + (mScaleLength / 3 * 2), mPaint);
            } else
                canvas.drawLine(getClockRadius(), getHeight() / 2 - getClockRadius(), getClockRadius(), getHeight() / 2 - getClockRadius() + (mScaleLength / 3), mPaint);
            canvas.rotate(6, getWidth() / 2, getHeight() / 2);
        }
    }

    private int getClockRadius() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2;
    }

    private void drawCircle(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getClockRadius(), mPaint);
    }
}
