package com.example.administrator.s02e01activity.dbtester;

/**
 * Created by Administrator on 2015/9/25.
 */
import java.io.File;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.s02e01activity.R;

public class DBTesterActivity extends Activity {

    protected static final String TABLE_NAME = "table_timestamp";

    static {
        System.loadLibrary("DB_TESTER");
    }

    private File mDbFileExternal;

    private File mDbFileInternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dbtester);

        mDbFileExternal = new File(getExternalFilesDir(null), "tester_ext.db");
        mDbFileInternal = new File(getFilesDir(), "tester_int.db");

        ((Button)findViewById(R.id.button_e_add)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTimestamp(true);
            }
        });

        ((Button)findViewById(R.id.button_e_del)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDbFile(true);
            }
        });

        ((Button)findViewById(R.id.button_i_add)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTimestamp(false);
            }
        });

        ((Button)findViewById(R.id.button_i_del)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDbFile(false);
            }
        });

        ((Button)findViewById(R.id.button_display)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMessageView(getNativeMessage());
            }
        });
    }

    private void addNewTimestamp(boolean external) {
        long time = System.currentTimeMillis();

        File file;

        if (external) {
            file = mDbFileExternal;
        } else {
            file = mDbFileInternal;
        }

        boolean createNewDb = !file.exists();

        SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null,
                SQLiteDatabase.CREATE_IF_NECESSARY | SQLiteDatabase.NO_LOCALIZED_COLLATORS
                        | SQLiteDatabase.OPEN_READWRITE);

        if (createNewDb) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(TIMESTAMP INT PRIMARY KEY)");
        }

        ContentValues values = new ContentValues();
        values.put("TIMESTAMP", time);
        db.insert(TABLE_NAME, null, values);

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        setMessageView("Table now has " + cursor.getCount() + " entries." + "\n\n" + "Path:  "
                + file.getAbsolutePath());
    }

    private void deleteDbFile(boolean external) {
        // workaround for Android bug that sometimes doesn't delete a file
        // immediately, preventing recreation

        File file;

        if (external) {
            file = mDbFileExternal;
        } else {
            file = mDbFileInternal;
        }

        // practically guarantee unique filename by using timestamp
        File to = new File(file.getAbsolutePath() + "." + System.currentTimeMillis());

        file.renameTo(to);
        to.delete();

        setMessageView("Table deleted." + "\n\n" + "Path:  " + file.getAbsolutePath());
    }

    private void setMessageView(String msg) {
        ((TextView)findViewById(R.id.text_messages)).setText(msg);
    }

    private native String getNativeMessage();
}