package com.example.library_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler_AddAuthor extends SQLiteOpenHelper {
    private static final String DB_NAME = "LMS";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Book_Author";
    private static final String ID_COL = "book_id";
    private static final String NAME_COL = "author_name";
    public DBHandler_AddAuthor(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " VARCHAR(13) ,"
                + NAME_COL + " VARCHAR(20)PRIMARY KEY,"
                + "FOREIGN KEY("+ID_COL+")REFERENCES Book)";

        db.execSQL(query);
    }

    public void addNewAuthor(String bookId, String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        
        values.put(ID_COL, bookId);
        values.put(NAME_COL, authorName);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
