package com.xpamii.datastorage.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db"; // Database name
    private static final int DATABASE_VERSION = 1; // Database version

    private static SQLiteHelper sqLiteHelper;

    //Create a constructor for SQLiteHelper
    private SQLiteHelper(@Nullable Context context) {
        super(context, sqLiteHelper.DATABASE_NAME, null, sqLiteHelper.DATABASE_VERSION);
    }

    public static synchronized SQLiteHelper getInstance(Context context){

        if(sqLiteHelper == null){
            sqLiteHelper = new SQLiteHelper(context.getApplicationContext());
        }
        return sqLiteHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // Create a table in the database

        String studentTable = "CREATE TABLE  `student` " +
                "('id' INTEGER PRIMARY KEY AUTOINCREMENT," + //use AUTOINCREMENT instead of mysql AUTO_INCREMENT
                " 'name' VARCHAR(150), " +
                "'age' INTEGER)";

        sqLiteDatabase.execSQL(studentTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { // Upgrade the database if the version changes

        String dropTable = "DROP TABLE IF EXISTS `student`"; // Drop the table if the version changes
        sqLiteDatabase.execSQL(dropTable);
        onCreate(sqLiteDatabase);

    }
}
