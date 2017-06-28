package com.meng.memoryleak;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * handler可能会产生内存泄漏
 * @author Administrator
 * @time 2017/6/28,18:04
 **/

public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = "HandlerActivity";
    private Button btn;
    private TextView tv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.d(TAG, "handleMessage: "+(String) msg.obj);
            tv.setText((String) msg.obj);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);

        new Thread(){
            @Override
            public void run() {
                super.run();
                Message msg = new Message();
                msg.obj = "模拟数据";
                handler.sendMessageDelayed(msg,2000);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用LeakCanary观察是否有内存泄漏
        MyApp.getRefWatcher(this).watch(this);
        Log.d(TAG, "onDestroy: "+handler);
    }
}
