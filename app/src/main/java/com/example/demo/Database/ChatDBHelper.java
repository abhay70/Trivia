package com.example.demo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 *
 */

public class ChatDBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Trivia.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String LONG_TYPE = " LONG ";

    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_DATA_LIST_TABLE =
            "CREATE TABLE " + FeedReaderContract.DataList.TABLE_NAME + " (" +
                    FeedReaderContract.DataList.COLUMN_NAME_ID + INTEGER_TYPE  + " PRIMARY KEY AUTOINCREMENT " + COMMA_SEP +
                    FeedReaderContract.DataList.COLUMN_NAME_NAME + TEXT_TYPE  + COMMA_SEP +
                    FeedReaderContract.DataList.COLUMN_NAME_FAV_CRICKETER + TEXT_TYPE  + COMMA_SEP +
                    FeedReaderContract.DataList.COLUMN_NAME_DATE_AND_TIME + TEXT_TYPE  + COMMA_SEP +
                    FeedReaderContract.DataList.COLUMN_NAME_COLORS + TEXT_TYPE  +

                    " )";



    public static final String SQL_CREATE_COLORS_TABLE =
            "CREATE TABLE " + FeedReaderContract.ColorsList.TABLE_NAME + " (" +
                    FeedReaderContract.ColorsList.COLUMN_NAME_ID + INTEGER_TYPE   + COMMA_SEP +
                    FeedReaderContract.ColorsList.COLUMN_NAME_COLORS_NAME + TEXT_TYPE  +
                    " )";

    public static final String SQL_CREATE_CRICKETER_TABLE =
            "CREATE TABLE " + FeedReaderContract.CricketerList.TABLE_NAME + " (" +
                    FeedReaderContract.CricketerList.COLUMN_NAME_ID + INTEGER_TYPE   + COMMA_SEP +
                    FeedReaderContract.CricketerList.COLUMN_NAME_CRICKETER_NAME + TEXT_TYPE  +
                    " )";



    public ChatDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(SQL_CREATE_DATA_LIST_TABLE);

        db.execSQL(SQL_CREATE_COLORS_TABLE);

        db.execSQL(SQL_CREATE_CRICKETER_TABLE);


    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over


    }






    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }









}

