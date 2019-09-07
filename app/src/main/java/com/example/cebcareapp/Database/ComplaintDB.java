package com.example.cebcareapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cebcareapp.Entity.Complaint;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplaintDB extends SQLiteOpenHelper {
    private Context context;

    public ComplaintDB(Context context) {
        super(context, "ComplaintDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Complaint(ID INTEGER PRIMARY KEY AUTOINCREMENT, Account TEXT, ComplaintType TEXT, Complaint TEXT, Phone INTEGER, Email TEXT, DateAdded TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Complaint;");
        onCreate(sqLiteDatabase);
    }

    public List<Complaint> findAll(){

        List<Complaint> complaints = new ArrayList<Complaint>();
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Complaint", null);

            if(cursor.moveToFirst()){
                do{
                    Complaint complaint = new Complaint();
                    complaint.setID(cursor.getInt(0));
                    complaint.setAccount(cursor.getString(1));
                    complaint.setComplaintType(cursor.getString(2));
                    complaint.setComplaint(cursor.getString(3));
                    complaint.setDateAdded(cursor.getString(6));
                    complaints.add(complaint);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return complaints;
        }catch (Exception e){
            return null;
        }
    }

    public boolean create(Complaint complaint){
        try{
            String currentDT = DateFormat.getDateTimeInstance().format(new Date());

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Account",complaint.getAccount());
            contentValues.put("ComplaintType",complaint.getComplaintType());
            contentValues.put("Complaint",complaint.getComplaint());
            contentValues.put("Phone",complaint.getPhone());
            contentValues.put("Email",complaint.getEmail());
            contentValues.put("DateAdded", currentDT);

            long rows = sqLiteDatabase.insert("Complaint", null, contentValues);
            sqLiteDatabase.close();
            System.out.println("Row Count" + rows);
            return rows > 0;
        }catch ( Exception e){
            return false;
        }
    }

}
