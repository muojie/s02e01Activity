package com.example.administrator.s02e01activity.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.s02e01activity.R;

public class TitleViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);      //must be called before adding content (include super.onCreate())
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,      // full screen
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_view);
    }
}
