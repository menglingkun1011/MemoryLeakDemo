package com.meng.memoryleak;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2017/6/28.
 * 非静态内部类创建静态实例
 */

public class InnerClsActivity extends AppCompatActivity {

    private static final String TAG = "InnerClsActivity";
    private static InnerClass innerClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_innercls);
        if(innerClass == null){
            innerClass = new InnerClass();
        }

    }

    class InnerClass{
        public InnerClass() {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用LeakCanary观察是否有内存泄漏
        MyApp.getRefWatcher(this).watch(this);
//        innerClass = null;
        Log.d(TAG, "onDestroy: "+innerClass);
    }
}
