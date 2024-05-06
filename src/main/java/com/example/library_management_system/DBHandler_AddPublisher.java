package com.example.library_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler_AddPublisher extends SQLiteOpenHelper {
    private static final String DB_NAME = "LMS";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Publisher";
    private static final String NAME_COL = "name";
    private static final String ADDR_COL = "address";
    private static final String TPNO_COL = "tp_no";
    public DBHandler_AddPublisher(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + NAME_COL + " VARCHAR(20) PRIMARY KEY,"
                + ADDR_COL + " VARCHAR(30),"
                + TPNO_COL + " VARCHAR(10))";

        db.execSQL(query);
    }

    public void addNewPublisher(String publisherName, String address, String tpNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, publisherName);
        values.put(ADDR_COL, address);
        values.put(TPNO_COL, tpNumber);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
