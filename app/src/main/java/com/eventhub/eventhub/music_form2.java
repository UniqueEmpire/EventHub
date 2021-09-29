package com.eventhub.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.libraries.places.api.Places;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class music_form2 extends AppCompatActivity {

    TextView time1,time2,offloca;
    int PLACE_PICKER_REQUEST = 1;
    EditText compyname,desc;
    Button create,update,delete;
    String managername,managerphn,officephn,officeaddress,ofemail;

    private DatabaseReference databaseRef;
    private FirebaseDatabase firebaseDatabase;
    int time1hour, time2hour, time1min, time2min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_form2);
        create = findViewById(R.id.createbtn);

        managername = getIntent().getStringExtra("manager_name");
        managerphn = getIntent().getStringExtra("manager_phone");
        officephn = getIntent().getStringExtra("office_phone");
        officeaddress = getIntent().getStringExtra("office_address");
        ofemail = getIntent().getStringExtra("office_email");

        compyname=findViewById(R.id.compname);
        offloca=findViewById(R.id.muloca);
        desc=findViewById(R.id.etmu_des);
        compyname.setText(managername);
        time1=findViewById(R.id.mutime1);
        time2=findViewById(R.id.mutime2);

        time1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(music_form2.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                            time1hour=hourOfDay;
                            time1min = minute;
                            String time = time1hour+":"+time1min;
                            try {
                                Date date = new SimpleDateFormat("HH:mm").parse(time);
                                SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                                time1.setText(f12hours.format(date));
                            }
                            catch (ParseException e){
                                e.printStackTrace();
                            }
                        }
                },12,0,false);
                timePickerDialog.updateTime(time2hour,time2min);

                timePickerDialog.show();
            }
        });

        time2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(music_form2.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        time2hour=hourOfDay;
                        time2min = minute;
                        String time = time2hour+":"+time2min;
                        try {
                            Date date = new SimpleDateFormat("HH:mm").parse(time);
                            SimpleDateFormat f12hors = new SimpleDateFormat("hh:mm aa");
                            time2.setText(f12hors.format(date));
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



/*        databaseRef = firebaseDatabase.getReference("catering").child(ID);
        update.setOnClickListener(new View.OnClickListener() {
            MusicModel musicModel = new MusicModel(managername,managerphn, officephn, officeaddress, ofemail, comname,opentime, clstime,location, des);
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("comname",comname);
                map.put("managername",managername);
                map.put("managerphn",managerphn);
                map.put("officephn",officephn);
                map.put("officeaddress",officeaddress);
                map.put("ofemail",ofemail);
                map.put("opentime",opentime);
                map.put("clstime",clstime);
                map.put("des",des);
                map.put("location",location);
                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseRef.updateChildren(map);
                        Toast.makeText(getApplicationContext(),"Your Music Band is updated" , Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Your music band is not updated" , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });*/

/*        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRef.removeValue();
                Toast.makeText(getApplicationContext(),"Your music band is deleted" , Toast.LENGTH_LONG).show();
                startActivity(new Intent(music_form2.this,Home.class));
            }
        });*/

    }
    public void submit (View view){
        String bandname = compyname.getText().toString();
        //String opentime,,,;
        String opentime = time1.getText().toString();
        String clstime = time2.getText().toString();
        String location = offloca.getText().toString();
        String des = desc.getText().toString();
        String msg=" "+managername+"\n"+managerphn+"\n"+officephn+"\n"+officeaddress+"\n"+ofemail+"\n"+bandname+"\n"+opentime+"\n"+clstime+"\n"+location+"\n"+des;
        Toast.makeText(getApplicationContext(), "Hii"+msg, Toast.LENGTH_LONG).show();

        /*String ID=bandname;
        create.setOnClickListener(new View.OnClickListener() {
            MusicModel musicModel = new MusicModel(managername,managerphn, officephn, officeaddress, ofemail, bandname,opentime, clstime,location, des);
            @Override
            public void onClick(View v) {
                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseRef.child(ID).setValue(musicModel);
                        Toast.makeText(getApplicationContext(),"Your music band is added" , Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Your music band is not added" , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });*/
    }
}

