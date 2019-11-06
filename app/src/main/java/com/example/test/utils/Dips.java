package com.example.test.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;

import com.example.test.R;


public class Dips {
    public static float pixelsToFloatDips(final float pixels, final Context context) {
        return pixels / getDensity(context);
    }

    public static int pixelsToIntDips(final float pixels, final Context context) {
        return (int) (pixelsToFloatDips(pixels, context) + 0.5f);
    }

    public static float dipsToFloatPixels(final float dips, final Context context) {
        return dips * getDensity(context);
    }

    public static int dipsToIntPixels(final float dips, final Context context) {
        return (int) (dipsToFloatPixels(dips, context) + 0.5f);
    }

    public static float getDensity(final Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth(final Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(final Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static float asFloatPixels(float dips, Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, displayMetrics);
    }

    public static int asIntPixels(float dips, Context context) {
        return (int) (asFloatPixels(dips, context) + 0.5f);
    }

    public static int screenWidthAsIntDips(@NonNull Context context) {

        return pixelsToIntDips(context.getResources().getDisplayMetrics().widthPixels, context);
    }

    public static int screenHeightAsIntDips(@NonNull Context context) {

        return pixelsToIntDips(context.getResources().getDisplayMetrics().heightPixels, context);
    }

    public static Point getFitSize(Context context, int width, int height) {
        if (width <= 0 || height <= 0) {
            int fitWidth = width <= 0 ? width : dipsToIntPixels(width, context);
            int fitHeight = height <= 0 ? height : dipsToIntPixels(height, context);
            return new Point(fitWidth, fitHeight);
        }
        float ratio;
        float widthRatio = 1.0f;
        float heightRatio = 1.0f;

        int screenWidth = getScreenWidth(context);
        int screenHeight = getScreenHeight(context);
        int originWidth = dipsToIntPixels(width, context);
        int originHeight = dipsToIntPixels(height, context);

        if (originWidth > screenWidth) {
            widthRatio = (float) screenWidth / originWidth;
        }
        if (originHeight > screenHeight) {
            heightRatio = (float) screenHeight / originHeight;
        }

        ratio = widthRatio < heightRatio ? widthRatio : heightRatio;
        int fitWidth = (int) (originWidth * ratio);
        int fitHeight = (int) (originHeight * ratio);

        return new Point(fitWidth, fitHeight);
    }

    public static int getRelativePixel(@DimenRes int dimenRes, Context context) {
        return (int) (context.getResources().getDimension(dimenRes) * getRelativeScale(context));
    }

    public static float getRelativeScale(Context context) {
        return Dips.getScreenWidth(context) /
                context.getResources().getDimension(R.dimen.base_screen_width);
    }

    public static int getScreenWidthSpec(Context context) {
        return View.MeasureSpec.makeMeasureSpec(getScreenWidth(context), View.MeasureSpec.AT_MOST);
    }

    public static int getScreenHeightSpec(Context context) {
        return View.MeasureSpec.makeMeasureSpec(getScreenHeight(context), View.MeasureSpec.AT_MOST);
    }

}
