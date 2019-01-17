package com.example.week2daily2homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="tag_act_one";
    RecyclerViewAdapter rvAdapter;
    MySqlDatabaseHelper mySqlDatabaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnNext:
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            case R.id.btnEdit:
                Intent intent2 = new Intent(this, EditActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnPopulate:
                mySqlDatabaseHelper = new MySqlDatabaseHelper(this);
                Students student1 = new Students("Josh", "Computer Science", "Religion", 4.0, "12/27/1995", "LaGrange", "GA", 555555555);
                mySqlDatabaseHelper.insertStudent(student1);
                break;

            default:
                break;
        }
    }
}
