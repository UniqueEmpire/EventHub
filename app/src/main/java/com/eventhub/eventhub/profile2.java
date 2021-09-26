package com.eventhub.eventhub;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class profile2 extends AppCompatActivity {
    TextView time1,time2;
    EditText compname,desc,editloc;
    ToggleButton mon,tue,wed,thu,fri,sat,sun;
    Button createbtn,changebtn,deletebtn;
    int time1hour, time2hour, time1min, time2min;
    String managername,managerphn,officephn,officeaddress,ofemail;
    String comname,manname,manphnnum,offlandnum,offadd,catertype,deltype,opentime,clstime,des,opendays,location;
    private DatabaseReference databaseRef;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        comname = getIntent().getStringExtra("company_name");
        manname = getIntent().getStringExtra("manager_name");
        manphnnum = getIntent().getStringExtra("manager_phone");
        offlandnum = getIntent().getStringExtra("office_phone");
        offadd = getIntent().getStringExtra("office_address");
        catertype = getIntent().getStringExtra("catering_type");
        deltype = getIntent().getStringExtra("delivery_type");

        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        desc= findViewById(R.id.ed_des);
        mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tue);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        sun = findViewById(R.id.sun);
        editloc = findViewById(R.id.ed_loca);

        createbtn = findViewById(R.id.button2);

        des=desc.getText().toString();
        location=editloc.getText().toString();

        time1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(profile2.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                            time1hour=hourOfDay;
                            time1min = minute;
                            String otime = time1hour+":"+time1min;
                            try {
                                Date date = new SimpleDateFormat("HH:mm").parse(otime);
                                SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                time1.setText(f12hours.format(date));
                                opentime=otime;
                            }
                            catch (ParseException e){
                                e.printStackTrace();
                            }
                        }
                    },12,0,false
                );
                timePickerDialog.updateTime(time2hour,time2min);

                timePickerDialog.show();
            }
        });

        time2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(profile2.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                time2hour=hourOfDay;
                                time2min = minute;
                                String ctime = time2hour+":"+time2min;

                                try {
                                    Date date = new SimpleDateFormat("HH:mm").parse(ctime);
                                    SimpleDateFormat f12hors = new SimpleDateFormat("hh:mm aa");
                                    time2.setText(f12hors.format(date));
                                    clstime=ctime;
                                }
                                catch (ParseException e){
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.updateTime(time2hour,time2min);
                timePickerDialog.show();
            }
        });



        if(mon.isChecked() && tue.isChecked() && wed.isChecked() && thu.isChecked() && fri.isChecked() && !sat.isChecked() && !sun.isChecked()){
            opendays="Weekdays";
        }else if(!mon.isChecked() && !tue.isChecked() && !wed.isChecked() && !thu.isChecked() && !fri.isChecked() && sat.isChecked() && sun.isChecked()){
            opendays="Weekdays";
        }else{
            opendays=mon+","+tue+","+wed+","+thu+","+fri+","+sat+","+sun;
        }

        if(isDescriptionValid(des)){
            Toast.makeText(getApplicationContext(),"Your description is too long" , Toast.LENGTH_LONG).show();
        }
        String ID=comname;
        createbtn.setOnClickListener(new View.OnClickListener() {
            modelcatering modelcatering = new modelcatering(comname, manname, manphnnum, offlandnum, offadd, catertype, deltype, opentime, clstime, des, opendays,location);
            @Override
            public void onClick(View v) {
                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseRef.child(comname).setValue(modelcatering);
                        Toast.makeText(getApplicationContext(),"Your business is added" , Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Your business is not added" , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
//        databaseRef = firebaseDatabase.getReference("catering").child(ID);
//        changebtn.setOnClickListener(new View.OnClickListener() {
//            modelcatering modelcatering = new modelcatering(comname, manname, manphnnum, offlandnum, offadd, catertype, deltype, opentime, clstime, des, opendays,location);
//            @Override
//            public void onClick(View v) {
//                HashMap<String,Object> map = new HashMap<>();
//                map.put("comname",comname);
//                map.put("manname",manname);
//                map.put("manphnnum",manphnnum);
//                map.put("offlandnum",offlandnum);
//                map.put("offadd",offadd);
//                map.put("deltype",deltype);
//                map.put("opentime",opentime);
//                map.put("clstime",clstime);
//                map.put("des",des);
//                map.put("opendays",opendays);
//                map.put("location",location);
//                databaseRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        databaseRef.updateChildren(map);
//                        Toast.makeText(getApplicationContext(),"Your business is updated" , Toast.LENGTH_LONG).show();
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(getApplicationContext(),"Your business is not updated" , Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
//
//        deletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                databaseRef.removeValue();
//                Toast.makeText(getApplicationContext(),"Your business is deleted" , Toast.LENGTH_LONG).show();
//                startActivity(new Intent(profile2.this,Home.class));
//            }
//        });
    }

    public boolean isDescriptionValid(String chars) {
        if (chars.length() == 200) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isPhoneNumberValid(String chars) {
        if (chars.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

}
