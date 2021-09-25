package com.eventhub.eventhub.DataBase;

import android.provider.BaseColumns;

public class UserMaster {
    public UserMaster(){

    }
    public static class Users implements BaseColumns{
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PWD = "password";

    }
}
