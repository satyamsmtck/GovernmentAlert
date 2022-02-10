package com.satyam.governmentalert.Helper;

import static com.satyam.governmentalert.Helper.ConstantClass.CAT_NAMES;
import static com.satyam.governmentalert.Helper.ConstantClass.DETAILS;
import static com.satyam.governmentalert.Helper.ConstantClass.FUNCTIONAL_AREA;
import static com.satyam.governmentalert.Helper.ConstantClass.ID;
import static com.satyam.governmentalert.Helper.ConstantClass.IMPORTANT;
import static com.satyam.governmentalert.Helper.ConstantClass.INDEXED_TYPE;
import static com.satyam.governmentalert.Helper.ConstantClass.LANGUAGE_ID;
import static com.satyam.governmentalert.Helper.ConstantClass.LOCATION;
import static com.satyam.governmentalert.Helper.ConstantClass.NOTIFICATION;
import static com.satyam.governmentalert.Helper.ConstantClass.ORGANIZATION;
import static com.satyam.governmentalert.Helper.ConstantClass.PROFILE_TYPE;
import static com.satyam.governmentalert.Helper.ConstantClass.PUBLISH_DATE;
import static com.satyam.governmentalert.Helper.ConstantClass.QUALIFICATION;
import static com.satyam.governmentalert.Helper.ConstantClass.SUBMISSION_DATE;
import static com.satyam.governmentalert.Helper.ConstantClass.SUMMARY;
import static com.satyam.governmentalert.Helper.ConstantClass.TAGS;
import static com.satyam.governmentalert.Helper.ConstantClass.TITLE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "job_notification";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "job_table";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " TEXT NOT NULL UNIQUE," +
                    SUBMISSION_DATE + " TEXT," +
                    PUBLISH_DATE + " TEXT," +
                    LANGUAGE_ID + " TEXT," +
                    TITLE + " TEXT," +
                    SUMMARY + " TEXT," +
                    CAT_NAMES + " TEXT," +
                    FUNCTIONAL_AREA + " TEXT," +
                    IMPORTANT + " TEXT," +
                    INDEXED_TYPE + " TEXT," +
                    LOCATION + " TEXT," +
                    NOTIFICATION + " TEXT," +
                    ORGANIZATION + " TEXT," +
                    PROFILE_TYPE + " TEXT," +
                    QUALIFICATION + " TEXT," +
                    TAGS + " TEXT," +
                    DETAILS + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}