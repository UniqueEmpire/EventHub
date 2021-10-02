package com.eventhub.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class music extends AppCompatActivity implements MusicRVAdapter.MusicClickInterface {

    private DatabaseReference databaseReference;
    private ArrayList <MusicModel> musicModelarrlist;
    private RelativeLayout bottomsheet;
    private MusicRVAdapter musicRVAdapter;
    Button edit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        RecyclerView recyclerView = findViewById(R.id.murecycler_view);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("bands");
        musicModelarrlist = new ArrayList<>();
        musicRVAdapter = new MusicRVAdapter(musicModelarrlist,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(musicRVAdapter);

        getAllbands();

    }

    private void getAllbands() {
        musicModelarrlist.clear();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                musicModelarrlist.add(snapshot.getValue(MusicModel.class));
                musicRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                musicRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                musicRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                musicRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }


    @Override
    public void onMusicClick(int position) {
        displayBottomSheet(musicModelarrlist.get(position));
    }

    private void displayBottomSheet(MusicModel musicModel){

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.music_bottom_sheet,bottomsheet);
        bottomsheet = layout.findViewById(R.id.bottomSheet);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView band = layout.findViewById(R.id.idbandname);
        TextView des = layout.findViewById(R.id.idbandDes);
        ImageView img = layout.findViewById(R.id.bandlogo);

        edit_btn = layout.findViewById(R.id.editbtn);

        band.setText(musicModel.getComname());
        des.setText(musicModel.getDes());
        Picasso.get().load(musicModel.getLogourl()).into(img);

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(music.this,updateband.class);
                intent.putExtra("music_band",musicModelarrlist);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.music,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:{
                Intent intent = new Intent(this,music_form.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}