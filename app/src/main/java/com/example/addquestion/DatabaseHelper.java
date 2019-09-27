package com.example.addquestion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static java.sql.Types.NULL;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "library.db";
    private static final String FEEDBACK = "Feedback";

    //columns
    private static final String ID = "ID";
    private static final String USERNAME = "USERNAME";
    private static final String EMAIL = "EMAIL";
    private static final String PHONE = "PHONE";
    private static final String MESSAGE = "MESSAGE";

    //CREATE TABLE
    private static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE " + FEEDBACK + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USERNAME + " TEXT, " +
            EMAIL + " TEXT, " +
            PHONE + " TEXT, " +
            MESSAGE + " TEXT " + ")";

    public DatabaseHelper(Context context){
        super(context, DB_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE_FEEDBACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FEEDBACK);
    }

    //Insert Data
    public boolean feedback(String username, String email, String phone, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,username);
        contentValues.put(EMAIL,email);
        contentValues.put(PHONE,phone);
        contentValues.put(MESSAGE,message);

        long result = db.insert(FEEDBACK, null, contentValues);
        return result != -1;
    }
    //View Data
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from "+ FEEDBACK;
        Cursor cursor =db.rawQuery(query,null);
        return cursor;
    }
    //Delete Data
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FEEDBACK,"id = ?",new String[] { id });
    }

    //Update Data
    public Integer updateData(String username, String email, String phone, String message,String id){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(USERNAME,username);
        contentValues.put(EMAIL,email);
        contentValues.put(PHONE,phone);
        contentValues.put(MESSAGE,message);


        String selection = ID + "?=";
        String[] selectionArgs = {String.valueOf(id)};


        int count = db.update(FEEDBACK,contentValues,selection,selectionArgs);
        Log.i("FEEDBACK_DB","========Updated=======" + toString().valueOf(count));

        if (count > 0){
            return 1;

        }

        return 0;





    }
}
