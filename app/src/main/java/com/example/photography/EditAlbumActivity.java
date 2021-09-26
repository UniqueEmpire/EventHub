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

import java.util.HashMap;
import java.util.Map;

public class EditAlbumActivity extends AppCompatActivity {
    private TextInputEditText albumNameEdt, albumAddressEdt, albumNumberEdt, albumImageEdt, albumLinkEdt, albumDisEdt;
    private Button updateAlbumBtn,deleteAlbumBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String albumID;
    private AlbumRVModal albumRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_album);
        firebaseDatabase = FirebaseDatabase.getInstance();
        albumNameEdt = findViewById(R.id.idEdtAlbumName);
        albumAddressEdt = findViewById(R.id.idEdtAlbumAddress);
        albumNumberEdt = findViewById(R.id.idEdtContactNumber);
        albumImageEdt = findViewById(R.id.idEdtAlbumImageLink);
        albumLinkEdt = findViewById(R.id.idEdtAlbumLink);
        albumDisEdt = findViewById(R.id.idEdtAlbumDis);
        updateAlbumBtn = findViewById(R.id.idBtnUpdateAlbum);
        deleteAlbumBtn = findViewById(R.id.idBtnDeleteAlbum);
        loadingPB = findViewById(R.id.idPBLoading);

        //get data from previous activity
        albumRVModal = getIntent().getParcelableExtra("album");
        if (albumRVModal != null) {
            albumNameEdt.setText(albumRVModal.getAlbumName());
            albumAddressEdt.setText(albumRVModal.getAlbumAddress());
            albumNumberEdt.setText(albumRVModal.getAlbumNumber());
            albumImageEdt.setText(albumRVModal.getAlbumImage());
            albumLinkEdt.setText(albumRVModal.getAlbumLink());
            albumDisEdt.setText(albumRVModal.getAlbumDescription());
            albumID = albumRVModal.getAlbumID();
        }

        databaseReference = firebaseDatabase.getReference("Albums").child(albumID);

        updateAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String albumName = albumNameEdt.getText().toString();
                String albumAddress = albumAddressEdt.getText().toString();
                String albumNumber = albumNumberEdt.getText().toString();
                String albumImage = albumImageEdt.getText().toString();
                String albumLink = albumLinkEdt.getText().toString();
                String albumDescription = albumDisEdt.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("albumName",albumName);
                map.put("albumAddress",albumAddress);
                map.put("albumNumber",albumNumber);
                map.put("albumImage",albumImage);
                map.put("albumLink",albumLink);
                map.put("albumDescription",albumDescription);
                map.put("albumID",albumID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditAlbumActivity.this,"Album Updated..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditAlbumActivity.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditAlbumActivity.this,"Failed to update Album..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        deleteAlbumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlbum();
            }
        });
    }
    private void deleteAlbum(){
        databaseReference.removeValue();
        Toast.makeText(this,"Album deleted..", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditAlbumActivity.this,MainActivity.class));
    }
}