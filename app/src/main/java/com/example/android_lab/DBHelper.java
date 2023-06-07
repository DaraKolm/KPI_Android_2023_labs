package com.example.android_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "dbNotes";
    private static final String TABLE_NAME = "notesTable";
    private static final int DB_VERSION=1;

    public DBHelper(Context cntx){
        super(cntx, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableQuery = "CREATE TABLE "+TABLE_NAME+" (fontSize TEXT,text TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        String str = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(str);
        onCreate(db);
    }

    public int addNote(String fs,String t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fontSize",fs);
        cv.put("text",t);
        int res=(int)db.insert(TABLE_NAME,null, cv);
        db.close();
        return res;
    }

    public List<Note> getAllNotes(){
        List<Note> noteList = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllQuery,null);

        if(cursor.moveToFirst()){
           do{
               Note note = new Note (cursor.getString(0),cursor.getString(1));
               noteList.add(note);
           }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return noteList;
    }

    public void deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null, null);
        db.close();
    }






}
