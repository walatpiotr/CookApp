package com.example.cookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "Cook.db";
    public static final int DATABASE_VERSION =1;

    public static final String TABLE_NAME ="formula";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_COOKWARE_ID = "cookware_id";
    public static final String COLUMN_DEVICE = "device";
    public static final String COLUMN_POWER = "power";
    public static final String COLUMN_MINUTES = "minute";
    public static final String COLUMN_RATING = "rating";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
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
        long result = db.insert(TABLE_NAME, null, cv);
    }
}
