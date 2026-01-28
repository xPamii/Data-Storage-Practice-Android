package com.xpamii.datastorage.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ExternalStorageActivity extends AppCompatActivity {

    private Button exWriteButton, exReadButton, exPickerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_external_storage);

        exWriteButton = findViewById(R.id.writeButton);
        exReadButton = findViewById(R.id.readButton);
        exPickerButton = findViewById(R.id.pickerButton);


        exWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
                writeFileInDownloads();
                Toast.makeText(ExternalStorageActivity.this, "Write Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        exReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
                readFileFromDownloads();
                Toast.makeText(ExternalStorageActivity.this, "Read Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        exPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilePicker();
                Toast.makeText(ExternalStorageActivity.this, "Picker Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //App Specific External Storage
    private static final String FILE_NAME = "data.txt";


    //Write to External Storage
    private void writeFile() {
        try {
            File dir = getExternalFilesDir(null);
            File file = new File(dir, FILE_NAME);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String text = "Hello, im xPamii";
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Read from External Storage
    private void readFile() {
        try {
            File dir = getExternalFilesDir(null);
            File file = new File(dir, FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));//FileInputStream(file)
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Open File Picker
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*"); //*/* means all types of files
        intent.addCategory(Intent.CATEGORY_OPENABLE); //CATEGORY_OPENABLE means only openable files
        startActivityForResult(intent, 101);// Deprecated.Code 101 is used to identify the request code.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) { //check if the request code is 101 and the result is ok (user selected a file)
            if (data != null) { // if data is not null (user selected a file)
                Uri uri = data.getData(); // uri of the selected file (file path)

                try { // try to read the file
                    if (uri == null)
                        throw new RuntimeException("Something went wrong"); // if uri is null, throw an exception

                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {// read the file line by line
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void writeFileInDownloads() {

        try {

            ContentValues values = new ContentValues();
            values.put(MediaStore.Downloads.DISPLAY_NAME, FILE_NAME);
            values.put(MediaStore.Downloads.MIME_TYPE, "text/plain");

            Uri uri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
            }

            OutputStream outputStream = getContentResolver().openOutputStream(uri);
            String text = "This is sample text";
            outputStream.write(text.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(this, "File Written Successfully", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void readFileFromDownloads() {

    }


}