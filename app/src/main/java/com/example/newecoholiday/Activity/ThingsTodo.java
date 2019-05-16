package com.example.newecoholiday.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.newecoholiday.R;

import java.util.Arrays;

public class ThingsTodo extends AppCompatActivity {

    CardView cardHiking,cardCamping,cardLookouts;
    SharedPreferences sharedpreferences;
    String parkName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_todo);

        sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);
        parkName = sharedpreferences.getString("ParkName","");

        cardHiking = (CardView)findViewById(R.id.cardHiking);
        cardLookouts = (CardView)findViewById(R.id.cardLookouts);
        cardCamping = (CardView)findViewById(R.id.cardCamping);


        if( Arrays.asList("Barmah National Park",
                "Errinundra National Park",
                "Organ Pipes National Park",
                "Wyperfeld National Park",
                "Yarra Ranges National Park").contains(parkName) ){
            cardLookouts.setVisibility(View.INVISIBLE);
        }

        if( Arrays.asList("Mornington Peninsula National Park",
                "Mount Richmond National Park",
                "Organ Pipes National Park",
                "Port Campbell National Park",
                "Tarra-Bulga National Park",
                "Yarra Ranges National Park").contains(parkName) ){
            cardCamping.setVisibility(View.INVISIBLE);
        }

        if( Arrays.asList("Kara Kara National Park",
                "Kinglake National Park",
                "Mount Eccles National Park",
                "Murray - Sunset National Park").contains(parkName) ){
            cardHiking.setVisibility(View.INVISIBLE);
        }


        cardHiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThingsTodo.this,Hiking.class));
            }
        });


        cardLookouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThingsTodo.this,Lookouts.class));
            }
        });


        cardCamping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThingsTodo.this,Camping.class));
            }
        });
    }
}
