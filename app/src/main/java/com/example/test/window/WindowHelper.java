package com.example.test.window;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.example.test.anim.AppPromptView;

class WindowHelper {

    static void addBannerToActivity(@NonNull Activity activity,
                                    @NonNull AppPromptView appPromptView,
                                    int y, int height) {

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSPARENT;
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        params.y = y;
        params.height = height;
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.windowAnimations = 0;
        activity.getWindowManager().addView(appPromptView, params);
    }

    static void updateBannerLayout(@NonNull Activity activity,
                                   @NonNull AppPromptView appPromptView,
                                   @NonNull ViewGroup.LayoutParams params) {
        activity.getWindowManager().updateViewLayout(appPromptView, params);
    }

    static void removeBannerFromActivity(@NonNull Activity activity,
                                         @NonNull AppPromptView appPromptView) {
        activity.getWindowManager().removeViewImmediate(appPromptView);
    }
}
