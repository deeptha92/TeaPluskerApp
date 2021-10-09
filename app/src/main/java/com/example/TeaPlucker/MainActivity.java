package com.example.TeaPlucker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.Cancellable;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int STORAGE_EXTERNAL_STORAGE = 0;
    public static final String TAG = "MainActivity-->";
    LottieAnimationView lav_form, loading, lav_sync, LVsuccess, LVwarning, lav_viewDetails, lav_add_supplier;
    LinearLayout LLFirst, LLSecond, linearLayout, LLSecondLable, LLdetails, LLMessage;
    TextView tvMessage;
    private SQLiteDatabase sqLiteDatabase;
    String newDateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        lav_form = findViewById(R.id.lav_form);
        lav_viewDetails = findViewById(R.id.lav_viewDetails);
        lav_add_supplier = findViewById(R.id.lav_add_supplier);
        loading = findViewById(R.id.loading);
        LLFirst = findViewById(R.id.LLfirst);
        LLSecond = findViewById(R.id.LLsecond);
        linearLayout = findViewById(R.id.linearLayout);
        LLSecondLable = findViewById(R.id.LLSecondLable);
        lav_sync = findViewById(R.id.lav_sync);
        LLdetails = findViewById(R.id.LLdetails);
        LVsuccess = findViewById(R.id.LVsuccess);
        LVwarning = findViewById(R.id.LVwarning);
        tvMessage = findViewById(R.id.tvMessage);
        LLMessage = findViewById(R.id.LLMessage);

        Date c = Calendar.getInstance().getTime();


        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        newDateStr = df.format(c);
        Log.d(TAG, "onCreate: "+ newDateStr);


        lav_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LLFirst.setVisibility(View.INVISIBLE);
                LLSecond.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.GONE);
                LLSecondLable.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loading.playAnimation();

                final Handler handler2 = new Handler(Looper.getMainLooper());
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent myIntent = new Intent(MainActivity.this, SupplierFormActivity.class);
                        MainActivity.this.startActivity(myIntent);


                        loading.setVisibility(View.GONE);
                        loading.pauseAnimation();
                        LLFirst.setVisibility(View.VISIBLE);
                        LLSecond.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                        LLSecondLable.setVisibility(View.GONE);

                    }
                }, 3000);


            }
        });

        lav_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestGrantPermission();

            }
        });

        lav_viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LLFirst.setVisibility(View.INVISIBLE);
                LLSecond.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.GONE);
                LLSecondLable.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loading.playAnimation();
                final Handler handler2 = new Handler(Looper.getMainLooper());
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, ViewDetailsActivity.class);
                        startActivity(intent);

                        loading.setVisibility(View.GONE);
                        loading.pauseAnimation();
                        LLFirst.setVisibility(View.VISIBLE);
                        LLSecond.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                        LLSecondLable.setVisibility(View.GONE);

                    }
                }, 3000);

            }
        });

        lav_add_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LLFirst.setVisibility(View.INVISIBLE);
                LLSecond.setVisibility(View.INVISIBLE);
                linearLayout.setVisibility(View.GONE);
                LLSecondLable.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loading.playAnimation();
                final Handler handler2 = new Handler(Looper.getMainLooper());
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, AddSuplierActivity.class);
                        startActivity(intent);

                        loading.setVisibility(View.GONE);
                        loading.pauseAnimation();
                        LLFirst.setVisibility(View.VISIBLE);
                        LLSecond.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                        LLSecondLable.setVisibility(View.GONE);

                    }
                }, 3000);
            }
        });


    }

    public void requestGrantPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission")
                    .setMessage("Permission nedded")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                exportDB();
            } else {
                Toast.makeText(MainActivity.this, "granted failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //
    private void exportDB() {
        loading.setVisibility(View.INVISIBLE);
        LLFirst.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.GONE);
        LLSecond.setVisibility(View.INVISIBLE);
        LLSecondLable.setVisibility(View.GONE);


        DatabaseHelperClass dbhelper = new DatabaseHelperClass(getApplicationContext());
        File exportDir = new File(getExternalFilesDir(null).getAbsolutePath());
        if (!exportDir.exists()) {
            Objects.requireNonNull(exportDir.getParentFile()).mkdirs();
        }

        File file = new File(exportDir, ""+newDateStr+".csv");
        try {
//            file.mkdirs();
            file.createNewFile();
            Log.d("MainActivity", String.valueOf(file));
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM SUPPLIER", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to exprort
                String arrStr[] = {curCSV.getString(0),
                        curCSV.getString(1),
                        curCSV.getString(2),
                        curCSV.getString(3),
                        curCSV.getString(4),
                        curCSV.getString(5),
                        curCSV.getString(6),
                        curCSV.getString(7),
                        curCSV.getString(8),
                        curCSV.getString(9),
                        curCSV.getString(10),
                        curCSV.getString(11),
                        curCSV.getString(12),
                        curCSV.getString(13),
                        curCSV.getString(14),
                        curCSV.getString(15),
                        curCSV.getString(16),
                        curCSV.getString(17),
                        curCSV.getString(18),
                        curCSV.getString(19)};
                csvWrite.writeNext(arrStr);
                Log.d(TAG, "exportDB: "+ "OK");
            }
            LVsuccess.setVisibility(View.VISIBLE);
            LVsuccess.playAnimation();
            final Handler handler3 = new Handler(Looper.getMainLooper());
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        csvWrite.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    curCSV.close();
                    LVsuccess.setVisibility(View.GONE);
                    LVsuccess.pauseAnimation();
                    loading.setVisibility(View.INVISIBLE);
                    LLFirst.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    LLSecond.setVisibility(View.VISIBLE);
                    LLSecondLable.setVisibility(View.GONE);
                }
            }, 3000);


        } catch (Exception sqlEx) {
            LVwarning.setVisibility(View.VISIBLE);
            LLMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(sqlEx.getMessage());
            LVwarning.playAnimation();
            final Handler handler3 = new Handler(Looper.getMainLooper());
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
                    LLMessage.setVisibility(View.GONE);
                    LVwarning.setVisibility(View.GONE);
                    LVwarning.pauseAnimation();
                    loading.setVisibility(View.INVISIBLE);
                    LLFirst.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    LLSecond.setVisibility(View.VISIBLE);
                    LLSecondLable.setVisibility(View.GONE);
                }
            }, 3000);


        }


    }


}