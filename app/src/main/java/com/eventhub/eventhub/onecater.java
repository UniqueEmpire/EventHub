package com.eventhub.eventhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class onecater extends AppCompatActivity {

    ImageView logo;
    TextView title,mangername,mgnnum,landnum,opntime,clstime,offadd,deltype,cattype,opendays,location;
    private DatabaseReference databaseRef;
    FirebaseDatabase firebaseDatabase;

    private modelcatering modelcatering;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onecater);

        //intent.putExtra("single_data",modelcatering);
        modelcatering = getIntent().getParcelableExtra("single_data");
        String ID = modelcatering.getCaterID();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef = firebaseDatabase.getReference("catering").child(ID);

        title = findViewById(R.id.tv_title);
        mangername=findViewById(R.id.tv_mngname);
        mgnnum = findViewById(R.id.textView22);
        landnum = findViewById(R.id.textView27);
        opntime= findViewById(R.id.textView32);
        clstime = findViewById(R.id.textView33);
        logo = findViewById(R.id.imageView9);
        offadd = findViewById(R.id.textView23);
        deltype = findViewById(R.id.textView19);
        cattype = findViewById(R.id.textView24);
        opendays = findViewById(R.id.textView26);
        location = findViewById(R.id.textView28);

        title.setText(modelcatering.getComname());
        mangername.setText(modelcatering.getManname());
        mgnnum.setText(modelcatering.getManphnnum());
        landnum.setText(modelcatering.getOfflandnum());
        opntime.setText(modelcatering.getOpentime());
        clstime.setText(modelcatering.getClstime());
        Picasso.get().load(modelcatering.getLogourl()).into(logo);
        offadd.setText(modelcatering.getOffadd());
        deltype.setText(modelcatering.getDeltype());
        cattype.setText(modelcatering.getCatertype());
        opendays.setText(modelcatering.getOpendays());
        location.setText(modelcatering.getLocation());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.onecater,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item2) {
            Intent intent = new Intent(this, caterupdate.class);
            intent.putExtra("update_data",modelcatering);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.item3){
            delete();
        }
        return super.onOptionsItemSelected(item);
    }

    public void delete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(onecater.this);

        builder.setTitle("Alert !");
        builder.setMessage("Do you want to delete this advertisement ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseRef.removeValue();
                startActivity(new Intent(onecater.this,catering.class));
                finish();
                Toast.makeText(onecater.this, "Music band is deleted", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}