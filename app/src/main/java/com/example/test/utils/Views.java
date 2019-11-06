package com.example.test.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Views {
    public static void removeFromParent(@Nullable View view) {
        if (view == null || view.getParent() == null) {
            return;
        }

        if (view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    /**
     * Finds the topmost view in the current Activity or current view hierarchy.
     *
     * @param context If an Activity Context, used to obtain the Activity's DecorView. This is
     *                ignored if it is a non-Activity Context.
     * @param view    A View in the currently displayed view hierarchy. If a null or non-Activity
     *                Context is provided, this View's topmost parent is used to determine the
     *                rootView.
     * @return The topmost View in the currency Activity or current view hierarchy. Null if no
     * applicable View can be found.
     */
    @Nullable
    public static View getTopmostView(@Nullable final Context context, @Nullable final View view) {
        final View rootViewFromActivity = getRootViewFromActivity(context);
        final View rootViewFromView = getRootViewFromView(view);

        // Prefer to use the rootView derived from the Activity's DecorView since it provides a
        // consistent value when the View is not attached to the Window. Fall back to the passed-in
        // View's hierarchy if necessary.
        return rootViewFromActivity != null
                ? rootViewFromActivity
                : rootViewFromView;
    }

    @Nullable
    private static View getRootViewFromActivity(@Nullable final Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }

        return ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
    }

    @Nullable
    private static View getRootViewFromView(@Nullable final View view) {
        if (view == null) {
            return null;
        }


        final View rootView = view.getRootView();

        if (rootView == null) {
            return null;
        }

        final View rootContentView = rootView.findViewById(android.R.id.content);
        return rootContentView != null
                ? rootContentView
                : rootView;
    }

    public static boolean isVisible(View view, int minVisibleArea, int minPercentArea) {
        if (view == null || !view.isShown()) {
            return false;
        }

        boolean visible = view.getWindowVisibility() == View.VISIBLE;
        if (!visible) {
            return false;
        }

        Rect clipRect = new Rect();
        if (!view.getGlobalVisibleRect(clipRect)) {
            return false;
        }

        long visibleViewArea = (long) clipRect.height() * clipRect.width();
        long totalViewArea = (long) view.getHeight() * view.getWidth();

        if (totalViewArea <= 0) {
            return false;
        }

        if (minVisibleArea > 0) {
            return visibleViewArea >= minVisibleArea;
        }

        return 100 * visibleViewArea >= minPercentArea * totalViewArea;
    }

    public static void registerViewOnTouch(View v, View.OnTouchListener listener) {
        if (v == null) {
            return;
        }
        List<View> children = getAllChildrenList(v);
        for (View view : children) {
            view.setOnTouchListener(listener);
        }
    }

    public static List<View> getAllChildrenList(View v) {
        ArrayList<View> ret = new ArrayList<>();
        if (v instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                ret.addAll(getAllChildrenList(viewGroup.getChildAt(i)));
            }
            ret.add(v);
        } else {
            ret.add(v);
        }
        return ret;
    }

    private static Point sizePoint = new Point(-1, -1);
    private static Point downPoint = new Point(-1, -1);
    private static Point upPoint = new Point(-1, -1);

    public static List<Integer> calculateClickLocation(View view, MotionEvent event) {
        if (view == null || event == null) {
            return null;
        }
        try {
            int[] location = new int[2];
            List<Integer> clickInfos = new ArrayList<>();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sizePoint.x = view.getWidth();
                    sizePoint.y = view.getHeight();

                    view.getLocationOnScreen(location);
                    int downRawX = (int) event.getRawX();
                    int downRawY = (int) event.getRawY();

                    downPoint.x = downRawX - location[0];
                    downPoint.y = downRawY - location[1];
                    break;
                case MotionEvent.ACTION_UP:
                    view.getLocationOnScreen(location);
                    int upRawX = (int) event.getRawX();
                    int upRawY = (int) event.getRawY();

                    upPoint.x = upRawX - location[0];
                    upPoint.y = upRawY - location[1];

                    clickInfos.add(sizePoint.x);
                    clickInfos.add(sizePoint.y);
                    clickInfos.add(downPoint.x);
                    clickInfos.add(downPoint.y);
                    clickInfos.add(upPoint.x);
                    clickInfos.add(upPoint.y);
                    emptyPint();
                    break;
                default:
                    break;
            }
            return clickInfos;
        } catch (Exception e) {
            //  ignore
        }
        return null;
    }

    private static void emptyPint() {
        sizePoint.x = -1;
        sizePoint.y = -1;
        downPoint.x = -1;
        downPoint.y = -1;
        upPoint.x = -1;
        upPoint.y = -1;
    }

    /**
     * 从view中获取activity 引用
     *
     * @param view 视图
     * @return Activity view所在activity
     */
    @Nullable
    public static Activity getActivityOfView(View view) {
        int contextWrapperNum;
        while (view != null) {
            Context context = view.getContext();
            contextWrapperNum = 0;
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                context = ((ContextWrapper) context).getBaseContext();
                contextWrapperNum++;
                if (contextWrapperNum >= 10) {
                    //嵌套层数过多，可能存在死循环，直接跳出
                    break;
                }
            }
            //从父布局中找activity
            ViewParent viewParent = view.getParent();
            if (viewParent instanceof ViewGroup) {
                view = ((ViewGroup) viewParent);
            } else {
                //上层某些写法，parent 可能为空。
                view = null;
            }
        }
        return null;
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    /**
     * Generate a value suitable for use in {@link #setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            return View.generateViewId();
        }
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static void resizeView(View view, float scale) {
        if (view == null || scale <= 0 || scale == 1) {
            return;
        }


        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = ((ViewGroup.MarginLayoutParams) layoutParams);
                marginLayoutParams.bottomMargin *= scale;
                marginLayoutParams.topMargin *= scale;
                marginLayoutParams.leftMargin *= scale;
                marginLayoutParams.rightMargin *= scale;
            }
            if (layoutParams.height > 0) {
                layoutParams.height *= scale;
            }
            if (layoutParams.width > 0) {
                layoutParams.width *= scale;
            }
        }
        if (view instanceof TextView) {
            TextView textView = ((TextView) view);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.getTextSize() * scale);
        }
        float paddingLeft = view.getPaddingLeft() * scale;
        float paddingRight = view.getPaddingRight() * scale;
        float paddingTop = view.getPaddingTop() * scale;
        float paddingBottom = view.getPaddingBottom() * scale;
        view.setPadding((int) paddingLeft, (int) paddingTop, (int) paddingRight, (int) paddingBottom);

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = ((ViewGroup) view);
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                resizeView(viewGroup.getChildAt(i), scale);
            }
        }
    }


    public static void resizeChildView(ViewGroup viewGroup, float scale) {
        if (viewGroup == null || scale <= 0 || scale == 1) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            resizeView(viewGroup.getChildAt(i), scale);
        }
    }



}
