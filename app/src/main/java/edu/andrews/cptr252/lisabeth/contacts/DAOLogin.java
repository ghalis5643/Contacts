package edu.andrews.cptr252.lisabeth.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DAOLogin extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private final String TABLE = "Login";
    private static final String DATABASE = "LoginList";

    private static final String COLUMN_PASSWORD_HASHED = "password_hashed";


    public DAOLogin(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE +
                "( userId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertLogin (UserLogin userLogin) {

        String hashedPassword = ShaHelper.hash(userLogin.getPassword());

        ContentValues values = new ContentValues();
        values.put("userName", userLogin.getUsername());
        values.put("password", hashedPassword);
        getWritableDatabase().insert(TABLE, null, values);
    }
    public void editLogin (UserLogin userLogin) {

        String hashedPassword = ShaHelper.hash(userLogin.getPassword());

        ContentValues values = new ContentValues();
        values.put("userId", userLogin.getUserId());
        values.put("userName", userLogin.getUsername());
        values.put("password", hashedPassword);

        String[] idToEdit = {userLogin.getUserId().toString()};
        getWritableDatabase().update(TABLE, values, "userId=?", idToEdit);
    }

    public void deleteLogin (UserLogin userLogin) {
        SQLiteDatabase db = getWritableDatabase();
        String[] userIdToDelete = {userLogin.getUserId().toString()};
        db.delete(TABLE, "userId=?", userIdToDelete);
    }
}
