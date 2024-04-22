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

    public DAOLogin(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE +
                "( userId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public List<InfoContact> getList(String order) {
        List<InfoContact> contacts = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE +
                " ORDER BY name " + order + ";", null);
        while (cursor.moveToNext()) {
            InfoContact c = new InfoContact();

            int userIdIndex = cursor.getColumnIndex("userId");
            int usernameIndex = cursor.getColumnIndex("username");
            int passwordIndex = cursor.getColumnIndex("password");
            c.setName(cursor.getString(userIdIndex));
            c.setPhone(cursor.getString(usernameIndex));
            c.setAddress(cursor.getString(passwordIndex));

            contacts.add(c);

        }
        cursor.close();
        return contacts;
    }

    public void insertLogin (InfoContact c) {

        ContentValues values = new ContentValues();
        values.put("userName", c.getPhone());
        values.put("password", c.getAddress());
        getWritableDatabase().insert(TABLE, null, values);
    }
    public void editLogin (InfoContact c) {
        ContentValues values = new ContentValues();
        values.put("userId", c.getId());
        values.put("userName", c.getName());
        values.put("password", c.getPhone());

        String[] idToEdit = {c.getId().toString()};
        getWritableDatabase().update(TABLE, values, "userId=?", idToEdit);
    }

    public void deleteLogin (InfoContact c) {
        SQLiteDatabase db = getWritableDatabase();
        String[] userIdToDelete = {c.getUserId().toString()};
        db.delete(TABLE, "userId=?", userIdToDelete);
    }
}
