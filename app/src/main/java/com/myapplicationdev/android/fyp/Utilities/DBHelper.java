package com.myapplicationdev.android.fyp.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.myapplicationdev.android.fyp.Models.ScoreBoard;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simpleScoreBoard.db";
    // To make the app calls onUpgrade(), increment the variable DATABASE_VERSION from 1 to 2.
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SCOREBOARD = "scoreBoard";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createNoteTableSql = "CREATE TABLE " + TABLE_SCOREBOARD + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_SCORE + " TEXT,"
                + COLUMN_DATE + " TEXT )";
        // Create the table with a default settings to put current datetime automatically ^


        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        // TODO: to prepare the database for testing,
        //  we could create some dummy data during the table creation process.
        //  Dummy records, to be inserted when the database is created
        for (int i = 0; i < 4; i++) {

            // Creates an empty set of values using the default initial size
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, "ScoreBoard ID " + i);
            values.put(COLUMN_USERNAME, "ScoreBoard Username " + i);
            values.put(COLUMN_SCORE, "ScoreBoard Score " + i);
            values.put(COLUMN_DATE, "ScoreBoard Date " + i);

            db.insert(TABLE_SCOREBOARD, null, values);
        }
        Log.i("info", "dummy records inserted");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCOREBOARD);
        onCreate(db);

    }

//    private static final String COLUMN_ID = "id";
//    private static final String COLUMN_MODE = "mode";
//    private static final String COLUMN_SCORE = "score";
//    private static final String COLUMN_DATE = "date";

    // TODO: Insert a new record , C in CRUD
    public long insertScoreBoard(String username, String score, String date) {

        // Create and/or open a SQLiteDatabase that will be used for reading and writing.
        SQLiteDatabase db = this.getWritableDatabase();

        // Creates an empty set of values using the default initial size
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_DATE, date);


        long result = db.insert(TABLE_SCOREBOARD, null, values);
        db.close();
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        } else {
            Log.d("SQL Insert", "ID:" + result);
        }
        return result;
    }


    // TODO: Record retrieval from database table , R in CRUD
    public ArrayList<ScoreBoard> getAllScoreBoard() {


        ArrayList<ScoreBoard> ScoreBoards = new ArrayList<>();

        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_USERNAME + ", "
                + COLUMN_SCORE + ", "
                + COLUMN_DATE
                + " FROM " + TABLE_SCOREBOARD;

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

                ScoreBoard scoreBoard = new ScoreBoard(id, modeContent, scoreContent, dateContent);

                // Appends the specified element to the end of the ScoreBoards ArrayList
                ScoreBoards.add(scoreBoard);

// Move the cursor to the next row.
//This method will return false if the cursor is already past the last entry in the result set.
            } while (cursor.moveToNext());
        }

        // Closes the Cursor, releasing all of its resources and making it completely invalid.
        cursor.close();

        // Releases a reference to the object, closing the object if the last reference was released.
        // Calling this method is equivalent to calling
        db.close();

        // return the ScoreBoards ArrayList
        return ScoreBoards;
    }

    /*TODO: In order to perform an update,
   a method called updateNote() must be implemented in DBHelper.java.
    The method will accept a Note object and perform a database update. */
    public int updateScoreBoard(ScoreBoard data) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Creates an empty set of values using the default initial size
        ContentValues values = new ContentValues();

        // Add values to the values (ContentValues)
        values.put(COLUMN_USERNAME, data.getUsername());
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
        int result = db.update(TABLE_SCOREBOARD, values, condition, args);

        db.close();
        return result;
    }


    /*TODO:To delete a note, a deleteNote() method in DBHelper.java must be implemented.
        To delete a record from the database, the method will accept an ID as the primary reference. */
    public int deleteScoreBoard(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        String condition = COLUMN_ID + "= ?";

        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SCOREBOARD, condition, args);
        db.close();
        return result;
    }
    public ArrayList<String> getNameInScoreBoard(){
        ArrayList<String> NameScoreBoards = new ArrayList<>();

        String selectQuery = "SELECT " + COLUMN_USERNAME
                + " FROM " + TABLE_SCOREBOARD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String name = cursor.getString(0);
                NameScoreBoards.add(name);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return NameScoreBoards;
    }

}
