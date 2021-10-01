package com.eventhub.eventhub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
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

public class music_form2 extends AppCompatActivity {

    TextView time1,time2,offloca;
    ImageView imgselect,imgview;
    int PLACE_PICKER_REQUEST = 1;
    EditText compyname,desc;
    Button create,update,delete;
    String managername,managerphn,officephn,officeaddress,ofemail,dwnloadurl;
    int time1hour, time2hour, time1min, time2min;
    public Uri imguri;

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseRef;
    private StorageReference mStoreageRef;
    ProgressDialog progressDialog;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_form2);
        progressDialog=new ProgressDialog(this);
        create = findViewById(R.id.createbtn);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef = firebaseDatabase.getReference("bands");
        mStoreageRef = FirebaseStorage.getInstance().getReference("bandlogos");

        managername = getIntent().getStringExtra("manager_name");
        managerphn = getIntent().getStringExtra("manager_phone");
        officephn = getIntent().getStringExtra("office_phone");
        officeaddress = getIntent().getStringExtra("office_address");
        ofemail = getIntent().getStringExtra("office_email");

        compyname=findViewById(R.id.compname);
        offloca=findViewById(R.id.muloca);
        desc=findViewById(R.id.etmu_des);
        //compyname.setText(managername);
        time1=findViewById(R.id.mutime1);
        time2=findViewById(R.id.mutime2);

        imgselect = findViewById(R.id.chooseimg);
        imgview = findViewById(R.id.imgView);

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
                timePickerDialog.updateTime(time1hour,time1min);

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

        imgselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filechooser();
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

    public void uploadFile(View view){
        if (imguri!=null&&flag==0){
            progressDialog.show();
            StorageReference fileReference = mStoreageRef.child(System.currentTimeMillis()+"."+getFileExtension(imguri));
            fileReference.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(music_form2.this,"Upload successful",Toast.LENGTH_LONG).show();
                            fileReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    dwnloadurl = task.getResult().toString();
                                    flag=1;
                                    submit();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(music_form2.this,e.getMessage(),Toast.LENGTH_LONG).show();
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
            submit();
        }
        else{
            Toast.makeText(this,"No file selected",Toast.LENGTH_LONG).show();
        }
    }
    public void submit (){
        String bandname = compyname.getText().toString();
        String opentime = time1.getText().toString();
        String clstime = time2.getText().toString();
        //String location = offloca.getText().toString();
        String des = desc.getText().toString();
        String location="Colombo";
        //desc.setText(dwnloadurl);
        String logourl =dwnloadurl;
        String ID=databaseRef.push().getKey();
        if(bandname.isEmpty() || opentime.isEmpty()||clstime.isEmpty()||des.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill all the fields !", Toast.LENGTH_LONG).show();
        }
        else {
            String msg = " " + managername + "\n" + managerphn + "\n" + officephn + "\n" + officeaddress + "\n" + ofemail + "\n" + bandname + "\n" + opentime + "\n" + clstime + "\n" + location + "\n" + des + "\n" + logourl;
            //Toast.makeText(getApplicationContext(), "Hii"+msg, Toast.LENGTH_LONG).show();
            MusicModel musicModel = new MusicModel(managername, managerphn, officephn, officeaddress, ofemail, bandname, opentime, clstime, location, des, logourl);

            databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseRef.child(ID).setValue(musicModel);
                    Toast.makeText(getApplicationContext(), "Your music band is added", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(music_form2.this, music.class);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Your music band is not added", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}

