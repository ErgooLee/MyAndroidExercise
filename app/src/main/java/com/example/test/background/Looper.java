package com.example.test.background;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Looper {
    private static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    public static void prepare(String name) {
        sThreadLocal.set(new Looper(name));
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    public final String name;

    private Looper(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public String toString() {
        return name == null ? ":" : name;
    }

    public static void testLooper() {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare("mainThread");

                System.out.println("1 " + Looper.myLooper());

                Thread workThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        {
                            System.out.println("2 " + Looper.myLooper());
                            Looper.prepare("workThread");
                            System.out.println("3 " + Looper.myLooper());
                        }
                    }
                });
                workThread.start();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("4 " + Looper.myLooper().name);
            }
        });
        mainThread.start();
    }


}





