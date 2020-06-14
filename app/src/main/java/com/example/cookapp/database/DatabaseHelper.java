package com.example.cookapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Cook.db";
    private static final int DATABASE_VERSION =3;

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
                + COLUMN_COOKWARE_ID + " TEXT,"
                + COLUMN_DEVICE + " TEXT,"
                + COLUMN_POWER + " TEXT,"
                + COLUMN_MINUTES + " TEXT,"
                + COLUMN_RATING  + " REAL);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    void addFormula(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, "test3");
        cv.put(COLUMN_TYPE, "typ1");
        cv.put(COLUMN_COOKWARE_ID,33);
        cv.put(COLUMN_DEVICE, "device3");
        cv.put(COLUMN_POWER, "moc123");
        cv.put(COLUMN_MINUTES, 55);
        cv.put(COLUMN_RATING, 22);
        db.insert(TABLE_NAME, null, cv);
    }
    public void addRecord(String name, String cookware, String device, String power, String minutes, double rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_TYPE, "typ1");
        cv.put(COLUMN_COOKWARE_ID,cookware);
        cv.put(COLUMN_DEVICE, device);
        cv.put(COLUMN_POWER, power);
        cv.put(COLUMN_MINUTES, minutes);
        cv.put(COLUMN_RATING, rating);
        db.insert(TABLE_NAME, null, cv);
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor searchData(String text, boolean name, boolean cookware, boolean device){
        String query = "";
        if(name && !cookware && !device){
        query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " LIKE '%" + text + "%'";
        }
        if(!name && cookware && !device){
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_COOKWARE_ID + " LIKE '%" + text + "%'";
        }
        if(!name && !cookware && device){
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DEVICE + " LIKE '%" + text + "%'";
        }
        if(name && cookware && !device){
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " LIKE '%" + text + "%' OR " + COLUMN_COOKWARE_ID + " LIKE '%" + text + "%'";
        }
        if(!name && cookware && device){
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_DEVICE + " LIKE '%" + text + "%' OR " + COLUMN_COOKWARE_ID + " LIKE '%" + text + "%'";
        }
        if(name && !cookware && device){
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " LIKE '%" + text + "%' OR " + COLUMN_DEVICE + " LIKE '%" + text + "%'";
        }
        if(name && cookware && device){
            query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " LIKE '%" + text + "%' OR " + COLUMN_DEVICE + " LIKE '%" + text + "%' OR " + COLUMN_COOKWARE_ID + " LIKE '%" + text + "%'";
        }
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }

}
