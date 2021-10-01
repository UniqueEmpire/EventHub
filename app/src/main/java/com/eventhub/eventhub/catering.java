package com.eventhub.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class catering extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catering);
        Log.d(TAG, "onCreate: started");
        initImageBitmaps();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.catering,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item2:{
                Intent intent = new Intent(this,profile.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private static final String TAG = "catering";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_70635799-e1559839116897.jpeg?auto=format&q=60&fit=max&w=930");
        mNames.add("Southern Jubilee");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_61118743-e1560425183155.png?auto=format&q=60&fit=max&w=930");
        mNames.add("Bake Shop");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_48320126-e1560425048816.jpeg?auto=format&q=60&fit=max&w=930");
        mNames.add("Sweets by millie");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_91814252-e1559839133542.jpeg?auto=format&q=60&fit=max&w=930");
        mNames.add("Movable Feast");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_65973336-e1560425151271.png?auto=format&q=60&fit=max&w=930");
        mNames.add("The Hungry Gnome");
        mImageUrls.add("https://99designs-blog.imgix.net/blog/wp-content/uploads/2019/06/attachment_57563454-e1559839436619.jpeg?auto=format&q=60&fit=max&w=930");
        mNames.add("Chef Emme");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Mirissa-Fisheries-Harbor.jpg");
        mNames.add("Mirissa");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Leopards.jpg");
        mNames.add("Yala National Park");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Colombo.jpg");
        mNames.add("Colombo");
        mImageUrls.add("https://img.traveltriangle.com/blog/wp-content/tr:w-700,h400/uploads/2015/06/Jaffna.jpg");
        mNames.add("Jaffna");
        mImageUrls.add("https://images-workbench.99static.com/KYwbd_Z-lrWKpJxwW7p6eECuvdE=/99designs-contests-attachments/77/77799/attachment_77799601");
        mNames.add("Uprightman");
        mImageUrls.add("https://images-workbench.99static.com/HkgPz8Y2csysmOyfjGa0_NL2NHY=/99designs-contests-attachments/96/96443/attachment_96443245");
        mNames.add("Paris Chansons");
        mImageUrls.add("https://images-workbench.99static.com/KFO6DofSiuRnnJ81eececSwQzVU=/http://s3.amazonaws.com/projects-files/45/4573/457382/38b2c101-d348-4590-8a18-ab4814a5071e.jpg");
        mNames.add("Mrtorin");
        mImageUrls.add("https://images-workbench.99static.com/b11bYTiU1oQAf2Qr2byjaNJrLRg=/99designs-contests-attachments/98/98733/attachment_98733481");
        mNames.add("Musikfreunde");
        mImageUrls.add("https://images-workbench.99static.com/C81MpyhDD08ZKz6hURSjoRDlVZU=/99designs-contests-attachments/106/106271/attachment_106271948");
        mNames.add("Jason Penna");
        mImageUrls.add("https://images-workbench.99static.com/siANVH9VCXARF5VlXlKnIh6mD0o=/99designs-contests-attachments/69/69854/attachment_69854609");
        mNames.add("Dark Eyes");

        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

