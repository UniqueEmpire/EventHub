package com.example.eventhubapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditHotelActivity extends AppCompatActivity {

    private TextInputEditText hotelNameEdt, hotelAddressEdt, hotelNumberEdt,hotelEmailEdt, hotelImageEdt, priceLinkEdt, hotelDisEdt;
    private Button updateHotelBtn,deleteHotelBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String hotelID;
    private HotelRVModal hotelRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hotel);
        firebaseDatabase = FirebaseDatabase.getInstance();

        hotelNameEdt = findViewById(R.id.idEdtHotelName);
        hotelAddressEdt = findViewById(R.id.idEdtHotelAddress);
        hotelNumberEdt = findViewById(R.id.idEdtContactNumber);
        hotelEmailEdt = findViewById(R.id.idEdtEmailAddress);
        hotelImageEdt = findViewById(R.id.idEdtHotelImageLink);
        priceLinkEdt = findViewById(R.id.idEdtPriceLink);
        hotelDisEdt = findViewById(R.id.idEdtHotelDis);

        updateHotelBtn = findViewById(R.id.idBtnUpdateHotel);
        deleteHotelBtn = findViewById(R.id.idBtnDeleteHotel);
        loadingPB = findViewById(R.id.idPBLoading);

        hotelRVModal = getIntent().getParcelableExtra("hotel");
        if(hotelRVModal!=null){
            hotelNameEdt.setText(hotelRVModal.getHotelName());
            hotelAddressEdt.setText(hotelRVModal.getHotelAddress());
            hotelNumberEdt.setText(hotelRVModal.getHotelNumber());
            hotelEmailEdt.setText(hotelRVModal.getHotelEmail());
            hotelImageEdt.setText(hotelRVModal.getHotelImage());
            priceLinkEdt.setText(hotelRVModal.getHotelPrice());
            hotelDisEdt.setText(hotelRVModal.getHotelDescription());
            hotelID = hotelRVModal.getHotelID();
        }

        databaseReference = firebaseDatabase.getReference("Hotels").child(hotelID);
        updateHotelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String hotelName = hotelNameEdt.getText().toString();
                String hotelAddress = hotelAddressEdt.getText().toString();
                String hotelNumber = hotelNumberEdt.getText().toString();
                String hotelEmail = hotelEmailEdt.getText().toString();
                String hotelImage = hotelImageEdt.getText().toString();
                String hotelPrice = priceLinkEdt.getText().toString();
                String hotelDescription = hotelDisEdt.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("hotelName",hotelName);
                map.put("hotelAddress",hotelAddress);
                map.put("hotelNumber",hotelNumber);
                map.put("hotelEmail",hotelEmail);
                map.put("hotelImage",hotelImage);
                map.put("hotelPrice",hotelPrice);
                map.put("hotelDescription",hotelDescription);
                map.put("hotelID",hotelID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditHotelActivity.this,"Hotel Updated..",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditHotelActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditHotelActivity.this,"Fail to update..",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        deleteHotelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHotel();
            }
        });


    }

    private void deleteHotel(){
        databaseReference.removeValue();
        Toast.makeText(this, "Hotel Ad Deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditHotelActivity.this, MainActivity.class));

    }

}