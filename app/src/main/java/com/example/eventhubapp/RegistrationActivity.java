package com.example.eventhubapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private TextInputEditText userNameEdt, pwdEdt, cnfPwdEdt;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userNameEdt = findViewById(R.id.idEdtUserName);
        pwdEdt = findViewById(R.id.idEdtPwd);
        cnfPwdEdt = findViewById(R.id.idEdtCnfPwd);
        registerBtn = findViewById(R.id.idBtnRegister);
        loadingPB = findViewById(R.id.idPBLoading);
        loginTV = findViewById(R.id.idTVLogin);
        mAuth = FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this,LogInActivity.class);
                startActivity(i);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                String cnfPwd = cnfPwdEdt.getText().toString();

                if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "Password is Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<6){
                    Toast.makeText(RegistrationActivity.this, "Password Length must be greater than 6 letters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd.equals(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "Please check both passwords", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //loadingPB.setVisibility(View.GONE);
                            Toast .makeText(RegistrationActivity.this, "User Registered..",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegistrationActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            //loadingPB.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "Failed to register user..",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}