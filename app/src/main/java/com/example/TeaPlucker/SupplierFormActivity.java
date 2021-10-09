package com.example.TeaPlucker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
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
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class SupplierFormActivity extends AppCompatActivity {

    LinearLayout LLGreenTea, LLAdition, LLOther_deduction, LLKOK_deduction,
            LLMadeTea_deduction, LLManure_deduction, LLTransport_deduction,
            LLWelfare_deduction, LLCashAdvance_deduction, LLTotalDeduction, LLTotalEarnings;
    Button BtnExpand, BtnExpandDeduction, Btnadd, BtnBack;
    NestedScrollView nestedScrollView;
    LottieAnimationView celebrate, loading;
    EditText et_quantity_gt, et_price_gt, et_quantity_ae, et_price_ae, et_quantity_ca_deduction,
            et_price_ca_deduction, et_quantity_wf_deduction, et_price_wf_deduction, et_quantity_tp_deduction,
            et_price_tp_deduction, et_quantity_mr_deduction, et_price_mr_deduction, et_quantity_mt_deduction,
            et_price_mt_deduction, et_quantity_kok_deduction, et_price_kok_deduction, et_quantity_ot_deduction,
            et_price_ot_deduction, et_unitPrice_gt, et_unitPrice_ae, et_unitPrice_ca_deduction, et_unitPrice_wf_deduction,
            et_unitPrice_tp_deduction, et_unitPrice_mr_deduction, et_unitPrice_mt_deduction, et_unitPrice_kok_deduction,
            et_unitPrice_ot_deduction, et_total_earnings, et_total_deduction;
    CardView CvcardView, cvLoading, cvCelebrate;
    String suppler_name, quantity_gt, price_gt, quantity_ae, price_ae, quantity_ca_deduction, price_ca_deduction,
            quantity_wf_deduction, price_wf_deduction, quantity_mt_deduction, price_mt_deduction,
            quantity_mr_deduction, price_mr_deduction, quantity_tp_deduction, price_tp_deduction,
            quantity_kok_deduction, price_kok_deduction, quantity_ot_deduction, price_ot_deduction;

    Spinner spinner_supplier;
    List<String> supplier_names = new ArrayList<String>();
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
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, supplier_names);


        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SupplierFormActivity.this, MainActivity.class);
                SupplierFormActivity.this.startActivity(myIntent);
            }
        });


        BtnExpand.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (LLGreenTea.getVisibility() == View.GONE && LLAdition.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(CvcardView, new AutoTransition());
                    LLGreenTea.setVisibility(View.VISIBLE);
                    LLAdition.setVisibility(View.VISIBLE);

                    LLTotalEarnings.setVisibility(View.VISIBLE);
                    BtnExpand.setBackgroundResource(R.drawable.ic_baseline_expand_less_24);
                } else {
                    TransitionManager.beginDelayedTransition(CvcardView, new AutoTransition());
                    LLGreenTea.setVisibility(View.GONE);
                    LLAdition.setVisibility(View.GONE);
                    LLTotalEarnings.setVisibility(View.GONE);
                    BtnExpand.setBackgroundResource(R.drawable.ic_baseline_expand_more_24);
                }
            }
        });

        BtnExpandDeduction.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (LLCashAdvance_deduction.getVisibility() == View.GONE && LLWelfare_deduction.getVisibility() == View.GONE
                        && LLTransport_deduction.getVisibility() == View.GONE && LLMadeTea_deduction.getVisibility() == View.GONE
                        && LLManure_deduction.getVisibility() == View.GONE && LLKOK_deduction.getVisibility() == View.GONE
                        && LLOther_deduction.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(CvcardView, new AutoTransition());
                    LLCashAdvance_deduction.setVisibility(View.VISIBLE);
                    LLWelfare_deduction.setVisibility(View.VISIBLE);
                    LLMadeTea_deduction.setVisibility(View.VISIBLE);
                    LLManure_deduction.setVisibility(View.VISIBLE);
                    LLTransport_deduction.setVisibility(View.VISIBLE);
                    LLKOK_deduction.setVisibility(View.VISIBLE);
                    LLOther_deduction.setVisibility(View.VISIBLE);
                    LLTotalDeduction.setVisibility(View.VISIBLE);

                    BtnExpandDeduction.setBackgroundResource(R.drawable.ic_baseline_expand_less_24);
                } else {
                    TransitionManager.beginDelayedTransition(CvcardView, new AutoTransition());
                    LLCashAdvance_deduction.setVisibility(View.GONE);
                    LLWelfare_deduction.setVisibility(View.GONE);
                    LLMadeTea_deduction.setVisibility(View.GONE);
                    LLManure_deduction.setVisibility(View.GONE);
                    LLTransport_deduction.setVisibility(View.GONE);
                    LLKOK_deduction.setVisibility(View.GONE);
                    LLOther_deduction.setVisibility(View.GONE);
                    LLTotalDeduction.setVisibility(View.GONE);

                    BtnExpandDeduction.setBackgroundResource(R.drawable.ic_baseline_expand_more_24);
                }
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


                            Toast.makeText(SupplierFormActivity.this, price_ot_deduction, Toast.LENGTH_SHORT).show();


                            if (quantity_gt.length() <= 0 || price_gt.length() <= 0) {
                                Toast.makeText(SupplierFormActivity.this, "Enter name", Toast.LENGTH_SHORT).show();
                            } else {
                                final Handler handler2 = new Handler(Looper.getMainLooper());
                                handler2.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Do something after 100ms
                                        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(SupplierFormActivity.this);
                                        EmployeeModalClass employeeModalClass = new EmployeeModalClass(suppler_name, quantity_gt, price_gt, quantity_ae,
                                                price_ae, quantity_ca_deduction, price_ca_deduction, quantity_wf_deduction, price_wf_deduction,
                                                quantity_tp_deduction, price_tp_deduction, quantity_mt_deduction, price_mt_deduction, quantity_mr_deduction,
                                                price_mr_deduction, quantity_kok_deduction, price_kok_deduction, quantity_ot_deduction, price_ot_deduction);
                                        databaseHelperClass.addEmployee(employeeModalClass);

                                        Toast.makeText(SupplierFormActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                                        nestedScrollView.setVisibility(View.VISIBLE);
                                        celebrate.pauseAnimation();
                                        celebrate.setVisibility(View.GONE);
                                    }
                                }, 4000);

                                loading.setVisibility(View.GONE);
                                loading.pauseAnimation();
                                celebrate.setVisibility(View.VISIBLE);
                                celebrate.playAnimation();

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

                                et_unitPrice_gt.setText("");
                                et_unitPrice_ae.setText("");
                                et_unitPrice_ca_deduction.setText("");
                                et_unitPrice_wf_deduction.setText("");
                                et_unitPrice_tp_deduction.setText("");
                                et_unitPrice_mr_deduction.setText("");
                                et_unitPrice_mt_deduction.setText("");
                                et_unitPrice_kok_deduction.setText("");
                                et_unitPrice_ot_deduction.setText("");
                                et_total_earnings.setText("");
                                et_total_deduction.setText("");
//                    finish();
//                    startActivity(getIntent());


                            }

                        }
                    }, 2000);

                }

            }
        });

        et_quantity_gt.addTextChangedListener(new TextWatcher() {

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

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
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
}