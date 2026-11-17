package com.example.helloandroid;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class CineLogApp extends Application {

    public static final String PREFS_NAME = "cinelog_prefs";
    public static final String KEY_THEME = "theme_mode"; // "dark" | "light" | "system"

    @Override
    public void onCreate() {
        super.onCreate();
        String mode = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(KEY_THEME, "dark");
        AppCompatDelegate.setDefaultNightMode(toNightMode(mode));
    }

    public static int toNightMode(String mode) {
        if ("light".equals(mode)) {
            return AppCompatDelegate.MODE_NIGHT_NO;
        }
        if ("system".equals(mode)) {
            return AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
        }
        return AppCompatDelegate.MODE_NIGHT_YES; // dark is the default
    }
}
