package com.gzh.library;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by gongzhanhong on 15/2/3.
 */
public class LogisticsTimeline extends View {
  private Paint mPaint;
  private int            mLargeCircleRadius;
  private int            mSmallCircleRadius;
  private int            mLargeCircleColor;
  private int            mSmallCircleColor;
  private int            mLineWidth;
  private int            mLineHeight;
  private int            mLineColor;
  private int            mTextColor;
  private int            mTextSize;
  private CharSequence[] mDescArray;
  private CharSequence[] mDateArray;
  private int            mHorizontalSpacing;
  private int            mDatePaddingTop;

  public LogisticsTimeline(Context context) {
    this(context, null, 0);
  }

  public LogisticsTimeline(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LogisticsTimeline(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs, defStyleAttr, 0);
  }

  private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    mLargeCircleRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
        getResources().getDisplayMetrics());
    mSmallCircleRadius = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6,
        getResources().getDisplayMetrics()));
    mLargeCircleColor = getResources().getColor(android.R.color.holo_red_light);
    mSmallCircleColor = getResources().getColor(android.R.color.darker_gray);
    mLineHeight = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics
        ()));
    mLineWidth = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
    mTextSize = (int) ((TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics())));
    mTextColor = getResources().getColor(R.color.abc_secondary_text_material_light);
    mLineColor = getResources().getColor(android.R.color.darker_gray);
    mHorizontalSpacing = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources()
        .getDisplayMetrics()));

    mDatePaddingTop = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
        getResources().getDisplayMetrics()));

    if (attrs != null) {
      TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LogisticTimeline, defStyleAttr, defStyleRes);
      mLargeCircleRadius = array.getDimensionPixelSize(R.styleable.LogisticTimeline_large_circle_radius,
          mLargeCircleRadius);
      mSmallCircleRadius = array.getDimensionPixelSize(R.styleable.LogisticTimeline_small_circle_radius,
          mSmallCircleRadius);
      mLargeCircleColor = array.getColor(R.styleable.LogisticTimeline_large_circle_color, mLargeCircleColor);
      mSmallCircleColor = array.getColor(R.styleable.LogisticTimeline_small_circle_color, mSmallCircleColor);
      mLineWidth = array.getDimensionPixelSize(R.styleable.LogisticTimeline_line_width, mLineWidth);
      mLineHeight = array.getDimensionPixelSize(R.styleable.LogisticTimeline_line_height, mLineHeight);
      mLineColor = array.getColor(R.styleable.LogisticTimeline_line_color, mLineColor);
      mTextColor = array.getColor(R.styleable.LogisticTimeline_text_color, mTextColor);
      mTextSize = array.getDimensionPixelSize(R.styleable.LogisticTimeline_text_size, mTextSize);
      mHorizontalSpacing = array.getDimensionPixelSize(R.styleable.LogisticTimeline_horizontal_spacing,
          mHorizontalSpacing);

      mDescArray = array.getTextArray(R.styleable.LogisticTimeline_desc_array);
      mDateArray = array.getTextArray(R.styleable.LogisticTimeline_date_array);

      array.recycle();
    }

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public LogisticsTimeline(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    int xOffset = getPaddingLeft();
    int yOffset = getPaddingTop();
    if (mDescArray == null) {
      return;
    }
    Rect rect = new Rect();
    int length = mDescArray.length;
    int topY;


    //must draw line firstly and then draw circle
    if (length == 1) {
      mPaint.setColor(mLargeCircleColor);
      mPaint.getTextBounds(mDescArray[0].toString(), 0, mDescArray[0].length(), rect);
      topY = Math.max(mLargeCircleRadius, -rect.top);

      canvas.drawCircle(xOffset + mLargeCircleRadius, yOffset + topY,
          mLargeCircleRadius, mPaint);
      mPaint.setColor(mTextColor);
      mPaint.setTextSize(mTextSize);

      canvas.drawText(mDescArray[0], 0, mDescArray[0].length(), xOffset + mLargeCircleRadius * 2 +
          mHorizontalSpacing, yOffset + topY, mPaint);
      if (mDateArray.length != 0){
        canvas.drawText(mDateArray[0],0,mDateArray[0].length(),xOffset + mLargeCircleRadius * 2 +
            mHorizontalSpacing,yOffset + topY +  rect.height() + mDatePaddingTop,mPaint);
      }
    } else {
      int dateCount = mDateArray.length;
      for (int i = 0; i < length; i++) {
        mPaint.getTextBounds(mDescArray[i].toString(), 0, mDescArray[i].length(), rect);
        topY = Math.max(mLargeCircleRadius, -rect.top);
        if (i != mDescArray.length - 1) {
          mPaint.setColor(mLineColor);
          canvas.drawRect(xOffset + mLargeCircleRadius - mLineWidth / 2, yOffset + topY + i *
              mLineHeight, xOffset + mLargeCircleRadius + mLineWidth / 2, yOffset + topY + (i + 1) *
              mLineHeight, mPaint);
        }

        mPaint.setColor(i == 0 ? mLargeCircleColor : mSmallCircleColor);
        canvas.drawCircle(xOffset + mLargeCircleRadius, yOffset + topY + i * mLineHeight,
            i == 0 ? mLargeCircleRadius : mSmallCircleRadius, mPaint);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.getTextBounds(mDescArray[i].toString(), 0, mDescArray[i].length(), rect);
        canvas.drawText(mDescArray[i], 0, mDescArray[i].length(), xOffset + mLargeCircleRadius * 2 +
            mHorizontalSpacing, yOffset + topY + i * mLineHeight, mPaint);
        if(i < dateCount ){
          canvas.drawText(mDateArray[i],0,mDateArray[i].length(),xOffset+mLargeCircleRadius * 2 +
              mHorizontalSpacing,yOffset + topY + i * mLineHeight+mDatePaddingTop+rect.height(),mPaint);
        }
      }
    }


  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);
    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);

    int count = mDescArray == null ? 0 : mDescArray.length;
    int maxDescWidth = getMaxWidth(mDescArray);
    switch (widthMode) {
      case MeasureSpec.EXACTLY:
        break;
      case MeasureSpec.AT_MOST:
        int desire = getPaddingLeft() + getPaddingRight() + mLargeCircleRadius * 2 + mHorizontalSpacing +
            maxDescWidth;
        width = Math.min(desire, width);
        break;
    }

    mPaint.setTextSize(mTextSize);
    Rect rect1 = new Rect();
    Rect rect2 = new Rect();
    int topY = 0;
    int bottomY = 0;
    if (count > 0) {
      mPaint.getTextBounds(mDescArray[0].toString(), 0, mDescArray[0].length(), rect1);
      mPaint.getTextBounds(mDescArray[count - 1].toString(), 0, mDescArray[count - 1].length(), rect2);
      topY = Math.max(mLargeCircleRadius, -rect1.top);
      bottomY = Math.max(mSmallCircleColor, rect2.bottom + mDatePaddingTop + rect2.height());
    }

    switch (heightMode) {
      case MeasureSpec.EXACTLY:
        if (count != 0 && count != 1) {
          mLineHeight = (height - getPaddingTop() - getPaddingBottom() - topY - bottomY) /
              (count - 1);
        }
        break;
      case MeasureSpec.AT_MOST:
        int desire;
        if (count != 0) {
          desire = getPaddingBottom() + getPaddingTop() + (count - 1) * mLineHeight + topY +
              bottomY;
          height = Math.min(height, desire);
        }
        else {
          height = 0;
        }
        break;
    }
    setMeasuredDimension(width, height);
  }

  private int getMaxWidth(CharSequence[] array) {
    int max = 0;
    if (array != null) {
      mPaint.setTextSize(mTextSize);
      for (int i = 0; i < array.length; i++) {
        int width = (int)mPaint.measureText(array[i], 0, array[i].length());
        if (width > max) {
          max = width;
        }
      }
    }
    return max;
  }

  public CharSequence[] getDateArray() {
    return mDateArray;
  }

  public void setDateArray(CharSequence[] mDateArray) {
    this.mDateArray = mDateArray;
  }

  public CharSequence[] getDescArray() {
    return mDescArray;
  }

  public void setDescArray(CharSequence[] mDescArray) {
    this.mDescArray = mDescArray;
  }

  public int getHorizontalSpacing() {
    return mHorizontalSpacing;
  }

  public void setHorizontalSpacing(int mHorizontalSpacing) {
    this.mHorizontalSpacing = mHorizontalSpacing;
  }

  public int getLargeCircleRadius() {
    return mLargeCircleRadius;
  }

  public void setLargeCircleRadius(int mLargeCircleRadius) {
    this.mLargeCircleRadius = mLargeCircleRadius;
  }

  public int getLargeCircleColor() {
    return mLargeCircleColor;
  }

  public void setLargeCircleColor(int mLargeCircleColor) {
    this.mLargeCircleColor = mLargeCircleColor;
  }

  public int getLineColor() {
    return mLineColor;
  }

  public void setLineColor(int mLineColor) {
    this.mLineColor = mLineColor;
  }

  public int getSmallCircleColor() {
    return mSmallCircleColor;
  }

  public void setSmallCircleColor(int mSmallCircleColor) {
    this.mSmallCircleColor = mSmallCircleColor;
  }

  public int getSmallCircleRadius() {
    return mSmallCircleRadius;
  }

  public void setSmallCircleRadius(int mSmallCircleRadius) {
    this.mSmallCircleRadius = mSmallCircleRadius;
  }

  public int getTextSize() {
    return mTextSize;
  }

  public void setTextSize(int mTextSize) {
    this.mTextSize = mTextSize;
  }

  public int getTextColor() {
    return mTextColor;
  }

  public void setTextColor(int mTextColor) {
    this.mTextColor = mTextColor;
  }
}