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
    public static final String TASK_DAY = "TASKDAY";
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

    String fetchedDetails;
    String patientNameQuery;
    String medicationNameQuery;
    String medicationNoteUpdateQuery;
    String medIdQuery;
    String usernameCheckQuery;
    String userIdQuery;
    String taskQuery1;
    String taskQuery2;
    String taskQuery3;
    String taskQuery4;
    String taskQuery5;
    String fetchedTaskName;
    String fetchedTaskDay;
    Integer fetchedTaskStart;
    Integer fetchedTaskFinish;
    String fetchedTaskInfo;
    String medQuantString;
    String medNotesQuery;
    String fetchedInfo;
    String selectEmergencyDetails;
    String selectRelevantInfo;
    String fetchedMedNameInfo;
    String labelQuery;
    String testMed;
    String medQuantQuery;
    String fetchedMedNotes;
    String fetchedTaskStartConversion, fetchedTaskFinishConversion;
    int retrievedMedID;
    Integer fetchedMedQuantInfo, fetchedMedId;
    ArrayList<String> patientInfoDetails, medicationInfo, patientNames, medicationNames, taskArray;
    ArrayList<Integer> medicationQuantInfo;
    Cursor emergencyDetailsCursor, relevantInfoCursor, medInfoCursor, fetchedPatientInfoCursor, medQuantCursor, medNotesCursor, patientLabelCursor, medicationLabelCursor, fetchedMedIdCursor, taskNameCursor, taskDayCursor, taskStartCursor, taskFinishCursor, taskInfoCursor, usernameCheckCursor, passwordCheckCursor, userIdCursor;
    Integer patientInfoId, relatedPatientID, fetchedUserId;
    long feedbackResult, medNoteUpdateResult, noteInputResult;
    SQLiteDatabase db;


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
                + USERFEEDBACK_COL5 + " TEXT, FOREIGN KEY(" + USERFEEDBACK_COL2 + ") REFERENCES " + TABLE_USER + "(" + USER_COL1 + "));";
        db.execSQL(USERFEEDBACK_TABLE_CREATE);

        String TASK_TABLE_CREATE = "CREATE TABLE " + TABLE_TASK + " ("
                + TASK_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERFEEDBACK_COL2 + " INTEGER NOT NULL, "
                + TASK_COL3 + " TEXT NOT NULL, "
                + TASK_DAY + " TEXT NOT NULL,"
                + TASK_COL4 + " INTEGER NOT NULL, "
                + TASK_COL5 + " INTEGER NOT NULL, "
                + TASK_COL6 + " TEXT, FOREIGN KEY(" + USERFEEDBACK_COL2 + ") REFERENCES " + TABLE_USER + "(" + USER_COL1 + "));";
        db.execSQL(TASK_TABLE_CREATE);

        String PATIENTINFO_TABLE_CREATE = "CREATE TABLE " + TABLE_PATIENTINFO + " ("
                + PATIENTINFO_COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERFEEDBACK_COL2 + " INTEGER, "
                + USERINFO_COL2 + " TEXT NOT NULL, "
                + USERINFO_COL3 + " TEXT NOT NULL, "
                + PATIENTINFO_COL5 + " INTEGER NOT NULL, "
                + PATIENTINFO_COL6 + " TEXT NOT NULL, "
                + PATIENTINFO_COL7 + " TEXT, "
                + PATIENTINFO_COL8 + " TEXT NOT NULL, FOREIGN KEY(" + USERFEEDBACK_COL2 + ") REFERENCES " + TABLE_USER + "(" + USER_COL1 + "));";
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
                + PATIENTMED_COL4 + " TEXT, FOREIGN KEY(" + PATIENTMED_COL2 + ") REFERENCES " + TABLE_PATIENTINFO + "(" + PATIENTINFO_COL1 + "), FOREIGN KEY(" + PATIENTMED_COL3 + ") REFERENCES " + TABLE_MEDICATION + "(" + PATIENTMED_COL3 + "));";
        db.execSQL(PATIENTMED_TABLE_CREATE);

        String userD1 = "('admin', 'admin')";
        String userD2 = "('SmithJ', 'root')";
        String userD3 = "('SaraJ', 'root')";


        String userInfoD1 = "(1, 'Bradley', 'Onyett', '07422526869', 'up858239@myport.ac.uk')";
        String userInfoD2 = "(2, 'James', 'Sutton', '07555436891', 'James.Sutton@gmail.com')";

        String patientInfoD1 = "(1, 'John', 'Smith', 63, '56 Example Lane', 'Tendency to misplace things', '07123456789')";
        String patientInfoD2 = "(2, 'Jane', 'Doe', 78, '99 Example Lane', 'Dislikes toast at breakfast', '07123453428')";
        String patientInfoD3 = "(2, 'Jim', 'Jones', 85, '11 Example Lane', 'Doesnt drink hot drinks', '07987654321')";
        String patientInfoD4 = "(1, 'Jennifer', 'Adams', 93, '33 Example Lane', 'Struggles with poor eyesight', '07213465789')";

        String patientMedD1 = "(2, 4, 'Allergic to ibuprofen')";
        String patientMedD2 = "(3, 1, 'Allergic to paracetamol')";
        String patientMedD3 = "(1, 3, 'Allergic to warfarin')";
        String patientMedD4 = "(4, 2, 'Allergic to antihystamines')";

        String medicationD1 = "('Warfarin', 12)";
        String medicationD2 = "('Hydrocortizone', 2)";
        String medicationD3 = "('Salbutamol', 3)";
        String medicationD4 = "('Roacatane', 2)";

        String taskD1 = "(2, 'Morning Visit: Mr. John Smith', 'Monday', '0900', '1015', 'Parking available at patients house.')";
        String taskD2 = "(2, 'Afternoon Visit: Mrs. Jane Doe', 'Monday', '1100', '1215', 'Nearest parking = 5 minute walk.')";
        String taskD3 = "(2, 'Hospital Meeting: Care Management', 'Monday', '1400', '1600', 'N/A')";
        String taskD4 = "(2, 'Evening Visit: Ms. Jennifer Adams', 'Monday', '1700', '1900', 'Parking available at patients house.')";
        String taskD5 = "(3, 'Morning Visit: Ms. Jennifer Adams', 'Monday', '0815', '0915', 'Use Key safe for entry')";
        String taskD6 = "(3, 'Morning Visit Mr. Jim Jones', 'Monday', '1000', '1100', 'Parking available at patients house.')";
        String taskD7 = "(3, 'Afternoon Visit: Mr. John Smith', 'Monday', '1400', '1600', 'Patient has an early dinner during this visit.')";
        String taskD8 = "(3, 'Evening Visit Mr. Jane Doe', 'Monday', '1745', '1900', 'Patient will sometimes have an early dinner during their afternoon visit.')";

        //User default info implementation
        String USER_DEFAULT_VALUES = "INSERT INTO " + TABLE_USER + "(" + USER_COL2 + "," + USER_COL3 + ") VALUES " + userD1 + "," + userD2 + "," + userD3 + ";";
        db.execSQL(USER_DEFAULT_VALUES);

        String USERINFO_DEFAULT_VALUES = "INSERT INTO " + TABLE_USERINFO + "(" + USER_COL1 + "," + USERINFO_COL2 + "," + USERINFO_COL3 + "," + USERINFO_COL4 + "," + USERINFO_COL5 + ") VALUES " + userInfoD1 + "," + userInfoD2 + ";";
        db.execSQL(USERINFO_DEFAULT_VALUES);

        String PATIENTINFO_DEFAULT_VALUES = "INSERT INTO " + TABLE_PATIENTINFO + "(" + USERFEEDBACK_COL2 + "," + USERINFO_COL2 + "," + USERINFO_COL3 + "," + PATIENTINFO_COL5 + "," + PATIENTINFO_COL6 + "," + PATIENTINFO_COL7 + "," + PATIENTINFO_COL8 + ") VALUES " + patientInfoD1 + "," + patientInfoD2 + "," + patientInfoD3 + "," + patientInfoD4 + ";";
        db.execSQL(PATIENTINFO_DEFAULT_VALUES);

        // Medication default info implementation
        String MEDICATION_DEFAULT_VALUES = "INSERT INTO " + TABLE_MEDICATION + "(" + MEDICATION_COL2 + "," + MEDICATION_COL3 + ") VALUES " + medicationD1 + "," + medicationD2 + "," + medicationD3 + "," + medicationD4 + ";";
        db.execSQL(MEDICATION_DEFAULT_VALUES);

        // Patient Medication default info implementation
        String PATIENTMED_DEFAULT_VALUES = "INSERT INTO " + TABLE_PATIENTMED + "(" + PATIENTMED_COL2 + "," + PATIENTMED_COL3 + "," + PATIENTMED_COL4 + ") VALUES " + patientMedD1 + "," + patientMedD2 + "," + patientMedD3 + "," + patientMedD4 + ";";
        db.execSQL(PATIENTMED_DEFAULT_VALUES);

        String TASK_DEFAULT_VALUES = "INSERT INTO " + TABLE_TASK + "(" + USERFEEDBACK_COL2 + "," + TASK_COL3 + "," + TASK_DAY + "," + TASK_COL4 + "," + TASK_COL5 + "," + TASK_COL6 + ") VALUES " + taskD1 + "," + taskD2 + "," + taskD3 + "," + taskD4 + "," + taskD5 + "," + taskD6 + "," + taskD7 + "," + taskD8 + ";";
        db.execSQL(TASK_DEFAULT_VALUES);
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

    // INSERT METHODS CAN BE MADE INTO ONE METHOD TO STOP DUPLICATION.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean insertPatientData(String firstName, String surname, Integer age, String address,
                                     String extraNotes, String emergencyNumbers) {
        db = this.getWritableDatabase();
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
        if (patientResult == -1) {
            return false;
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean insertNewMedication(String patientName, String medicationName, Integer medicationQuantity) {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);

        ContentValues newMedicationValues = new ContentValues();
        newMedicationValues.put(MEDICATION_COL2, medicationName);
        newMedicationValues.put(MEDICATION_COL3, medicationQuantity);
        long newMedResult = db.insert(TABLE_MEDICATION, null, newMedicationValues);
        insertMedicationRelation(patientName, medicationName);
        if (newMedResult == -1) {
            return false;
        } else {
            return true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean insertMedicationRelation(String patientName, String medicationName) {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        relatedPatientID = getPatientInfoID(patientName);
        String medicationInfoID = "SELECT " + PATIENTMED_COL3 + " FROM " + TABLE_MEDICATION + " WHERE " + MEDICATION_COL2 + "= '" + medicationName + "';";
        Cursor medicationIdCursor = db.rawQuery(medicationInfoID, null);

        if (medicationIdCursor != null && medicationIdCursor.getCount() > 0) {
            medicationIdCursor.moveToFirst();
            do {
                retrievedMedID = medicationIdCursor.getInt(0);
            } while (medicationIdCursor.moveToNext());
        }
        medicationIdCursor.close();

        ContentValues newRelationValues = new ContentValues();
        newRelationValues.put(PATIENTMED_COL2, patientInfoId);
        newRelationValues.put(PATIENTMED_COL3, retrievedMedID);
        long relationUpdateCheck = db.insert(TABLE_PATIENTMED, null, newRelationValues);
        db.close();
        if (relationUpdateCheck == -1) {
            return false;
        } else {
            return true;
        }

    }

    //HERE

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    // needs to add the user that added the feedback.
    public boolean insertFeedbackData(Boolean type, String feedback, Float rating) {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        ContentValues feedbackValues = new ContentValues();
        feedbackValues.put(USERFEEDBACK_COL3, type);
        feedbackValues.put(USERFEEDBACK_COL4, feedback);
        feedbackValues.put(USERFEEDBACK_COL5, rating);
        feedbackResult = db.insert(TABLE_USERFEEDBACK, null, feedbackValues);
        db.close();
        if (feedbackResult == -1) {
            return false;
        } else {
            return true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean insertMedicationNoteData(Integer medicationId, String medicationNotes) {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        ContentValues medNoteValues = new ContentValues();
        medNoteValues.put(PATIENTMED_COL4, medicationNotes);
        medicationNoteUpdateQuery = " " + PATIENTMED_COL3 + " = " + medicationId + ";";
        medNoteUpdateResult = db.update(TABLE_PATIENTMED, medNoteValues, medicationNoteUpdateQuery, null);
        db.close();
        if (medNoteUpdateResult == -1) {
            return false;
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean insertTimetableNoteData(Integer userID, String taskName, String taskDay, Integer taskStart, Integer taskFinish, String taskInfo){
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        ContentValues noteValues = new ContentValues();
        noteValues.put(USERFEEDBACK_COL2, userID);
        noteValues.put(TASK_COL3, taskName);
        noteValues.put(TASK_DAY, taskDay);
        noteValues.put(TASK_COL4, taskStart);
        noteValues.put(TASK_COL5, taskFinish);
        noteValues.put(TASK_COL6, taskInfo);
        noteInputResult = db.insert(TABLE_TASK, null, noteValues);
        db.close();
        if (noteInputResult == -1){
            return false;
        } else {
            return true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<String> getPatientLabels() {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        patientNames = new ArrayList<String>();
        patientNameQuery = "SELECT " + USERINFO_COL2 + "||" + "' '" + "||" + USERINFO_COL3 + " FROM " + TABLE_PATIENTINFO;
        patientLabelCursor = db.rawQuery(patientNameQuery, null);
        if (patientLabelCursor.moveToFirst()) {
            do {
                patientNames.add(patientLabelCursor.getString(0));
            } while (patientLabelCursor.moveToNext());
        }
        patientLabelCursor.close();
        db.close();

        return patientNames;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public List<String> getMedicationLabels(Integer patientInfoId) {
        medicationNames = new ArrayList<String>();
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        medicationNameQuery = "select medication_table.MEDICATIONNAME from patientMed_table Inner join medication_table on medication_table.MEDICATIONID = patientMed_table.MEDICATIONID where patientMed_table.PATIENTINFOID =" + patientInfoId + ";";
        medicationLabelCursor = db.rawQuery(medicationNameQuery, null);

        if (medicationLabelCursor.moveToFirst()) {
            do {
                medicationNames.add(medicationLabelCursor.getString(0));
            } while (medicationLabelCursor.moveToNext());
        }
        medicationLabelCursor.close();
        db.close();

        return medicationNames;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList<String> getPatientInfoDetails(String patientName) {
        patientInfoDetails = new ArrayList<String>(); //Can initialise at top
        String[] splitString = patientName.split(" "); //TALK ABOUT THIS FIX
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        selectEmergencyDetails = "SELECT " + PATIENTINFO_COL8 + " FROM " + TABLE_PATIENTINFO + " WHERE " + USERINFO_COL2 + "= '" + splitString[0] + "'";
        selectRelevantInfo = "SELECT " + PATIENTINFO_COL7 + " FROM " + TABLE_PATIENTINFO + " WHERE " + USERINFO_COL2 + "= '" + splitString[0] + "'";
        emergencyDetailsCursor = db.rawQuery(selectEmergencyDetails, null);
        relevantInfoCursor = db.rawQuery(selectRelevantInfo, null);

        if (relevantInfoCursor != null && relevantInfoCursor.getCount() > 0) {
            relevantInfoCursor.moveToFirst();
            do {
                fetchedInfo = relevantInfoCursor.getString(0);
            } while (relevantInfoCursor.moveToNext());
        }

        if (emergencyDetailsCursor != null && emergencyDetailsCursor.getCount() > 0) {
            emergencyDetailsCursor.moveToFirst();
            do {
                fetchedDetails = emergencyDetailsCursor.getString(0);
            } while (emergencyDetailsCursor.moveToNext());
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
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        testMed = "select medication_table.MEDICATIONNAME from patientMed_table Inner join medication_table on medication_table.MEDICATIONID = patientMed_table.MEDICATIONID where patientMed_table.PATIENTINFOID =" + patientInfoID + ";";
        // medName = "SELECT " +TABLE_MEDICATION+"."+MEDICATION_COL2+","+TABLE_MEDICATION+"."+MEDICATION_COL3+" FROM "+TABLE_MEDICATION+" INNER JOIN "+TABLE_MEDICATION+" ON "+TABLE_MEDICATION+"."+PATIENTMED_COL3+"="+TABLE_PATIENTMED+"."+PATIENTMED_COL3+" WHERE "+TABLE_PATIENTMED+"."+PATIENTMED_COL2+"="+patientInfoID+";";
        medQuantQuery = "select medication_table.MEDICATIONQUANTITY from patientMed_table Inner join medication_table on medication_table.MEDICATIONID = patientMed_table.MEDICATIONID where patientMed_table.PATIENTINFOID =" + patientInfoID + ";";
        medInfoCursor = db.rawQuery(testMed, null);
        medQuantCursor = db.rawQuery(medQuantQuery, null);

        if (medInfoCursor != null && medInfoCursor.getCount() > 0 && medQuantCursor != null && medQuantCursor.getCount() > 0) {
            medInfoCursor.moveToFirst();
            medQuantCursor.moveToFirst();
            do {
                fetchedMedNameInfo = medInfoCursor.getString(0);
                fetchedMedQuantInfo = medQuantCursor.getInt(0);
                medQuantString = fetchedMedQuantInfo.toString();
                medicationInfo.add(fetchedMedNameInfo);
                medicationInfo.add(medQuantString);
            } while (medInfoCursor.moveToNext() && medQuantCursor.moveToNext());
        }
/*      // Removed Code (Caused Error)
        if (medQuantCursor!=null && medQuantCursor.getCount()>0){
            medQuantCursor.moveToFirst();
            do{
                fetchedMedQuantInfo = medQuantCursor.getInt(0);
                medQuantString = fetchedMedQuantInfo.toString();
                medicationInfo.add(medQuantString);
            }while(medQuantCursor.moveToNext());
        }
*/

        medInfoCursor.close();
        medQuantCursor.close();
        db.close();
        return medicationInfo;
    }

    //NOTE TO SELF: IF YOU GET A CHANCE TO CHANGE THESE FUNCTIONS THAT USE THIS SAME IF STATEMENT AND SOMEHOW SAVE ON DUPLICATED CODE THAT WOULD BE GREAT BUT IS NOT ESSENTIAL IF NOT ENOUGH TIME.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public String getMedicationNotes(Integer patientInfoId) {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        medNotesQuery = "SELECT " + PATIENTMED_COL4 + " FROM " + TABLE_PATIENTMED + " WHERE " + PATIENTMED_COL2 + "= " + patientInfoId + ";";
        medNotesCursor = db.rawQuery(medNotesQuery, null);


        if (medNotesCursor != null && medNotesCursor.getCount() > 0) {
            medNotesCursor.moveToFirst();
            do {
                fetchedMedNotes = medNotesCursor.getString(0);
            } while (medNotesCursor.moveToNext());
        }
        return fetchedMedNotes;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public int getPatientInfoID(String patientLabel) {
        db = this.getWritableDatabase();
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public int getMedicationId(String medicationName) {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        medIdQuery = "SELECT " + PATIENTMED_COL3 + " FROM " + TABLE_MEDICATION + " WHERE " + MEDICATION_COL2 + " = '" + medicationName + "';";
        fetchedMedIdCursor = db.rawQuery(medIdQuery, null);

        if (fetchedMedIdCursor != null && fetchedMedIdCursor.getCount() > 0) {
            fetchedMedIdCursor.moveToFirst();
            do {
                fetchedMedId = fetchedMedIdCursor.getInt(0);
            } while (fetchedMedIdCursor.moveToNext());
        }
        fetchedMedIdCursor.close();
        db.close();
        return fetchedMedId;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Integer getUserId(String username) {
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        userIdQuery = "SELECT " +USER_COL1+ " FROM " +TABLE_USER+ " WHERE " +USER_COL2+ " = '" +username+ "';";
        userIdCursor = db.rawQuery(userIdQuery, null);

        if (userIdCursor != null && userIdCursor.getCount() > 0) {
            userIdCursor.moveToFirst();
            do {
                fetchedUserId = userIdCursor.getInt(0);
            } while (userIdCursor.moveToNext());
        }
        userIdCursor.close();
        db.close();
        return fetchedUserId;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public ArrayList<String> getTaskInfo(Integer userId, String dayOfWeek) {
        taskArray = new ArrayList<String>();
        String[] splitDay = dayOfWeek.split(" ");
        db = this.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
        taskQuery1 = "SELECT " + TASK_COL3 + " FROM " + TABLE_TASK + " WHERE " + USERFEEDBACK_COL2 + " = " + userId + " AND " + TASK_DAY + " = '" + dayOfWeek + "' ORDER BY " + TASK_COL4 + " ASC;";
        taskQuery2 = "SELECT " + TASK_DAY + " FROM " + TABLE_TASK + " WHERE " + USERFEEDBACK_COL2 + " = " + userId + " AND " + TASK_DAY + " = '" + dayOfWeek + "' ORDER BY " + TASK_COL4 + " ASC;";
        taskQuery3 = "SELECT " + TASK_COL4 + " FROM " + TABLE_TASK + " WHERE " + USERFEEDBACK_COL2 + " = " + userId + " AND " + TASK_DAY + " = '" + dayOfWeek + "' ORDER BY " + TASK_COL4 + " ASC;";
        taskQuery4 = "SELECT " + TASK_COL5 + " FROM " + TABLE_TASK + " WHERE " + USERFEEDBACK_COL2 + " = " + userId + " AND " + TASK_DAY + " = '" + dayOfWeek + "' ORDER BY " + TASK_COL4 + " ASC;";
        taskQuery5 = "SELECT " + TASK_COL6 + " FROM " + TABLE_TASK + " WHERE " + USERFEEDBACK_COL2 + " = " + userId + " AND " + TASK_DAY + " = '" + dayOfWeek + "' ORDER BY " + TASK_COL4 + " ASC;";

        taskNameCursor = db.rawQuery(taskQuery1, null);
        taskDayCursor = db.rawQuery(taskQuery2, null);
        taskStartCursor = db.rawQuery(taskQuery3, null);
        taskFinishCursor = db.rawQuery(taskQuery4, null);
        taskInfoCursor = db.rawQuery(taskQuery5, null);

        if (taskNameCursor != null && taskNameCursor.getCount() > 0 &&
                taskDayCursor != null && taskDayCursor.getCount() > 0 &&
                taskStartCursor != null && taskStartCursor.getCount() > 0 &&
                taskFinishCursor != null && taskFinishCursor.getCount() > 0 &&
                taskInfoCursor != null && taskInfoCursor.getCount() > 0 ) {
            taskNameCursor.moveToFirst();
            taskDayCursor.moveToFirst();
            taskStartCursor.moveToFirst();
            taskFinishCursor.moveToFirst();
            taskInfoCursor.moveToFirst();
            do {
                fetchedTaskName = taskNameCursor.getString(0);
                taskArray.add(fetchedTaskName);

                fetchedTaskDay = taskDayCursor.getString(0);
                taskArray.add(fetchedTaskDay);

                fetchedTaskStart = taskStartCursor.getInt(0);
                fetchedTaskStartConversion = fetchedTaskStart.toString();
                taskArray.add(fetchedTaskStartConversion);

                fetchedTaskFinish = taskFinishCursor.getInt(0);
                fetchedTaskFinishConversion = fetchedTaskFinish.toString();
                taskArray.add(fetchedTaskFinishConversion);

                fetchedTaskInfo = taskInfoCursor.getString(0);
                taskArray.add(fetchedTaskInfo);

            } while (taskNameCursor.moveToNext() &&
                    taskDayCursor.moveToNext() &&
                    taskStartCursor.moveToNext() &&
                    taskFinishCursor.moveToNext() &&
                    taskInfoCursor.moveToNext());
        }
        return taskArray;
    }

    public boolean checkLoginCredentials(String username, String password){
        db = this.getReadableDatabase();
        usernameCheckQuery = "SELECT * FROM " +TABLE_USER+ " WHERE " +USER_COL2+ " = '" +username+ "' AND " +USER_COL3+ " = '" +password+"';";
        usernameCheckCursor = db.rawQuery(usernameCheckQuery, null);
        if(usernameCheckCursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

}

