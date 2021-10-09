package com.example.TeaPlucker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class AddSuplierActivity extends AppCompatActivity {
    EditText Et_id, Et_name;
    Button Btn_Add, Btn_back;
    String String_id, String_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_suplier);

        Et_id = findViewById(R.id.Et_Id);
        Et_name = findViewById(R.id.Et_name);
        Btn_Add = findViewById(R.id.Brn_Add_Supplier);
        Btn_back = findViewById(R.id.Btn_back);

        Btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String_id = Et_id.getText().toString();
                String_name = Et_name.getText().toString();
                try {
                    if (!Et_id.getText().toString().isEmpty() && !Et_name.getText().toString().isEmpty()) {
                        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(AddSuplierActivity.this);
                        SupplierModalClass supplierModalClass = new SupplierModalClass(String_id, String_name);
                        databaseHelperClass.addSuppliers(supplierModalClass);
                        Toast.makeText(AddSuplierActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(AddSuplierActivity.this, MainActivity.class);
                        AddSuplierActivity.this.startActivity(myIntent);
                    } else {
                        Toast.makeText(AddSuplierActivity.this, "Already Exists !!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AddSuplierActivity.this, "Already Exists !!!", Toast.LENGTH_SHORT).show();
                }



            }
        });

        Btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(AddSuplierActivity.this, MainActivity.class);
                AddSuplierActivity.this.startActivity(myIntent);
            }
        });
    }



    public void noData() {
        Toast.makeText(AddSuplierActivity.this, "Already Exists !!!", Toast.LENGTH_SHORT).show();
    }
}