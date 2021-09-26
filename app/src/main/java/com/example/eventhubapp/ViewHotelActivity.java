package com.example.eventhubapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewHotelActivity extends AppCompatActivity {
    private TextView hotelNameTV,hotelAddressTV,hotelNumberTV,hotelEmailTV,hotelDescriptionTV;
    private Button viewPriceBtn,viewGalleryBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private HotelRVModal hotelRVModal;
    private String hotelID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hotel);

        firebaseDatabase = FirebaseDatabase.getInstance();

        hotelNameTV =findViewById(R.id.idhotelNameTV);
        hotelAddressTV =findViewById(R.id.idhotelAddressTV);
        hotelNumberTV =findViewById(R.id.idhotelNumberTV);
        hotelEmailTV =findViewById(R.id.idhotelEmailTV);
        hotelDescriptionTV =findViewById(R.id.idhotelDescriptionTV);

        viewPriceBtn =findViewById(R.id.idBtnViewPrice);
        viewGalleryBtn =findViewById(R.id.idBtnViewGallery);


        hotelRVModal = getIntent().getParcelableExtra("hotel");
        hotelID = hotelRVModal.getHotelID();


        //ArrayList<String> list = new ArrayList<>();
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.hotel_details,list);
        //listView.setAdapter(adapter);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Hotels").child(hotelID);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    list.add(dataSnapshot.getValue().toString());*/
                String name=snapshot.child("hotelName").getValue().toString();
                String address=snapshot.child("hotelAddress").getValue().toString();
                String number=snapshot.child("hotelNumber").getValue().toString();
                String email=snapshot.child("hotelEmail").getValue().toString();
                String description=snapshot.child("hotelDescription").getValue().toString();
                hotelNameTV.setText(name);
                hotelAddressTV.setText(address);
                hotelNumberTV.setText(number);
                hotelEmailTV.setText(email);
                hotelDescriptionTV.setText(description);
                viewPriceBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(hotelRVModal.getHotelPrice()));
                        startActivity(i);
                    }
                });


            /*}
                adapter.notifyDataSetChanged();*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewHotelActivity.this,"Fail to load details..",Toast.LENGTH_SHORT).show();

            }
        });
        viewPriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(hotelRVModal.getHotelPrice()));
                startActivity(i);
            }
        });
    }
}