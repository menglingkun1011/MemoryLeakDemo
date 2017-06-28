package com.meng.memoryleak;

import android.content.Context;

public class AppManager {

    private static AppManager instance;
    private Context context;

    private AppManager(Context context) {
        this.context = context;//(修改)
//        this.context = context.getApplicationContext();
    }
    public static AppManager getInstance(Context context) {
        if (instance == null) {
            instance = new AppManager(context);
        }
        return instance;
    }
}
