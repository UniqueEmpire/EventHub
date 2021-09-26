package com.example.eventhubapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddHotelActivity extends AppCompatActivity {

    private TextInputEditText hotelNameEdt, hotelAddressEdt, hotelNumberEdt, hotelEmailEdt, hotelImageEdt, priceLinkEdt, hotelDisEdt;
    CheckBox checkParking, checkAc, checkWifi, checkDeco;
    Facilities facilities;
    int i=0;
    private Button addHotelBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String hotelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel);

        hotelNameEdt = findViewById(R.id.idEdtHotelName);
        hotelAddressEdt = findViewById(R.id.idEdtHotelAddress);
        hotelNumberEdt = findViewById(R.id.idEdtContactNumber);
        hotelEmailEdt = findViewById(R.id.idEdtEmailAddress);
        hotelImageEdt = findViewById(R.id.idEdtHotelImageLink);
        priceLinkEdt = findViewById(R.id.idEdtPriceLink);
        hotelDisEdt = findViewById(R.id.idEdtHotelDis);

        /*facilities = new Facilities();
        checkParking = findViewById(R.id.idCheckParking);
        checkAc = findViewById(R.id.idCheckAc);
        checkWifi = findViewById(R.id.idCheckWifi);
        checkDeco = findViewById(R.id.idCheckDeco);*/

        addHotelBtn = findViewById(R.id.idBtnAddHotel);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Hotels");

        /*String checkParking = "checkParking";
        String checkAc = "checkAc";
        String checkWifi = "checkWifi";
        String checkDeco = "checkDeco";*/


        addHotelBtn.setOnClickListener(new View.OnClickListener() {
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

               /*CheckBox checkParking = (CheckBox)view.toString();
                if(checkParking.isChecked()){
                    facilities.setCheckParking(checkParking.toString());
                    databaseReference.child(String.valueOf(i+1)).setValue(facilities).toString();
                }
                CheckBox checkAc = (CheckBox)view;
                if(checkAc.isChecked()){
                    facilities.setCheckAc(checkAc.toString());
                    databaseReference.child(String.valueOf(i+1)).setValue(facilities);
                }
                CheckBox checkWifi = (CheckBox)view;
                if(checkWifi.isChecked()){
                    facilities.setCheckWifi(checkWifi.toString());
                    databaseReference.child(String.valueOf(i+1)).setValue(facilities);
                }
                CheckBox checkDeco = (CheckBox)view;
                if(checkDeco.isChecked()){
                    facilities.setCheckDeco(checkDeco.toString());
                    databaseReference.child(String.valueOf(i+1)).setValue(facilities);
                }*/



                hotelID = hotelName;
                HotelRVModal hotelRVModal = new HotelRVModal(hotelName,hotelAddress,hotelNumber,hotelEmail,hotelImage,hotelPrice,hotelDescription,hotelID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        /*if(snapshot.exists()){
                            i=(int)snapshot.getChildrenCount();
                        }*/
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(hotelID).setValue(hotelRVModal);
                        Toast.makeText(AddHotelActivity.this,"Hotel Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddHotelActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddHotelActivity.this, "Error is "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });

    }
}

