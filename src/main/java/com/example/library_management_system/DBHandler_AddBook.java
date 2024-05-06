package com.example.library_management_system;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import java.util.List;

public class DBHandler_AddBook extends SQLiteOpenHelper {
    // Database constants
    private static final String DB_NAME = "LMS.db";
    private static final int DB_VERSION = 1;

    // Table and column constants
    private static final String TABLE_NAME = "Book";
    private static final String COL_BOOK_ID = "book_id";
    private static final String COL_TITLE = "title";
    private static final String COL_AUTHOR = "author";
    private static final String COL_PUBLISHER = "publisher";
    private static final String COL_BRANCH_ID = "branch_id";

    public DBHandler_AddBook(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL query to create the book table
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_BOOK_ID + " VARCHAR(13) PRIMARY KEY, "
                + COL_TITLE + " VARCHAR(30), "
                + COL_AUTHOR + " VARCHAR(20), "
                + COL_PUBLISHER + " VARCHAR(20), "
                + COL_BRANCH_ID + " VARCHAR(5))";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to add a new book to the database
    public void addNewBook(String bookId, String bookTitle, String authorName, String publisherName, String branchId) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            // Populate the values with the book details
            values.put(COL_BOOK_ID, bookId);
            values.put(COL_TITLE, bookTitle);
            values.put(COL_AUTHOR, authorName);
            values.put(COL_PUBLISHER, publisherName);
            values.put(COL_BRANCH_ID, branchId);

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
    public Book getBookById(String bookId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();

            // Query to find the book by ID
            cursor = db.query(
                    TABLE_NAME, // The table to query
                    null, // Return all columns
                    COL_BOOK_ID + " = ?", // WHERE clause
                    new String[]{bookId}, // WHERE clause arguments
                    null, // GROUP BY
                    null, // HAVING
                    null // ORDER BY
            );

            // Check if the cursor contains a valid record
            if (cursor != null && cursor.moveToFirst()) {
                // Create a new book object and set its fields
                Book book = new Book();
                book.setBookId(cursor.getString(cursor.getColumnIndex(COL_BOOK_ID)));
                book.setBookTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
                book.setAuthorName(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
                book.setPublisherName(cursor.getString(cursor.getColumnIndex(COL_PUBLISHER)));
                book.setBranchId(cursor.getString(cursor.getColumnIndex(COL_BRANCH_ID)));

                return book;
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

    public void updateBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_TITLE, book.getBookTitle());
        values.put(COL_AUTHOR, book.getAuthorName());
        values.put(COL_PUBLISHER, book.getPublisherName());
        values.put(COL_BRANCH_ID, book.getBranchId());

        db.update(TABLE_NAME, values, COL_BOOK_ID + " = ?", new String[]{book.getBookId()});
        db.close();
    }

    public void deleteBook(String bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_BOOK_ID + " = ?", new String[]{bookId});
        db.close();
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
                    Book book = new Book();
                    book.setBookId(cursor.getString(cursor.getColumnIndex(COL_BOOK_ID)));
                    book.setBookTitle(cursor.getString(cursor.getColumnIndex(COL_TITLE)));
                    book.setAuthorName(cursor.getString(cursor.getColumnIndex(COL_AUTHOR)));
                    book.setPublisherName(cursor.getString(cursor.getColumnIndex(COL_PUBLISHER)));
                    book.setBranchId(cursor.getString(cursor.getColumnIndex(COL_BRANCH_ID)));
                    bookList.add(book);
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

