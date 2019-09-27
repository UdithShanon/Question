package com.example.addquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class update extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    EditText uName;
    EditText eMail;
    EditText phone;
    EditText answer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        this.databaseHelper = new DatabaseHelper(this);
        Button button = findViewById(R.id.submit);
        button.setOnClickListener(this);

        uName = findViewById(R.id.updateName);
        eMail = findViewById(R.id.updateEmail);
        phone = findViewById(R.id.updatePhone);
        answer = findViewById(R.id.updateAnswer);

        Intent recieveIntent = getIntent();

    }


    private Feedback getFeedback(){
        Feedback f = new Feedback();
        f.uName = this.uName.getText().toString();
        f.email = this.eMail.getText().toString();
        f.phone = this.phone.getText().toString();
        f.answer = this.answer.getText().toString();

        return f;
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();

        this.databaseHelper.close();
    }

    @Override
    public void onClick(View view) {

        Feedback newFeedback = this.getFeedback();
        int result = databaseHelper.updateData(newFeedback.uName,newFeedback.email,newFeedback.phone,newFeedback.answer,newFeedback.id);


    }
}
