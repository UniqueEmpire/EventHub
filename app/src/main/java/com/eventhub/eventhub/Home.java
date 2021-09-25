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

    String url1 = "https://www.geeksforgeeks.org/wp-content/uploads/gfg_200X200-1.png";
    String url2 = "https://qphs.fs.quoracdn.net/main-qimg-8e203d34a6a56345f86f1a92570557ba.webp";
    String url3 = "https://bizzbucket.co/wp-content/uploads/2020/08/Life-in-The-Metro-Blog-Title-22.png";
    //ImageView imageView = findViewById(R.id.imageView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = findViewById(R.id.slider);
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    public void catering(View view){

        //Go to the next activity
        Intent intent = new Intent(this,catering.class);

        //Start the activity 02
        startActivity(intent);
    }
    public void music(View view){

        //Go to the next activity
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

}