package com.meng.memoryleak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/6/28.
 * 测试单列上下文生命周期长短引发的内存泄漏
 */

public class TestSingleActivity extends AppCompatActivity {

    private AppManager instance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testsingle);
        instance = AppManager.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用LeakCanary观察是否有内存泄漏
        MyApp.getRefWatcher(this).watch(this);
    }
}
