package com.example.liban.balinasofttest.mvp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by liban on 24.08.2018.
 */

public class DataBaseUsersHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Users";
    private static final int DB_VERSION = 1;
    private String mUserName;
    private String mEmail;
    private String mPassword;
    private Context mContext;

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public DataBaseUsersHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USERS("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "USERNAME TEXT, "
                + "EMAIL TEXT, "
                + "PASSWORD TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertUsers(String userName, String email, String password) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("USERNAME", userName);
            cv.put("EMAIL", email);
            cv.put("PASSWORD", password);
            db.insert("USERS", null, cv);
            db.close();
            Toast.makeText(mContext,"Пользователь добавлен", Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(mContext,"Пользователь не добавлен",Toast.LENGTH_LONG).show();
        }

    }

    public void upLoad(String email, String password) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("USERS", new String[]{"USERNAME", "EMAIL", "PASSWORD"},
                    "EMAIL = ? AND PASSWORD = ?", new String[]{email, password}
                    , null, null, null);

            if (cursor.moveToFirst()) {
                int indexUser = cursor.getColumnIndex("USERNAME");
                int indexEmail = cursor.getColumnIndex("EMAIL");
                int indexPassword = cursor.getColumnIndex("PASSWORD");
                setmUserName(cursor.getString(indexUser));
                setmEmail(cursor.getString(indexEmail));
                setmPassword(cursor.getString(indexPassword));
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast.makeText(mContext, "Пользователь не найден", Toast.LENGTH_LONG).show();
        }

    }
}
