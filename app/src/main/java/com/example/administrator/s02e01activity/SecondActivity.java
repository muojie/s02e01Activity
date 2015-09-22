package com.example.administrator.s02e01activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.s02e01activity.sqlit.DatabaseHelper;

import java.io.File;


public class SecondActivity extends Activity {

    private File mFile;
    private DatabaseHelper db;
    private Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        System.out.println("onCreate");

        testDatabase();
    }

    public void testDatabase() {
        mFile = Environment.getExternalStoragePublicDirectory("123");
        Log.e("test", "photo directory " + mFile);
        mFile.mkdir();

        db = new DatabaseHelper(SecondActivity.this, "test");
        db.getReadableDatabase();
        //myCursor=db.select();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
