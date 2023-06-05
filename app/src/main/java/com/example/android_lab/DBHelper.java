package com.example.android_lab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "dbNotes";
    private static final String TABLE_NAME = "notesTable";
    private static final int DB_VERSION=1;

    public DBHelper(Context cntx){
        super(cntx, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableQuery = "CREATE TABLE "+TABLE_NAME+" (text TEXT, fontSize TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        String str = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(str);
        onCreate(db);
    }









}
