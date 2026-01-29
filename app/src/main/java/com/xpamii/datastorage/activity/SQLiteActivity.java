package com.xpamii.datastorage.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xpamii.datastorage.R;
import com.xpamii.datastorage.helper.SQLiteHelper;

public class SQLiteActivity extends AppCompatActivity {

    private Button insertButton, updateButton, deleteButton, searchButton; // Declare the buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlite);

        insertButton = findViewById(R.id.insertButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        searchButton = findViewById(R.id.searchButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData();
            }
        });


    }

    //Insert Data
    private void insertData() {

        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "Pamith Herath");
        values.put("age", 20);
        writableDatabase.insert("student", null, values);

    }

    //Update Data
    private void updateData() {

        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("age", "25");

        //UPDATE `student` SET `age` = ? WHERE `id` = ?
        //new String[]{1} ==> Incorrect data passing (String array =! Integer value)
        //new String[]{String.valueOf(1)} ==> Passing parameter as String array
        //Complete query ==> UPDATE `student` SET `age` = '25' WHERE `id` = 1;

        // ? is used to prevent SQL injection. It is used to prevent SQL injection.
        writableDatabase.update("student", values, "id=?", new String[]{String.valueOf(1)});
        //new String[]{String.valueOf(1), String.valueOf(true)}
        //Complete query ==> UPDATE `student` SET `age` = '25' WHERE `id` = 1 AND `isLogged` = 'true';
    }

    //Delete Data
    private void deleteData() {

        SQLiteHelper sqLiteHelper = SQLiteHelper.getInstance(this);
        SQLiteDatabase writableDatabase = sqLiteHelper.getWritableDatabase();

        int deleted = writableDatabase.delete("student", "id=?", new String[]{String.valueOf(1)});
        //new String[]{String.valueOf(1), String.valueOf(true)}
        //Complete query ==> DELETE FROM `student` WHERE `id` = 1 AND `isLogged` = 'true';
        //deleted = 1;
        Log.d(SQLiteActivity.class.getSimpleName(), "deletedData : " + deleted); // deleted count. If deleted count is 0, then no data is deleted.

    }

    //Search Data
    private void searchData() {

    }


}