package com.example.TeaPlucker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.Modal.UserModalClass;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText EtUsernameVal, EtPasswordVal;
    private LinearLayout LLLoading, LLError;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginButton = findViewById(R.id.loginButton);
        LLLoading = findViewById(R.id.LLLoading);
        LLError = findViewById(R.id.LLError);
        EtUsernameVal = (EditText) findViewById(R.id.EtUsername);
        EtPasswordVal = (EditText) findViewById(R.id.EtPassword);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LLLoading.setVisibility(View.VISIBLE);
                loginButton.setEnabled(false);
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LLLoading.setVisibility(View.GONE);
                        loginButton.setEnabled(true);


                        String userName = EtUsernameVal.getText().toString();
                        String password = EtPasswordVal.getText().toString();

                        UserModalClass userModalClass = new UserModalClass();

                        if (userName.equals("ADMIN") && password.equals("admin123")) {
                            userModalClass.setUserName("ADMIN");
                            userModalClass.setUserType("ADMIN");

                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();

                            // write all the data entered by the user in SharedPreference and apply
                            myEdit.putString("userName", userModalClass.getUserName());
                            myEdit.putString("userType", userModalClass.getUserType());

                            myEdit.apply();

                            Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mainActivityIntent);

                            LLError.setVisibility(View.GONE);
                            LLLoading.setVisibility(View.GONE);
                            loginButton.setEnabled(true);

                        } else if (userName.equals("USER") && password.equals("user123")) {
                            userModalClass.setUserName("USER");
                            userModalClass.setUserType("USER");

                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();

                            // write all the data entered by the user in SharedPreference and apply
                            myEdit.putString("userName", userModalClass.getUserName());
                            myEdit.putString("userType", userModalClass.getUserType());

                            myEdit.apply();

                            LLError.setVisibility(View.GONE);
                            LLLoading.setVisibility(View.GONE);
                            loginButton.setEnabled(true);

                            Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(mainActivityIntent);

                        } else {
                            LLError.setVisibility(View.VISIBLE);
                            LLLoading.setVisibility(View.GONE);
                            loginButton.setEnabled(true);
                        }

                    }
                }, 3000);
            }
        });
    }

    public void ShowHidePass(View view) {
        if (view.getId() == R.id.showHidePass) {

            if (EtPasswordVal.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {

                EtPasswordVal.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {

                //Hide Password
                EtPasswordVal.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
}