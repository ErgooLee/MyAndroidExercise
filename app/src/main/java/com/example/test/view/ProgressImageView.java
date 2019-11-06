package com.example.test.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;


@SuppressLint("AppCompatCustomView")
public class ProgressImageView extends ImageView {

    private ClipDrawable clipDrawable;
    private ValueAnimator progressAnimator;

    public ProgressImageView(Context context) {
        this(context, null);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    @SuppressWarnings("unused")
    private void init(Context context, AttributeSet attrs, int defStyle) {
        initClipDrawable();
    }

    private void initClipDrawable() {
        Drawable drawable = getDrawable();
        if (drawable instanceof ClipDrawable) {
            clipDrawable = ((ClipDrawable) drawable);
            setProgress(100);
        }
    }

    public void changeForegroundColor(int color) {
        GradientDrawable shapeDrawable = new GradientDrawable();
        shapeDrawable.setShape(GradientDrawable.RECTANGLE);
        shapeDrawable.setColor(color);
        shapeDrawable.setCornerRadius(23);
        ClipDrawable clipDrawable = new ClipDrawable(shapeDrawable, Gravity.START, ClipDrawable.HORIZONTAL);
        setImageDrawable(clipDrawable);
        clipDrawable.setLevel(this.clipDrawable.getLevel());
        this.clipDrawable = clipDrawable;
    }

    public void setProgress(int progress) {
        clipDrawable.setLevel(progress * 100);
        if (progressAnimator != null) {
            progressAnimator.cancel();
            progressAnimator = null;
        }
    }

    public int getProgress() {
        return clipDrawable.getLevel() / 100;
    }

    public void setProgressWithAnim(int progress) {
        int newLevel = progress * 100;
        if (newLevel > clipDrawable.getLevel()) {
            startProgressAnim(newLevel);
        } else {
            setProgress(progress);
        }
    }

    private void startProgressAnim(int level){
        if (progressAnimator != null) {
            progressAnimator.cancel();
            progressAnimator = null;
        }
        progressAnimator = ValueAnimator.ofInt(clipDrawable.getLevel(), level);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.setDuration(200);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = ((int) animation.getAnimatedValue());
                clipDrawable.setLevel(value);
            }
        });
        progressAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (progressAnimator != null) {
            progressAnimator.cancel();
        }
    }
}

