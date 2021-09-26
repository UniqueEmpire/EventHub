package com.example.photography;

import androidx.annotation.NonNull;
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

public class AddAlbumActivity extends AppCompatActivity {
    private TextInputEditText albumNameEdt, albumAddressEdt, albumNumberEdt, albumImageEdt, albumLinkEdt, albumDisEdt;
    private Button addAlbumBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String albumID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album);
        albumNameEdt = findViewById(R.id.idEdtAlbumName);
        albumAddressEdt = findViewById(R.id.idEdtAlbumAddress);
        albumNumberEdt = findViewById(R.id.idEdtContactNumber);
        albumImageEdt = findViewById(R.id.idEdtAlbumImageLink);
        albumLinkEdt = findViewById(R.id.idEdtAlbumLink);
        albumDisEdt = findViewById(R.id.idEdtAlbumDis);

        addAlbumBtn = findViewById(R.id.idBtnAddAlbum);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Albums");

        addAlbumBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String albumName = albumNameEdt.getText().toString();
                String albumAddress = albumAddressEdt.getText().toString();
                String albumNumber = albumNumberEdt.getText().toString();
                String albumImage = albumImageEdt.getText().toString();
                String albumLink = albumLinkEdt.getText().toString();
                String albumDescription = albumDisEdt.getText().toString();
                albumID = albumName;
                AlbumRVModal albumRVModal = new AlbumRVModal(albumName,albumAddress,albumNumber,albumImage,albumLink,albumDescription,albumID);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(albumID).setValue(albumRVModal);
                        Toast.makeText(AddAlbumActivity.this, "Album Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddAlbumActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddAlbumActivity.this, "Error is "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}