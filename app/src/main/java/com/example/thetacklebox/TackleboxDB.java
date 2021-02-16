package com.example.thetacklebox;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.thetacklebox.LureTemplate.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TackleboxDB extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "customlures.sqLiteDatabase";
    public static final int DATABASE_VERSION = 1;
    public TackleboxDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_LURE_TABLE = "CREATE TABLE " +
                LureItems.TABLE_NAME + " (" +
                LureItems.COLUMN_NAME + " TEXT NOT NULL, " + //0
                LureItems.COLUMN_TYPE + " TEXT NOT NULL, " + //1
                LureItems.COLUMN_COLOR + " TEXT NOT NULL, " + //2
                LureItems.COLUMN_LENGTH + " TEXT NOT NULL, " + //3
                LureItems.COLUMN_NUM_COLOR + " TEXT NOT NULL, " + //4
                LureItems.COLUMN_DEPTH + " TEXT NOT NULL, " + //5
                LureItems.COLUMN_WEIGHT + " TEXT NOT NULL, " + //6
                LureItems.COLUMN_MODEL + " TEXT NOT NULL, " + //7
                LureItems.COLUMN_DESC + " TEXT NOT NULL, " + //8
                LureItems.COLUMN_IMG + " INTEGER NOT NULL, " + //9
                LureItems._ID + " INTEGER PRIMARY KEY AUTOINCREMENT"+ ");"; //10

        //LureItems._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ //0

        sqLiteDatabase.execSQL(SQL_CREATE_LURE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LureItems.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public ArrayList<Items> getAll(){

        ArrayList<Items> finalList = new ArrayList<>();

        String testString = "SELECT * FROM " +  LureItems.TABLE_NAME;
        SQLiteDatabase dbR = this.getReadableDatabase();

        Cursor cursor = dbR.rawQuery(testString, null);

        if(cursor.moveToFirst()){
            do{

                String name = cursor.getString(0);
                String type = cursor.getString(1);
                String color = cursor.getString(2);
                String length = cursor.getString(3);
                String numCol = cursor.getString(4);
                String depth = cursor.getString(5);
                String weight = cursor.getString(6);
                String model = cursor.getString(7);
                String desc = cursor.getString(8);
                int img = cursor.getInt(9);
                int id = cursor.getInt(10);

                finalList.add(new Items(img, name, type, color, length, numCol, depth, weight, model, desc, id));

            }
            while(cursor.moveToNext());
        }
        else{
            finalList.add(new Items(0, "null", "null", "null", "null", "null", "null", "null", "null", "null"));
        }


        cursor.close();
        return finalList;
    }

    public void deleteFromDB(int item){

        SQLiteDatabase delQuery = getWritableDatabase();
        delQuery.execSQL(" DELETE FROM " + LureItems.TABLE_NAME + " WHERE " + LureItems._ID + "=\"" + item + "\";");


    }

    public boolean onUpdate(String name, String type, String color, String length, String numCol, String weight, String depth, String model, String desc, int id, int img){

        //nameNEW = name;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(LureItems.COLUMN_NAME, name);
        cv.put(LureItems.COLUMN_TYPE, type);
        cv.put(LureItems.COLUMN_COLOR, color);
        cv.put(LureItems.COLUMN_NUM_COLOR, numCol);
        cv.put(LureItems.COLUMN_DESC, desc);
        cv.put(LureItems.COLUMN_DEPTH, depth);
        cv.put(LureItems.COLUMN_LENGTH, length);
        cv.put(LureItems.COLUMN_WEIGHT, weight);
        cv.put(LureItems.COLUMN_MODEL, model);
        cv.put(LureItems._ID, id);
        cv.put(LureItems.COLUMN_IMG, img);

        db.update(LureItems.TABLE_NAME, cv, "_ID = ?", new String[] {String.valueOf(id)});

        return true;

    }

    public void resetDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " +LureItems.TABLE_NAME);
        //database.execSQL(SQL_CREATE_LURE_TABLE);
        database.close();
    }

}


