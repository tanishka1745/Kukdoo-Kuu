package com.example;

import android.app.Application;

import com.backendless.Backendless;

public class TestApplication extends Application {
    private static  final String API_KEY="C56C43B1-1A8C-4B91-80CE-9C6DA5B8200B";
    private static final String APPLICATION_ID="B75D47D7-59C5-0351-FFF2-72E6F17A2200";
    private static final String SERVER_URL="https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl(SERVER_URL);
        Backendless.initApp(getApplicationContext(),APPLICATION_ID,API_KEY);

    }
}
