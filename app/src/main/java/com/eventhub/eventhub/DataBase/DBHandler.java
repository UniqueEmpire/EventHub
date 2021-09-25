package com.eventhub.eventhub.DataBase;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "userinfo.db";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String SQL_CREATE_ENTRIES = "CREATE TABLE "+ UserMaster.Users.TABLE_NAME + "(" +
                 UserMaster.Users._ID + " INTEGER PRIMARY KEY, " +
                 UserMaster.Users.COLUMN_NAME_EMAIL + " TEXT, " +
                 UserMaster.Users.COLUMN_NAME_PWD + " TEXT)";

         db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo (String email, String password){
         SQLiteDatabase db = getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(UserMaster.Users.COLUMN_NAME_EMAIL, email);
         values.put(UserMaster.Users.COLUMN_NAME_PWD,password);

         long newRowId = db.insert(UserMaster.Users.TABLE_NAME,null,values);
         return newRowId;
    }

    public List readAllinfo (String req){
         SQLiteDatabase db = getReadableDatabase();
         String[] projection = {
                 UserMaster.Users._ID,
                 UserMaster.Users.COLUMN_NAME_EMAIL,
                 UserMaster.Users.COLUMN_NAME_PWD
         };
         String sortOrder = UserMaster.Users.COLUMN_NAME_EMAIL + "DESC";

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List emails = new ArrayList<>();
        List pwds = new ArrayList<>();

        while(cursor.moveToNext()){
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_EMAIL));
            String pwd = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PWD));
            emails.add(email);
            pwds.add(pwd);
        }
        cursor.close();
        Log.i(TAG, "readAllInfo: "+emails);

        if(req=="email"){
            return emails;
        }
        else if (req=="password"){
            return pwds;
        }
        else{
            return null;
        }
    }

    public void deleteinfo (String email){
            SQLiteDatabase db = getReadableDatabase();
            String selection = UserMaster.Users.COLUMN_NAME_EMAIL + "LIKE ?";
            String[] selectionArgs = {email};
            db.delete(UserMaster.Users.TABLE_NAME,selection,selectionArgs);

    }
    public int updateInfo(String email,String password){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_PWD,password);;

        String selection = UserMaster.Users.COLUMN_NAME_EMAIL + "LIKE ?";
        String[] selectionargs = {email};

        int count = db.update(
                UserMaster.Users.TABLE_NAME,
                values,
                selection,
                selectionargs
        );
        return count;
    }
 }
