package com.example.android.contacts_udacity_project.data;

import android.provider.BaseColumns;

/**
 * Created by mikem on 6/19/2017.
 */

public class ContactsContract {

    public class ContactsEntry implements BaseColumns{

        private ContactsEntry(){
        }

        //create constant for table name
        public static final String TABLE_NAME = "contacts";

        //create constant for columns
        public static final String COLUMN_CONTACTS_ID = BaseColumns._ID;
        public static final String COLUMN_CONTACTS_NAME = "name";
        public static final String COLUMN_CONTACTS_NUMBER = "number";
    }
}
