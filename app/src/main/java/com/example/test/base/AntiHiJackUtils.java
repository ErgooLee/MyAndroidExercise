package com.example.test.base;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;


import com.example.test.utils.RomUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;

public class AntiHiJackUtils {

    private static class InstallIntentProxy implements InvocationHandler {

        private int type;
        private Object activityManager;

        private InstallIntentProxy(Object obj, int type) {
            this.activityManager = obj;
            this.type = type;
        }

        @Override
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            boolean z = true;
            Log.d("chao", "proxy invoke ");

            try {
                if ("startActivity".contains(method.getName()) && (objArr[1] instanceof String) && (objArr[2] instanceof Intent)) {
                    Log.d("chao", "startActivity ");
                    Intent intent = (Intent) objArr[2];
                    if ("android.intent.action.VIEW".equals(intent.getAction()) && "application/vnd.android.package-archive".equals(intent.getType())) {
                        Log.d("chao", "proxy install ");
                        if (RomUtils.isVIVO()) {
                            if (type == 1) {
                                objArr[1] = "com.bbk.appstore";
                            } else {
                                objArr[1] = "com.android.settings";
                            }
                            Log.d("chao", "go vivo ");

                        } else if (RomUtils.isOPPO()) {
                            if (type == 1) {
                                objArr[1] =  "com.oppo.market";
                            } else {
                                objArr[1] = "com.android.settings";
                            }
                            String optString3 = "com.android.browser";
                            String optString4 = "m.store.oppomobile.com";
                            intent.putExtra("oppo_extra_pkg_name", optString3);
                            intent.putExtra("refererHost", optString4);
//                            if (C0606ly.m2976h().optInt("hook_oppo_arg4", 0) != 1) {
//                                z = false;
//                            }
                            if (z) {
                                Intent intent2 = new Intent();
                                intent2.putExtra("oppo_extra_pkg_name", optString3);
                                intent2.putExtra("refererHost", optString4);
                                intent.putExtra("android.intent.extra.INTENT", intent2);
                            }
                            Log.d("chao", "go oppo ");

                        } else if (RomUtils.isHuaWei()) {
                            String optString5 = "com.huawei.appmarket";
                            if (type == 2) {
                                optString5 = "com.android.settings";
                            }

                            objArr[1] = optString5;
                            intent.putExtra("caller_package", "com.huawei.appmarket");
                            Log.d("chao", "go huawei ");

                        }
                    }
                }
            } catch (Throwable th) {
                Log.e("chao", "error 1", th);

            }
            try {
                return method.invoke(this.activityManager, objArr);
            } catch (Throwable th2) {
                Log.e("chao", "error 2", th2);

                throw m3086a(th2);
            }
        }

        private Throwable m3086a(Throwable th) {
            Throwable th2;
            StackTraceElement[] stackTraceElementArr;
            try {
                StackTraceElement[] stackTrace = th.getStackTrace();
                if (th instanceof InvocationTargetException) {
                    InvocationTargetException invocationTargetException = (InvocationTargetException) th;
                    if (invocationTargetException.getTargetException() != null) {
                        Throwable targetException = invocationTargetException.getTargetException();
                        try {
                            stackTraceElementArr = targetException.getStackTrace();
                            th2 = targetException;
                        } catch (Throwable th3) {
                            return targetException;
                        }
                    } else {
                        th2 = new RuntimeException();
                        stackTraceElementArr = stackTrace;
                    }
                    stackTrace = stackTraceElementArr;
                } else if (th instanceof UndeclaredThrowableException) {
                    UndeclaredThrowableException undeclaredThrowableException = (UndeclaredThrowableException) th;
                    if (undeclaredThrowableException.getUndeclaredThrowable() != null) {
                        th2 = undeclaredThrowableException.getUndeclaredThrowable();
                        try {
                            stackTrace = th2.getStackTrace();
                        } catch (Throwable th4) {
                            return th2;
                        }
                    } else {
                        th2 = new RuntimeException();
                    }
                } else {
                    th2 = th;
                }
                try {
                    ArrayList arrayList = new ArrayList();
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        if (stackTraceElement != null) {
                            String className = stackTraceElement.getClassName();
                            if (TextUtils.isEmpty(className) || (!className.contains("com.ss.android.downloadlib") && !className.equals(Proxy.class.getName()) && !className.equals(Method.class.getName()))) {
                                arrayList.add(stackTraceElement);
                            }
                        }
                    }
                    if (arrayList.isEmpty()) {
                        return th2;
                    }
                    StackTraceElement[] stackTraceElementArr2 = new StackTraceElement[arrayList.size()];
                    for (int i = 0; i < stackTraceElementArr2.length; i++) {
                        stackTraceElementArr2[i] = (StackTraceElement) arrayList.get(i);
                    }
                    th2.setStackTrace(stackTraceElementArr2);
                    return th2;
                } catch (Throwable th5) {
                    return th2;
                }
            } catch (Throwable th6) {
                return th;
            }
        }
    }



    public static void setDynamicProxy(int type) {
        Field activityManager;
        Log.d("chao", "set start");

        try {
            if (Build.VERSION.SDK_INT < 26) {
                activityManager = Class.forName("android.app.ActivityManagerNative").getDeclaredField("gDefault");
            } else {
                activityManager = Class.forName("android.app.ActivityManager").getDeclaredField("IActivityManagerSingleton");
            }
            activityManager.setAccessible(true);
            Object obj = activityManager.get(null);
            Field declaredField2 = Class.forName("android.util.Singleton").getDeclaredField("mInstance");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            if (obj2 != null) {
                Class cls = Class.forName("android.app.IActivityManager");
                if (cls != null) {
                    InstallIntentProxy aVar = new InstallIntentProxy(obj2, type);
                    declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, aVar));
                    Log.d("chao", "proxy done");

                } else {
                    Log.d("chao", "set error 2");

                }
            } else {
                Log.d("chao", "set error 1");

            }
        } catch (Throwable th) {
            Log.e("chao", "set error", th);

        }
    }



}
