package com.example.cookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Cook.db";
    private static final int DATABASE_VERSION =1;

    private static final String TABLE_NAME ="formula";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_COOKWARE_ID = "cookware_id";
    private static final String COLUMN_DEVICE = "device";
    private static final String COLUMN_POWER = "power";
    private static final String COLUMN_MINUTES = "minute";
    private static final String COLUMN_RATING = "rating";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_COOKWARE_ID + " INTEGER,"
                + COLUMN_DEVICE + " TEXT,"
                + COLUMN_POWER + " TEXT,"
                + COLUMN_MINUTES + " INTEGER,"
                + COLUMN_RATING  + " INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    void addFormula(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, "test1");
        cv.put(COLUMN_TYPE, "typ1");
        cv.put(COLUMN_COOKWARE_ID,69);
        cv.put(COLUMN_DEVICE, "device1");
        cv.put(COLUMN_POWER, "moc123");
        cv.put(COLUMN_MINUTES, 123);
        cv.put(COLUMN_RATING, 1410);
        db.insert(TABLE_NAME, null, cv);
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
