package com.eventhub.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signup(View view){

        //Go to the next activity
        Intent intent = new Intent(this,signup.class);

        //Start the activity 02
        startActivity(intent);
    }
}
