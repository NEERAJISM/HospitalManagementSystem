package com.whysoserious.neeraj.hospitalmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Neeraj on 18-Mar-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "HMS_DATABASE.db";
    private static final String TABLE_NAME_USER = "USER_CREDENTIALS";
    private static final String TABLE_NAME_D_LEAVES = "DOCTOR_LEAVES";
    private static final String TABLE_NAME_D_SLOT = "DOCTOR_SLOT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TABLE FOR USER CREDENTIAL
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME_USER + " (" +
                            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "first_name VARCHAR," +
                            "last_name VARCHAR," +
                            "age VARCHAR," +
                            "sex VARCHAR," +
                            "dob VARCHAR," +
                            "blood_group VARCHAR," +
                            "u_type VARCHAR," +
                            "city VARCHAR," +
                            "pincode VARCHAR," +
                            "mobile_number VARCHAR," +
                            "password VARCHAR," +
                            "username VARCHAR);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //*****************************TABLE FOR DOCTOR LEAVES**************************************
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME_D_LEAVES + " (" +
                            "username VARCHAR," +
                            "password VARCHAR," +
                            "user_type VARCHAR," +
                            "date_from VARCHAR," +
                            "date_to VARCHAR," +
                            "approval VARCHAR);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //*****************************TABLE FOR DOCTOR SLOTS**************************************
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME_D_SLOT + " (" +
                            "username VARCHAR," +
                            "password VARCHAR," +
                            "specialization VARCHAR," +
                            "slot_from VARCHAR," +
                            "slot_to VARCHAR," +
                            "available VARCHAR);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_D_LEAVES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_D_SLOT);
        onCreate(db);
    }

    //*************************************USER CREDENTIALS TABLE* *********************************************************
    //CHECHK THAT THE REGISTERED USER ALREADY EXIST ******** and returns all favourable values

    public Cursor checkduplicates_in_user_credentials(String user_name, String password, String table) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res;
        if (table.equals(TABLE_NAME_D_LEAVES)) {
            res = db.rawQuery("select * from " + TABLE_NAME_D_LEAVES + " where username=? and password=?", new String[]{user_name, password});
        } else if (table.equals(TABLE_NAME_D_SLOT)) {
            res = db.rawQuery("select * from " + TABLE_NAME_D_SLOT + " where username=? and password=?", new String[]{user_name, password});
        } else {
            res = db.rawQuery("select * from " + TABLE_NAME_USER + " where username=? and password=?", new String[]{user_name, password});
        }
        return res;
    }

    //INSERT INTO USER CREDENTIALS check duplication or alredy exists
    public boolean insert_user_credentials(String fnames, String lnames, String ages, String dobs, String citys, String pincodes, String unames, String passwords, String mobnos, String utypes, String sexs, String bgroups) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", fnames);
        contentValues.put("last_name", lnames);
        contentValues.put("age", ages);
        contentValues.put("sex", sexs);
        contentValues.put("blood_group", bgroups);
        contentValues.put("dob", dobs);
        contentValues.put("city", citys);
        contentValues.put("pincode", pincodes);
        contentValues.put("u_type", utypes);
        contentValues.put("mobile_number", mobnos);
        contentValues.put("username", unames);
        contentValues.put("password", passwords);

        long l = db.insert(TABLE_NAME_USER, null, contentValues);

        if (l != -1) {
            Message.message(context, "new entry inserted");
            return true;
        } else {
            Message.message(context, "Registration Failed");
            return false;
        }
    }
    //*************************************************DOCTOR LEAVES TABLE ********************************************************

    //insert leaves

    public boolean insert_leaves(String username, String password, String user_type, String dfrom, String dto, String approval) {

        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("user_type", user_type);
        contentValues.put("date_from", dfrom);
        contentValues.put("date_to", dto);
        contentValues.put("approval", approval);

        long l = db1.insert(TABLE_NAME_D_LEAVES, null, contentValues);

        if (l != -1) {
            return true;
        } else {
            return false;
        }
    }


    //**********************************************DOCTOR SLOT TABLE ************************************************************
    //insert slots

    public boolean insert_slot(String username, String password, String specialization, String dfrom, String dto, String available) {

        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("specialization", specialization);
        contentValues.put("slot_from", dfrom);
        contentValues.put("slot_to", dto);
        contentValues.put("available", available);

        long l = db1.insert(TABLE_NAME_D_SLOT, null, contentValues);
        if (l != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update_slot(String username, String password, String specialization, String dfrom, String dto, String available) {

        SQLiteDatabase db1 = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("specialization", specialization);
        contentValues.put("slot_from", dfrom);
        contentValues.put("slot_to", dto);
        contentValues.put("available", available);

        long l = db1.update(TABLE_NAME_D_SLOT, contentValues, "username=? and password=?", new String[]{username,password});
        if (l != -1) {
            return true;
        } else {
            return false;
        }
    }
}