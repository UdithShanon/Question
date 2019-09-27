package com.example.addquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class View extends AppCompatActivity {

    DatabaseHelper db;
    Button delete;
    EditText id;

    ListView feedbackList;

    ArrayList<String> list;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        db = new DatabaseHelper(this);

        list = new ArrayList<>();
        feedbackList = findViewById(R.id.view);
        id = findViewById(R.id.fid);
        delete = findViewById(R.id.delete);

        viewData();
        deleteData();

    }
    public void view(){
        Intent intent = new Intent(this, com.example.addquestion.View.class);
        startActivity(intent);
    }
    private void viewData() {
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0){
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                list.add("Feedback Id: "+cursor.getString(0));
                list.add("Feedback: "+cursor.getString(4));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
            feedbackList.setAdapter(adapter);
        }
    }
    //initializing toast messages
    public void toast(Context context, String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    public void deleteData(){
        delete.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Integer deleteRows = db.deleteData(id.getText().toString());
                if(deleteRows > 0){
                    view();
                    toast(View.this,"Deleted");
                }else{
                    toast(View.this,"Not Deleted");
                }
            }
        });
    }

}
