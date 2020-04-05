package com.example.careonthego;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CareOnTheGo.db";
    public static final String USER_COL1 = "ID"; //PK
    public static final String USERFEEDBACK_COL2 = "USERID"; //FK
    public static final String USERINFO_COL2 = "FIRSTNAME";
    public static final String USERINFO_COL3 = "SURNAME";
    public static final String PATIENTMED_COL3 = "MEDICATIONID"; //FK

    public static final String TABLE_USER = "user_table";
    public static final String USER_COL2 = "USERNAME";
    public static final String USER_COL3 = "PASSWORD";

    public static final String TABLE_USERINFO = "userInfo_table";
    public static final String USERINFO_COL4 = "NUMBER";
    public static final String USERINFO_COL5 = "EMAIL";

    public static final String TABLE_USERFEEDBACK = "userFeedback_table";
    public static final String USERFEEDBACK_COL3 = "TYPE";
    public static final String USERFEEDBACK_COL4 = "INFORMATION";
    public static final String USERFEEDBACK_COL5 = "RATING";

    public static final String TABLE_TASK = "task_table";
    public static final String TASK_COL1 = "TASKID";
    public static final String TASK_COL3 = "TASKNAME";
    public static final String TASK_COL4 = "TASKSTART";
    public static final String TASK_COL5 = "TASKFINISH";
    public static final String TASK_COL6 = "TASKINFO";

    public static final String TABLE_PATIENTMED = "patientMed_table";
    public static final String PATIENTMED_COL1 = "PATIENTMEDICATIONID";
    public static final String PATIENTMED_COL2 = "PATIENTINFOID"; //FK
    public static final String PATIENTMED_COL4 = "MEDICATIONNOTES";

    public static final String TABLE_PATIENTINFO = "patientInfo_table";
    public static final String PATIENTINFO_COL5 = "AGE";
    public static final String PATIENTINFO_COL6 = "ADDRESS";
    public static final String PATIENTINFO_COL7 = "RELEVANTNOTES";
    public static final String PATIENTINFO_COL8 = "EMERGENCYNUMBERS";

    public static final String TABLE_MEDICATION_ = "medication_table";
    public static final String MEDICATION_COL2 = "MEDICATIONNAME";
    public static final String MEDICATION_COL3 = "MEDICATIONQUANTITY";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* db.execSQL("create table " + TABLE_USER + "("+USER_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USER_COL2+" TEXT, "+USER_COL3+" TEXT)");
        db.execSQL("create table " + TABLE_USERINFO + "("+USER_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USERINFO_COL2+" TEXT, "+USERINFO_COL3+" TEXT, "+USERINFO_COL4+" INT, "+USERINFO_COL5+"TEXT)");
        db.execSQL("create table " + TABLE_USERFEEDBACK + "("+USER_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USERFEEDBACK_COL2+" INTEGER, "+USERFEEDBACK_COL3+" INTEGER, "+USERFEEDBACK_COL4+" TEXT, "+USERFEEDBACK_COL5+"INTEGER)");
        db.execSQL("create table " + TABLE_TASK + "("+TASK_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USERFEEDBACK_COL2+" INTEGER, "+TASK_COL3+" TEXT, "+TASK_COL4+" TEXT, "+TASK_COL5+"TEXT, "+TASK_COL6+"TEXT)");
        db.execSQL("create table " + TABLE_PATIENTMED + "("+PATIENTMED_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+PATIENTMED_COL2+" INTEGER , "+PATIENTMED_COL3+" INTEGER, "+PATIENTMED_COL4+" TEXT)");
        db.execSQL("create table " + TABLE_PATIENTINFO + "("+USER_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USERFEEDBACK_COL2+" INTEGER, "+USERINFO_COL2+" TEXT, "+USERINFO_COL3+" TEXT, "+PATIENTINFO_COL5+"INTEGER, "+PATIENTINFO_COL6+"TEXT, "+PATIENTINFO_COL7+"TEXT, "+PATIENTINFO_COL8+"TEXT)");
        db.execSQL("create table " + TABLE_MEDICATION_ + "("+PATIENTMED_COL3+" INTEGER PRIMARY KEY AUTOINCREMENT,"+MEDICATION_COL2+" TEXT, "+MEDICATION_COL3+" TEXT)");
    */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
    //onCreate(db);
    }
}