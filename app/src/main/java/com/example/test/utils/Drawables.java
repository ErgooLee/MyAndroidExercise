package com.example.test.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.graphics.drawable.DrawableCompat;

public class Drawables {

    public static Drawable tintDrawable(Context context, Drawable drawable, int color) {
        Drawable result = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(result, color);
        return result;
    }
}
