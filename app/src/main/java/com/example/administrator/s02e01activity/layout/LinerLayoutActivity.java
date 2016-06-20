package com.example.administrator.s02e01activity.layout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.s02e01activity.OtherActivity;
import com.example.administrator.s02e01activity.R;

/**
 * Created by lenovo on 2016/6/17.
 * Moved from project S01E12Linerlayout
 */

public class LinerLayoutActivity extends ActionBarActivity {

    private final int NEX_PAGE = 2;

    private LinearLayout mainLayout;
    private Button prevButton;
    private Button button;
    private LayoutInflater layoutInflater;
    private View mView;

    private Handler handler;

    private int pageNumber = 0;
    private int layoutID[] = {
            R.layout.liner_activity_main1,
            R.layout.activity_relative_main,
            R.layout.activity_simple_layout,
            R.layout.activity_counts_view,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liner_activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        layoutInflater = LayoutInflater.from(this);

        button = (Button) findViewById(R.id.nextPage);
        prevButton = (Button) findViewById(R.id.prevPage);

        handler = new MyHandler();

        button.setOnClickListener(new myClickListener());
        prevButton.setOnClickListener(new myClickListener());
    }
    class myClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int pageCount = layoutID.length;
            switch (v.getId()) {
                case R.id.prevPage:
                    if(pageNumber>0)
                        pageNumber -= 1;
                    break;
                case R.id.nextPage:
                    pageNumber += 1;
                    if(pageNumber >= pageCount) {
                        pageNumber = 0;
                        Intent intent = new Intent();
                        intent.setClass(LinerLayoutActivity.this, MyListViewActivity.class);
                        startActivity(intent);
                    }
                    break;
            }

            Message msg = handler.obtainMessage();
            msg.what = NEX_PAGE;
            msg.arg1 = pageNumber;

            handler.sendMessage(msg);
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEX_PAGE: {
                    mainLayout.removeAllViews();
                    mView = layoutInflater.inflate(layoutID[msg.arg1], null);
                    mainLayout.addView(mView);
                    break;
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
