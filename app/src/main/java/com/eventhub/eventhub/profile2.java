package com.eventhub.eventhub;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class profile2 extends AppCompatActivity {
    TextView time1,time2;
    ///EditText time1,time2;
    int time1hour, time2hour, time1min, time2min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);

        time1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(profile2.this,
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
    }

}
