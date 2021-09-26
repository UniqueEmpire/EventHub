package com.eventhub.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class music_form extends AppCompatActivity {

    EditText manname,manphonenum,offnum,offadd,offemail;
    Button btn;
    String managername,managerphn,officephn,officeaddress,ofemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_form);

        manname=findViewById(R.id.et_managename);
        manphonenum=findViewById(R.id.et_phnnum);
        offnum=findViewById(R.id.et_ofnum);
        offadd=findViewById(R.id.et_address);
        offemail=findViewById(R.id.et_muemail);

         managername = manname.getText().toString();
         managerphn = manphonenum.getText().toString();
         officephn = offnum.getText().toString();
         officeaddress = offadd.getText().toString();
         ofemail = offemail.getText().toString();

    }

    public void munext(View view){

        Intent intent = new Intent(getBaseContext(), music_form2.class);
        intent.putExtra("manager_name", managername);
        intent.putExtra("manager_phone", managerphn);
        intent.putExtra("office_phone", officephn);
        intent.putExtra("office_address", officeaddress);
        intent.putExtra("office_email", ofemail);

        startActivity(intent);
    }
//    public boolean isDescriptionValid(String chars) {
//        if (chars.length() == 200) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public boolean isAddress(String chars) {
//        if (chars.length() == 200) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//
//    public boolean isDesValid(String c) {
//        if (c.length() == 100) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    public boolean isAddValid(String c) {
//        if (c.length() == 100) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}