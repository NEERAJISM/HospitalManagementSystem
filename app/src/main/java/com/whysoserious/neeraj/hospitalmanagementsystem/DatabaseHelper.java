package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Neeraj on 18-Mar-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "HMS_DATABASE";
    private static final String TABLE_NAME = "USER_CREDENTIALS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL("CREATE TABLE "+TABLE_NAME+" (" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "first_name VARCHAR(50)," +
                            "last_name VARCHAR(50)," +
                            "age VARCHAR(10)," +
                            "sex VARCHAR(10)," +
                            "dob VARCHAR(10)," +
                            "blood_group VARCHAR(10)," +
                            "u_type VARCHAR(15)," +
                            "city VARCHAR(50)," +
                            "pincode VARCHAR(10)," +
                            "mobile_number VARCHAR(15)," +
                            "password VARCHAR(50)," +
                            "user_name VARCHAR(50));"
            );

            Message.message(context,"Database Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER_CREDENTIALS");
        onCreate(db);
    }

    //CHECHK THAT THE REGISTERED USER ALREADY EXIST
    public Cursor checkduplicates_in_user_credentials(String user_name ,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from USER_CREDENTIALS where user_name=? and password=?",new String[] {user_name,password});
        return res;
    }


    //INSERT INTO USER CREDENTIALS *****************check duplication or alredy exists
    public boolean insert_user_credentials(String fnames, String lnames, String ages, String dobs, String citys, String pincodes, String unames, String passwords, String mobnos, String utypes, String sexs, String bgroups) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name",fnames);
        contentValues.put("last_name", lnames);
        contentValues.put("age",ages);
        contentValues.put("sex",sexs);
        contentValues.put("blood_group",bgroups);
        contentValues.put("dob",dobs);
        contentValues.put("city",citys);
        contentValues.put("pincode",pincodes);
        contentValues.put("u_type",utypes);
        contentValues.put("mobile_number", mobnos);
        contentValues.put("username",unames);
        contentValues.put("password",passwords);

        db.insert(TABLE_NAME, null, contentValues);
        Message.message(context,"new entry inserted");
        return true;
    }

}