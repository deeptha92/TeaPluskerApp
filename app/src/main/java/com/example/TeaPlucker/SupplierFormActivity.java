package com.example.TeaPlucker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.Helper.DatabaseHelperClass;
import com.example.Modal.EmployeeModalClass;
import com.example.Modal.SupplierModalClass;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SupplierFormActivity extends AppCompatActivity {

    LinearLayout LLGreenTea, LLAdition, LLOther_deduction, LLKOK_deduction,
            LLMadeTea_deduction, LLManure_deduction, LLTransport_deduction,
            LLWelfare_deduction, LLCashAdvance_deduction, LLTotalDeduction, LLTotalEarnings, LLLabelEarning, LLLbl_Deduction;
    Button BtnExpand, BtnExpandDeduction, Btnadd, BtnBack;
    NestedScrollView nestedScrollView;
    LottieAnimationView celebrate, loading;
    String String_id, String_name;
    EditText et_quantity_gt, et_price_gt, et_quantity_ae, et_price_ae, et_quantity_ca_deduction,
            et_price_ca_deduction, et_quantity_wf_deduction, et_price_wf_deduction, et_quantity_tp_deduction,
            et_price_tp_deduction, et_quantity_mr_deduction, et_price_mr_deduction, et_quantity_mt_deduction,
            et_price_mt_deduction, et_quantity_kok_deduction, et_price_kok_deduction, et_quantity_ot_deduction,
            et_price_ot_deduction, et_unitPrice_gt, et_unitPrice_ae, et_unitPrice_ca_deduction, et_unitPrice_wf_deduction,
            et_unitPrice_tp_deduction, et_unitPrice_mr_deduction, et_unitPrice_mt_deduction, et_unitPrice_kok_deduction,
            et_unitPrice_ot_deduction, et_total_earnings, et_total_deduction, et_total_sum;
    CardView CvcardView, cvLoading, cvCelebrate;
    String suppler_name, quantity_gt, price_gt, quantity_ae, price_ae, quantity_ca_deduction, price_ca_deduction,
            quantity_wf_deduction, price_wf_deduction, quantity_mt_deduction, price_mt_deduction,
            quantity_mr_deduction, price_mr_deduction, quantity_tp_deduction, price_tp_deduction,
            quantity_kok_deduction, price_kok_deduction, quantity_ot_deduction, price_ot_deduction;

    String up_gt, up_ae, up_ca, up_wf, up_mt, up_mr, up_tp, up_kok, up_ot;

    String greenTeaPrice_pre, transport_up_pre;

    Spinner spinner_supplier;
    TextView tv_additional_earnings;
    List<String> supplier_names = new ArrayList<String>();
    List<String> supplier_id = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;

    double grandTotalDeduction = 0.0;

    double CaTotalDeduction = 0.0;
    double WfTotalDeduction = 0.0;
    double MtTotalDeduction = 0.0;
    double TpTotalDeduction = 0.0;
    double MrTotalDeduction = 0.0;
    double KokTotalDeduction = 0.0;
    double OtTotalDeduction = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_form);

        LLGreenTea = findViewById(R.id.LLGreentea);
        LLAdition = findViewById(R.id.LLAditional);

        LLLabelEarning = findViewById(R.id.LLLabelEarning);
        LLLbl_Deduction = findViewById(R.id.LLLbl_Deduction);

        BtnBack = findViewById(R.id.button_back);

        LLCashAdvance_deduction = findViewById(R.id.LLCashAdvance_deduction);
        LLWelfare_deduction = findViewById(R.id.LLWelfare_deduction);
        LLTransport_deduction = findViewById(R.id.LLTransport_deduction);
        LLManure_deduction = findViewById(R.id.LLManure_deduction);
        LLMadeTea_deduction = findViewById(R.id.LLMadeTea_deduction);
        LLKOK_deduction = findViewById(R.id.LLKOK_deduction);
        LLOther_deduction = findViewById(R.id.LLOther_deduction);

        et_quantity_gt = findViewById(R.id.et_quantity_gt);
        et_price_gt = findViewById(R.id.et_price_gt);
        et_quantity_ae = findViewById(R.id.et_quantity_ae);
        et_price_ae = findViewById(R.id.et_price_ae);
        et_quantity_ca_deduction = findViewById(R.id.et_quantity_ca_deduction);
        et_price_ca_deduction = findViewById(R.id.et_price_ca_deduction);
        et_quantity_wf_deduction = findViewById(R.id.et_quantity_wf_deduction);
        et_price_wf_deduction = findViewById(R.id.et_price_wf_deduction);
        et_quantity_mt_deduction = findViewById(R.id.et_quantity_mt_deduction);
        et_price_mt_deduction = findViewById(R.id.et_price_mt_deduction);
        et_quantity_mr_deduction = findViewById(R.id.et_quantity_mr_deduction);
        et_price_mr_deduction = findViewById(R.id.et_price_mr_deduction);
        et_quantity_tp_deduction = findViewById(R.id.et_quantity_tp_deduction);
        et_price_tp_deduction = findViewById(R.id.et_price_tp_deduction);
        et_quantity_kok_deduction = findViewById(R.id.et_quantity_kok_deduction);
        et_price_kok_deduction = findViewById(R.id.et_price_kok_deduction);
        et_quantity_ot_deduction = findViewById(R.id.et_quantity_ot_deduction);
        et_price_ot_deduction = findViewById(R.id.et_price_ot_deduction);

        et_unitPrice_gt = findViewById(R.id.et_unitPrice_gt);
        et_unitPrice_ae = findViewById(R.id.et_unitPrice_ae);
        et_unitPrice_ca_deduction = findViewById(R.id.et_unitPrice_ca_deduction);
        et_unitPrice_wf_deduction = findViewById(R.id.et_unitPrice_wf_deduction);
        et_unitPrice_tp_deduction = findViewById(R.id.et_unitPrice_tp_deduction);
        et_unitPrice_mr_deduction = findViewById(R.id.et_unitPrice_mr_deduction);
        et_unitPrice_mt_deduction = findViewById(R.id.et_unitPrice_mt_deduction);
        et_unitPrice_kok_deduction = findViewById(R.id.et_unitPrice_kok_deduction);
        et_unitPrice_ot_deduction = findViewById(R.id.et_unitPrice_ot_deduction);

        et_total_earnings = findViewById(R.id.et_total_earnings);
        et_total_deduction = findViewById(R.id.et_total_deduction);
        et_total_sum = findViewById(R.id.et_total_sum);
        tv_additional_earnings = findViewById(R.id.tv_additional_earnings);


        Btnadd = findViewById(R.id.button_add_data);


        BtnExpand = findViewById(R.id.btn_more);
        CvcardView = findViewById(R.id.cardView);
        BtnExpandDeduction = findViewById(R.id.btn_more_deduction);

        celebrate = findViewById(R.id.celebrate);
        loading = findViewById(R.id.loading);

        LLTotalDeduction = findViewById(R.id.LLTotalDeduction);
        LLTotalEarnings = findViewById(R.id.LLTotalEarning);

        spinner_supplier = (Spinner) findViewById(R.id.spinner_supplier);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        et_unitPrice_gt.setEnabled(false);
        et_unitPrice_tp_deduction.setEnabled(false);
        et_quantity_tp_deduction.setEnabled(false);

        et_price_gt.setEnabled(false);
        et_price_ae.setEnabled(false);
        et_price_ca_deduction.setEnabled(false);
        et_price_wf_deduction.setEnabled(false);
        et_price_tp_deduction.setEnabled(false);
        et_price_ot_deduction.setEnabled(false);
        et_price_mt_deduction.setEnabled(false);
        et_price_mr_deduction.setEnabled(false);
        et_price_kok_deduction.setEnabled(false);
        et_total_earnings.setEnabled(false);
        et_total_deduction.setEnabled(false);
        et_total_sum.setEnabled(false);

        tv_additional_earnings.setSelected(true);

        // Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_APPEND);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        greenTeaPrice_pre = sh.getString("greetTea_pr_pre", "1");
        transport_up_pre = sh.getString("transport_pr_pre", "1");

        et_unitPrice_gt.setText(greenTeaPrice_pre);
        et_unitPrice_tp_deduction.setText(transport_up_pre);

        String json = sh.getString("myModel", "");

        if (!json.isEmpty()) {
            // Convert the JSON string back to a MyModel object
            Gson gson = new Gson();
            EmployeeModalClass myModel = gson.fromJson(json, EmployeeModalClass.class);

            Toast.makeText(SupplierFormActivity.this, "S===================", Toast.LENGTH_SHORT).show();


            et_quantity_gt.setText(myModel.getGreentea_qua());
            et_price_gt.setText(myModel.getGreentea_pr());
            et_quantity_ae.setText(myModel.getAdditional_qua());
            et_price_ae.setText(myModel.getAdditional_pr());
            et_quantity_ca_deduction.setText(myModel.getCash_qua());
            et_price_ca_deduction.setText(myModel.getCash_pr());
            et_quantity_wf_deduction.setText(myModel.getWelfare_qua());
            et_price_wf_deduction.setText(myModel.getWelfare_pr());
            et_quantity_mt_deduction.setText(myModel.getMt_qua());
            et_price_mt_deduction.setText(myModel.getMt_pr());
            et_quantity_mr_deduction.setText(myModel.getManure_qua());
            et_price_mr_deduction.setText(myModel.getManure_pr());
            et_quantity_tp_deduction.setText(myModel.getTransport_qua());
            et_price_tp_deduction.setText(myModel.getTransport_pr());
            et_quantity_kok_deduction.setText(myModel.getKok_qua());
            et_price_kok_deduction.setText(myModel.getKok_pr());
            et_quantity_ot_deduction.setText(myModel.getOther_qua());
            et_price_ot_deduction.setText(myModel.getOther_pr());

            et_unitPrice_gt.setText(greenTeaPrice_pre);
            et_unitPrice_ae.setText(myModel.getAdditional_up());
            et_unitPrice_ca_deduction.setText(myModel.getCash_up());
            et_unitPrice_wf_deduction.setText(myModel.getWelfare_up());
            et_unitPrice_tp_deduction.setText(transport_up_pre);
            et_unitPrice_mr_deduction.setText(myModel.getManure_up());
            et_unitPrice_mt_deduction.setText(myModel.getMt_up());
            et_unitPrice_kok_deduction.setText(myModel.getKok_up());
            et_unitPrice_ot_deduction.setText(myModel.getOther_up());
        } else {
            quantity_gt = "quantity_gt";
            price_gt = "0";
            quantity_ae = "0";
            price_ae = "0";
            quantity_ca_deduction = "0";
            quantity_wf_deduction = "0";
            quantity_tp_deduction = "0";
            quantity_mr_deduction = "0";
            quantity_mt_deduction = "0";
            quantity_kok_deduction = "0";
            quantity_ot_deduction = "0";

            price_ca_deduction = "0";
            price_wf_deduction = "0";
            price_tp_deduction = "0";
            price_mt_deduction = "0";
            price_mr_deduction = "0";
            price_kok_deduction = "0";
            price_ot_deduction = "0";

            up_gt = et_unitPrice_gt.getText().toString();
            up_ae = "20";
            up_ca = "10";
            up_wf = "20";
            up_tp = et_unitPrice_tp_deduction.getText().toString();
            up_mt = "20";
            up_mr = "20";
            up_kok = "20";
            up_ot = "0";
        }
        supplier_names.add("D.M. Ubesena / 01");
        supplier_names.add("D.M. Jayasena / 02");
        supplier_names.add("D.M. Anulawathi / 03");
        supplier_names.add("D.M. Jayasundara / 04");
        supplier_names.add("D.M. Seelawathi / 05");
        supplier_names.add("B.M.L. A. Kumara / 06");
        supplier_names.add("B.M. Karunarathna / 07");
        supplier_names.add("D.M. Pemawathi / 08");
        supplier_names.add("D.M.A. Bandara / 09");
        supplier_names.add("G.E.Dissanayaka / 10");
        supplier_names.add("D.M. Rathnasiri / 11");
        supplier_names.add("B.M.S. Ranjith / 12");
        supplier_names.add("D.M.B. Kanchana / 13");
        supplier_names.add("B.M. H. Bandara / 14");
        supplier_names.add("A.J.M. Rajesinghe / 15");
        supplier_names.add("D.M. Jayawardhana / 16");
        supplier_names.add("B.M.S. Priyankara / 17");
        supplier_names.add("E.W. Siripala / 18");
        supplier_names.add("D.M. Rathnathilaka / 19");
        supplier_names.add("D.M. Dhanasena / 20");
        supplier_names.add("B.M. Gajanayaka / 21");
        supplier_names.add("B.M. N. Jiyalath / 22");
        supplier_names.add("R.M. Muthumenika / 23");
        supplier_names.add("H.M. Ghanawathi / 24");
        supplier_names.add("B.M. Swarnalatha / 25");
        supplier_names.add("D.M.Dhanapala / 26");
        supplier_names.add("D.M. Swarnalatha / 27");
        supplier_names.add("D.M. Gunapala / 28");
        supplier_names.add("D.M. Ghanasiri / 29");
        supplier_names.add("D.M. Ghanawathi / 30");
        supplier_names.add("B.R.N. Premathilaka / 31");
        supplier_names.add("D.M. Aberathna / 32");
        supplier_names.add("W.M. Wasana / 33");
        supplier_names.add("D.M.Hindandara / 34");
        supplier_names.add("B.R.Shanthi / 35");
        supplier_names.add("D.M. Sumanawathi / 36");
        supplier_names.add("R.M.Nimalawathi / 37");
        supplier_names.add("V.M. Keerthilatha / 38");
        supplier_names.add("D.M. Muthubandara / 39");
        supplier_names.add("D.M. Kumaradasa / 40");
        supplier_names.add("B.M. K. Kumara / 41");
        supplier_names.add("A.M. Chandani / 42");
        supplier_names.add("B.M. Premawansha / 51");
        supplier_names.add("D.M.H. Kumari / 52");
        supplier_names.add("Sunil / 53");
        supplier_names.add("J.M.Indrani / 54");
        supplier_names.add("Karunarathna / 55");
        supplier_names.add("D.M. J.Podimenika / 56");

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

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, supplier_names);


        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
                SupplierFormActivity.this.startActivity(myIntent);
            }
        });


        Btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dataAdapter.isEmpty()) {
                    Toast.makeText(SupplierFormActivity.this, "PLEASE ADD SUPPLIER FIRST", Toast.LENGTH_SHORT).show();

                } else {
                    loading.setVisibility(View.VISIBLE);
                    loading.playAnimation();
                    nestedScrollView.setVisibility(View.GONE);

                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms


                            quantity_gt = et_quantity_gt.getText().toString();
                            price_gt = et_price_gt.getText().toString();
                            quantity_ae = et_quantity_ae.getText().toString();
                            price_ae = et_price_ae.getText().toString();
                            quantity_ca_deduction = et_quantity_ca_deduction.getText().toString();
                            quantity_wf_deduction = et_quantity_wf_deduction.getText().toString();
                            quantity_tp_deduction = et_quantity_tp_deduction.getText().toString();
                            quantity_mr_deduction = et_quantity_mr_deduction.getText().toString();
                            quantity_mt_deduction = et_quantity_mt_deduction.getText().toString();
                            quantity_kok_deduction = et_quantity_kok_deduction.getText().toString();
                            quantity_ot_deduction = et_quantity_ot_deduction.getText().toString();

                            price_ca_deduction = et_price_ca_deduction.getText().toString();
                            price_wf_deduction = et_price_wf_deduction.getText().toString();
                            price_tp_deduction = et_price_tp_deduction.getText().toString();
                            price_mt_deduction = et_price_mt_deduction.getText().toString();
                            price_mr_deduction = et_price_mr_deduction.getText().toString();
                            price_kok_deduction = et_price_kok_deduction.getText().toString();
                            price_ot_deduction = et_price_ot_deduction.getText().toString();


                            up_ae = et_unitPrice_ae.getText().toString();
                            up_ca = et_unitPrice_ca_deduction.getText().toString();
                            up_wf = et_unitPrice_wf_deduction.getText().toString();
                            up_mt = et_unitPrice_mt_deduction.getText().toString();
                            up_mr = et_price_mr_deduction.getText().toString();
                            up_kok = et_unitPrice_kok_deduction.getText().toString();
                            up_ot = et_unitPrice_ot_deduction.getText().toString();


                            Toast.makeText(SupplierFormActivity.this, price_ot_deduction, Toast.LENGTH_SHORT).show();


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

                                    String nameId = suppler_name;

                                    String[] separated = nameId.split("/");
                                    String name = separated[0];
                                    String id = separated[1];

                                    employeeModalClass.setSupplier_name(name);
                                    employeeModalClass.setSupplier_id(id);
                                    employeeModalClass.setGreentea_qua(quantity_gt);
                                    employeeModalClass.setGreentea_up(up_gt);
                                    employeeModalClass.setGreentea_pr(price_gt);
                                    employeeModalClass.setAdditional_qua(quantity_ae);
                                    employeeModalClass.setAdditional_up(up_ae);
                                    employeeModalClass.setAdditional_pr(price_ae);
                                    employeeModalClass.setCash_qua("Cash qua");
                                    employeeModalClass.setCash_up(up_ca);
                                    employeeModalClass.setCash_pr(price_ca_deduction);
                                    employeeModalClass.setWelfare_qua("welfare qua");
                                    employeeModalClass.setWelfare_up(up_wf);
                                    employeeModalClass.setWelfare_pr(price_wf_deduction);
                                    employeeModalClass.setTransport_qua(quantity_tp_deduction);
                                    employeeModalClass.setTransport_up(up_tp);
                                    employeeModalClass.setTransport_pr(price_tp_deduction);
                                    employeeModalClass.setManure_qua(quantity_mr_deduction);
                                    employeeModalClass.setManure_up(up_mr);
                                    employeeModalClass.setManure_pr(price_mr_deduction);
                                    employeeModalClass.setMt_qua(quantity_mt_deduction);
                                    employeeModalClass.setMt_up(up_mt);
                                    employeeModalClass.setMt_pr(price_mt_deduction);
                                    employeeModalClass.setKok_qua(quantity_kok_deduction);
                                    employeeModalClass.setKok_up(up_kok);
                                    employeeModalClass.setKok_pr(price_kok_deduction);
                                    employeeModalClass.setOther_qua(quantity_ot_deduction);
                                    employeeModalClass.setOther_up(up_ot);
                                    employeeModalClass.setOther_pr(price_ot_deduction);

                                    databaseHelperClass.addEmployee(employeeModalClass);

                                    Toast.makeText(SupplierFormActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                                    nestedScrollView.setVisibility(View.VISIBLE);
                                    celebrate.pauseAnimation();
                                    celebrate.setVisibility(View.GONE);
                                }
                            }, 2000);

                            loading.setVisibility(View.GONE);
                            loading.pauseAnimation();

                            et_quantity_gt.setText("");
                            et_price_gt.setText("");
                            et_quantity_ae.setText("");
                            et_price_ae.setText("");
                            et_quantity_ca_deduction.setText("");
                            et_price_ca_deduction.setText("");
                            et_quantity_wf_deduction.setText("");
                            et_price_wf_deduction.setText("");
                            et_quantity_mt_deduction.setText("");
                            et_price_mt_deduction.setText("");
                            et_quantity_mr_deduction.setText("");
                            et_price_mr_deduction.setText("");
                            et_quantity_tp_deduction.setText("");
                            et_price_tp_deduction.setText("");
                            et_quantity_kok_deduction.setText("");
                            et_price_kok_deduction.setText("");
                            et_quantity_ot_deduction.setText("");
                            et_price_ot_deduction.setText("");

                            et_unitPrice_gt.setText(greenTeaPrice_pre);
                            et_unitPrice_ae.setText("");
                            et_unitPrice_ca_deduction.setText("");
                            et_unitPrice_wf_deduction.setText("");
                            et_unitPrice_tp_deduction.setText(transport_up_pre);
                            et_unitPrice_mr_deduction.setText("");
                            et_unitPrice_mt_deduction.setText("");
                            et_unitPrice_kok_deduction.setText("");
                            et_unitPrice_ot_deduction.setText("");
                            et_total_earnings.setText("");
                            et_total_deduction.setText("");
//                    finish();
//                    startActivity(getIntent());
//                            }

                        }
                    }, 2000);

                }

            }
        });

        et_quantity_gt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_gt = et_quantity_gt.getText().toString();
                String string_unitPrice_gt = up_gt;

                if (string_quantity_gt.isEmpty()) {
                    string_quantity_gt = "0";
                }
                if (string_unitPrice_gt.isEmpty()) {
                    string_unitPrice_gt = "0";
                }

                double d_quantity_gt = Double.parseDouble(string_quantity_gt);
                double d_unitPrice_gt = Double.parseDouble(string_unitPrice_gt);

                String string_d_et_price_gt = Double.toString(d_quantity_gt * d_unitPrice_gt);

                et_price_gt.setText(string_d_et_price_gt);
                et_quantity_tp_deduction.setText(et_quantity_gt.getText().toString());


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_gt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_gt = et_quantity_gt.getText().toString();
                String string_unitPrice_gt = et_unitPrice_gt.getText().toString();

                if (string_quantity_gt.isEmpty()) {
                    string_quantity_gt = "0";
                }
                if (string_unitPrice_gt.isEmpty()) {
                    string_unitPrice_gt = "0";
                }
                double d_quantity_gt = Double.parseDouble(string_quantity_gt);
                double d_unitPrice_gt = Double.parseDouble(string_unitPrice_gt);

                String string_d_et_price_gt = Double.toString(d_quantity_gt * d_unitPrice_gt);

                et_price_gt.setText(string_d_et_price_gt);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_quantity_ae.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_ae = et_quantity_ae.getText().toString();
                String string_unitPrice_ae = et_unitPrice_ae.getText().toString();

                if (string_quantity_ae.isEmpty()) {
                    string_quantity_ae = "0";
                }
                if (string_unitPrice_ae.isEmpty()) {
                    string_unitPrice_ae = "0";
                }
                double d_quantity_ae = Double.parseDouble(string_quantity_ae);
                double d_unitPrice_ae = Double.parseDouble(string_unitPrice_ae);

                String string_d_et_price_ae = Double.toString(d_quantity_ae * d_unitPrice_ae);

                et_price_ae.setText(string_d_et_price_ae);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_ae.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_ae = et_quantity_ae.getText().toString();
                String string_unitPrice_ae = et_unitPrice_ae.getText().toString();

                if (string_quantity_ae.isEmpty()) {
                    string_quantity_ae = "0";
                }
                if (string_unitPrice_ae.isEmpty()) {
                    string_unitPrice_ae = "0";
                }
                double d_quantity_ae = Double.parseDouble(string_quantity_ae);
                double d_unitPrice_ae = Double.parseDouble(string_unitPrice_ae);

                String string_d_et_price_ae = Double.toString(d_quantity_ae * d_unitPrice_ae);

                et_price_ae.setText(string_d_et_price_ae);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_gt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_price_gt = et_price_gt.getText().toString();
                String string_price_ae = et_price_ae.getText().toString();

                if (string_price_gt.isEmpty()) {
                    string_price_gt = "0";
                }
                if (string_price_ae.isEmpty()) {
                    string_price_ae = "0";
                }
                double d_price_gt = Double.parseDouble(string_price_gt);
                double d_price_ae = Double.parseDouble(string_price_ae);

                String string_d_et_final_price_gt = Double.toString(d_price_gt + d_price_ae);

                et_total_earnings.setText(string_d_et_final_price_gt);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_ae.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_price_gt = et_price_gt.getText().toString();
                String string_price_ae = et_price_ae.getText().toString();

                if (string_price_gt.isEmpty()) {
                    string_price_gt = "0";
                }
                if (string_price_ae.isEmpty()) {
                    string_price_ae = "0";
                }
                double d_price_gt = Double.parseDouble(string_price_gt);
                double d_price_ae = Double.parseDouble(string_price_ae);

                String string_d_et_final_price_gt = Double.toString(d_price_gt + d_price_ae);

                et_total_earnings.setText(string_d_et_final_price_gt);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });


        et_quantity_mr_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_mr_deduction = et_quantity_mr_deduction.getText().toString();
                String string_unitPrice_mr_deduction = et_unitPrice_mr_deduction.getText().toString();

                if (string_quantity_mr_deduction.isEmpty()) {
                    string_quantity_mr_deduction = "0";
                }
                if (string_unitPrice_mr_deduction.isEmpty()) {
                    string_unitPrice_mr_deduction = "0";
                }
                double d_quantity_mr_deduction = Double.parseDouble(string_quantity_mr_deduction);
                double d_unitPrice_mr_deduction = Double.parseDouble(string_unitPrice_mr_deduction);

                String string_d_et_price_mr_deduction = Double.toString(d_quantity_mr_deduction * d_unitPrice_mr_deduction);

                et_price_mr_deduction.setText(string_d_et_price_mr_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_mr_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_mr_deduction = et_quantity_mr_deduction.getText().toString();
                String string_unitPrice_mr_deduction = et_unitPrice_mr_deduction.getText().toString();

                if (string_quantity_mr_deduction.isEmpty()) {
                    string_quantity_mr_deduction = "0";
                }
                if (string_unitPrice_mr_deduction.isEmpty()) {
                    string_unitPrice_mr_deduction = "0";
                }
                double d_quantity_mr_deduction = Double.parseDouble(string_quantity_mr_deduction);
                double d_unitPrice_mr_deduction = Double.parseDouble(string_unitPrice_mr_deduction);

                String string_d_et_price_mr_deduction = Double.toString(d_quantity_mr_deduction * d_unitPrice_mr_deduction);

                et_price_mr_deduction.setText(string_d_et_price_mr_deduction);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_quantity_mt_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_mt_deduction = et_quantity_mt_deduction.getText().toString();
                String string_unitPrice_mt_deduction = et_unitPrice_mt_deduction.getText().toString();

                if (string_quantity_mt_deduction.isEmpty()) {
                    string_quantity_mt_deduction = "0";
                }
                if (string_unitPrice_mt_deduction.isEmpty()) {
                    string_unitPrice_mt_deduction = "0";
                }
                double d_quantity_mt_deduction = Double.parseDouble(string_quantity_mt_deduction);
                double d_unitPrice_mt_deduction = Double.parseDouble(string_unitPrice_mt_deduction);

                String string_d_et_mt_deduction = Double.toString(d_quantity_mt_deduction * d_unitPrice_mt_deduction);

                et_price_mt_deduction.setText(string_d_et_mt_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_mt_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_mt_deduction = et_quantity_mt_deduction.getText().toString();
                String string_unitPrice_mt_deduction = et_unitPrice_mt_deduction.getText().toString();

                if (string_quantity_mt_deduction.isEmpty()) {
                    string_quantity_mt_deduction = "0";
                }
                if (string_unitPrice_mt_deduction.isEmpty()) {
                    string_unitPrice_mt_deduction = "0";
                }
                double d_quantity_mt_deduction = Double.parseDouble(string_quantity_mt_deduction);
                double d_unitPrice_mt_deduction = Double.parseDouble(string_unitPrice_mt_deduction);

                String string_d_et_mt_deduction = Double.toString(d_quantity_mt_deduction * d_unitPrice_mt_deduction);

                et_price_mt_deduction.setText(string_d_et_mt_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_quantity_kok_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_kok_deduction = et_quantity_kok_deduction.getText().toString();
                String string_unitPrice_kok_deduction = et_unitPrice_kok_deduction.getText().toString();

                if (string_quantity_kok_deduction.isEmpty()) {
                    string_quantity_kok_deduction = "0";
                }
                if (string_unitPrice_kok_deduction.isEmpty()) {
                    string_unitPrice_kok_deduction = "0";
                }
                double d_quantity_kok_deduction = Double.parseDouble(string_quantity_kok_deduction);
                double d_unitPrice_kok_deduction = Double.parseDouble(string_unitPrice_kok_deduction);

                String string_d_et_price_kok_deduction = Double.toString(d_quantity_kok_deduction * d_unitPrice_kok_deduction);

                et_price_kok_deduction.setText(string_d_et_price_kok_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_kok_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_kok_deduction = et_quantity_kok_deduction.getText().toString();
                String string_unitPrice_kok_deduction = et_unitPrice_kok_deduction.getText().toString();

                if (string_quantity_kok_deduction.isEmpty()) {
                    string_quantity_kok_deduction = "0";
                }
                if (string_unitPrice_kok_deduction.isEmpty()) {
                    string_unitPrice_kok_deduction = "0";
                }
                double d_quantity_kok_deduction = Double.parseDouble(string_quantity_kok_deduction);
                double d_unitPrice_kok_deduction = Double.parseDouble(string_unitPrice_kok_deduction);

                String string_d_et_price_kok_deduction = Double.toString(d_quantity_kok_deduction * d_unitPrice_kok_deduction);

                et_price_kok_deduction.setText(string_d_et_price_kok_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_quantity_ot_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_ot_deduction = et_quantity_ot_deduction.getText().toString();
                String string_unitPrice_ot_deduction = et_unitPrice_ot_deduction.getText().toString();

                if (string_quantity_ot_deduction.isEmpty()) {
                    string_quantity_ot_deduction = "0";
                }
                if (string_unitPrice_ot_deduction.isEmpty()) {
                    string_unitPrice_ot_deduction = "0";
                }
                double d_quantity_ot_deduction = Double.parseDouble(string_quantity_ot_deduction);
                double d_unitPrice_ot_deduction = Double.parseDouble(string_unitPrice_ot_deduction);

                String string_d_et_price_ot_deduction = Double.toString(d_quantity_ot_deduction * d_unitPrice_ot_deduction);

                et_price_ot_deduction.setText(string_d_et_price_ot_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_ot_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_ot_deduction = et_quantity_ot_deduction.getText().toString();
                String string_unitPrice_ot_deduction = et_unitPrice_ot_deduction.getText().toString();

                if (string_quantity_ot_deduction.isEmpty()) {
                    string_quantity_ot_deduction = "0";
                }
                if (string_unitPrice_ot_deduction.isEmpty()) {
                    string_unitPrice_ot_deduction = "0";
                }
                double d_quantity_ot_deduction = Double.parseDouble(string_quantity_ot_deduction);
                double d_unitPrice_ot_deduction = Double.parseDouble(string_unitPrice_ot_deduction);

                String string_d_et_price_ot_deduction = Double.toString(d_quantity_ot_deduction * d_unitPrice_ot_deduction);

                et_price_ot_deduction.setText(string_d_et_price_ot_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_quantity_ca_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_ca_deduction = et_quantity_ca_deduction.getText().toString();
                String string_unitPrice_ca_deduction = et_unitPrice_ca_deduction.getText().toString();

                if (string_quantity_ca_deduction.isEmpty()) {
                    string_quantity_ca_deduction = "0";
                }
                if (string_unitPrice_ca_deduction.isEmpty()) {
                    string_unitPrice_ca_deduction = "0";
                }
                double d_quantity_ca_deduction = Double.parseDouble(string_quantity_ca_deduction);
                double d_unitPrice_ca_deduction = Double.parseDouble(string_unitPrice_ca_deduction);

                String string_d_et_price_ca_deduction = Double.toString(d_quantity_ca_deduction * d_unitPrice_ca_deduction);

                et_price_ca_deduction.setText(string_d_et_price_ca_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_ca_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_ca_deduction = et_quantity_ca_deduction.getText().toString();
                String string_unitPrice_ca_deduction = et_unitPrice_ca_deduction.getText().toString();

                if (string_quantity_ca_deduction.isEmpty()) {
                    string_quantity_ca_deduction = "1";
                }
                if (string_unitPrice_ca_deduction.isEmpty()) {
                    string_unitPrice_ca_deduction = "0";
                }
                double d_quantity_ca_deduction = Double.parseDouble(string_quantity_ca_deduction);
                double d_unitPrice_ca_deduction = Double.parseDouble(string_unitPrice_ca_deduction);

                String string_d_et_price_ca_deduction = Double.toString(d_quantity_ca_deduction * d_unitPrice_ca_deduction);

                et_price_ca_deduction.setText(string_d_et_price_ca_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_quantity_wf_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_wf_deduction = et_quantity_wf_deduction.getText().toString();
                String string_unitPrice_wf_deduction = et_unitPrice_wf_deduction.getText().toString();

                if (string_quantity_wf_deduction.isEmpty()) {
                    string_quantity_wf_deduction = "1";
                }
                if (string_unitPrice_wf_deduction.isEmpty()) {
                    string_unitPrice_wf_deduction = "0";
                }
                double d_quantity_wf_deduction = Double.parseDouble(string_quantity_wf_deduction);
                double d_unitPrice_wf_deduction = Double.parseDouble(string_unitPrice_wf_deduction);

                String string_d_et_price_wf_deduction = Double.toString(d_quantity_wf_deduction * d_unitPrice_wf_deduction);

                et_price_wf_deduction.setText(string_d_et_price_wf_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_wf_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_wf_deduction = "1";
                String string_unitPrice_wf_deduction = et_unitPrice_wf_deduction.getText().toString();

                if (string_quantity_wf_deduction.isEmpty()) {
                    string_quantity_wf_deduction = "0";
                }
                if (string_unitPrice_wf_deduction.isEmpty()) {
                    string_unitPrice_wf_deduction = "0";
                }
                double d_quantity_wf_deduction = Double.parseDouble(string_quantity_wf_deduction);
                double d_unitPrice_wf_deduction = Double.parseDouble(string_unitPrice_wf_deduction);

                String string_d_et_price_wf_deduction = Double.toString(d_quantity_wf_deduction * d_unitPrice_wf_deduction);

                et_price_wf_deduction.setText(string_d_et_price_wf_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_unitPrice_tp_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_tp_deduction = et_quantity_tp_deduction.getText().toString();
                String string_unitPrice_tp_deduction = et_unitPrice_tp_deduction.getText().toString();

                if (string_quantity_tp_deduction.isEmpty()) {
                    string_quantity_tp_deduction = "0";
                }
                if (string_unitPrice_tp_deduction.isEmpty()) {
                    string_unitPrice_tp_deduction = "0";
                }
                double d_quantity_tp_deduction = Double.parseDouble(string_quantity_tp_deduction);
                double d_unitPrice_tp_deduction = Double.parseDouble(string_unitPrice_tp_deduction);

                String string_d_et_price_tp_deduction = Double.toString(d_quantity_tp_deduction * d_unitPrice_tp_deduction);

                et_price_tp_deduction.setText(string_d_et_price_tp_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_quantity_tp_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String string_quantity_tp_deduction = et_quantity_tp_deduction.getText().toString();
                String string_unitPrice_tp_deduction = et_unitPrice_tp_deduction.getText().toString();

                if (string_quantity_tp_deduction.isEmpty()) {
                    string_quantity_tp_deduction = "0";
                }
                if (string_unitPrice_tp_deduction.isEmpty()) {
                    string_unitPrice_tp_deduction = "0";
                }
                double d_quantity_tp_deduction = Double.parseDouble(string_quantity_tp_deduction);
                double d_unitPrice_tp_deduction = Double.parseDouble(string_unitPrice_tp_deduction);

                String string_d_et_price_tp_deduction = Double.toString(d_quantity_tp_deduction * d_unitPrice_tp_deduction);

                et_price_tp_deduction.setText(string_d_et_price_tp_deduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_ca_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                CaTotalDeduction = 0.0;
                String string_et_price_ca_deduction = et_price_ca_deduction.getText().toString();


                if (string_et_price_ca_deduction.isEmpty()) {
                    string_et_price_ca_deduction = "0";
                }

                double d_et_price_ca_deduction = Double.parseDouble(string_et_price_ca_deduction);


                CaTotalDeduction += d_et_price_ca_deduction;
                grandTotalDeduction = CaTotalDeduction + WfTotalDeduction + MtTotalDeduction +
                        TpTotalDeduction + MrTotalDeduction + KokTotalDeduction + OtTotalDeduction;
                String string_d_grandTotalDeduction = Double.toString(grandTotalDeduction);

                et_total_deduction.setText(string_d_grandTotalDeduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_wf_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                WfTotalDeduction = 0.0;
                String string_et_price_wf_deduction = et_price_wf_deduction.getText().toString();


                if (string_et_price_wf_deduction.isEmpty()) {
                    string_et_price_wf_deduction = "0";
                }

                double d_et_price_wf_deduction = Double.parseDouble(string_et_price_wf_deduction);


                WfTotalDeduction += d_et_price_wf_deduction;
                grandTotalDeduction = CaTotalDeduction + WfTotalDeduction + MtTotalDeduction +
                        TpTotalDeduction + MrTotalDeduction + KokTotalDeduction + OtTotalDeduction;
                String string_d_grandTotalDeduction = Double.toString(grandTotalDeduction);

                et_total_deduction.setText(string_d_grandTotalDeduction);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_mt_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                MtTotalDeduction = 0.0;
                String string_et_price_mt_deduction = et_price_mt_deduction.getText().toString();


                if (string_et_price_mt_deduction.isEmpty()) {
                    string_et_price_mt_deduction = "0";
                }

                double d_et_price_mt_deduction = Double.parseDouble(string_et_price_mt_deduction);


                MtTotalDeduction += d_et_price_mt_deduction;
                grandTotalDeduction = CaTotalDeduction + WfTotalDeduction + MtTotalDeduction +
                        TpTotalDeduction + MrTotalDeduction + KokTotalDeduction + OtTotalDeduction;
                String string_d_grandTotalDeduction = Double.toString(grandTotalDeduction);

                et_total_deduction.setText(string_d_grandTotalDeduction);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_mr_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                MrTotalDeduction = 0.0;
                String string_et_price_mr_deduction = et_price_mr_deduction.getText().toString();


                if (string_et_price_mr_deduction.isEmpty()) {
                    string_et_price_mr_deduction = "0";
                }

                double d_et_price_mr_deduction = Double.parseDouble(string_et_price_mr_deduction);


                MrTotalDeduction += d_et_price_mr_deduction;
                grandTotalDeduction = CaTotalDeduction + WfTotalDeduction + MtTotalDeduction +
                        TpTotalDeduction + MrTotalDeduction + KokTotalDeduction + OtTotalDeduction;
                String string_d_grandTotalDeduction = Double.toString(grandTotalDeduction);

                et_total_deduction.setText(string_d_grandTotalDeduction);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_tp_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                TpTotalDeduction = 0.0;
                String string_et_price_tp_deduction = et_price_tp_deduction.getText().toString();


                if (string_et_price_tp_deduction.isEmpty()) {
                    string_et_price_tp_deduction = "0";
                }

                double d_et_price_tp_deduction = Double.parseDouble(string_et_price_tp_deduction);


                TpTotalDeduction += d_et_price_tp_deduction;
                grandTotalDeduction = CaTotalDeduction + WfTotalDeduction + MtTotalDeduction +
                        TpTotalDeduction + MrTotalDeduction + KokTotalDeduction + OtTotalDeduction;
                String string_d_grandTotalDeduction = Double.toString(grandTotalDeduction);

                et_total_deduction.setText(string_d_grandTotalDeduction);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_kok_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                KokTotalDeduction = 0.0;
                String string_et_price_kok_deduction = et_price_kok_deduction.getText().toString();


                if (string_et_price_kok_deduction.isEmpty()) {
                    string_et_price_kok_deduction = "0";
                }

                double d_et_price_kok_deduction = Double.parseDouble(string_et_price_kok_deduction);


                KokTotalDeduction += d_et_price_kok_deduction;
                grandTotalDeduction = CaTotalDeduction + WfTotalDeduction + MtTotalDeduction +
                        TpTotalDeduction + MrTotalDeduction + KokTotalDeduction + OtTotalDeduction;
                String string_d_grandTotalDeduction = Double.toString(grandTotalDeduction);

                et_total_deduction.setText(string_d_grandTotalDeduction);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_price_ot_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                OtTotalDeduction = 0.0;
                String string_et_price_ot_deduction = et_price_ot_deduction.getText().toString();


                if (string_et_price_ot_deduction.isEmpty()) {
                    string_et_price_ot_deduction = "0";
                }

                double d_et_price_ot_deduction = Double.parseDouble(string_et_price_ot_deduction);


                OtTotalDeduction += d_et_price_ot_deduction;
                grandTotalDeduction = CaTotalDeduction + WfTotalDeduction + MtTotalDeduction +
                        TpTotalDeduction + MrTotalDeduction + KokTotalDeduction + OtTotalDeduction;
                String string_d_grandTotalDeduction = Double.toString(grandTotalDeduction);

                et_total_deduction.setText(string_d_grandTotalDeduction);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_total_earnings.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String totalEarning = et_total_earnings.getText().toString();
                String totalDeduction = et_total_deduction.getText().toString();

                if (totalEarning.isEmpty()) {
                    totalEarning = "0";
                }
                if (totalDeduction.isEmpty()) {
                    totalDeduction = "0";
                }
                double d_total_earning = Double.parseDouble(totalEarning);
                double d_total_deduction = Double.parseDouble(totalDeduction);

                String string_d_et_totalNet = Double.toString(d_total_earning - d_total_deduction);

                et_total_sum.setText(string_d_et_totalNet);


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        et_total_deduction.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String totalEarning = et_total_earnings.getText().toString();
                String totalDeduction = et_total_deduction.getText().toString();

                if (totalEarning.isEmpty()) {
                    totalEarning = "0";
                }
                if (totalDeduction.isEmpty()) {
                    totalDeduction = "0";
                }
                double d_total_earning = Double.parseDouble(totalEarning);
                double d_total_deduction = Double.parseDouble(totalDeduction);

                String string_d_et_totalNet = Double.toString(d_total_earning - d_total_deduction);

                et_total_sum.setText(string_d_et_totalNet);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//                Toast.makeText(getApplicationContext(), "before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });

        try {
            List<SupplierModalClass> supplierModalClassList = databaseHelperClass.getSupplierNames();

            for (SupplierModalClass supplier_names_derails : supplierModalClassList
            ) {
                String names_derails = supplier_names_derails.getSupplier_name();
                supplier_names.add(names_derails);
            }
            if (supplierModalClassList.size() > 0) {

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spinner_supplier.setAdapter(dataAdapter);


            } else {
                Toast.makeText(SupplierFormActivity.this, "No details to view", Toast.LENGTH_SHORT).show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        spinner_supplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                suppler_name = spinner_supplier.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }

    public void getPreData() {
        Toast.makeText(SupplierFormActivity.this, "No ================", Toast.LENGTH_SHORT).show();

    }
}