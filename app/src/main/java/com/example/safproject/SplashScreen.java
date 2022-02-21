package com.example.safproject;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SplashScreen extends AppCompatActivity {
    private File storage;
    private String[] storagePaths;
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //load data here
        storagePaths = StorageUtil.getStorageDirectories(this);
        for (String path : storagePaths) {
            storage = new File(path);
            Method.load_Directory_Files(storage);
        }
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        try {
            sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }
}
