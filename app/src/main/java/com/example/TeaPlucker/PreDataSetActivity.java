package com.example.TeaPlucker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Helper.DatabaseHelperClass;
import com.example.Modal.PreDataModalClass;

import androidx.appcompat.app.AppCompatActivity;

public class PreDataSetActivity extends AppCompatActivity {
    EditText Et_id, Et_name;
    public static TextView TvFinaltr, TvFinalGt;
    Button Btn_Add, Btn_back;
    String String_gt_pr, String_tr_pr;
    String greenTeaPrice_pre, transport_up_pre;
    PreDataModalClass preDataModalClass = new PreDataModalClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_data_set);

        Et_id = findViewById(R.id.Et_Id);
        Et_name = findViewById(R.id.Et_name);
        TvFinaltr = findViewById(R.id.TvFinaltr);
        TvFinalGt = findViewById(R.id.TvFinalGt);
        Btn_Add = findViewById(R.id.Brn_Add_Supplier);
        Btn_back = findViewById(R.id.Btn_back);
        try {
            @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
            greenTeaPrice_pre = sh.getString("greetTea_pr_pre", "1");
            transport_up_pre = sh.getString("transport_pr_pre", "1");
            Et_id.setText(greenTeaPrice_pre);
            Et_name.setText(transport_up_pre);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String_gt_pr = Et_id.getText().toString();
                String_tr_pr = Et_name.getText().toString();
                try {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(PreDataSetActivity.this);

                    preDataModalClass.setGreenTeaPrice(String_gt_pr);
                    preDataModalClass.setTransportPrice(String_tr_pr);
                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

// Creating an Editor object to edit(write to the file)
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
                    myEdit.putString("greetTea_pr_pre", String_gt_pr);
                    myEdit.putString("transport_pr_pre", String_tr_pr);

// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
                    myEdit.commit();

                    Toast.makeText(PreDataSetActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(PreDataSetActivity.this, MainActivity.class);
                    PreDataSetActivity.this.startActivity(myIntent);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PreDataSetActivity.this, "Already Exists !!!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        Btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PreDataSetActivity.this, MainActivity.class);
                PreDataSetActivity.this.startActivity(myIntent);
            }
        });
    }


    public void noData() {
        Toast.makeText(PreDataSetActivity.this, "Already Exists !!!", Toast.LENGTH_SHORT).show();
    }
}