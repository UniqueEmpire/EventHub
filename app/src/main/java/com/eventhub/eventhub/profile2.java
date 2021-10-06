package com.eventhub.eventhub;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class profile2 extends AppCompatActivity {
    TextView time1,time2;
    ImageView imgview,imgselect;
    EditText compname,desc,editloc;
    ToggleButton mon,tue,wed,thu,fri,sat,sun;
    Button createbtn,changebtn,deletebtn;
    int time1hour, time2hour, time1min, time2min;
    String comname,manname,manphnnum,offlandnum,offadd,catertype,deltype,location,dwnloadurl;
    private DatabaseReference databaseRef;
    FirebaseDatabase firebaseDatabase;
    private StorageReference mStoreageRef;
    ProgressDialog progressDialog;
    String opendays;
    int flag=0;
    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        progressDialog=new ProgressDialog(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef = firebaseDatabase.getReference("catering");
        mStoreageRef = FirebaseStorage.getInstance().getReference("caterlogos");

        comname = getIntent().getStringExtra("company_name");
        manname = getIntent().getStringExtra("manager_name");
        manphnnum = getIntent().getStringExtra("manager_phone");
        offlandnum = getIntent().getStringExtra("office_phone");
        offadd = getIntent().getStringExtra("office_address");
        catertype = getIntent().getStringExtra("catering_type");
        deltype = getIntent().getStringExtra("delivery_type");

        imgview = findViewById(R.id.imageView3);
        imgselect = findViewById(R.id.imageView);

        time1 = findViewById(R.id.time12);
        time2 = findViewById(R.id.time22);
        desc= findViewById(R.id.ed_des);
        mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tue);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        sun = findViewById(R.id.sun);
        editloc = findViewById(R.id.ed_loca);

        createbtn = findViewById(R.id.createbtn1);

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

        imgselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filechooser();
            }
        });


        //String ID=databaseRef.push().getKey();

        //String msg = comname+","+manname+","+manphnnum+","+offlandnum+","+offadd+","+catertype+","+deltype+","+opentime+","+clstime+","+des+","+opendays+","+location;

        /*changebtn.setOnClickListener(new View.OnClickListener() {
            modelcatering modelcatering = new modelcatering(comname, manname, manphnnum, offlandnum, offadd, catertype, deltype, opentime, clstime, des, opendays,location);
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("comname",comname);
                map.put("manname",manname);
                map.put("manphnnum",manphnnum);
                map.put("offlandnum",offlandnum);
                map.put("offadd",offadd);
                map.put("deltype",deltype);
                map.put("opentime",opentime);
                map.put("clstime",clstime);
                map.put("des",des);
                map.put("opendays",opendays);
                map.put("location",location);
                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseRef.updateChildren(map);
                        Toast.makeText(getApplicationContext(),"Your business is updated" , Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Your business is not updated" , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });*/

/*        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRef.removeValue();
                Toast.makeText(getApplicationContext(),"Your business is deleted" , Toast.LENGTH_LONG).show();
                startActivity(new Intent(profile2.this,Home.class));
            }
        });*/
    }
    public boolean isPhoneNumberValid(String chars) {
        if (chars.length() == 10) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isDescriptionValid(String chars) {
        if (chars.length() == 200) {
            return true;
        } else {
            return false;
        }
    }


    private void filechooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imguri=data.getData();
            imgview.setImageURI(imguri);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    public void createData(){
        String opentime=time1.getText().toString();
        String clstime=time2.getText().toString();
        String des=desc.getText().toString();
        location=editloc.getText().toString();
        String ID=databaseRef.push().getKey();
        String logourl =dwnloadurl;

        opendays="";
        String d0="",d1="",d2="",d3="",d4="",d5="",d6="";
        if(mon.isChecked() && tue.isChecked() && wed.isChecked() && thu.isChecked() && fri.isChecked() && !sat.isChecked() && !sun.isChecked()){
            opendays="Weekdays";
        }else if(!mon.isChecked() && !tue.isChecked() && !wed.isChecked() && !thu.isChecked() && !fri.isChecked() && sat.isChecked() && sun.isChecked()){
            opendays="Weekend";
        }else if (mon.isChecked() && tue.isChecked() && wed.isChecked() && thu.isChecked() && fri.isChecked() && sat.isChecked() && sun.isChecked()){
            opendays="Everyday";
        }
        else{
            if(mon.isChecked()){
                d0="Monday ";
            }
            if(tue.isChecked()){
                d1="Tuesday ";
            }
            if(wed.isChecked()){
                d2="Wednesday ";
            }
            if(thu.isChecked()){
                d3="Thursday ";
            }
            if(fri.isChecked()){
                d4="Friday ";
            }
            if(sat.isChecked()){
                d5="Saturday ";
            }
            if(sun.isChecked()){
                d6="Sunday ";
            }
            opendays=d0+""+d1+""+d2+""+d3+""+d4+""+d5+""+d6;
        }

        if(opentime.equals("Select open time")||clstime.equals("Select close time")||des.isEmpty()||opendays.isEmpty()||location.isEmpty()){
            Toast.makeText(profile2.this,"Please fill all the fields",Toast.LENGTH_LONG).show();
        }
        else if(isDescriptionValid(des)){
            Toast.makeText(getApplicationContext(),"Your description is too long" , Toast.LENGTH_LONG).show();
        }
        else {
            modelcatering modelcatering = new modelcatering(comname, manname, manphnnum, offlandnum, offadd, catertype, deltype, opentime, clstime, des, opendays,location,ID,logourl);
            String msg = comname + "\n" + manname + "\n" + manphnnum + "\n" + offlandnum + "\n" + offadd + "\n" + catertype + "\n" + deltype + "\n" + opentime + "\n" + clstime + "\n" + opendays + "\n" + des + "\n" + location;
            Toast.makeText(profile2.this, "Hii" + msg, Toast.LENGTH_LONG).show();
            databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseRef.child(ID).setValue(modelcatering);
                    Toast.makeText(getApplicationContext(),"Your business is added" , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(profile2.this,catering.class);
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(),"Your business is not added" , Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void uploadimg(View view){
        if (imguri!=null&&flag==0){
            progressDialog.show();
            StorageReference fileReference = mStoreageRef.child(System.currentTimeMillis()+"."+getFileExtension(imguri));
            fileReference.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(profile2.this,"Upload successful",Toast.LENGTH_LONG).show();
                            fileReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    dwnloadurl = task.getResult().toString();
                                    flag=1;
                                    createData();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(profile2.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double pr = (100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploading "+(int)pr+"%");
                        }
                    });
        }
        else if(imguri!=null&&flag==1){
            createData();
        }
        else{
            Toast.makeText(this,"No file selected",Toast.LENGTH_LONG).show();
        }
    }
}