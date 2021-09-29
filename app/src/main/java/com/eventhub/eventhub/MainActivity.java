package com.eventhub.eventhub;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        // we are creating array list for storing our image urls.
//        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
//
//        // initializing the slider view.
//        SliderView sliderView = findViewById(R.id.slider);
//
//        // adding the urls inside array list
//        sliderDataArrayList.add(new SliderData(url1));
//        sliderDataArrayList.add(new SliderData(url2));
//        sliderDataArrayList.add(new SliderData(url3));
//
//        // passing this array list inside our adapter class.
//        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);
//
//        // below method is used to set auto cycle direction in left to
//        // right direction you can change according to requirement.
//        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//
//        // below method is used to
//        // setadapter to sliderview.
//        sliderView.setSliderAdapter(adapter);
//
//        // below method is use to set
//        // scroll time in seconds.
//        sliderView.setScrollTimeInSec(5);
//
//        // to set it scrollable automatically
//        // we use below method.
//        sliderView.setAutoCycle(true);
//
//        // to start autocycle below method is used.
//        sliderView.startAutoCycle();
    }

    public void signup(View view){

        //Go to the next activity
        Intent intent = new Intent(this,signup.class);

        //Start the activity 02
        startActivity(intent);
        finish();

    }
    public void login(View view){

        //Go to the next activity
        Intent intent2 = new Intent(this,login.class);

        //Start the activity 02
        startActivity(intent2);
        finish();
    }
    public void skip(View view){

        //Go to the next activity
        Intent intent3 = new Intent(this,Home.class);

        //Start the activity 02
        startActivity(intent3);
        finish();
    }
}