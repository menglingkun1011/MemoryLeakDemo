package com.meng.memoryleak;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import static com.squareup.leakcanary.LeakCanary.install;

/**
 * Created by Administrator on 2017/6/28.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApp application = (MyApp) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;
}
