package com.example.test.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.test.utils.Dips;

public class ProgressView extends View {

    private ValueAnimator progressAnimator;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int strokeColor = Color.GREEN;
    int backgroundColor = Color.GRAY;
    int foregroundColor = Color.RED;
    RectF strokeRect = null;
    RectF progressRectLeft = null;
    RectF progressRectRight = null;
    boolean hasStroke = false;
    int strokeWidth;
    int halfStrokeWidth;
    int progress = 20;
    float progressFloat = 0;
    float degree;

    public ProgressView(Context context) {
        super(context);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProgressWithAnim((progress + 10) % 100);
            }
        });
        strokeWidth = Dips.dipsToIntPixels(1, context);
        halfStrokeWidth = strokeWidth / 2;
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public ProgressView setProgressStrokeColor(int color) {
        this.strokeColor = color;
        this.hasStroke = true;
        invalidate();
        return this;
    }

    public ProgressView setProgressBackgroundColor(int color) {
        this.backgroundColor = color;
        invalidate();
        return this;
    }

    public ProgressView setProgressForegroundColor(int color) {
        this.foregroundColor = color;
        invalidate();
        return this;
    }

    public ProgressView enableStroke(boolean enable) {
        if (hasStroke != enable) {
            hasStroke = enable;
            invalidate();
        }
        return this;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.strokeRect = new RectF(halfStrokeWidth, halfStrokeWidth, w - halfStrokeWidth, h - halfStrokeWidth);
        this.progressRectLeft = new RectF(0, 0, h, h);
        calculate();
    }

    public void changeProgress(int newProgress) {
        if (newProgress < 0) {
            newProgress = 0;
        }
        if (newProgress > 100) {
            newProgress = 100;
        }
        this.progress = newProgress;
        calculate();
        invalidate();
    }

    public int getProgress() {
        return progress;
    }

    public void changeProgressAndCancelAnim(int newProgress) {
        cancelAnim();
        changeProgress(newProgress);
    }

    public void changeProgressWithAnim(int newProgress) {
        startProgressAnim(progress, newProgress);
    }

    private void calculate() {
        int height = getHeight();
        int width = getWidth();
        progressFloat = progress / 100.f;
        if (height != 0 && width != 0) {
            float w2 = progressFloat * width;
            progressRectRight = new RectF(w2 - height, 0, w2, height);
            degree = (float) Math.toDegrees(Math.acos((height - (double) progressFloat * width) / height));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getHeight() == 0 || getWidth() == 0) {
            return;
        }
        drawBackground(canvas);
        drawForeground(canvas);
        drawStroke(canvas);
    }

    private void drawBackground(Canvas canvas) {
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(backgroundColor);
        paint.setStrokeWidth(getHeight());
        int halfHeight = getHeight() / 2;
        int width = getWidth();
        canvas.drawLine(halfHeight, halfHeight, width - halfHeight, halfHeight, paint);
    }

    private void drawForeground(Canvas canvas) {
        int height = getHeight();
        float halfHeight = height / 2.f;
        int width = getWidth();
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(foregroundColor);
        if (progressFloat <= (float) height / width) {
            paint.setStrokeWidth(1);
            canvas.drawArc(progressRectLeft, 180 - degree, 2 * degree, false, paint);
            canvas.drawArc(progressRectRight, -degree, 2 * degree, false, paint);
        } else {
            paint.setStrokeWidth(height);
            canvas.drawLine(halfHeight, halfHeight, width * progressFloat - halfHeight, halfHeight, paint);
        }
    }


    private void drawStroke(Canvas canvas) {
        if (hasStroke) {
            paint.setStrokeCap(Paint.Cap.SQUARE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(strokeColor);
            paint.setStrokeWidth(strokeWidth);
            int halfHeight = getHeight() / 2;
            canvas.drawRoundRect(strokeRect, halfHeight, halfHeight, paint);
        }
    }

    private void startProgressAnim(int start, int end) {
        cancelAnim();
        progressAnimator = ValueAnimator.ofInt(start, end);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.setDuration(200);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = ((int) animation.getAnimatedValue());
                changeProgress(value);
            }
        });
        progressAnimator.start();
    }

    private void cancelAnim() {
        if (progressAnimator != null) {
            progressAnimator.cancel();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnim();
    }
}
