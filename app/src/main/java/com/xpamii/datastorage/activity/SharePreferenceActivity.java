package com.xpamii.datastorage.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.xpamii.datastorage.R;

public class SharePreferenceActivity extends AppCompatActivity {

    private static final String APP_DATA = "my_app_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share_preferance);

        EditText emailInputField = findViewById(R.id.emailInputField);
        EditText passwordInputField = findViewById(R.id.passwordInputField);
        Button submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailInputField.getText().toString().trim();
                String password = passwordInputField.getText().toString().trim();

                if (email.equals("pbherath2018@gmail.com") && password.equals("12345@A")) {
                    Toast.makeText(SharePreferenceActivity.this,
                            "LogIn Successful",
                            Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences(APP_DATA, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.putBoolean("isLoggedIn", true);
                    //editor.commit(); // synchronously saves the data (Immediately)
                    editor.apply();//asynchronously saves the data


                } else {
                    Toast.makeText(SharePreferenceActivity.this,
                            "Please Enter Valid Credentials",
                            Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void checkIsLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences(APP_DATA, MODE_PRIVATE);//
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);// false is the default value.
        if (isLoggedIn) {
            Toast.makeText(this, "User is already logged in", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User is not logged in", Toast.LENGTH_SHORT).show();
        }
    }
}