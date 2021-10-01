package com.eventhub.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

    //private static final String TAG = "music";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);
        RecyclerView recyclerView = findViewById(R.id.murecycler_view);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("bands");
        musicModelarrlist = new ArrayList<>();
//        //RelativeLayout relativeLayout = findViewById(R.id.idRLHomeMusic);
        musicRVAdapter = new MusicRVAdapter(musicModelarrlist,this,this);
        bottomsheet = findViewById(R.id.bottomSheet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(musicRVAdapter);

        getAllbands();

        /*Log.d(TAG, "initRecyclerView: started");


        MusicRVAdapter adapter = new  MusicRVAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setContentView(R.layout.music);
        Log.d(TAG, "onCreate: started");
        initImageBitmaps();*/
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
    @Override
    public void onMusicClick(int position) {
        displayBottemSheet(musicModelarrlist.get(position));
    }
    private void displayBottemSheet(MusicModel musicModel){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View layout = LayoutInflater.from(this).inflate(R.layout.music_bottom_sheet,bottomsheet);
        bottomSheetDialog.setContentView(layout);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        TextView band = layout.findViewById(R.id.idbandname);
        TextView des = layout.findViewById(R.id.idbandDes);
        ImageView img = layout.findViewById(R.id.bandlogo);

        band.setText(musicModel.getComname());
        des.setText(musicModel.getDes());
        Picasso.get().load(musicModel.getLogourl()).into(img);
    }

    /*private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");
        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/eventhub-b7101.appspot.com/o/0e2422cbe498308f066567536a6ece73.jpg?alt=media&token=6fcaebae-1d4e-4aa8-a939-0c24f0e7e002");
        mNames.add("Seegiriya");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_48320126-e1560425048816.jpeg?auto=format&q=60&fit=max&w=930");
        mNames.add("Ella");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_91814252-e1559839133542.jpeg?auto=format&q=60&fit=max&w=930");
        mNames.add("Nuwara Eliya");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_65973336-e1560425151271.png?auto=format&q=60&fit=max&w=930");
        mNames.add("Pinnawala Elephant Orphanage");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Ruins-of-Polonnaruwa.jpg");
        mNames.add("Polonnaruwa");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Adams-Peak.jpg");
        mNames.add("Adams Peak");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Mirissa-Fisheries-Harbor.jpg");
        mNames.add("Mirissa");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Leopards.jpg");
        mNames.add("Yala National Park");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Colombo.jpg");
        mNames.add("Colombo");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Jaffna.jpg");
        mNames.add("Jaffna");

    }*/


}