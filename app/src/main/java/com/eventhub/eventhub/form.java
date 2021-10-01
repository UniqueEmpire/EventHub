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

    EditText comname,manname,manphonenum,offnum,offadd,offemail;
    String compname,manaagername;
    String managerphn,officephn,officeaddress,ofemail,cat,del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        comname=findViewById(R.id.compname);
        manname=findViewById(R.id.et_managename);
        manphonenum=findViewById(R.id.et_phnnum);
        offnum=findViewById(R.id.et_ofnum);
        offadd=findViewById(R.id.et_address);

        compname = comname.getText().toString();
        manaagername = manname.getText().toString();
        managerphn = manphonenum.getText().toString();
        officephn = offnum.getText().toString();
        officeaddress = offadd.getText().toString();



        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.catertype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.delivertype, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                String text = arg0.getItemAtPosition(position).toString();
                if(position >= 0){
                    Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
                    cat=text;
                }else{
                    Toast.makeText(getApplicationContext(),"Select catering type" , Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        spin2.setAdapter(adapter2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                String text = arg0.getItemAtPosition(position).toString();
                if(position >= 0){
                    Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
                    del=text;
                }else{
                    Toast.makeText(getApplicationContext(),"Select delivery type" , Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
    }


    public void proceed (View view) {
        Intent intent = new Intent(this, profile2.class);
        intent.putExtra("company_name", compname);
        intent.putExtra("manager_name", manaagername);
        intent.putExtra("manager_phone", managerphn);
        intent.putExtra("office_phone", officephn);
        intent.putExtra("office_address", officeaddress);
        intent.putExtra("catering_type", cat);
        intent.putExtra("delivery_type", del);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}