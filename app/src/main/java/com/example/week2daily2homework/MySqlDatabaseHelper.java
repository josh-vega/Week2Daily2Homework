package com.example.week2daily2homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.example.week2daily2homework.DatabaseConstants.DATABASE_NAME;
import static com.example.week2daily2homework.DatabaseConstants.DATABASE_VERSION;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_CITY;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_DOB;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_GPA;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_MAJOR;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_MINOR;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_NAME;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_SSN;
import static com.example.week2daily2homework.DatabaseConstants.FIELD_STATE;
import static com.example.week2daily2homework.DatabaseConstants.TABLE_NAME;


public class MySqlDatabaseHelper extends SQLiteOpenHelper {

    public MySqlDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + FIELD_NAME + " TEXT, "
                + FIELD_MAJOR + " TEXT, "
                + FIELD_MINOR + " TEXT, "
                + FIELD_GPA + " TEXT, "
                + FIELD_DOB + " TEXT, "
                + FIELD_CITY + " TEXT, "
                + FIELD_STATE + " TEXT, "
                + FIELD_SSN + " TEXT PRIMARY KEY);";
        db.execSQL(createQuery);

        Students student = new Students("Josh", "Computer Science", "Religion", 3.8, "12/27/1995", "LaGrange", "GA", 555555555);
        ContentValues content = new ContentValues();
        content.put(FIELD_NAME, student.getName());
        content.put(FIELD_MAJOR, student.getMajor());
        content.put(FIELD_MINOR, student.getMinor());
        content.put(FIELD_GPA, student.getGpa());
        content.put(FIELD_DOB, student.getDob());
        content.put(FIELD_CITY, student.getHomeCity());
        content.put(FIELD_STATE, student.getHomeState());
        content.put(FIELD_SSN, student.getSsn());
        db.insert(TABLE_NAME, null, content);

        student = new Students("Michelle", "Psychology", "Communication", 4.0, "10/08/1976", "Manhattan", "NY", 666666666);
        content = new ContentValues();
        content.put(FIELD_NAME, student.getName());
        content.put(FIELD_MAJOR, student.getMajor());
        content.put(FIELD_MINOR, student.getMinor());
        content.put(FIELD_GPA, student.getGpa());
        content.put(FIELD_DOB, student.getDob());
        content.put(FIELD_CITY, student.getHomeCity());
        content.put(FIELD_STATE, student.getHomeState());
        content.put(FIELD_SSN, student.getSsn());
        db.insert(TABLE_NAME, null, content);

        student = new Students("Herman", "Physical Training", "Education", 2.7, "08/15/1968", "Bronx", "NY", 777777777 );
        content = new ContentValues();
        content.put(FIELD_NAME, student.getName());
        content.put(FIELD_MAJOR, student.getMajor());
        content.put(FIELD_MINOR, student.getMinor());
        content.put(FIELD_GPA, student.getGpa());
        content.put(FIELD_DOB, student.getDob());
        content.put(FIELD_CITY, student.getHomeCity());
        content.put(FIELD_STATE, student.getHomeState());
        content.put(FIELD_SSN, student.getSsn());
        db.insert(TABLE_NAME, null, content);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insertStudent(Students student){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(student != null){
            String name = student.getName();
            String major = student.getMajor();
            String minor = student.getMinor();
            String gpa = String.valueOf(student.getGpa());
            String dob = student.getDob();
            String city = student.getHomeCity();
            String state = student.getHomeState();
            String ssn = String.valueOf(student.getSsn());

            contentValues.put(FIELD_NAME, name);
            contentValues.put(FIELD_MAJOR, major);
            contentValues.put(FIELD_MINOR, minor);
            contentValues.put(FIELD_GPA, gpa);
            contentValues.put(FIELD_DOB, dob);
            contentValues.put(FIELD_CITY, city);
            contentValues.put(FIELD_STATE, state);
            contentValues.put(FIELD_SSN, ssn);

            database.insert(TABLE_NAME, null,contentValues);
        }

    }

    public ArrayList<Students> getAllStudents(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query ="SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            ArrayList<Students> arrayList = new ArrayList<>();
            do {
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR));
                Double gpa = Double.parseDouble(cursor.getString(cursor.getColumnIndex(FIELD_GPA)));
                String dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB));
                String city = cursor.getString(cursor.getColumnIndex(FIELD_CITY));
                String state = cursor.getString(cursor.getColumnIndex(FIELD_STATE));
                int ssn = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FIELD_SSN)));
                arrayList.add(new Students(name, major, minor, gpa, dob, city, state, ssn));
            } while(cursor.moveToNext());
            return arrayList;
        } else {
            return null;
        }
    }

    public Students getStudent(String passedSsn){
        Students returnStudent = null;
        if(passedSsn != null && !passedSsn.isEmpty()){
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME
                    + " WHERE " + FIELD_SSN + " = \"" + passedSsn + "\"";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String major = cursor.getString(cursor.getColumnIndex(FIELD_MAJOR));
                String minor = cursor.getString(cursor.getColumnIndex(FIELD_MINOR));
                Double gpa = Double.parseDouble(cursor.getString(cursor.getColumnIndex(FIELD_GPA)));
                String dob = cursor.getString(cursor.getColumnIndex(FIELD_DOB));
                String city = cursor.getString(cursor.getColumnIndex(FIELD_CITY));
                String state = cursor.getString(cursor.getColumnIndex(FIELD_STATE));
                int ssn = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FIELD_SSN)));
                returnStudent = new Students(name, major, minor, gpa, dob, city, state, ssn);
            }
            cursor.close();
        }
        return returnStudent;
    }

    public int deleteStudent(String passedSSN){
        String whereClause = FIELD_SSN + " = \"" + passedSSN + "\"";
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, whereClause, null);
    }

    public int updatePerson(Students passedStudent){
        if(passedStudent != null) {
            String whereClause = FIELD_SSN + " = \"" + passedStudent.getSsn() + "\"";
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(FIELD_NAME, passedStudent.getName());
            contentValues.put(FIELD_MAJOR, passedStudent.getMajor());
            contentValues.put(FIELD_MINOR, passedStudent.getMinor());
            contentValues.put(FIELD_GPA, String.valueOf(passedStudent.getGpa()));
            contentValues.put(FIELD_DOB, passedStudent.getDob());
            contentValues.put(FIELD_CITY, passedStudent.getHomeCity());
            contentValues.put(FIELD_STATE, passedStudent.getHomeState());
            contentValues.put(FIELD_SSN, String.valueOf(passedStudent.getSsn()));
            return sqLiteDatabase.update(TABLE_NAME, contentValues, whereClause, null);
        }
        return 0;
    }

}
