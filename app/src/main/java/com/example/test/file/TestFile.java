package com.example.test.file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.test.R;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestFile extends AppCompatActivity {
    private static final String TAG = TestFile.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_file);
        List<File> files = Arrays.asList(
                new File(getFilesDir(), "fileDir"),
                new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "externalFileDir"),
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "environmentExternalFileDir")
        );
        for (File file : files) {
            testFile(file);
        }

    }

    private void testFile(File file) {
        try {
            if (file.exists()) {
                Log.d(TAG, file.getAbsolutePath() + " exist");
            } else {
                file.mkdir();
                Log.d(TAG, file.getAbsolutePath() + " not exist");
            }
        } catch (Exception exception) {
            Log.d(TAG, file.getAbsolutePath() + " " + exception.getMessage());
        }

    }


}