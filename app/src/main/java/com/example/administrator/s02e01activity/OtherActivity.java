package com.example.administrator.s02e01activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/4/24.
 */
public class OtherActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        Intent intent = getIntent();
        int age = intent.getIntExtra("org.mobile.s02e04.Age", 10);

        long currTime = System.currentTimeMillis();
        long time = intent.getLongExtra("org.mobile.s02e04.time", 0);

        Toast.makeText(OtherActivity.this, "use time: " + String.valueOf(currTime - time) + "ms",
                Toast.LENGTH_SHORT).show();

        textView = (TextView)findViewById(R.id.textView);
        textView.setText("age: " + age + ", time: " + time);
    }

}
