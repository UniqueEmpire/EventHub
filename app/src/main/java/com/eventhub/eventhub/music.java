package com.eventhub.eventhub;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class music extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music);

        Log.d(TAG, "onCreate: started");
        initImageBitmaps();
    }

    private static final String TAG = "catering";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_70635799-e1559839116897.jpeg?auto=format&q=60&fit=max&w=930");
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
        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MusicRVAdapter adapter = new  MusicRVAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}