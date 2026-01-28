package com.xpamii.datastorage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xpamii.datastorage.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorageActivity extends AppCompatActivity {

    private Button storeButton, retrieveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_internal_storage);

        storeButton = findViewById(R.id.storeButton);
        retrieveButton = findViewById(R.id.retrieveButton);

        storeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                storeData();
            }
        });

        retrieveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

    }

    private void storeData() {
        //getFilesDir(); /// Files Directory --> com/xpamii/datastorage/files
        //getCacheDir(); /// Cache Directory --> com/xpamii/datastorage/cache

        File file = new File(getFilesDir(), "App_Data.txt");// com/xpamii/datastorage/files/App_Data.txt
        try {

            FileOutputStream fileOutputStream = openFileOutput("App_Data.txt", MODE_PRIVATE);
            String text = "Hello, im xPamii";
            fileOutputStream.write(text.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "Data Stored Successfully", Toast.LENGTH_SHORT).show();

        }catch (IOException e){
            throw new RuntimeException(e);
        }


    }

    private void readData() {
        try {
            FileInputStream fileInputStream = openFileInput("App_Data.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            fileInputStream.close();
            bufferedReader.close();
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();

        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

}