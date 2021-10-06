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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class caterupdate extends AppCompatActivity {
    EditText compname,mangname,mangphn,offland,offaddress,offloca,offdes;
    ImageView imgview,imgselect;
    TextView opntime,clostime;
    Spinner cattype,delitype;
    Button updatebtn;
    String cat,del,dwnloadurl;
    int time1hour, time2hour, time1min, time2min;
    private DatabaseReference databaseRef;
    FirebaseDatabase firebaseDatabase;
    private StorageReference mStoreageRef;
    ProgressDialog progressDialog;
    int flag=0;
    public Uri imguri;

    modelcatering modelcatering;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterupdate);

        //intent.putExtra("update_data",modelcatering);
        modelcatering=getIntent().getParcelableExtra("update_data");
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(this);
        String ID =modelcatering.getCaterID();
        databaseRef = firebaseDatabase.getReference("catering").child(ID);
        mStoreageRef = FirebaseStorage.getInstance().getReference("caterlogos");

        compname=findViewById(R.id.ct_compname);
        mangname=findViewById(R.id.ct_managename);
        mangphn=findViewById(R.id.ct_phnnum);
        offland=findViewById(R.id.ct_ofnum);
        offaddress=findViewById(R.id.ct_address);
        cattype=findViewById(R.id.spinner3);
        delitype=findViewById(R.id.spinner4);
        imgview=findViewById(R.id.et_imgView);
        imgselect=findViewById(R.id.et_chooseimg);
        opntime=findViewById(R.id.et_cttime1);
        clostime=findViewById(R.id.et_cttime2);
        offloca=findViewById(R.id.et_loca);
        offdes=findViewById(R.id.et_des);
        updatebtn=findViewById(R.id.ct_updatebtn);

        compname.setText(modelcatering.getComname());
        mangname.setText(modelcatering.getManname());
        mangphn.setText(modelcatering.getManphnnum());
        offland.setText(modelcatering.getOfflandnum());
        offaddress.setText(modelcatering.getOffadd());

        cattype=findViewById(R.id.spinner3);
        delitype=findViewById(R.id.spinner4);
        Picasso.get().load(modelcatering.getLogourl()).into(imgview);

        opntime.setText(modelcatering.getOpentime());
        clostime.setText(modelcatering.getClstime());
        offloca.setText(modelcatering.getLocation());
        offdes.setText(modelcatering.getDes());

        String temp2="Off-premise";
        cattype.setSelection(2,true);
        /*if(modelcatering.getCatertype().equals("On-premise")) {

            deltype.setEnabled(false);
        }
        else if(temp2.equals("Off-premise")) {
            cattype.setSelection(2);
            deltype.setEnabled(true);
            if(modelcatering.getCatertype().equals("On-premise")) {
                cattype.setSelection(1);
                deltype.setEnabled(false);
            }
            if(modelcatering.getCatertype().equals("On-premise")) {
                cattype.setSelection(1);
                deltype.setEnabled(false);
            }
        }*/


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.catertype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.delivertype, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        cattype.setAdapter(adapter);
        cattype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                String text = arg0.getItemAtPosition(position).toString();
                if (position > 0) {

                    //Toast.makeText(getApplicationContext(),text , Toast.LENGTH_LONG).show();
                    cat = text;
                    delitype.setEnabled(true);
                    if(cat.equals("On-premise")){
                        delitype.setEnabled(false);
                    }
                    else{
                        delitype.setEnabled(true);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        delitype.setAdapter(adapter2);
        delitype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        opntime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(caterupdate.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        time1hour=hourOfDay;
                        time1min = minute;
                        String otime = time1hour+":"+time1min;
                        try {
                            Date date = new SimpleDateFormat("HH:mm").parse(otime);
                            SimpleDateFormat f12hours = new SimpleDateFormat("hh:mm aa");
                            opntime.setText(f12hours.format(date));
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

        clostime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(caterupdate.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        time2hour=hourOfDay;
                        time2min = minute;
                        String ctime = time2hour+":"+time2min;

                        try {
                            Date date = new SimpleDateFormat("HH:mm").parse(ctime);
                            SimpleDateFormat f12hors = new SimpleDateFormat("hh:mm aa");
                            clostime.setText(f12hors.format(date));
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

    public void updateData(){
        String comname = compname.getText().toString();
        String manname = mangname.getText().toString();
        String manphnnum = mangphn.getText().toString();
        String offlandnum = offland.getText().toString();
        String offadd = offaddress.getText().toString();
        String catertype=cat;
        String deltype=del;
        String opentime=opntime.getText().toString();
        String clstime=clostime.getText().toString();
        String des=offdes.getText().toString();
        String location=offloca.getText().toString();
        String logourl=dwnloadurl;

        HashMap<String,Object> map = new HashMap<>();
        map.put("comname",comname);
        map.put("manname",manname);
        map.put("managerphn",manphnnum);
        map.put("officephn",offlandnum);
        map.put("offadd",offadd);
        map.put("catertype",catertype);
        map.put("deltype",deltype);
        map.put("opentime",opentime);
        map.put("clstime",clstime);
        map.put("des",des);
        map.put("logourl",logourl);
        map.put("location",location);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseRef.updateChildren(map);
                Toast.makeText(getApplicationContext(),"Your Music Band is updated" , Toast.LENGTH_LONG).show();
                Intent intent =new Intent(caterupdate.this,catering.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Your music band is not updated" , Toast.LENGTH_LONG).show();
            }
        });
    }
    public void updFile(View view){
        if (imguri!=null){
            progressDialog.show();
            StorageReference fileReference = mStoreageRef.child(System.currentTimeMillis()+"."+getFileExtension(imguri));
            fileReference.putFile(imguri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(caterupdate.this,"Upload successful",Toast.LENGTH_LONG).show();
                            fileReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    dwnloadurl = task.getResult().toString();
                                    updateData();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(caterupdate.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double pr = (100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploading "+(int)pr+"%");
                }
            });
        }
        else{
            Toast.makeText(this,"No file selected",Toast.LENGTH_LONG).show();
        }
    }
}