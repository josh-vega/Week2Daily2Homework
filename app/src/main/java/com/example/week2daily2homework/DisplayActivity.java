package com.example.week2daily2homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter rvAdapter;
    MySqlDatabaseHelper mySqlDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mySqlDatabaseHelper = new MySqlDatabaseHelper(this);
        recyclerView = findViewById(R.id.rvMainRecyclerView);
        rvAdapter = new RecyclerViewAdapter(listOfStudents());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rvAdapter);

    }

    public void onClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private ArrayList<Students> listOfStudents(){
        ArrayList<Students> studentsArrayList = mySqlDatabaseHelper.getAllStudents();
        return studentsArrayList;
    }
}
