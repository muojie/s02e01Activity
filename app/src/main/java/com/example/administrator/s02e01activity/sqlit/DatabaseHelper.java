
package com.example.administrator.s02e01activity.sqlit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

//DatabaseeHelper作为一个访问SQLite的助手类，提供两个方面的功能：
//第一，getReadableDatabase(),getWritableDatabase可以获得SQLiteDatabase对象，通过该对象可以对数据库进行操作
//第二，提供了onCreate()和onUpgrade()两个回调函数，允许我们在创建和升级数据库时，进行自己的操作。
public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME="sec_db";
    private final static int DATABASE_VERSION=1;
    private final static String TABLE_NAME="sec_pwd";
    public final static String FIELD_ID="_id";
    public final static String FIELD_TITLE="sec_Title";

    //在SQLiteOpenHelper的子类中，必须有该构造函数
    public DatabaseHelper(Context context)
    {
        //必须通过super调用父类当中的构造函数
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name)
    {
        //必须通过super调用父类当中的构造函数
        super(context, name, null, DATABASE_VERSION);
    }

    //该函数是在第一次创建数据库的时候执行，实际上是在第一次得到SQLiteDatabase对象的时候，才会调用这个方法。
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        System.out.print("create a Databasee");
        //exeSQL函数用于执行SQL语句
        String sql="Create table "+TABLE_NAME+"("+FIELD_ID+" integer primary key autoincrement,"
                +FIELD_TITLE+" text );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql=" DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public Cursor select()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null,  " _id desc");
        return cursor;
    }

    public long insert(String Title)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(FIELD_TITLE, Title);
        long row=db.insert(TABLE_NAME, null, cv);
        return row;
    }

    public void delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String where=FIELD_ID+"=?";
        String[] whereValue={Integer.toString(id)};
        db.delete(TABLE_NAME, where, whereValue);
    }

    public void update(int id,String Title)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String where=FIELD_ID+"=?";
        String[] whereValue={Integer.toString(id)};
        ContentValues cv=new ContentValues();
        cv.put(FIELD_TITLE, Title);
        db.update(TABLE_NAME, cv, where, whereValue);
    }
}