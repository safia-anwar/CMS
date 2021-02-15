package com.example.complaintms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
   // public static final String dbname = "Complaint.db";
    private Context context;
//    private Context contest;

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
//        super(context, "Complaint.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create Table users(firstName TEXT , Lastname TEXT , cellno TEXT primary key, EmailAddress TEXT ,TextPassword TEXT)");
        Mydb.execSQL("create Table complaints(location TEXT  NOT NULL, department TEXT NOT NULL, category TEXT NOT NULL, mRnum TEXT , subject TEXT NOT NULL, comment TEXT NOT NULL) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("drop table if exists users");
        Mydb.execSQL("drop table if exists complaints");

    }

    public Boolean insertData(String firstName, String Lastname, String cellno, String EmailAddress, String TextPassword) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("Lastname", Lastname);
        contentValues.put("cellno", cellno);
        contentValues.put("EmailAddress", EmailAddress);
        contentValues.put("TextPassword", TextPassword);

        long results = Mydb.insert("users", null, contentValues);
        if (results == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean insertComplaint(String location ,String department, String category , String Mrnum, String Subject , String comment){
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("location",location);
        contentValues.put("department",department);
        contentValues.put("category",category);
        contentValues.put("mRnum",Mrnum);
        contentValues.put("Subject",Subject);
        contentValues.put("comment",comment);

        long results = Mydb.insert("complaints", null, contentValues);
        if (results == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getComplaint(){
        SQLiteDatabase Mydb = this.getReadableDatabase();
        Cursor cursor= Mydb.rawQuery("Select Subject from complaints",null);
        return cursor;
    }

    public Boolean checkEmailAddress(String EmailAddress) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where EmailAddress = ?", new String[]{EmailAddress});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkEmailAddresspassword(String EmailAddress , String Textpassword)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
         Cursor cursor = Mydb.rawQuery("Select * from users where EmailAddress = ? and TextPassword =?", new String[] { EmailAddress , Textpassword});
         if (cursor.getCount() > 0)
             return true;
         else
             return false;
    }


}
