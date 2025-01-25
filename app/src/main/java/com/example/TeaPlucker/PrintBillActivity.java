package com.example.TeaPlucker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class PrintBillActivity extends AppCompatActivity {

    private Button printBtn, backBtn;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;
    private static final int REQUEST_BLUETOOTH_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_bill);

        printBtn = findViewById(R.id.printBtn);
        backBtn = findViewById(R.id.backBtn);
        //getFormAsImage();

        printBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkBluetoothPermissions();
            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("MySharedPref_update", MODE_APPEND);
//local storage deeptha

                String clicked = sh.getString("updateClick", "");
                if (clicked.equals("1")) {
                    Intent mainActivityIntent = new Intent(PrintBillActivity.this, ViewDetailsActivity.class);
                    startActivity(mainActivityIntent);
                } else {
                    Intent mainActivityIntent = new Intent(PrintBillActivity.this, SupplierFormActivity.class);
                    startActivity(mainActivityIntent);
                }


            }
        });

//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        if (bluetoothAdapter == null) {
//            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Set the Bluetooth device address (MAC address) of your printer
//        String printerAddress = "XX:XX:XX:XX:XX:XX"; // Replace with your printer's MAC address
//        bluetoothDevice = bluetoothAdapter.getRemoteDevice(printerAddress);

    }

    private void getFormAsImage() {
//        View formView = findViewById(R.id.formView); // Replace with the ID of your form View
//        formView.setDrawingCacheEnabled(true);
//        Bitmap screenshot = Bitmap.createBitmap(formView.getDrawingCache());
//        formView.setDrawingCacheEnabled(false);
    }

    private void checkBluetoothPermissions() {
        String[] permissions = {
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.BLUETOOTH_CONNECT,
        };

        boolean allPermissionsGranted = true;

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allPermissionsGranted = false;
                break;
            }
        }

        if (!allPermissionsGranted) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_BLUETOOTH_PERMISSIONS);
        } else {
            printBillToBluetoothPrinter();
        }
    }

    @SuppressLint("MissingPermission")
    private void printBillToBluetoothPrinter() {
        try {
            // Create a secure Bluetooth socket
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // Standard SPP UUID
            bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
            bluetoothSocket.connect();

            // Get the output stream to the printer
            outputStream = bluetoothSocket.getOutputStream();

            // Send a sample text message to the printer
            String billData = "Your bill goes here...\n";
            outputStream.write(billData.getBytes());

            // Close the connection
            outputStream.close();
            bluetoothSocket.close();

            Toast.makeText(this, "Bill sent to printer", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error printing bill", Toast.LENGTH_SHORT).show();
        }
    }
}