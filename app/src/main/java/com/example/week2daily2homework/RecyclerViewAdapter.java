package com.example.week2daily2homework;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<Students> studentsArrayList;

    public RecyclerViewAdapter(ArrayList<Students> studentsArrayList) {
        this.studentsArrayList = studentsArrayList;
    }

    //Inflate - Take xml and bind it to context and then inflate view to memory
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    //Each item is created
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        Students students = studentsArrayList.get(position);

        if(students != null) {
            String name = students.getName();
            String major = students.getMajor();
            String minor = students.getMinor();
            String gpa = String.valueOf(students.getGpa());
            String dob = students.getDob();
            String city = students.getHomeCity();
            String state = students.getHomeState();
            String ssn = String.valueOf(students.getSsn());

            viewHolder.tvName.setText(name);
            viewHolder.tvMajor.setText(major);
            viewHolder.tvMinor.setText(minor);
            viewHolder.tvGpa.setText(gpa);
            viewHolder.tvDob.setText(dob);
            viewHolder.tvCity.setText(city);
            viewHolder.tvState.setText(state);
            viewHolder.tvSsn.setText(ssn);
        }
    }

    public void addStudent(Students students){
        studentsArrayList.add(students);
        notifyDataSetChanged();
    }

    public void removeStudent(Students students){
        studentsArrayList.remove(students);
        notifyDataSetChanged();
    }


    //number of items in list
    @Override
    public int getItemCount() {
        return studentsArrayList != null ? studentsArrayList.size() : 0;
    }

    //creating our viewHolder - declare and bind views
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgImage;
        TextView tvName;
        TextView tvMajor;
        TextView tvMinor;
        TextView tvGpa;
        TextView tvDob;
        TextView tvCity;
        TextView tvState;
        TextView tvSsn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.imgViewImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvMajor = itemView.findViewById(R.id.tvMajor);
            tvMinor = itemView.findViewById(R.id.tvMinor);
            tvGpa = itemView.findViewById(R.id.tvGpa);
            tvDob = itemView.findViewById(R.id.tvDob);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvState = itemView.findViewById(R.id.tvState);
            tvSsn = itemView.findViewById(R.id.tvSsn);
        }
    }
}
