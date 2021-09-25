package com.eventhub.eventhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signup(View view){

        //Go to the next activity
        Intent intent = new Intent(this,signup.class);

        //Start the activity 02
        startActivity(intent);
    }
    public void login(View view){

        //Go to the next activity
        Intent intent2 = new Intent(this,login.class);

        //Start the activity 02
        startActivity(intent2);
    }
    public void skip(View view){

        //Go to the next activity
        Intent intent3 = new Intent(this,Home.class);

        //Start the activity 02
        startActivity(intent3);
    }
}