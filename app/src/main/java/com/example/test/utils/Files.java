package com.example.test.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class Files {

    private static final String PROVIDER_NAME = ".fileprovider";


    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            if (inputChannel != null) {
                inputChannel.close();
            }
            if (outputChannel != null) {
                outputChannel.close();
            }
        }
    }

    public static void copyFileUsingStreams(InputStream input, OutputStream output)
            throws IOException {
        try {
            byte[] buf = new byte[8192];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            input.close();
            output.close();
        }
    }

    public static Uri buildFileUri(@NonNull Context context, @NonNull File file) {
        Uri uri = null;
        try {
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)) {
                uri = FileProvider.getUriForFile(context, context.getPackageName() + PROVIDER_NAME, file);
            } else {
                uri = Uri.fromFile(file);
            }
        } catch (Throwable ignored) {
        }
        return uri;
    }
}
