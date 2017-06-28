package com.meng.memoryleak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,HandlerActivity.class));
                startActivity(new Intent(MainActivity.this,Handler2Activity.class));
//                startActivity(new Intent(MainActivity.this,StaticViewActivity.class));
//                startActivity(new Intent(MainActivity.this,TestSingleActivity.class));
//                startActivity(new Intent(MainActivity.this,InnerClsActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //使用LeakCanary观察是否有内存泄漏
        MyApp.getRefWatcher(this).watch(this);
    }
}
