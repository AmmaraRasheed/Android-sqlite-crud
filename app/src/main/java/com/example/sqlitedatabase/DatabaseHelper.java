package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 99;
    private static final String DATABASE_NAME = "HelloNew";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Data.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String email,String pass){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Email",email);
        values.put("Password",pass);
        long id=db.insert("HelloNew",null,values);
        db.close();
        return id;
    }

    public List<Data> getAllDataFromDb(){
        List<Data> notes = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + "HelloNew" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Data note = new Data();
                note.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                note.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                note.setPass(cursor.getString(cursor.getColumnIndex("Password")));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        db.close();
        return notes;
    }

    public int updateNote(Data note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Password", note.getPass());

        // updating row
        return db.update("HelloNew", values, "Id" + " = ?",
                new String[]{String.valueOf(note.getId())});
    }


    public void deleteNote(Data note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("HelloNew", "Id" + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
