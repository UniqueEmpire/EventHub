package com.eventhub.eventhub;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.eventhub.eventhub.DataBase.DBHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class signup extends AppCompatActivity {
    EditText eText;
    EditText email,pwd,repwd;
    Button btn;
    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        email = findViewById(R.id.et_email);
        pwd = findViewById(R.id.et_pwd);
        repwd = findViewById(R.id.et_repwd);
        btn= findViewById(R.id.btn_signup);
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String e_mail = email.getText().toString();
                String password = pwd.getText().toString();
                String repassword = repwd.getText().toString();

                if(!password.equals(repassword)){
                    Toast.makeText(signup.this,"Please check the passwords",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(e_mail)||TextUtils.isEmpty(password)||TextUtils.isEmpty(repassword)){
                    Toast.makeText(signup.this,"Please add your credentials!!",Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(e_mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(signup.this,"User Registered :)",Toast.LENGTH_SHORT).show();
                                Intent i =new Intent(signup.this,login.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(signup.this,"User Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

//    public void addData (View view){
//          DBHandler dbHandler = new DBHandler(this);
//
//          long val=-1;
//          if(et_pwd.equals(et_repwd)){
//              val = dbHandler.addInfo(et_email.getText().toString(),et_pwd.getText().toString());
//              if(val>0){
//                  Toast.makeText(this,"Account Successfully Created",Toast.LENGTH_SHORT).show();
//              }
//              else{
//                  Toast.makeText(this,"Account Not Created",Toast.LENGTH_SHORT).show();
//              }
//          }
//          else{
//              Toast.makeText(this,"Password mismatch",Toast.LENGTH_SHORT).show();
//          }
//    }


    public void login(View view){

        //Go to the next activity
        Intent intent2 = new Intent(this,login.class);

        //Start the activity 02
        startActivity(intent2);
    }
    private void updateUI(FirebaseUser user) {

    }
}
