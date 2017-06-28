package com.meng.memoryleak;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * handler可能会产生内存泄漏
 * @author Administrator
 * @time 2017/6/28,18:04
 **/

public class Handler2Activity extends AppCompatActivity {

    private static final String TAG = "HandlerActivity";
    private Button btn;
    private TextView tv;
    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        handler = new MyHandler(this);

        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);

        new Thread(){//模拟耗时操作
            @Override
            public void run() {
                super.run();
                Message msg = new Message();
                msg.obj = "模拟数据";
                handler.sendMessageDelayed(msg,600000);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用LeakCanary观察是否有内存泄漏
        MyApp.getRefWatcher(this).watch(this);
    }

    private static class MyHandler extends Handler{

        private final WeakReference<Context> reference;

        public MyHandler(Context activity) {
            reference = new WeakReference<Context>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Handler2Activity activity = (Handler2Activity) reference.get();
            if(activity != null){
                activity.tv.setText((String) msg.obj);
            }
        }
    }
}
