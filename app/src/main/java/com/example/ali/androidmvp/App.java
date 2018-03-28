package com.example.ali.androidmvp;

import android.app.Application;


/**
 * Created by Ali Esa Assadi on 10/03/2018.
 */
public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    private static void init(App app) {
        sInstance = app;
    }

    public static App getInstance() {
        return sInstance;
    }

}
