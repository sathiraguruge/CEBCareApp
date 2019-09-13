package com.example.cebcareapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cebcareapp.Entity.User;

import java.text.DateFormat;
import java.util.Date;

public class UserDB extends SQLiteOpenHelper {

    private Context context;

    public UserDB(Context context){
        super(context, "UserDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE User(ID INTEGER PRIMARY KEY AUTOINCREMENT, fullName TEXT, email TEXT, phone INTEGER, landPhone INTEGER, userName TEXT, password TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS User;");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(User user){
        try{
            String currentDT = DateFormat.getDateTimeInstance().format(new Date());

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("fullName", user.getFullName());
            contentValues.put("email",user.getEmail());
            contentValues.put("phone",user.getPhone());
            contentValues.put("landPhone",user.getLandPhone());
            contentValues.put("userName",user.getUserName());
            contentValues.put("password", user.getPassword());

            long rows = sqLiteDatabase.insert("User", null, contentValues);
            sqLiteDatabase.close();
            System.out.println("Row Count" + rows);
            return rows > 0;
        }catch ( Exception e){
            return false;
        }
    }

    public User getOneUser(){

        try {

            User user = new User();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Complaint", null);

            cursor.moveToFirst();
            user.setID(cursor.getInt(0));
            user.setFullName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPhone(cursor.getInt(3));
            user.setLandPhone(cursor.getInt(4));
            user.setUserName(cursor.getString(5));
            user.setPassword(cursor.getString(6));

            sqLiteDatabase.close();
            return user;
        }catch (Exception e){
            return null;
        }
    }

}
