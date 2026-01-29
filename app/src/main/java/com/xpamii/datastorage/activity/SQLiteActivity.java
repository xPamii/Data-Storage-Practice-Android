package com.xpamii.datastorage.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

    }

    //Delete Data
    private void deleteData() {

    }

    //Search Data
    private void searchData() {

    }


}