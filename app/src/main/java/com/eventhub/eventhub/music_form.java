package com.eventhub.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class music_form extends AppCompatActivity {

    EditText manname,manphonenum,offnum,offadd,offemail;
    Button btn;
    String managername,managerphn,officephn,officeaddress,ofemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_form);

        manname=findViewById(R.id.et_managename1);
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

//        if((!isPhnValid(officephn))||(!isPhnValid(managerphn))||(!isAddValid(officeaddress))||(!isEmailValid(ofemail))){
//        if(isPhnValid(officephn)==true){
//            Toast.makeText(getApplicationContext(),"Your landline number length is invalid" , Toast.LENGTH_LONG).show();
//        }
//        else if(isPhnValid(managerphn)==false){
//            Toast.makeText(getApplicationContext(),"Manager's phone number length is invalid" , Toast.LENGTH_LONG).show();
//        }
//        else if(isAddValid(officeaddress)==false){
//            Toast.makeText(getApplicationContext(),"Your address length is high" , Toast.LENGTH_LONG).show();
//        }
//        else if(isEmailValid(ofemail)==false){
//            Toast.makeText(getApplicationContext(),"Your email is invalid" , Toast.LENGTH_LONG).show();
//        }
//        }
//        else {
            Intent intent = new Intent(this, music_form2.class);
            intent.putExtra("manager_name", managername);
            intent.putExtra("manager_phone", managerphn);
            intent.putExtra("office_phone", officephn);
            intent.putExtra("office_address", officeaddress);
            intent.putExtra("office_email", ofemail);
            String msg = "hi "+managername;
        Toast.makeText(getApplicationContext(),msg , Toast.LENGTH_LONG).show();
            //startActivity(intent);
//        }
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
//        if (chars.length() <= 200) {
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
    public boolean isPhnValid(String c) {
        if (c.length() == 10) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isAddValid(String c) {
        if (c.length() <= 100) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isEmailValid(String c) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return c.matches(regex);
    }
//    static boolean isValid(String email) {
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//        return email.matches(regex);
//    }

}