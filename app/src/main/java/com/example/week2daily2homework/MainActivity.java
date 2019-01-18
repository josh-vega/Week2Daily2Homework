package com.example.week2daily2homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="tag_act_one";
    MySqlDatabaseHelper mySqlDatabaseHelper;
    EditText etName, etMajor, etMinor, etGpa, etDob, etCity, etState, etSsn;
    String name, major, minor, gpa, dob, city, state, ssn;
    Students student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySqlDatabaseHelper = new MySqlDatabaseHelper(this);
        etName = findViewById(R.id.etName);
        etMajor = findViewById(R.id.etMajor);
        etMinor = findViewById(R.id.etMinor);
        etGpa = findViewById(R.id.etGpa);
        etDob = findViewById(R.id.etDob);
        etCity = findViewById(R.id.etHomeCity);
        etState = findViewById(R.id.etHomeState);
        etSsn = findViewById(R.id.etSsn);

    }

    public void onClick(View view){
        name = etName.getText().toString();
        major = etMajor.getText().toString();
        minor = etMinor.getText().toString();
        gpa = etGpa.getText().toString();
        dob = etDob.getText().toString();
        city = etCity.getText().toString();
        state = etState.getText().toString();
        ssn = etSsn.getText().toString();

        switch (view.getId()){
            case R.id.btnNext:
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            case R.id.btnInsert:
                if(name != null && major != null && minor != null && gpa != null && dob != null && city != null && state != null && ssn != null){
                    Students students = new Students(name, major, minor, Double.parseDouble(gpa), dob, city, state, Integer.parseInt(ssn));
                    mySqlDatabaseHelper.insertStudent(students);
                    Toast.makeText(this, students.getName() + " has been inserted.", Toast.LENGTH_SHORT);
                }
                break;
            case R.id.btnUpdate:
                if(student != null){
                    student.setName(etName.getText().toString());
                    student.setMajor(etMajor.getText().toString());
                    student.setMinor(etMinor.getText().toString());
                    student.setGpa(Double.parseDouble(etGpa.getText().toString()));
                    student.setDob(etDob.getText().toString());
                    student.setHomeCity(etCity.getText().toString());
                    student.setHomeState(etState.getText().toString());
                    mySqlDatabaseHelper.updatePerson(student);
                }
                break;
            case R.id.btnDelete:
                if(ssn != null){
                    mySqlDatabaseHelper.deleteStudent(ssn);
                    Toast.makeText(this, "Delete operation carried out", Toast.LENGTH_SHORT);
                }
                break;
            case R.id.btnGet:
                if(ssn != null){
                    student = mySqlDatabaseHelper.getStudent(ssn);
                    etName.setText(student.getName());
                    etMajor.setText(student.getMajor());
                    etMinor.setText(student.getMinor());
                    etGpa.setText(String.valueOf(student.getGpa()));
                    etDob.setText(student.getDob());
                    etCity.setText(student.getHomeCity());
                    etState.setText(student.getHomeState());
                    etSsn.setText(String.valueOf(student.getSsn()));
                    Toast.makeText(this, student.getName() + " has been retrieved.", Toast.LENGTH_SHORT);
                }
                break;
            default:
                break;
        }
    }
}
