package com.example.TeaPlucker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewEmployeeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<EmployeeModalClass> employeeModalClassListl = databaseHelperClass.getEmployeeList();

        if (employeeModalClassListl.size() > 0) {
            EmployeeAdapterClass employeeAdapterClass = new EmployeeAdapterClass(employeeModalClassListl, ViewEmployeeActivity.this);
            recyclerView.setAdapter(employeeAdapterClass);
        } else {
            Toast.makeText(ViewEmployeeActivity.this, "There is no employee to show in the database", Toast.LENGTH_SHORT).show();
        }

    }
}