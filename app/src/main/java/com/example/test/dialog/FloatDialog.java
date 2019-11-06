package com.example.test.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FloatDialog extends Dialog {

    public FloatDialog(@NonNull Context context) {
        super(context);
    }

    public FloatDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FloatDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        Window window = getWindow();
        if (window != null) {
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setDimAmount(0.0f);
            window.setGravity(Gravity.BOTTOM);
        }
        Button button = new Button(getContext());
        button.setText("button");
        setContentView(button);
    }

    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //todo may crash
            Activity activity = (Activity) getContext();
            activity.dispatchKeyEvent(event);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


}
