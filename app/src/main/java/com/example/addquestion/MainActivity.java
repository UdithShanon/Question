package com.example.addquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText username,phone,email,message;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        username = findViewById(R.id.updateName);
        phone = findViewById(R.id.updateEmail);
        email = findViewById(R.id.updatePhone);
        message = findViewById(R.id.updateAnswer);

        submit = findViewById(R.id.submit);

        insertFeedback();
    }
    //initializing toast messages
    public void toast(Context context, String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }
    public void view(){
        Intent intent = new Intent(this, com.example.addquestion.View.class);
        startActivity(intent);
    }
    public void insertFeedback(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().trim().length() == 0 ||
                phone.getText().toString().trim().length() == 0 ||
                        email.getText().toString().trim().length() == 0||
                        message.getText().toString().trim().length() == 0) {
                    toast(MainActivity.this,"All fields are required");
                }else if(db.feedback(username.getText().toString(), email.getText().toString(), phone.getText().toString(), message.getText().toString())){
                    view();
                    toast(MainActivity.this, "Success");

                    username.setText("");
                    email.setText("");
                    phone.setText("");
                    message.setText("");
                }else {
                    toast(MainActivity.this,"Error");
                }
            }
        });
    }
}
