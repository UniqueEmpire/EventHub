package com.eventhub.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    EditText email,pwd;
    Button btn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.edit_Email);
        pwd=findViewById(R.id.edit_password);
        btn=findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logemail = email.getText().toString();
                String logpwd = pwd.getText().toString();

                if (TextUtils.isEmpty(logemail)||TextUtils.isEmpty(logpwd)){
                    Toast.makeText(login.this,"Please input the credentials",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.signInWithEmailAndPassword(logemail,logpwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent i1 = new Intent(login.this, Home.class);
                                startActivity(i1);
                                finish();
                            }
                            else{
                                Toast.makeText(login.this,"User Login Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

 /*   @Override
     protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null){
            Intent i =new Intent(login.this,Home.class);
            startActivity(i);
            this.finish();
        }
    }*/

    public void signup(View view){

        //Go to the next activity
        Intent intent = new Intent(this,signup.class);

        //Start the activity 02
        startActivity(intent);
    }
}
