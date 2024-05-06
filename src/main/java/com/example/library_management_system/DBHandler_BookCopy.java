package com.example.library_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler_BookCopy extends SQLiteOpenHelper {
    private static final String DB_NAME = "LMS";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Book_Copy";
    private static final String BOOKID_COL = "name";
    private static final String BRANCHID_COL = "branch_id";
    private static final String ACCESSNO_COL = "ac_no";
    public DBHandler_BookCopy(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + BOOKID_COL + " VARCHAR(13) PRIMARY KEY,"
                + BRANCHID_COL + " VARCHAR(5),"
                + ACCESSNO_COL + " VARCHAR(5) PRIMARY KEY,"
                + "FOREIGN KEY("+BOOKID_COL+")REFERENCES Book,"
                + "FOREIGN KEY("+BRANCHID_COL+")REFERENCES Branch)";

        db.execSQL(query);
    }

    public void updateBookCopy(String bookId, String branchId, String acNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BOOKID_COL, bookId);
        values.put(BRANCHID_COL, branchId);
        values.put(ACCESSNO_COL, acNumber);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
