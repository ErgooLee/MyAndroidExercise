package com.example.test.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Packages {

    private static final String APK_MIME = "application/vnd.android.package-archive";

    public static Intent getInstallApkIntent(@NonNull Uri uri,
                                             @NonNull Context context) {

        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.setDataAndType(uri, APK_MIME);
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)) {
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        if (installIntent.resolveActivity(context.getPackageManager()) != null) {
            return installIntent;
        } else {
            return null;
        }
    }

    public static @Nullable
    String getSourceDir(@NonNull Context context, @NonNull String pkgName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(pkgName, PackageManager.GET_META_DATA);
            return applicationInfo.sourceDir;
        } catch (Exception ignored) {

        }
        return null;
    }

}
