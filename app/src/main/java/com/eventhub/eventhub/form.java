package com.eventhub.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class form extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText comname, manname, manphonenum, offnum, offadd, offemail;
    //String compname, manaagername;
    //String managerphn,officephn,officeaddress,ofemail,
    String cat, del;
    Spinner spin,spin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        comname = findViewById(R.id.compname1);
        manname = findViewById(R.id.et_managename);
        manphonenum = findViewById(R.id.et_phnnum);
        offnum = findViewById(R.id.et_ofnum);
        offadd = findViewById(R.id.et_address);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.catertype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.delivertype, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                String text = arg0.getItemAtPosition(position).toString();
                if (position > 0) {
                    //Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
                    cat = text;
                    spin2.setEnabled(true);
                    if(cat.equals("On-premise")){
                        spin2.setEnabled(false);
                    }
                    else{
                        spin2.setEnabled(true);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

            spin2.setAdapter(adapter2);
            spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                    String text = arg0.getItemAtPosition(position).toString();
                    if (position > 0) {
                        // Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
                        del = text;
                    } else {
                        //Toast.makeText(getApplicationContext(), "Select delivery type", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
    }


    public void proceed(View view) {

        String compname = comname.getText().toString();
        String manaagername = manname.getText().toString();
        String managerphn = manphonenum.getText().toString();
        String officephn = offnum.getText().toString();
        String officeaddress = offadd.getText().toString();

         if((compname.isEmpty())||(manaagername.isEmpty())||(!isPhoneValid(officephn))||(!isPhoneValid(managerphn))||(!isAddress(officeaddress))||(cat==null)||((del==null)&&(spin2.isEnabled()))){
             if(compname.isEmpty()){
                 Toast.makeText(getApplicationContext(),"Company name is invalid" , Toast.LENGTH_LONG).show();
             }
             else if(manaagername.isEmpty()){
                 Toast.makeText(getApplicationContext(),"Manager's name is invalid" , Toast.LENGTH_LONG).show();
             }
             else if(!isPhoneValid(managerphn)){
                 Toast.makeText(getApplicationContext(),"Manager's phone number length is invalid" , Toast.LENGTH_LONG).show();
             }
            else if(!isPhoneValid(officephn)){
                Toast.makeText(getApplicationContext(),"Your landline number length is invalid" , Toast.LENGTH_LONG).show();
            }
            else if(!isAddress(officeaddress)){
                Toast.makeText(getApplicationContext(),"Your address length is invalid" , Toast.LENGTH_LONG).show();
            }
            else if(cat==null){
                 Toast.makeText(getApplicationContext(),"Please select the catering type" , Toast.LENGTH_LONG).show();
            }
            else if((del==null)&&(spin2.isEnabled())){
                Toast.makeText(getApplicationContext(),"Please select the delivery type" , Toast.LENGTH_LONG).show();
            }
         }
        else{
            Intent intent = new Intent(this, profile2.class);
            intent.putExtra("company_name", compname);
            intent.putExtra("manager_name", manaagername);
            intent.putExtra("manager_phone", managerphn);
            intent.putExtra("office_phone", officephn);
            intent.putExtra("office_address", officeaddress);
            intent.putExtra("catering_type", cat);
            intent.putExtra("delivery_type", del);
            String msg = compname + "\n" + manaagername + "\n" + managerphn + "\n" + officephn + "\n" + officeaddress + "\n" + cat + "\n" + del;
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
}


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    public boolean isPhoneValid(String chars) {
        if(chars==null){
            return false;
        }
        else if (chars.length() == 10) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isDescriptionValid(String chars) {
        if (chars.length() <= 200) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAddress(String chars) {
        if ((chars.length() <= 50)&&(chars.length()>0)) {
            return true;
        } else {
            return false;
        }
    }
}