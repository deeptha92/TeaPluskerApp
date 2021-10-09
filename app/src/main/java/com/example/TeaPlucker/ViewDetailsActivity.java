package com.example.TeaPlucker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class ViewDetailsActivity extends AppCompatActivity {
    RecyclerView viewDetailsRecyclerview;
    LottieAnimationView lav_not_found;
    Button BtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        viewDetailsRecyclerview = findViewById(R.id.viewDetailsRecyclerview);
        lav_not_found = (LottieAnimationView) findViewById(R.id.lav_not_found);
        BtnBack = (Button) findViewById(R.id.BtnBack);
        viewDetailsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        viewDetailsRecyclerview.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<EmployeeModalClass> employeeModalClassList = databaseHelperClass.getEmployeeList();

        if (employeeModalClassList.size() > 0) {
            EmployeeAdapterClass employeeAdapterClass = new EmployeeAdapterClass(employeeModalClassList, ViewDetailsActivity.this);
            viewDetailsRecyclerview.setAdapter(employeeAdapterClass);
            lav_not_found.setVisibility(View.GONE);
        } else {
            lav_not_found.setVisibility(View.VISIBLE);
            Toast.makeText(ViewDetailsActivity.this, "No details to view", Toast.LENGTH_SHORT).show();

        }

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ViewDetailsActivity.this, MainActivity.class);
                ViewDetailsActivity.this.startActivity(myIntent);
            }
        });

    }
}