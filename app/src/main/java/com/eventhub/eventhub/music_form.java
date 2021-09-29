package com.eventhub.eventhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class music_form extends AppCompatActivity {

    EditText manname,manphonenum,offnum,offadd,offemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_form);

        manname=findViewById(R.id.et_managename1);
        manphonenum=findViewById(R.id.et_phnnum1);
        offnum=findViewById(R.id.et_ofnum1);
        offadd=findViewById(R.id.et_address1);
        offemail=findViewById(R.id.et_muemail1);
    }

    public void munext(View view){
        String managername = manname.getText().toString();
        String managerphn = manphonenum.getText().toString();
        String officephn = offnum.getText().toString();
        String officeaddress = offadd.getText().toString();
        String ofemail = offemail.getText().toString();

       /* if((!isPhnValid(officephn))||(!isPhnValid(managerphn))||(!isAddValid(officeaddress))||(!isEmailValid(ofemail))){
            if(!isPhnValid(managerphn)){
                Toast.makeText(getApplicationContext(),"Manager's phone number length is invalid" , Toast.LENGTH_LONG).show();
            }
            else if(!isPhnValid(officephn)){
                Toast.makeText(getApplicationContext(),"Your landline number length is invalid" , Toast.LENGTH_LONG).show();
            }

            else if(!isAddValid(officeaddress)){
                Toast.makeText(getApplicationContext(),"Your address length is invalid" , Toast.LENGTH_LONG).show();
            }
            else if(!isEmailValid(ofemail)){
                Toast.makeText(getApplicationContext(),"Your email is invalid" , Toast.LENGTH_LONG).show();
            }
        }
        else {*/
            Intent intent = new Intent(this, music_form2.class);
            intent.putExtra("manager_name", managername);
            intent.putExtra("manager_phone", managerphn);
            intent.putExtra("office_phone", officephn);
            intent.putExtra("office_address", officeaddress);
            intent.putExtra("office_email", ofemail);
            String msg = managername+"\n"+managerphn+"\n"+officephn+"\n"+officeaddress+"\n"+ofemail;
            Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
            startActivity(intent);
//        }
    }
    /*public boolean isDescriptionValid(String chars) {
        if (chars.length() == 200) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAddress(String chars) {
        if (chars.length() <= 200) {
            return true;
        } else {
            return false;
        }
    }



    public boolean isDesValid(String c) {
        if (c.length() == 100) {
            return true;
        } else {
            return false;
        }
    }*/
    public boolean isPhnValid(String c) {
        if(c==null){
            return false;
        }
        else if (c.length() == 10) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isAddValid(String c) {
        if ((c.length() <= 100)&&(c.length() > 0)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isEmailValid(String email) {
        if(email==null){
            return false;
        }
        else{
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }
//    static boolean isValid(String email) {
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//        return email.matches(regex);
//    }

}