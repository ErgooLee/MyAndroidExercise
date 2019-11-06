package com.example.test.anim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.R;
import com.example.test.utils.Dips;
import com.example.test.utils.Views;

@SuppressLint("ViewConstructor")
public class AppPromptView extends FrameLayout {
    private View mainPart;
    private ImageView closeBtn;

    public AppPromptView(@NonNull Context context) {
        this(context, null);
    }

    public AppPromptView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppPromptView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(@NonNull Context context) {
        removeAllViews();
        LayoutInflater.from(context).inflate(R.layout.tweens_anim_layout, this);
        closeBtn = findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startCloseAnim();
            }
        });
        mainPart = findViewById(R.id.mainPart);
        float scale = Dips.getRelativeScale(context);

        Views.resizeView(this, scale);
    }

    private void startCloseAnim() {

        Animation scale = AnimationUtils.loadAnimation(getContext(),
                R.anim.scale_out);
        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation rotateScale = AnimationUtils.loadAnimation(getContext(),
                        R.anim.rotate_out);
                rotateScale.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        initView(getContext());
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                closeBtn.startAnimation(rotateScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mainPart.startAnimation(scale);
    }


}
