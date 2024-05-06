package com.example.library_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler_AddBranch extends SQLiteOpenHelper {
    private static final String DB_NAME = "LMS";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Branch";
    private static final String ID_COL = "branch_id";
    private static final String NAME_COL = "branch_name";
    private static final String ADDR_COL = "address";
    public DBHandler_AddBranch(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " VARCHAR(5) PRIMARY KEY,"
                + NAME_COL + " VARCHAR(20),"
                + ADDR_COL + " VARCHAR(30))";

        db.execSQL(query);
    }

    public void addNewBranch(String branchId, String branchName, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(ID_COL, branchId);
        values.put(NAME_COL, branchName);
        values.put(ADDR_COL, address);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
