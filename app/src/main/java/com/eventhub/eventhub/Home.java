package com.eventhub.eventhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    String url1 = "https://images.unsplash.com/photo-1527751171053-6ac5ec50000b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80";
    String url2 = "https://images.unsplash.com/photo-1517833969405-d4a24c2c8280?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80";
    String url3 = "https://images.unsplash.com/photo-1524824267900-2fa9cbf7a506?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80";
    String url4 = "https://images.unsplash.com/photo-1461784121038-f088ca1e7714?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80";
    String url5 = "https://images.unsplash.com/photo-1552334823-ca7f70376914?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80";

    //ImageView imageView = findViewById(R.id.imageView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MainSlider();
    }

    public void catering(View view){

        //Go to the next activity
        Intent intent = new Intent(this,catering.class);

        //Start the activity 02
        startActivity(intent);
    }
    public void music(View view){

        //Go to the next activity
        //Intent intent2 = new Intent(this,music.class);
        Intent intent2 = new Intent(this,music.class);

        //Start the activity 02
        startActivity(intent2);
    }

    public void calculator(View view){

        //Go to the next activity
        Intent intent3 = new Intent(this,catering.class);

        //Start the activity 02
        startActivity(intent3);
    }
    public void venue(View view){

        //Go to the next activity
        Intent intent4 = new Intent(this,catering.class);

        //Start the activity 02
        startActivity(intent4);
    }
    public void photography(View view){

        //Go to the next activity
        Intent intent5 = new Intent(this,catering.class);

        //Start the activity 02
        startActivity(intent5);
    }
    public void aboutus(View view){

        //Go to the next activity
        Intent intent6 = new Intent(this,catering.class);

        //Start the activity 02
        startActivity(intent6);
    }

    public void MainSlider(){
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = findViewById(R.id.slider);
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
        sliderDataArrayList.add(new SliderData(url4));
        sliderDataArrayList.add(new SliderData(url5));
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }
}