package com.example.library_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler_BookLoan extends SQLiteOpenHelper {
    private static final String DB_NAME = "LMS";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Book_Loan";
    private static final String ACCESSNO_COL = "access_no";
    private static final String BRANCHID_COL = "branch_id";
    private static final String CARDNO_COL = "card_no";
    private static final String OUTDATE_COL = "out_date";
    private static final String DUEDATE_COL = "due_date";
    private static final String RETURNDATE_COL = "return_date";
    public DBHandler_BookLoan(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ACCESSNO_COL + " VARCHAR(5) PRIMARY KEY, "
                + BRANCHID_COL + " VARCHAR(5)PRIMARY KEY,"
                + CARDNO_COL + " VARCHAR(5)PRIMARY KEY,"
                + OUTDATE_COL + " DATE PRIMARY KEY,"
                + DUEDATE_COL + " DATE,"
                + RETURNDATE_COL + " DATE,"
                + "FOREIGN KEY("+ACCESSNO_COL+","+BRANCHID_COL+")REFERENCES Book_Copy ON DELETE CASCADE,"
                + "FOREIGN KEY("+CARDNO_COL+")REFERENCES Member,"
                + "FOREIGN KEY("+BRANCHID_COL+")REFERENCES Branch)";

        db.execSQL(query);
    }

    public boolean updateBookLoan(String accNo, String branchId, String cardNo, String outDate, String dueDate, String returnDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ACCESSNO_COL, accNo);
        values.put(BRANCHID_COL, branchId);
        values.put(CARDNO_COL, cardNo);
        values.put(OUTDATE_COL, outDate);
        values.put(DUEDATE_COL, dueDate);
        values.put(RETURNDATE_COL, returnDate);

        db.insert(TABLE_NAME, null, values);

        db.close();
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
