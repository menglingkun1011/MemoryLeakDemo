package com.meng.memoryleak;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by Administrator on 2017/6/28.
 * 静态的view的泄漏
 */
public class StaticViewActivity extends AppCompatActivity {


    private static Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用LeakCanary观察是否有内存泄漏
        MyApp.getRefWatcher(this).watch(this);
//        btn2 = null;
    }

}

