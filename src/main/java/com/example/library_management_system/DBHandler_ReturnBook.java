package com.example.library_management_system;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler_ReturnBook extends SQLiteOpenHelper {
    // Database constants
    private static final String DB_NAME = "LMS";
    private static final int DB_VERSION = 1;

    // Table and column constants
    private static final String TABLE_NAME = "Return";
    private static final String COL_BRANCH_ID = "branch_id";
    private static final String COL_CARDNO = "card_No";
    private static final String COL_OUTDATE = "out_Date";
    private static final String COL_DUEDATE = "due_Date";
    private static final String COL_RETURNDATE = "return_Date";

    public DBHandler_ReturnBook(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create the book table
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_BRANCH_ID + " VARCHAR(13) PRIMARY KEY, "
                + COL_CARDNO + " VARCHAR(30), "
                + COL_OUTDATE + " VARCHAR(20), "
                + COL_DUEDATE + " VARCHAR(20), "
                + COL_RETURNDATE + " VARCHAR(5))";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add a new book to the database
    public void returnBook(String branchId, String cardNo, String outDate, String dueDate, String returnDate) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            // Populate the values with the book details
            values.put(COL_BRANCH_ID, branchId);
            values.put(COL_CARDNO, cardNo);
            values.put(COL_OUTDATE, outDate);
            values.put(COL_DUEDATE, dueDate);
            values.put(COL_RETURNDATE, returnDate);

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
    public Book getBookById(String branchId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();

            // Query to find the book by ID
            cursor = db.query(
                    TABLE_NAME, // The table to query
                    null, // Return all columns
                    COL_BRANCH_ID + " = ?", // WHERE clause
                    new String[]{branchId}, // WHERE clause arguments
                    null, // GROUP BY
                    null, // HAVING
                    null // ORDER BY
            );

            // Check if the cursor contains a valid record
            if (cursor != null && cursor.moveToFirst()) {
                // Create a new book object and set its fields
                Book rbook = new Book();
                rbook.setBranchId(cursor.getString(cursor.getColumnIndex(COL_BRANCH_ID)));
                rbook.setBookTitle(cursor.getString(cursor.getColumnIndex(COL_CARDNO)));
                rbook.setAuthorName(cursor.getString(cursor.getColumnIndex(COL_OUTDATE)));
                rbook.setPublisherName(cursor.getString(cursor.getColumnIndex(COL_DUEDATE)));
                rbook.setBranchId(cursor.getString(cursor.getColumnIndex(COL_RETURNDATE)));

                return rbook;
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

    @SuppressLint("Range")
    public List<Book> getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Book> bookList = new ArrayList<>();
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
                    Book rbook = new Book();
                    rbook.setBookId(cursor.getString(cursor.getColumnIndex(COL_BRANCH_ID)));
                    rbook.setBookTitle(cursor.getString(cursor.getColumnIndex(COL_CARDNO)));
                    rbook.setAuthorName(cursor.getString(cursor.getColumnIndex(COL_OUTDATE)));
                    rbook.setPublisherName(cursor.getString(cursor.getColumnIndex(COL_DUEDATE)));
                    rbook.setBranchId(cursor.getString(cursor.getColumnIndex(COL_RETURNDATE)));
                    bookList.add(rbook);
                } while (cursor.moveToNext());
            }

            return bookList;
        } finally {
            // Close cursor and database
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
    }

}

