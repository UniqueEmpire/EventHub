package com.eventhub.eventhub;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.eventhub.eventhub.DataBase.DBHandler;

import java.util.Calendar;

public class signup extends AppCompatActivity {
    EditText eText;
    EditText et_email;
    EditText et_pwd;
    EditText et_repwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_email = findViewById(R.id.et_email);
        et_pwd = findViewById(R.id.et_pwd);
        et_repwd = findViewById(R.id.et_repwd);

    }

    public void addData (View view){
          DBHandler dbHandler = new DBHandler(this);

          long val=-1;
          if(et_pwd.equals(et_repwd)){
              val = dbHandler.addInfo(et_email.getText().toString(),et_pwd.getText().toString());
              if(val>0){
                  Toast.makeText(this,"Account Successfully Created",Toast.LENGTH_SHORT).show();
              }
              else{
                  Toast.makeText(this,"Account Not Created",Toast.LENGTH_SHORT).show();
              }
          }
          else{
              Toast.makeText(this,"Password mismatch",Toast.LENGTH_SHORT).show();
          }
    }

    public void login(View view){

        //Go to the next activity
        Intent intent2 = new Intent(this,login.class);

        //Start the activity 02
        startActivity(intent2);
    }
}
