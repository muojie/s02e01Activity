package com.example.administrator.s02e01activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.test.AndroidTestCase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2015/4/24.
 */
public class OtherActivity extends ActionBarActivity {

    private TextView textView;

    private EditText nameText;
    private EditText ageText;
    private TextView resultText;

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

        preferenceText();
    }


    // SharePreferences study
    // 摘自：http://www.cnblogs.com/linjiqin/archive/2011/05/26/2059133.html
    private void preferenceText() {
        setContentView(R.layout.activity_prefernces_layout);

        nameText = (EditText)this.findViewById(R.id.name);
        ageText = (EditText)this.findViewById(R.id.age);
        resultText = (TextView)this.findViewById(R.id.showText);

        Button button = (Button)this.findViewById(R.id.button);
        Button showButton = (Button)this.findViewById(R.id.showButton);
        button.setOnClickListener(listener);
        showButton.setOnClickListener(listener);

        // 回显
        SharedPreferences sharedPreferences = getSharedPreferences("test",
                Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
        String nameValue = sharedPreferences.getString("name", "");
        int ageValue = sharedPreferences.getInt("age", 1);
        nameText.setText(nameValue);
        ageText.setText(String.valueOf(ageValue));
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        public void onClick(View v) {
            Button button = (Button)v;
            //test文件存放在/data/data/<package name>/shared_prefs目录下
            SharedPreferences sharedPreferences = getSharedPreferences("test",
                    Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
            switch (button.getId()) {
                case R.id.button:
                    String name = nameText.getText().toString();
                    int age = Integer.parseInt(ageText.getText().toString());
                    SharedPreferences.Editor editor = sharedPreferences.edit(); //获取编辑器
                    editor.putString("name", name);
                    editor.putInt("age", age);
                    editor.commit();//提交修改
                    Toast.makeText(OtherActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                    break;
                case R.id.showButton:
                    String nameValue = sharedPreferences.getString("name", "");
                    int ageValue = sharedPreferences.getInt("age", 1);
                    resultText.setText("姓名：" + nameValue + "，年龄：" + ageValue);
                    break;
            }
        }
    };

    /**
     * 访问SharePreference的方式一，注：权限要足够
     * @throws Exception
     */
    /*
    public void testAccessPreference() throws Exception{
        String path = "/data/data/com.ljq.activity/shared_prefs/ljq123.xml";
        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);
        //获取的是一个xml字符串
        String data = new FileService().read(inputStream);
    }
    */

    /**
     * 访问SharePreference的方式二，注：权限要足够
     * @throws Exception
     */
    /*
    public void testAccessPreference2() throws Exception{
        Context context = this.getContext().createPackageContext("com.ljq.activity",
                Context.CONTEXT_IGNORE_SECURITY);
        SharedPreferences sharedPreferences = context.getSharedPreferences("ljq123",
                Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 1);
    }
    */
}
