package com.example.careonthego;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CareOnTheGo.db";
    public static final Integer DATABASE_VERSION = 1;

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
    public static final String USERFEEDBACK_COL1 = "USERFEEDBACK_ID";

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
    public static final String PATIENTINFO_COL1 = "PATIENTINFOID";
    public static final String PATIENTINFO_COL5 = "AGE";
    public static final String PATIENTINFO_COL6 = "ADDRESS";
    public static final String PATIENTINFO_COL7 = "RELEVANTNOTES";
    public static final String PATIENTINFO_COL8 = "EMERGENCYNUMBERS";

    public static final String TABLE_MEDICATION = "medication_table";
    public static final String MEDICATION_COL2 = "MEDICATIONNAME";
    public static final String MEDICATION_COL3 = "MEDICATIONQUANTITY";

    String fetchedDetails, fetchedInfo, medName, selectEmergencyDetails, selectRelevantInfo, fetchedMedInfo, labelQuery;
    ArrayList<String> patientInfoDetails;
    ArrayList<String> medicationInfo;
    Cursor emergencyDetailsCursor, relevantInfoCursor, medInfoCursor, fetchedPatientInfoCursor;
    Integer patientInfoId;


    //Default Values for Patient Info Table



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);

        db.execSQL("PRAGMA foreign_keys = ON");

        String USER_TABLE_CREATE = "CREATE TABLE " + TABLE_USER + " ("
                + USER_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_COL2 + " TEXT NOT NULL, "
                + USER_COL3 + " TEXT NOT NULL);";
        db.execSQL(USER_TABLE_CREATE);

        String USERINFO_TABLE_CREATE = "CREATE TABLE " + TABLE_USERINFO + " ("
                + USER_COL1 + " INTEGER PRIMARY KEY, "
                + USERINFO_COL2 + " TEXT NOT NULL, "
                + USERINFO_COL3 + " TEXT NOT NULL, "
                + USERINFO_COL4 + " INTEGER NOT NULL, "
                + USERINFO_COL5 + " INTEGER NOT NULL);";
        db.execSQL(USERINFO_TABLE_CREATE);

        String USERFEEDBACK_TABLE_CREATE = "CREATE TABLE " + TABLE_USERFEEDBACK + " ("
                + USERFEEDBACK_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERFEEDBACK_COL2 + " INTEGER, "
                + USERFEEDBACK_COL3 + " INTEGER, "
                + USERFEEDBACK_COL4 + " TEXT, "
                + USERFEEDBACK_COL5 + " TEXT, FOREIGN KEY("+USERFEEDBACK_COL2+") REFERENCES "+TABLE_USER+"("+USER_COL1+"));";
        db.execSQL(USERFEEDBACK_TABLE_CREATE);

        String TASK_TABLE_CREATE = "CREATE TABLE " + TABLE_TASK + " ("
                + TASK_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERFEEDBACK_COL2 + " TEXT NOT NULL, "
                + TASK_COL3 + " TEXT NOT NULL, "
                + TASK_COL4 + " TEXT NOT NULL, "
                + TASK_COL5 + " TEXT NOT NULL, "
                + TASK_COL6 + " TEXT, FOREIGN KEY("+USERFEEDBACK_COL2+") REFERENCES "+TABLE_USER+"("+USER_COL1+"));";
        db.execSQL(TASK_TABLE_CREATE);

        String PATIENTINFO_TABLE_CREATE = "CREATE TABLE " + TABLE_PATIENTINFO + " ("
                + PATIENTINFO_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERFEEDBACK_COL2 + " INTEGER, "
                + USERINFO_COL2 + " TEXT NOT NULL, "
                + USERINFO_COL3 + " TEXT NOT NULL, "
                + PATIENTINFO_COL5 + " INTEGER NOT NULL, "
                + PATIENTINFO_COL6 + " TEXT NOT NULL, "
                + PATIENTINFO_COL7 + " TEXT, "
                + PATIENTINFO_COL8 + " TEXT NOT NULL, FOREIGN KEY("+USERFEEDBACK_COL2+") REFERENCES "+TABLE_USER+"("+USER_COL1+"));";
        db.execSQL(PATIENTINFO_TABLE_CREATE);

        String MEDICATION_TABLE_CREATE = "CREATE TABLE " + TABLE_MEDICATION + " ("
                + PATIENTMED_COL3 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MEDICATION_COL2 + " TEXT NOT NULL, "
                + MEDICATION_COL3 + " INTEGER NOT NULL);";
        db.execSQL(MEDICATION_TABLE_CREATE);

        String PATIENTMED_TABLE_CREATE = "CREATE TABLE " + TABLE_PATIENTMED + " ("
                + PATIENTMED_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PATIENTMED_COL2 + " INTEGER, "
                + PATIENTMED_COL3 + " INTEGER, "
                + PATIENTMED_COL4 + " TEXT, FOREIGN KEY("+PATIENTMED_COL2+") REFERENCES "+TABLE_PATIENTINFO+"("+PATIENTINFO_COL1+"), FOREIGN KEY("+PATIENTMED_COL3+") REFERENCES "+TABLE_MEDICATION+"("+PATIENTMED_COL3+"));";
        db.execSQL(PATIENTMED_TABLE_CREATE);

        String userD1 = "('admin', 'admin')";
        String userD2 = "('SmithJ', 'root')";

        String userInfoD1 = "(1, 'Bradley', 'Onyett', '07422526869', 'up858239@myport.ac.uk')";
        String userInfoD2 = "(2, 'James', 'Sutton', '07555436891', 'James.Sutton@gmail.com')";

        String patientInfoD1 = "(1, 'John', 'Smith', 63, '56 Example Lane', 'Tendency to misplace things', '07123456789')";
        String patientInfoD2 = "(2, 'Jane', 'Doe', 78, '99 Example Lane', 'Dislikes toast at breakfast', '07123453428')";
        String patientInfoD3 = "(2, 'Jim', 'Jones', 85, '11 Example Lane', 'Doesnt drink hot drinks', '07987654321')";
        String patientInfoD4 = "(1, 'Jennifer', 'Adams', 93, '33 Example Lane', 'Struggles with poor eyesight', '07213465789')";

        String patientMedD1 = "(2, 4, 'Allergic to ibuprofen')";
        String patientMedD2 = "(3, 1, 'Allergic to paracetamol')";
        String patientMedD3 = "(1, 3, 'Allergic to  warfarin')";
        String patientMedD4 = "(4, 2, 'Allergic to antihystamines')";

        String medicationD1 = "('Warfarin', 12)";
        String medicationD2 = "('Hydrocortizone', 2)";
        String medicationD3 = "('Salbutamol', 3)";
        String medicationD4 = "('Roacatane', 2)";

        //User default info implementation
        String USER_DEFAULT_VALUES = "INSERT INTO " +TABLE_USER+ "(" +USER_COL2+ "," +USER_COL3+ ") VALUES " +userD1+ "," +userD2+ ";";
        db.execSQL(USER_DEFAULT_VALUES);

        String USERINFO_DEFAULT_VALUES = "INSERT INTO " +TABLE_USERINFO+ "(" +USER_COL1+ "," +USERINFO_COL2+ "," +USERINFO_COL3+ "," +USERINFO_COL4+ "," +USERINFO_COL5+") VALUES " +userInfoD1+ "," +userInfoD2+ ";";
        db.execSQL(USERINFO_DEFAULT_VALUES);

        String PATIENTINFO_DEFAULT_VALUES = "INSERT INTO " + TABLE_PATIENTINFO + "(" +USERFEEDBACK_COL2+ "," +USERINFO_COL2+ "," +USERINFO_COL3 + "," + PATIENTINFO_COL5+ "," +PATIENTINFO_COL6+ "," + PATIENTINFO_COL7 + "," + PATIENTINFO_COL8 + ") VALUES " + patientInfoD1 + "," + patientInfoD2 + "," + patientInfoD3 + "," + patientInfoD4 + ";";
        db.execSQL(PATIENTINFO_DEFAULT_VALUES);

        // Medication default info implementation
        String MEDICATION_DEFAULT_VALUES = "INSERT INTO " +TABLE_MEDICATION+ "(" +MEDICATION_COL2+ "," +MEDICATION_COL3+ ") VALUES " +medicationD1+ "," +medicationD2+ "," +medicationD3+ "," +medicationD4+ ";";
        db.execSQL(MEDICATION_DEFAULT_VALUES);

        // Patient Medication default info implementation
        String PATIENTMED_DEFUALT_VALUES = "INSERT INTO " +TABLE_PATIENTMED+ "(" +PATIENTMED_COL2+ "," +PATIENTMED_COL3+ "," +PATIENTMED_COL4+ ") VALUES "+patientMedD1+"," +patientMedD2+ "," +patientMedD3+ "," +patientMedD4+";";
        //String PATIENTMED_DEFUALT_VALUES = "INSERT INTO "+TABLE_PATIENTMED+"("+PATIENTMED_COL2+","+PATIENTMED_COL3+","+PATIENTMED_COL4+") VALUES"+patientMedD1+","+patientMedD2+","+patientMedD3+","+patientMedD4+";";
        //String PATIENTMED_DEFAULT_VALUES2 = "INSERT INTO " +TABLE_PATIENTMED+ "(" +PATIENTMED_COL2+ "," +PATIENTMED_COL3+ "," +PATIENTMED_COL4+ ") VALUES " +patientMedD2+";";
        //String PATIENTMED_DEFAULT_VALUES3 = "INSERT INTO " +TABLE_PATIENTMED+ "(" +PATIENTMED_COL2+ "," +PATIENTMED_COL3+ "," +PATIENTMED_COL4+ ") VALUES " +patientMedD3+";";
        //String PATIENTMED_DEFAULT_VALUES4 = "INSERT INTO " +TABLE_PATIENTMED+ "(" +PATIENTMED_COL2+ "," +PATIENTMED_COL3+ "," +PATIENTMED_COL4+ ") VALUES " +patientMedD4+";";
        //String patientMedDTest = "INSERT INTO patientMed_table(PATIENTINFOID, MEDICATIONID, MEDICATIONNOTES) VALUES (3,1,'Allergic to ibuprofen'), (1,3,'Allergic to paracetamol'), (4,2,'Allergic to antihystamines')";
        db.execSQL(PATIENTMED_DEFUALT_VALUES);
        //db.execSQL(PATIENTMED_DEFAULT_VALUES2);
        //db.execSQL(PATIENTMED_DEFAULT_VALUES3);
        //db.execSQL(PATIENTMED_DEFAULT_VALUES4);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setForeignKeyConstraintsEnabled(true);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERFEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTMED);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATION);
        onCreate(db);
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean insertPatientData(String firstName, String surname, Integer age, String address,
                                     String extraNotes, String emergencyNumbers) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        ContentValues patientValues = new ContentValues();
        patientValues.put(USERINFO_COL2, firstName);
        patientValues.put(USERINFO_COL3, surname);
        patientValues.put(PATIENTINFO_COL5, age);
        patientValues.put(PATIENTINFO_COL6, address);
        patientValues.put(PATIENTINFO_COL7, extraNotes);
        patientValues.put(PATIENTINFO_COL8, emergencyNumbers);
        long patientResult = db.insert(TABLE_PATIENTINFO, null, patientValues);
        db.close();
        if (patientResult == -1){
            return false;
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean insertFeedbackData(Boolean type, String feedback, Float rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        ContentValues feedbackValues = new ContentValues();
        feedbackValues.put(USERFEEDBACK_COL3, type);
        feedbackValues.put(USERFEEDBACK_COL4, feedback);
        feedbackValues.put(USERFEEDBACK_COL5, rating);
        long feedbackResult = db.insert(TABLE_USERFEEDBACK, null, feedbackValues);
        db.close();
        if (feedbackResult == -1){
            return false;
        } else {
            return true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<String> getPatientLabels() {
        List<String> patientNames = new ArrayList<String>();
        String selectQuery1 = "SELECT " + USERINFO_COL2 + "||" + "' '" + "||" + USERINFO_COL3 + " FROM " + TABLE_PATIENTINFO;
        SQLiteDatabase db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        Cursor patientLabelCursor = db.rawQuery(selectQuery1, null);
        if(patientLabelCursor.moveToFirst()){
            do{
                patientNames.add(patientLabelCursor.getString(0));
            } while (patientLabelCursor.moveToNext());
        }
        patientLabelCursor.close();
        db.close();

        return patientNames;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList<String> getPatientInfoDetails(String patientName) {
        patientInfoDetails = new ArrayList<String>(); //Can initialise at top
        String[] splitString = patientName.split(" "); //TALK ABOUT THIS FIX
        SQLiteDatabase db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        selectEmergencyDetails = "SELECT " +PATIENTINFO_COL8+ " FROM " + TABLE_PATIENTINFO+ " WHERE " + USERINFO_COL2 + "= '" +splitString[0]+ "'";
        selectRelevantInfo = "SELECT " +PATIENTINFO_COL7+ " FROM " + TABLE_PATIENTINFO+ " WHERE " + USERINFO_COL2 + "= '" +splitString[0]+ "'";
        emergencyDetailsCursor = db.rawQuery(selectEmergencyDetails, null);
        relevantInfoCursor = db.rawQuery(selectRelevantInfo, null);

        if(relevantInfoCursor!=null && relevantInfoCursor.getCount()>0){
            relevantInfoCursor.moveToFirst();
            do{
                fetchedInfo = relevantInfoCursor.getString(0);
            }while (relevantInfoCursor.moveToNext());
        }

        if(emergencyDetailsCursor!=null && emergencyDetailsCursor.getCount()>0){
            emergencyDetailsCursor.moveToFirst();
            do{
                fetchedDetails = emergencyDetailsCursor.getString(0);
            }while (emergencyDetailsCursor.moveToNext());
        }
        emergencyDetailsCursor.close();
        db.close();
        patientInfoDetails.add(fetchedDetails);
        patientInfoDetails.add(fetchedInfo);

        return patientInfoDetails;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList<String> getMedicationInfo(Integer patientInfoID) {
        medicationInfo = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        medName = "SELECT " +TABLE_MEDICATION+"."+MEDICATION_COL2+","+TABLE_MEDICATION+"."+MEDICATION_COL3+" FROM "+TABLE_MEDICATION+" INNER JOIN "+TABLE_MEDICATION+" ON "+TABLE_MEDICATION+"."+PATIENTMED_COL3+"="+TABLE_PATIENTMED+"."+PATIENTMED_COL3+" WHERE "+TABLE_PATIENTMED+"."+PATIENTMED_COL2+"="+patientInfoID+";";
        //TO-DO: MEDICATION SQL IS HERE, COULD BE SYNTAX, LOOK AT THIS WHEN YOU'RE NOT SLEEP DEPRIVED.
        medInfoCursor = db.rawQuery(medName, null);

        if (medInfoCursor!=null && medInfoCursor.getCount()>0){
            medInfoCursor.moveToFirst();
            do{
                fetchedMedInfo = medInfoCursor.getString(0);
                medicationInfo.add(fetchedMedInfo);
            }while(medInfoCursor.moveToNext());
        }
        medInfoCursor.close();
        db.close();
        return medicationInfo;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public int getPatientInfoID(String patientLabel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] splitName = patientLabel.split(" ");
        db.setForeignKeyConstraintsEnabled(true);
        labelQuery = "SELECT " + PATIENTINFO_COL1 + " FROM " + TABLE_PATIENTINFO + " WHERE " + USERINFO_COL2 + "= '" + splitName[0] + "';";
        fetchedPatientInfoCursor = db.rawQuery(labelQuery, null);

        if (fetchedPatientInfoCursor != null && fetchedPatientInfoCursor.getCount() > 0) {
            fetchedPatientInfoCursor.moveToFirst();
            do {
                patientInfoId = fetchedPatientInfoCursor.getInt(0);
            } while (fetchedPatientInfoCursor.moveToNext());
        }
        fetchedPatientInfoCursor.close();
        db.close();
        return patientInfoId;
    }

}
