package com.example.android.contacts_udacity_project.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.contacts_udacity_project.data.ContactsContract.ContactsEntry;
/**
 * Created by mikem on 6/19/2017.
 */

public class ContactsDbHelper extends SQLiteOpenHelper {

    //creat constants for db name and version
    public static final String DATABASE_NAME = "people";
    public static final int DATABASE_VERSION = 1;

    public ContactsDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_TABLE_CONTACTS);
        onCreate(db);
    }

    //create constant for creating contacts table
    public static final String SQL_CREATE_TABLE_CONTACTS =
            "CREATE TABLE " + ContactsEntry.TABLE_NAME + "(" +
            ContactsEntry.COLUMN_CONTACTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ContactsEntry.COLUMN_CONTACTS_NAME + " TEXT NOT NULL, " +
            ContactsEntry.COLUMN_CONTACTS_NUMBER + " INTEGER)";

    //create constant for Deleting contacts table
    public static final String SQL_DELETE_TABLE_CONTACTS =
            "DROP TABLE IF EXIST" + ContactsEntry.TABLE_NAME;

}
