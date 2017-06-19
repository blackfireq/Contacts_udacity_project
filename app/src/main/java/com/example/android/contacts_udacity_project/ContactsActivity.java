package com.example.android.contacts_udacity_project;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.contacts_udacity_project.data.ContactsContract.ContactsEntry;
import com.example.android.contacts_udacity_project.data.ContactsDbHelper;

import static android.R.attr.onClick;

public class ContactsActivity extends AppCompatActivity {

    //create helper to connect to db
    ContactsDbHelper mDbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //display database rows
        displayDbInfo();



        //link to add button
        TextView addContact = (TextView) findViewById(R.id.dummy_contact_add);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("batman"," here he goes again");
                insertContact();

            }
        });

    }

    private void displayDbInfo(){


        mDbHelper = new ContactsDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ContactsEntry._ID,
                ContactsEntry.COLUMN_CONTACTS_NAME,
                ContactsEntry.COLUMN_CONTACTS_NUMBER
        };

        Cursor cursor = db.query(ContactsEntry.TABLE_NAME,projection,null,null,null,null,null);

        TextView contactView = (TextView) findViewById(R.id.text_view_contact);

        //get the indicies of all the columns
        int idColumnIndex = cursor.getColumnIndex(ContactsEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(ContactsEntry.COLUMN_CONTACTS_NAME);
        int numberColumnIndex = cursor.getColumnIndex(ContactsEntry.COLUMN_CONTACTS_NUMBER);

        // set initial text wit the number of contacts
        contactView.setText("Number of Contacts: " +cursor.getCount() + "\n\n");

        //append the column names to the textview
        contactView.append("\n" +
                        ContactsEntry._ID + " - " +
                        ContactsEntry.COLUMN_CONTACTS_NAME + " - " +
                        ContactsEntry.COLUMN_CONTACTS_NUMBER + " - ");

        try {
            while (cursor.moveToNext()){

                //get values of current position
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentNumber = cursor.getInt(nameColumnIndex);

                //append to contact_view
                contactView.append("\n" +
                        currentID + " - " +
                        currentName + " - " +
                        currentNumber + " - ");
            }

        } finally {
            cursor.close();
        }
    }

    public void insertContact(){

        //gather info about dummy contact
        ContentValues values = new ContentValues();
        values.put(ContactsEntry.COLUMN_CONTACTS_NAME,"John Doe");
        values.put(ContactsEntry.COLUMN_CONTACTS_NUMBER,"8675309");

        //create db object to connect to db
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        db.insert(ContactsEntry.TABLE_NAME,null,values);


        displayDbInfo();
    }

}
