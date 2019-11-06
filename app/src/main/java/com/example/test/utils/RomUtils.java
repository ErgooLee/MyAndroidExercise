package com.example.test.utils;

import android.os.Build;
import android.text.TextUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RomUtils {

    private static String sMarketPkgName;

    private static String sRomName;

    private static String sRomVersion;

    public static boolean isHuaWei() {
        return isRomOf("EMUI");
    }

    public static boolean isVIVO() {
        return isRomOf("VIVO");
    }

    public static boolean isOPPO() {
        return isRomOf("OPPO");
    }

    public static String getMarketPkgName() {
        if (sMarketPkgName == null) {
//            isRomOf(BuildConfig.FLAVOR);
        }
        return sMarketPkgName;
    }

    private static boolean isRomOf(String str) {
        if (sRomName != null) {
            return sRomName.equals(str);
        }
        String b = getPropValue("ro.miui.ui.version.name");
        sRomVersion = b;
        if (!TextUtils.isEmpty(b)) {
            sRomName = "MIUI";
            sMarketPkgName = "com.xiaomi.market";
        } else {
            String b2 = getPropValue("ro.build.version.emui");
            sRomVersion = b2;
            if (!TextUtils.isEmpty(b2)) {
                sRomName = "EMUI";
                sMarketPkgName = "com.huawei.appmarket";
            } else {
                String b3 = getPropValue("ro.build.version.opporom");
                sRomVersion = b3;
                if (!TextUtils.isEmpty(b3)) {
                    sRomName = "OPPO";
                    sMarketPkgName = "com.oppo.market";
                } else {
                    String b4 = getPropValue("ro.vivo.os.version");
                    sRomVersion = b4;
                    if (!TextUtils.isEmpty(b4)) {
                        sRomName = "VIVO";
                        sMarketPkgName = "com.bbk.appstore";
                    } else {
                        String b5 = getPropValue("ro.smartisan.version");
                        sRomVersion = b5;
                        if (!TextUtils.isEmpty(b5)) {
                            sRomName = "SMARTISAN";
                            sMarketPkgName = "com.smartisanos.appstore";
                        } else {
                            String b6 = getPropValue("ro.gn.sv.version");
                            sRomVersion = b6;
                            if (!TextUtils.isEmpty(b6)) {
                                sRomName = "QIONEE";
                                sMarketPkgName = "com.gionee.aora.market";
                            } else {
                                String b7 = getPropValue("ro.lenovo.lvp.version");
                                sRomVersion = b7;
                                if (!TextUtils.isEmpty(b7)) {
                                    sRomName = "LENOVO";
                                    sMarketPkgName = "com.lenovo.leos.appstore";
                                } else if (getManufacture().toUpperCase().contains("SAMSUNG")) {
                                    sRomName = "SAMSUNG";
                                    sMarketPkgName = "com.sec.android.app.samsungapps";
                                } else if (getManufacture().toUpperCase().contains("ZTE")) {
                                    sRomName = "ZTE";
                                    sMarketPkgName = "zte.com.market";
                                } else if (getManufacture().toLowerCase().contains("NUBIA")) {
                                    sRomName = "NUBIA";
                                    sMarketPkgName = "cn.nubia.neostore";
                                } else {
                                    sRomVersion = Build.DISPLAY;
                                    if (sRomVersion.toUpperCase().contains("MEIZU")) {
                                        sRomName = "MEIZU";
                                        sMarketPkgName = "com.meizu.mstore";
                                    } else {
                                        sRomVersion = "unknown";
                                        sRomName = Build.MANUFACTURER.toUpperCase();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sRomName.equals(str);
    }

    private static String getPropValue(String key) {
        String line;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + key);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }

    private static String getManufacture() {
        return null;
//        return Build.MANUFACTURER == null ? BuildConfig.FLAVOR : Build.MANUFACTURER.trim();
    }
}
