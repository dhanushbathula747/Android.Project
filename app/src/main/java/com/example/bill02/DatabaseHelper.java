package com.example.bill02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Shop.db";
    public static final String TABLE_NAME = "items";
    public static final String COL_1 = "Slno";
    public static final String COL_2 = "Id";
    public static final String COL_3 = "Name";
    public static final String COL_4 = "Price";
    public static final String LBR = "(";
    public static final String RBR = ")";
    public static final String COM = ",";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table " + TABLE_NAME + LBR + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM +
                COL_2 + " TEXT " + COM + COL_3 + " TEXT" + COM + COL_4 + " INTEGER" +RBR );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate( db );

    }
    public boolean instertData(String id, String name, int price){

        //Get the instance of SQL Database which we have created
        SQLiteDatabase db = getWritableDatabase();

        //To pass all the values in database

        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_2, id );
        contentValues.put( COL_3, name );
        contentValues.put( COL_4, price );

        long result = db.insert( TABLE_NAME, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }
    public Integer deleteData(String id){

        SQLiteDatabase db = getWritableDatabase();
        return db.delete( TABLE_NAME, "ID = ?", new String [] {id} );
    }
    public Cursor getData(){

        //Get the data from database
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }
    public Cursor searchdata(String s){
        //Log.d("search ","i am serach function");
        String query="SELECT  * FROM " + TABLE_NAME + " WHERE "
                + COL_2 + " = " + s;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery( query,null );
        return res;
    }

}
