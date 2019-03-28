package com.example.demo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.demo.Model.ColorsDataRecords;
import com.example.demo.Model.CricketerDataRecords;
import com.example.demo.Model.DataList;

import java.util.ArrayList;


public class ChatDBUtility {

    public static ChatDBHelper chatDBHelper;

    public ChatDBHelper CreateChatDB(Context context)
    {
        if (chatDBHelper == null) {
            chatDBHelper = new ChatDBHelper(context);
        }

        return chatDBHelper;

    }


    /**
     * Add data to datalist table
     * @param chatDBHelper
     * @param name-name of user
     * @return rowId
     */
    public long AddToDataListDB(ChatDBHelper chatDBHelper, String name) {
        // Gets the data repository in write mode
        SQLiteDatabase db = chatDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        //values.put(FeedReaderContract.DataList.COLUMN_NAME_ID, dataResponse.getId());
        values.put(FeedReaderContract.DataList.COLUMN_NAME_NAME, name);




        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedReaderContract.DataList.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    /**
     * Getting rows from dataList table
     * @param DBHelper
     * @param id
     * @return datalist
     */
    public ArrayList<DataList> GetDataList(ChatDBHelper DBHelper,int id) {
        Cursor cursor = GetRowsDataListDB(DBHelper,id);

        ArrayList<DataList> datalists = new ArrayList<DataList>();
        DataList datalist;

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            datalist = new DataList();
            datalist.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.DataList.COLUMN_NAME_ID)));
            datalist.setColors(cursor.getString(cursor.getColumnIndex(FeedReaderContract.DataList.COLUMN_NAME_COLORS)));
            datalist.setDate(cursor.getString(cursor.getColumnIndex(FeedReaderContract.DataList.COLUMN_NAME_DATE_AND_TIME)));
            datalist.setFav_cricketer(cursor.getString(cursor.getColumnIndex(FeedReaderContract.DataList.COLUMN_NAME_FAV_CRICKETER)));
            datalist.setName(cursor.getString(cursor.getColumnIndex(FeedReaderContract.DataList.COLUMN_NAME_NAME)));




            datalists.add(datalist);

            cursor.moveToNext();
        }


        cursor.close();
        return datalists;
    }


    Cursor GetRowsDataListDB(ChatDBHelper chatDBHelper,int id) {
        SQLiteDatabase db = chatDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.DataList.COLUMN_NAME_COLORS,
                FeedReaderContract.DataList.COLUMN_NAME_FAV_CRICKETER,
                FeedReaderContract.DataList.COLUMN_NAME_NAME,
                FeedReaderContract.DataList.COLUMN_NAME_ID,
                FeedReaderContract.DataList.COLUMN_NAME_DATE_AND_TIME,

        };

        // How you want the results sorted in the resulting Cursor
        // String sortOrder =
        //
        String whereClause = "";

        if(id!=0)
        {
            whereClause = "(" + FeedReaderContract.DataList.COLUMN_NAME_ID + " = "  + id + ")";
        }


        Cursor c = db.query(
                FeedReaderContract.DataList.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );


        return c;
    }


    /**
     * Add data to colors table
     * @param chatDBHelper
     * @param color_name
     * @param id
     * @return rowId
     */
    public long AddToColorsListDB(ChatDBHelper chatDBHelper, String color_name,int id) {
        // Gets the data repository in write mode
        SQLiteDatabase db = chatDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();


        values.put(FeedReaderContract.ColorsList.COLUMN_NAME_COLORS_NAME, color_name);
        values.put(FeedReaderContract.ColorsList.COLUMN_NAME_ID, id);




        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedReaderContract.ColorsList.TABLE_NAME,
                null,
                values);
        return newRowId;
    }

    /**
     * Getting  data from colors table
     * @param DBHelper
     * @return colorsDataRecords
     */
    public ArrayList<CricketerDataRecords> GetColorsList(ChatDBHelper DBHelper) {
        Cursor cursor = GetRowsColorsListDB(DBHelper);

        ArrayList<CricketerDataRecords> colorsDataRecords = new ArrayList<CricketerDataRecords>();
        CricketerDataRecords colorsDataRecords1;

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            colorsDataRecords1 = new CricketerDataRecords();
            colorsDataRecords1.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.ColorsList.COLUMN_NAME_ID)));
            colorsDataRecords1.setCrickter(cursor.getString(cursor.getColumnIndex(FeedReaderContract.ColorsList.COLUMN_NAME_COLORS_NAME)));





            colorsDataRecords.add(colorsDataRecords1);

            cursor.moveToNext();
        }


        cursor.close();
        return colorsDataRecords;
    }


    Cursor GetRowsColorsListDB(ChatDBHelper chatDBHelper) {
        SQLiteDatabase db = chatDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.ColorsList.COLUMN_NAME_COLORS_NAME,
                FeedReaderContract.ColorsList.COLUMN_NAME_ID,

        };

        // How you want the results sorted in the resulting Cursor
        // String sortOrder =
        //
        String whereClause = "";




        Cursor c = db.query(
                FeedReaderContract.ColorsList.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );


        return c;
    }


    /**
     * Adding data to cricketers table
     * @param chatDBHelper
     * @param cricketer
     * @param id
     * @return
     */
    public long AddToCricketerListDB(ChatDBHelper chatDBHelper, String cricketer,int id) {
        // Gets the data repository in write mode
        SQLiteDatabase db = chatDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        //values.put(FeedReaderContract.DataList.COLUMN_NAME_ID, dataResponse.getId());
        values.put(FeedReaderContract.CricketerList.COLUMN_NAME_CRICKETER_NAME, cricketer);
        values.put(FeedReaderContract.CricketerList.COLUMN_NAME_ID, id);




        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                FeedReaderContract.CricketerList.TABLE_NAME,
                null,
                values);
        return newRowId;
    }


    public ArrayList<CricketerDataRecords> GetCricketerList(ChatDBHelper DBHelper) {
        Cursor cursor = GetRowsCricketerListDB(DBHelper);

        ArrayList<CricketerDataRecords> cricketerDataRecords = new ArrayList<CricketerDataRecords>();
        CricketerDataRecords cricketerDataRecords1;

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            cricketerDataRecords1 = new CricketerDataRecords();
            cricketerDataRecords1.setId(cursor.getInt(cursor.getColumnIndex(FeedReaderContract.CricketerList.COLUMN_NAME_ID)));
            cricketerDataRecords1.setCrickter(cursor.getString(cursor.getColumnIndex(FeedReaderContract.CricketerList.COLUMN_NAME_CRICKETER_NAME)));





            cricketerDataRecords.add(cricketerDataRecords1);

            cursor.moveToNext();
        }


        cursor.close();
        return cricketerDataRecords;
    }


    Cursor GetRowsCricketerListDB(ChatDBHelper chatDBHelper) {
        SQLiteDatabase db = chatDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.CricketerList.COLUMN_NAME_ID,
                FeedReaderContract.CricketerList.COLUMN_NAME_CRICKETER_NAME,


        };

        // How you want the results sorted in the resulting Cursor
        // String sortOrder =
        //
        String whereClause = "";



        Cursor c = db.query(
                FeedReaderContract.CricketerList.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );


        return c;
    }


    /**
     * Updating cricketers details
     * @param chatDBHelper
     * @param fav_cricketer - cricketers selected by user
     * @param id - current id
     */
    public void UpdateCricketer(ChatDBHelper chatDBHelper, String fav_cricketer,int id) {

        SQLiteDatabase db = chatDBHelper.getWritableDatabase();

        String strSQL = "UPDATE " + FeedReaderContract.DataList.TABLE_NAME + " Set " + FeedReaderContract.DataList.COLUMN_NAME_FAV_CRICKETER + " = '" + fav_cricketer +
                "' where " + FeedReaderContract.DataList.COLUMN_NAME_ID + " = " + id;
        db.execSQL(strSQL);
    }


    /**
     * Updating customers name
     * @param chatDBHelper
     * @param name - name entered by user
     * @param id - current id
     */
    public void UpdateName(ChatDBHelper chatDBHelper, String name,int id) {

        SQLiteDatabase db = chatDBHelper.getWritableDatabase();

        String strSQL = "UPDATE " + FeedReaderContract.DataList.TABLE_NAME + " Set " + FeedReaderContract.DataList.COLUMN_NAME_NAME + " = '" + name +
                "' where " + FeedReaderContract.DataList.COLUMN_NAME_ID + " = " + id;
        db.execSQL(strSQL);
    }

    /**
     * Updating colors based on id
     * @param chatDBHelper
     * @param colors - colors selected by customers
     * @param id - current id
     */
    public void UpdateColors(ChatDBHelper chatDBHelper, String colors,int id) {

        SQLiteDatabase db = chatDBHelper.getWritableDatabase();

        String strSQL = "UPDATE " + FeedReaderContract.DataList.TABLE_NAME + " Set " + FeedReaderContract.DataList.COLUMN_NAME_COLORS + " = '" + colors +
                "' where " + FeedReaderContract.DataList.COLUMN_NAME_ID + " = " + id;
        db.execSQL(strSQL);
    }

    /**
     * Updating date based on id
     * @param chatDBHelper
     * @param date -current date
     * @param id - current id
     *           
     */
    public void UpdateDate(ChatDBHelper chatDBHelper, String date,int id) {

        SQLiteDatabase db = chatDBHelper.getWritableDatabase();

        String strSQL = "UPDATE " + FeedReaderContract.DataList.TABLE_NAME + " Set " + FeedReaderContract.DataList.COLUMN_NAME_DATE_AND_TIME + " = '" + date +
                "' where " + FeedReaderContract.DataList.COLUMN_NAME_ID + " = " + id;
        db.execSQL(strSQL);
    }

}
