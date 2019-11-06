package com.example.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.test.R;

import java.util.Locale;

public class ZTextView extends TextView {

    public ZTextView(Context context) {
        this(context, null, 0);
    }

    public ZTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.ZTextViewStyle);
    }

    public ZTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, R.style.ZTextStyleDef);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ZTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        TypedArray typedArray = context.
                obtainStyledAttributes(attrs, R.styleable.ZTextView, defStyleAttr, defStyleRes);
        String attr1 = typedArray.getString(R.styleable.ZTextView_attr1);
        String attr2 = typedArray.getString(R.styleable.ZTextView_attr2);
        String attr3 = typedArray.getString(R.styleable.ZTextView_attr3);
        String attr4 = typedArray.getString(R.styleable.ZTextView_attr4);
        String attr5 = typedArray.getString(R.styleable.ZTextView_attr5);
        typedArray.recycle();
        setText(String.format(Locale.US, "attr1: %s\nattr2:%s\nattr3:%s\nattr4:%s\nattr5:%s\n",
                attr1,
                attr2,
                attr3,
                attr4,
                attr5));
    }


}
