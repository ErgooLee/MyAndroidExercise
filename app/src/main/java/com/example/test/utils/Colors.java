package com.example.test.utils;

import java.util.Locale;

public class Colors {

    public static String intColor2String(int colorInt) {
        return String.format(Locale.US, "#%08X", colorInt);
    }
}
