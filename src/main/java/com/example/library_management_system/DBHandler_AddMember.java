package com.example.library_management_system;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler_AddMember extends SQLiteOpenHelper {
    // Database constants
    private static final String DB_NAME = "LMS";
    private static final int DB_VERSION = 1;

    // Table and column constants
    private static final String TABLE_NAME = "Members";
    private static final String CARDNO_COL = "card_no";
    private static final String NAME_COL = "name";
    private static final String ADDR_COL = "address";
    private static final String TPNO_COL = "tp_no";

    public DBHandler_AddMember(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create the book table
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + CARDNO_COL + " VARCHAR(10) PRIMARY KEY, "
                + NAME_COL + " VARCHAR(20),"
                + ADDR_COL + " VARCHAR(30),"
                + TPNO_COL + " VARCHAR(10))";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add a new book to the database
    public void addNewMember(String cardNumber, String memberName, String address, String tpNumber) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            // Populate the values with the book details
            values.put(CARDNO_COL, cardNumber);
            values.put(NAME_COL, memberName);
            values.put(ADDR_COL, address);
            values.put(TPNO_COL, tpNumber);

            // Insert the new book into the table
            db.insert(TABLE_NAME, null, values);
        } finally {
            // Close the database connection
            if (db != null) {
                db.close();
            }
        }
    }

    // Method to get a book by its ID
    @SuppressLint("Range")
    public Member getMemberById(String cardNo) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();

            // Query to find the book by ID
            cursor = db.query(
                    TABLE_NAME, // The table to query
                    null, // Return all columns
                    CARDNO_COL + " = ?", // WHERE clause
                    new String[]{cardNo}, // WHERE clause arguments
                    null, // GROUP BY
                    null, // HAVING
                    null // ORDER BY
            );

            // Check if the cursor contains a valid record
            if (cursor != null && cursor.moveToFirst()) {
                // Create a new book object and set its fields
                Member member = new Member();
                member.setCardNo(cursor.getString(cursor.getColumnIndex(CARDNO_COL)));
                member.setMemberName(cursor.getString(cursor.getColumnIndex(NAME_COL)));
                member.setAddress(cursor.getString(cursor.getColumnIndex(ADDR_COL)));
                member.setContactNo(cursor.getString(cursor.getColumnIndex(TPNO_COL)));

                return member;
            }

            return null; // No book found
        } finally {
            // Ensure resources are closed properly
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
    }

    public void updateMember(Member member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CARDNO_COL, member.getCardNo());
        values.put(NAME_COL, member.getMemberName());
        values.put(ADDR_COL, member.getAddress());
        values.put(TPNO_COL, member.getContactNo());

        db.update(TABLE_NAME, values, CARDNO_COL + " = ?", new String[]{member.getCardNo()});
        db.close();
    }

    public void deleteMember(String cardNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, CARDNO_COL + " = ?", new String[]{cardNo});
        db.close();
    }

    @SuppressLint("Range")
    public List<Member> getAllMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Member> memberList = new ArrayList<>();
        Cursor cursor = null;

        try {
            // Query to fetch all books
            cursor = db.query(
                    TABLE_NAME, // The table to query
                    null, // Return all columns
                    null, // No WHERE clause
                    null, // No WHERE arguments
                    null, // No GROUP BY
                    null, // No HAVING
                    null // No ORDER BY
            );

            // Loop through each row in the result set
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Member member = new Member();
                    member.setCardNo(cursor.getString(cursor.getColumnIndex(CARDNO_COL)));
                    member.setMemberName(cursor.getString(cursor.getColumnIndex(NAME_COL)));
                    member.setAddress(cursor.getString(cursor.getColumnIndex(ADDR_COL)));
                    member.setContactNo(cursor.getString(cursor.getColumnIndex(TPNO_COL)));
                    memberList.add(member);
                } while (cursor.moveToNext());
            }

            return memberList;
        } finally {
            // Close cursor and database
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

}

