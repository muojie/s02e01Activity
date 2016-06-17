package com.example.administrator.s02e01activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.s02e01activity.dbtester.DBTesterActivity;
import com.example.administrator.s02e01activity.layout.LinerLayoutActivity;


public class MainActivity extends ActionBarActivity {

    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private Button dbButton;
    private Button layoutButton;

    View layoutMain = null;
    View layout4 = null;
    boolean firstFlag = true;

    private long time = (long) 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

        LayoutInflater inflater = this.getLayoutInflater();
        layoutMain = inflater.inflate(R.layout.activity_main, null);
        layout4 = inflater.inflate(R.layout.layout4, null);

        setContentView(layoutMain);
        button = (Button)findViewById(R.id.button);
        ButtonListener buttonListener = new ButtonListener();
        button.setOnClickListener(buttonListener);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(buttonListener);

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(buttonListener);

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(buttonListener);

        dbButton = (Button)findViewById(R.id.dbButton);
        dbButton.setOnClickListener(buttonListener);

        layoutButton = (Button)findViewById(R.id.layoutButton);
        layoutButton.setOnClickListener(buttonListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected  void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }

    public void jump2Second() {
        setContentView(layout4);
        long currTime = System.currentTimeMillis();
        Toast.makeText(MainActivity.this, "切换耗时： " + String.valueOf(currTime - time) + "毫秒",
                Toast.LENGTH_SHORT).show();
        if(firstFlag) {
            button3 = (Button) findViewById(R.id.button3);
            button3.setOnClickListener(new ButtonListener());
            firstFlag = false;
        }
    }

    public void jump2Main() {
        setContentView(layoutMain);
        long currTime = System.currentTimeMillis();
        Toast.makeText(MainActivity.this, "切换耗时： " + String.valueOf(currTime - time) + "毫秒",
                Toast.LENGTH_SHORT).show();
        //FIXME: need re findViewByID after setContentView, so we should use ViewGroup instead of just setContentView
    }

    public void startFragmentReplace() {
        Fragment newFragment = new ExampleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame1, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            time = System.currentTimeMillis();
            switch (v.getId()) {
                case R.id.button: {
                    Intent intent = new Intent();
                    //setClass函数的第一个参数是一个Context对象
                    //Context是一个类，Activity是Context类的子类，也就是说，所有的Activity对象，都可以向上转型为Context对象
                    //setClass函数的第二个参数是一个Class对象，在当前场景下，应该传入需要被启动的Activity类的class对象
                    intent.setClass(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.button1: {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, OtherActivity.class);
                    intent.putExtra("org.mobile.s02e04.Age", 20);
                    intent.putExtra("org.mobile.s02e04.time", time);
                    startActivity(intent);
                    break;
                }
                case R.id.button2: {
                    jump2Second();
                    break;
                }
                case R.id.button3: {
                    jump2Main();
                    break;
                }
                case R.id.button4: {
                    startFragmentReplace();
                    break;
                }
                case R.id.dbButton: {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, DBTesterActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.layoutButton: {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, LinerLayoutActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
