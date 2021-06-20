package com.myapplicationdev.android.fyp.Util;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myapplicationdev.android.fyp.Model.Leaderboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simpleLeaderboard.db";
    // To make the app calls onUpgrade(), increment the variable DATABASE_VERSION from 1 to 2.
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LEADERBOARD = "leaderboard";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MODE = "mode";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createNoteTableSql = "CREATE TABLE " + TABLE_LEADERBOARD + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MODE + " TEXT,"
                + COLUMN_SCORE + " TEXT,"
                + COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP )";
        // Create the table with a default settings to put current datetime automatically ^


        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        // TODO: to prepare the database for testing,
        //  we could create some dummy data during the table creation process.
        //  Dummy records, to be inserted when the database is created
        for (int i = 0; i < 4; i++) {

            // Creates an empty set of values using the default initial size
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, "Leaderboard number " + i);
            values.put(COLUMN_MODE, "Leaderboard number " + i);
            values.put(COLUMN_SCORE, "Leaderboard number " + i);
            values.put(COLUMN_DATE, "Leaderboard number " + i);

            db.insert(TABLE_LEADERBOARD, null, values);
        }
        Log.i("info", "dummy records inserted");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADERBOARD);
        onCreate(db);

    }

//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_MODE = "mode";
//    private static final String COLUMN_SCORE = "score";
//    private static final String COLUMN_DATE = "date";

    // TODO: Insert a new record , C in CRUD
    public long insertLeaderboard(String gameMode, String gameScore) {

        // Create and/or open a SQLiteDatabase that will be used for reading and writing.
        SQLiteDatabase db = this.getWritableDatabase();

        // Creates an empty set of values using the default initial size
        ContentValues values = new ContentValues();

        values.put(COLUMN_MODE, gameMode);
        values.put(COLUMN_SCORE, gameScore);

        // Create SimpleDateFormat and convert current date into SQL format string
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String playedDate = sdf.format(new Date());

        values.put(COLUMN_DATE, playedDate);


        long result = db.insert(TABLE_LEADERBOARD, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }


    // TODO: Record retrieval from database table , R in CRUD
    public ArrayList<Leaderboard> getAllLeaderboard() {


        ArrayList<Leaderboard> Leaderboards = new ArrayList<>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_MODE + ", "
                + COLUMN_SCORE + ", "
                + COLUMN_DATE
                + " FROM " + TABLE_LEADERBOARD;

        SQLiteDatabase db = this.getReadableDatabase();

        // an interface provides random read-write access to the result set returned by a database query.
        Cursor cursor = db.rawQuery(selectQuery, null);

// Cursor Moves the cursor to the first row.
//This method will return false if the cursor is empty.
        if (cursor.moveToFirst()) {
            do {

                // Getting relevant data
                int id = cursor.getInt(0);
                String modeContent = cursor.getString(1);
                String scoreContent = cursor.getString(2);
                String dateContent = cursor.getString(3);

                Leaderboard leaderboard = new Leaderboard(id, modeContent, scoreContent, dateContent);

                // Appends the specified element to the end of the Leaderboards ArrayList
                Leaderboards.add(leaderboard);

// Move the cursor to the next row.
//This method will return false if the cursor is already past the last entry in the result set.
            } while (cursor.moveToNext());
        }

        // Closes the Cursor, releasing all of its resources and making it completely invalid.
        cursor.close();

        // Releases a reference to the object, closing the object if the last reference was released.
        // Calling this method is equivalent to calling
        db.close();

        // return the Leaderboards ArrayList
        return Leaderboards;
    }

    /*TODO: In order to perform an update,
   a method called updateNote() must be implemented in DBHelper.java.
    The method will accept a Note object and perform a database update. */
    public int updateLeaderboard(Leaderboard data) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Creates an empty set of values using the default initial size
        ContentValues values = new ContentValues();

        // Add values to the values (ContentValues)
        values.put(COLUMN_MODE, data.getMode());
        values.put(COLUMN_SCORE, data.getScore());
        values.put(COLUMN_DATE, data.getDate());


        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};


/*TODO: This line of code will return a number representing the number
   of rows in the table that have been affected. We usually expect one
   or more records to be updated when we perform a record update.
   However, in this case, we anticipate only one record.
   As a result, if the affected record is 1,
   we can use it to determine whether or not a record was successfully updated.*/
        int result = db.update(TABLE_LEADERBOARD, values, condition, args);

        db.close();
        return result;
    }


    /*TODO:To delete a note, a deleteNote() method in DBHelper.java must be implemented.
        To delete a record from the database, the method will accept an ID as the primary reference. */
    public int deleteLeaderboard(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String condition = COLUMN_ID + "= ?";

        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_LEADERBOARD, condition, args);
        db.close();
        return result;
    }

}
