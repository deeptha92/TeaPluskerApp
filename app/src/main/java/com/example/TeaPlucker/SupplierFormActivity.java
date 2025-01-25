package com.example.TeaPlucker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.Helper.DatabaseHelperClass;
import com.example.Modal.EmployeeModalClass;
import com.example.Modal.SupplierModalClass;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import static com.example.TeaPlucker.MainActivity.TAG;

public class SupplierFormActivity extends AppCompatActivity {

    LinearLayout LLGreenTea, LLAdition, LLOther_deduction, LLKOK_deduction,
            LLMadeTea_deduction, LLManure_deduction, LLTransport_deduction,
            LLWelfare_deduction, LLCashAdvance_deduction, LLTotalDeduction, LLTotalEarnings, LLLabelEarning, LLLbl_Deduction;
    Button BtnExpand, BtnExpandDeduction, Btnadd, BtnBack, button_update_data;
    NestedScrollView nestedScrollView;
    LottieAnimationView celebrate, loading;
    String String_id, String_name;
    EditText et_quantity_gt, et_quantity_ae, et_quantity_ca_deduction,
            et_quantity_wf_deduction, et_quantity_tp_deduction,
            et_quantity_mr_deduction, et_quantity_mt_deduction,
            et_quantity_kok_deduction, et_quantity_ot_deduction, et_quantity_water, et_quantity_total_tea;
    CardView CvcardView, cvLoading, cvCelebrate;
    String suppler_name, quantity_gt, quantity_water, quantity_total_tea, quantity_ae, quantity_ca_deduction,
            quantity_wf_deduction, quantity_mt_deduction,
            quantity_mr_deduction, quantity_tp_deduction,
            quantity_kok_deduction, quantity_ot_deduction;

    TextView spinner_supplier;
    TextView tv_additional_earnings, TvSuppName, TvDate, TvTrId;
    List<String> supplier_names = new ArrayList<String>();
    List<String> supplier_id = new ArrayList<String>();
    private Button showDatePickerButton;
    Dialog dialog;
    boolean sameNameOrNot = false;
    String supplyDate;
    private int supplier_position = -1;
    private int incrementedCount = 0;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_form);

        LLGreenTea = findViewById(R.id.LLGreentea);
        LLAdition = findViewById(R.id.LLAditional);

        LLLabelEarning = findViewById(R.id.LLLabelEarning);
        LLLbl_Deduction = findViewById(R.id.LLLbl_Deduction);

        BtnBack = findViewById(R.id.button_back);
        button_update_data = findViewById(R.id.button_update_data);

        LLCashAdvance_deduction = findViewById(R.id.LLCashAdvance_deduction);
        LLWelfare_deduction = findViewById(R.id.LLWelfare_deduction);
        LLTransport_deduction = findViewById(R.id.LLTransport_deduction);
        LLManure_deduction = findViewById(R.id.LLManure_deduction);
        LLMadeTea_deduction = findViewById(R.id.LLMadeTea_deduction);
        LLKOK_deduction = findViewById(R.id.LLKOK_deduction);
        LLOther_deduction = findViewById(R.id.LLOther_deduction);

        et_quantity_gt = findViewById(R.id.et_quantity_gt);
        et_quantity_ae = findViewById(R.id.et_quantity_ae);
        et_quantity_water = findViewById(R.id.et_quantity_water);
        et_quantity_total_tea = findViewById(R.id.et_quantity_total_tea);

        et_quantity_ca_deduction = findViewById(R.id.et_quantity_ca_deduction);
        et_quantity_wf_deduction = findViewById(R.id.et_quantity_wf_deduction);
        et_quantity_mt_deduction = findViewById(R.id.et_quantity_mt_deduction);
        et_quantity_mr_deduction = findViewById(R.id.et_quantity_mr_deduction);
        et_quantity_tp_deduction = findViewById(R.id.et_quantity_tp_deduction);
        et_quantity_kok_deduction = findViewById(R.id.et_quantity_kok_deduction);
        et_quantity_ot_deduction = findViewById(R.id.et_quantity_ot_deduction);

        tv_additional_earnings = findViewById(R.id.tv_additional_earnings);
        TvTrId = findViewById(R.id.TvTrId);


        Btnadd = findViewById(R.id.button_add_data);


        showDatePickerButton = findViewById(R.id.showDatePickerButton);
        TvDate = (TextView) findViewById(R.id.TvDate);


        BtnExpand = findViewById(R.id.btn_more);
        CvcardView = findViewById(R.id.cardView);
        BtnExpandDeduction = findViewById(R.id.btn_more_deduction);

        celebrate = findViewById(R.id.celebrate);
        loading = findViewById(R.id.lottieLoading);

        LLTotalDeduction = findViewById(R.id.LLTotalDeduction);
        LLTotalEarnings = findViewById(R.id.LLTotalEarning);

        TvSuppName = findViewById(R.id.TvSuppName);

        spinner_supplier = (TextView) findViewById(R.id.spinner_supplier);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        et_quantity_tp_deduction.setEnabled(false);
        tv_additional_earnings.setSelected(true);
        loading.setVisibility(View.GONE);



        et_quantity_gt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that characters within `start` and `start + before` are about to be replaced with new text with a length of `count`.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that somewhere within `start` and `start + before`, the text has been replaced with new text with a length of `count`.
                int tea_qua;
                int water_qua;
                try {
                    if (et_quantity_gt.getText().toString().isEmpty()) {
                        tea_qua = 0;
                    } else {
                        tea_qua = (int) Double.parseDouble(et_quantity_gt.getText().toString());
                    }
                    if (et_quantity_water.getText().toString().isEmpty()) {
                        water_qua = 0;
                    } else {
                        water_qua = (int) Double.parseDouble(et_quantity_water.getText().toString());
                    }
                    int totalTea = tea_qua - water_qua;
                    et_quantity_total_tea.setText(String.valueOf(totalTea));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called to notify you that somewhere within the `Editable`, new text has been inserted or removed.
                int tea_qua;
                int water_qua;
                try {
                    if (et_quantity_gt.getText().toString().isEmpty()) {
                        tea_qua = 0;
                    } else {
                        tea_qua = (int) Double.parseDouble(et_quantity_gt.getText().toString());
                    }
                    if (et_quantity_water.getText().toString().isEmpty()) {
                        water_qua = 0;
                    } else {
                        water_qua = (int) Double.parseDouble(et_quantity_water.getText().toString());
                    }
                    int totalTea = tea_qua - water_qua;
                    et_quantity_total_tea.setText(String.valueOf(totalTea));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        et_quantity_water.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that characters within `start` and `start + before` are about to be replaced with new text with a length of `count`.
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // This method is called to notify you that somewhere within `start` and `start + before`, the text has been replaced with new text with a length of `count`.
                int tea_qua;
                int water_qua;
                try {
                    if (et_quantity_gt.getText().toString().isEmpty()) {
                        tea_qua = 0;
                    } else {
                        tea_qua = (int) Double.parseDouble(et_quantity_gt.getText().toString());
                    }
                    if (et_quantity_water.getText().toString().isEmpty()) {
                        water_qua = 0;
                    } else {
                        water_qua = (int) Double.parseDouble(et_quantity_water.getText().toString());
                    }
                    int totalTea = tea_qua - water_qua;
                    et_quantity_total_tea.setText(String.valueOf(totalTea));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called to notify you that somewhere within the `Editable`, new text has been inserted or removed.
                int tea_qua;
                int water_qua;
                try {
                    if (et_quantity_gt.getText().toString().isEmpty()) {
                        tea_qua = 0;
                    } else {
                        tea_qua = (int) Double.parseDouble(et_quantity_gt.getText().toString());
                    }
                    if (et_quantity_water.getText().toString().isEmpty()) {
                        water_qua = 0;
                    } else {
                        water_qua = (int) Double.parseDouble(et_quantity_water.getText().toString());
                    }
                    int totalTea = tea_qua - water_qua;
                    et_quantity_total_tea.setText(String.valueOf(totalTea));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });



        Date currentDate = new Date();

        // Define a date format for year, month, and day
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the date and set it to the TextView
        String formattedDate = dateFormat.format(currentDate);
        TvDate.setText(formattedDate);

        DatabaseHelperClass dbHelper = new DatabaseHelperClass(SupplierFormActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String tableNameToCheck = "SUPPLIER_NAMES";

        boolean tableExists = isTableExists(db, tableNameToCheck);

        if (tableExists) {
            // The table exists
            gettransactionNo();
        } else {
            // The table does not exist
            incrementedCount = 1;
        }



        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);
//local storage deeptha

        String json = sh.getString("myModel", "");

        if (!json.isEmpty()) {

            spinner_supplier.setVisibility(View.GONE);
            TvSuppName.setVisibility(View.VISIBLE);
            Btnadd.setVisibility(View.GONE);
            button_update_data.setVisibility(View.VISIBLE);
            showDatePickerButton.setVisibility(View.GONE);
            // Convert the JSON string back to a MyModel object
            Gson gson = new Gson();
            EmployeeModalClass myModel = gson.fromJson(json, EmployeeModalClass.class);

            Toast.makeText(SupplierFormActivity.this, "", Toast.LENGTH_SHORT).show();

            TvSuppName.setText(myModel.getSupplier_name() + " - " + myModel.getSupplier_id());
            suppler_name = myModel.getSupplier_name() + " - " + myModel.getSupplier_id();
            TvTrId.setText(myModel.getTrn_id());

            et_quantity_gt.setText(myModel.getGreentea_qua());
            et_quantity_water.setText(myModel.getWater_qua());
            et_quantity_total_tea.setText(myModel.getTotal_tea_qua());
            et_quantity_ae.setText(myModel.getAdditional_qua());
            et_quantity_ca_deduction.setText(myModel.getCash_qua());
            et_quantity_wf_deduction.setText(myModel.getWelfare_qua());
            et_quantity_mt_deduction.setText(myModel.getMt_qua());
            et_quantity_mr_deduction.setText(myModel.getManure_qua());
            et_quantity_tp_deduction.setText(myModel.getTransport_qua());
            et_quantity_kok_deduction.setText(myModel.getKok_qua());
            et_quantity_ot_deduction.setText(myModel.getOther_qua());

            TvDate.setText(myModel.getSupp_date());

        } else {
            TvTrId.setText(incrementedCount+"");
            spinner_supplier.setVisibility(View.VISIBLE);
            TvSuppName.setVisibility(View.GONE);
            Btnadd.setVisibility(View.VISIBLE);
            button_update_data.setVisibility(View.GONE);
            quantity_gt = "0";
            quantity_ae = "0";
            quantity_ca_deduction = "0";
            quantity_wf_deduction = "0";
            quantity_tp_deduction = "0";
            quantity_mr_deduction = "0";
            quantity_mt_deduction = "0";
            quantity_kok_deduction = "0";
            quantity_ot_deduction = "0";

        }

        String[] lines = FileReader.readTextFile(this, R.raw.suppliers);

        // Now 'lines' contains each line from the existing text file as a separate element
        // You can use it as needed, for example, print it to the console
        supplier_names.addAll(Arrays.asList(lines));

        spinner_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(SupplierFormActivity.this);
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                dialog.getWindow().setLayout(850, 1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.EtSearching);
                ListView listView = dialog.findViewById(R.id.LvItems);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(SupplierFormActivity.this, R.layout.support_simple_spinner_dropdown_item, supplier_names);
                listView.setAdapter(arrayAdapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        arrayAdapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(SupplierFormActivity.this, "Position "+position, Toast.LENGTH_SHORT).show();
                        spinner_supplier.setText(arrayAdapter.getItem(position));
                        suppler_name = arrayAdapter.getItem(position);
                        supplier_position = position;
                        dialog.dismiss();
                    }
                });
            }
        });

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(SupplierFormActivity.this);
        SupplierModalClass supplierModalClass = new SupplierModalClass();

        for (String name_sup : supplier_names
        ) {
            String_id = name_sup;
            String_name = name_sup;
            try {


                supplierModalClass.setSupplier_id(String_id);
                supplierModalClass.setSupplier_name(String_name);

//                Toast.makeText(SupplierFormActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
//                Intent myIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
//                SupplierFormActivity.this.startActivity(myIntent);
            } catch (Exception e) {
                e.printStackTrace();
//                Toast.makeText(SupplierFormActivity.this, "Already Exists !!!", Toast.LENGTH_SHORT).show();
            }
        }
        databaseHelperClass.addSuppliers(supplierModalClass);

//        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, supplier_names);
//        autoCompleteTextView.setAdapter(autoCompleteAdapter);
//
//        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, supplier_names);
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_supplier.setAdapter(spinnerAdapter);


        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
                SupplierFormActivity.this.startActivity(myIntent);
            }
        });

        button_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = SupplierFormActivity.this.getSharedPreferences("MySharedPref_update", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("updateClick", "1");
                editor.apply();


                loading.setVisibility(View.VISIBLE);
                loading.playAnimation();
                String trans_id = TvTrId.getText().toString();
                quantity_gt = et_quantity_gt.getText().toString();
                quantity_water = et_quantity_water.getText().toString();
                quantity_total_tea = et_quantity_total_tea.getText().toString();
                quantity_gt = et_quantity_gt.getText().toString();
                quantity_ae = et_quantity_ae.getText().toString();
                quantity_ca_deduction = et_quantity_ca_deduction.getText().toString();
                quantity_wf_deduction = et_quantity_wf_deduction.getText().toString();
                quantity_tp_deduction = et_quantity_tp_deduction.getText().toString();
                quantity_mr_deduction = et_quantity_mr_deduction.getText().toString();
                quantity_mt_deduction = et_quantity_mt_deduction.getText().toString();
                quantity_kok_deduction = et_quantity_kok_deduction.getText().toString();
                quantity_ot_deduction = et_quantity_ot_deduction.getText().toString();
                supplyDate = TvDate.getText().toString();

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        final Handler handler2 = new Handler(Looper.getMainLooper());
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(SupplierFormActivity.this);
                                databaseHelperClass.updateRecord("trn_id", trans_id, quantity_gt, quantity_water, quantity_total_tea,
                                        quantity_ae,
                                        quantity_ca_deduction,
                                        quantity_wf_deduction,
                                        quantity_tp_deduction,
                                        quantity_mr_deduction,
                                        quantity_mt_deduction,
                                        quantity_kok_deduction,
                                        quantity_ot_deduction);
                                //databaseHelperClass.addEmployee(employeeModalClass);

//                                    if (sameNameOrNot == true) {
//                                        Intent mainActivityIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
//                                        startActivity(mainActivityIntent);
//                                    }
//                                    Intent mainActivityIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
//                                    startActivity(mainActivityIntent);
                                celebrate.pauseAnimation();
                                celebrate.setVisibility(View.GONE);

                                Intent mainActivityIntent = new Intent(SupplierFormActivity.this, PrintBillActivity.class);
                                startActivity(mainActivityIntent);
                                Toast.makeText(SupplierFormActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                                nestedScrollView.setVisibility(View.VISIBLE);

                                loading.setVisibility(View.GONE);
                                loading.pauseAnimation();
                                Btnadd.setVisibility(View.VISIBLE);

                            }
                        }, 2000);
                    }

                }, 2000);


            }
        });


        Btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences("MySharedPref_update", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Btnadd.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                loading.playAnimation();
                if (supplier_position >= 0 || !TvSuppName.getText().toString().isEmpty()) {
                    String supplier_Name;
                    String nameId = suppler_name;

                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(SupplierFormActivity.this);
                    List<EmployeeModalClass> employeeModalClassList = databaseHelperClass.getEmployeeList();

//                    if (employeeModalClassList.size() >= 1) {
//
//                        String[] separated = nameId.split(" - ");
//                        String name = separated[1];
//                        int statusDelete = 0;
//
//                        for (EmployeeModalClass employeeModalClass1 : employeeModalClassList
//                        ) {
//                            supplier_Name = employeeModalClass1.getSupplier_name();
//                            Log.d(TAG, "onClick: " + supplier_Name);
//                            Log.d(TAG, "onClick2: " + name);
//
//                            if ((name.contains(supplier_Name)) && statusDelete == 0) {
//                                sameNameOrNot = true;
//                                statusDelete = 1;
//                                databaseHelperClass.deleteSupplier(employeeModalClass1.getSupplier_name());
//
//                            } else {
//                                sameNameOrNot = false;
//                            }
//                        }
//                    } else {
//                        sameNameOrNot = false;
//                    }

                    //nestedScrollView.setVisibility(View.GONE);

                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms

                            quantity_gt = et_quantity_gt.getText().toString();
                            quantity_water = et_quantity_water.getText().toString();
                            quantity_total_tea = et_quantity_total_tea.getText().toString();
                            quantity_gt = et_quantity_gt.getText().toString();
                            quantity_ae = et_quantity_ae.getText().toString();
                            quantity_ca_deduction = et_quantity_ca_deduction.getText().toString();
                            quantity_wf_deduction = et_quantity_wf_deduction.getText().toString();
                            quantity_tp_deduction = et_quantity_tp_deduction.getText().toString();
                            quantity_mr_deduction = et_quantity_mr_deduction.getText().toString();
                            quantity_mt_deduction = et_quantity_mt_deduction.getText().toString();
                            quantity_kok_deduction = et_quantity_kok_deduction.getText().toString();
                            quantity_ot_deduction = et_quantity_ot_deduction.getText().toString();
                            supplyDate = TvDate.getText().toString();

//                            if (up_ot.length() <= 0 || up_tp.length() <= 0) {
//                                Toast.makeText(SupplierFormActivity.this, "Green tea price or transport price empty", Toast.LENGTH_SHORT).show();
//                            } else {
                            final Handler handler2 = new Handler(Looper.getMainLooper());
                            handler2.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Do something after 100ms
                                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(SupplierFormActivity.this);
                                    EmployeeModalClass employeeModalClass = new EmployeeModalClass();

                                    String[] separated = nameId.split(" - ");
                                    String name = separated[1];
                                    String id = separated[0];

                                    employeeModalClass.setSupplier_name(name);
                                    employeeModalClass.setSupplier_id(id);
                                    employeeModalClass.setGreentea_qua(quantity_gt);
                                    employeeModalClass.setWater_qua(quantity_water);
                                    employeeModalClass.setTotal_tea_qua(quantity_total_tea);
                                    employeeModalClass.setAdditional_qua(quantity_ae);
                                    employeeModalClass.setCash_qua(quantity_ca_deduction);
                                    employeeModalClass.setWelfare_qua(quantity_wf_deduction);
                                    employeeModalClass.setTransport_qua(quantity_tp_deduction);
                                    employeeModalClass.setManure_qua(quantity_mr_deduction);
                                    employeeModalClass.setMt_qua(quantity_mt_deduction);
                                    employeeModalClass.setKok_qua(quantity_kok_deduction);
                                    employeeModalClass.setOther_qua(quantity_ot_deduction);
                                    employeeModalClass.setSupp_date(supplyDate);
                                    employeeModalClass.setTrn_id(String.valueOf("TRN"+incrementedCount));

                                    databaseHelperClass.addEmployee(employeeModalClass);

//                                    if (sameNameOrNot == true) {
//                                        Intent mainActivityIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
//                                        startActivity(mainActivityIntent);
//                                    }
//                                    Intent mainActivityIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
//                                    startActivity(mainActivityIntent);
                                    celebrate.pauseAnimation();
                                    celebrate.setVisibility(View.GONE);

                                    Intent mainActivityIntent = new Intent(SupplierFormActivity.this, PrintBillActivity.class);
                                    startActivity(mainActivityIntent);
                                    Toast.makeText(SupplierFormActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                                    nestedScrollView.setVisibility(View.VISIBLE);

                                    et_quantity_gt.setText("");
                                    et_quantity_ae.setText("");
                                    et_quantity_ca_deduction.setText("");
                                    et_quantity_wf_deduction.setText("");
                                    et_quantity_mt_deduction.setText("");
                                    et_quantity_mr_deduction.setText("");
                                    et_quantity_tp_deduction.setText("");
                                    et_quantity_kok_deduction.setText("");
                                    et_quantity_ot_deduction.setText("");
                                    loading.setVisibility(View.GONE);
                                    loading.pauseAnimation();
                                    Btnadd.setVisibility(View.VISIBLE);

                                }
                            }, 2000);
                        }

                    }, 2000);


                } else {
                    loading.setVisibility(View.GONE);
                    loading.pauseAnimation();
                    Btnadd.setVisibility(View.VISIBLE);
                    Toast.makeText(SupplierFormActivity.this,"Please select supplier first", Toast.LENGTH_SHORT).show();
                }
            }
        });


        showDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery(
                "PRAGMA table_info(" + tableName + ")",
                null
        );

        if (cursor != null) {
            cursor.close();
            return true; // Table exists
        } else {
            return false; // Table does not exist
        }
    }

    private void gettransactionNo() {
        SQLiteDatabase db = openOrCreateDatabase("Final_Tea", Context.MODE_PRIVATE, null);

// SQL query to count the records in the table
        //String countQuery = "SELECT COUNT(*) FROM SUPPLIER";
        String countQuery = "SELECT id FROM SUPPLIER ORDER BY id DESC LIMIT 1";

// Execute the query and get the result
        Cursor cursor = db.rawQuery(countQuery, null);

        int recordCount = 0;

        if (cursor.moveToFirst()) {
            recordCount = cursor.getInt(0);
        }

// Close the cursor and database
        cursor.close();
        db.close();

// Increment the count by 1
        incrementedCount = recordCount + 1;
        Log.d(TAG, "gettransactionNo: "+incrementedCount);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Handle the selected date here

                        // For demonstration, we'll simply show the selected date in a toast message
                        String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                        Toast.makeText(SupplierFormActivity.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                        supplyDate = selectedDate;
                        TvDate.setText(supplyDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}