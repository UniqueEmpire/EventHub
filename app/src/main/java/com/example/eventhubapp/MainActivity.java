package com.example.eventhubapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HotelRVAdapter.HotelClickInterface{

    private RecyclerView hotelRV;
    private ProgressBar loadingPB;
    private FloatingActionButton addFAB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ArrayList<HotelRVModal> hotelRVModalArrayList;
    private RelativeLayout bottomSheetRL;
    private HotelRVAdapter hotelRVAdapter;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hotelRV = findViewById(R.id.idRVHotels);
        loadingPB = findViewById(R.id.idPBLoading);
        addFAB = findViewById(R.id.idAddFAB);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Hotels");
        hotelRVModalArrayList = new ArrayList<>();
        bottomSheetRL = findViewById(R.id.idRLBSheet);
        mAuth = FirebaseAuth.getInstance();
        hotelRVAdapter = new HotelRVAdapter(hotelRVModalArrayList,this,this);
        hotelRV.setLayoutManager(new LinearLayoutManager(this));
        hotelRV.setAdapter(hotelRVAdapter);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddHotelActivity.class));
            }
        });


        getAllHotels();
    }

    private void getAllHotels(){
        hotelRVModalArrayList.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                hotelRVModalArrayList.add(snapshot.getValue(HotelRVModal.class));
                hotelRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                hotelRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                loadingPB.setVisibility(View.GONE);
                hotelRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                loadingPB.setVisibility(View.GONE);
                hotelRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onHotelClick(int position) {
        displayBottomSheet(hotelRVModalArrayList.get(position));
    }

    private void displayBottomSheet(HotelRVModal hotelRVModal){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_dialog, bottomSheetRL);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView hotelNameTV = layout.findViewById(R.id.idTVHotelName);
        TextView hotelAddressTV = layout.findViewById(R.id.idTVHotelAddress);
        TextView hotelNumTV = layout.findViewById(R.id.idTVHotelNumber);
        TextView hoteldisTV = layout.findViewById(R.id.idTVDescription);
        ImageView hotelIV = layout.findViewById(R.id.idIVHotel);
        Button editBtn = layout.findViewById(R.id.idBtnEdit);
        //Button viewPriceBtn = layout.findViewById(R.id.idBtnViewPrice);
        Button button1 = layout.findViewById(R.id.idBtnViewMore);


        hotelNameTV.setText(hotelRVModal.getHotelName());
        hotelAddressTV.setText(hotelRVModal.getHotelAddress());
        hotelNumTV.setText(hotelRVModal.getHotelNumber());
        hoteldisTV.setText(hotelRVModal.getHotelDescription());
        Picasso.get().load(hotelRVModal.getHotelImage()).into(hotelIV);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,EditHotelActivity.class);
                i.putExtra("hotel",hotelRVModal);
                startActivity(i);
            }
        });

        /*viewPriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(hotelRVModal.getHotelPrice()));
                startActivity(i);
            }
        });*/

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ViewHotelActivity.class);
                i.putExtra("hotel",hotelRVModal);
                startActivity(i);

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.idLogOut:
                Toast.makeText(this,"User Logged Out..",Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent i = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




   /*
    @Override
    public boolean OnCrateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<MainActivity> options =
                new FirebaseRecyclerOptions.Builder<MainActivity>
    }*/


}