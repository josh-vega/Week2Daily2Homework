package com.example.week2daily2homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;
    MySqlDatabaseHelper mySqlDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        recyclerView = findViewById(R.id.rvMainRecyclerView);
        rvAdapter = new RecyclerViewAdapter(listOfStudents());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvAdapter);

    }


    private ArrayList<Students> listOfStudents(){
        ArrayList<Students> studentsArrayList = mySqlDatabaseHelper.getAllStudents();
        Students students = new Students();
        students.setSsn(555555555);
        students.setName("Josh");
        students.setMajor("CS");
        students.setMinor("Religion");
        students.setGpa(4.0);
        students.setDob("12/27/1995");
        students.setHomeCity("LaGrange");
        students.setHomeState("GA");
        studentsArrayList.add(students);
        return studentsArrayList;
    }
}
