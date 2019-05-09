package com.example.newecoholiday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.newecoholiday.R;

public class ThingsTodo extends AppCompatActivity {

    CardView cardHiking,cardCamping,cardLookouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_todo);

        cardHiking = (CardView)findViewById(R.id.cardHiking);
        cardHiking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThingsTodo.this,Hiking.class));
            }
        });

        cardLookouts = (CardView)findViewById(R.id.cardLookouts);
        cardLookouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThingsTodo.this,Lookouts.class));
            }
        });

        cardCamping = (CardView)findViewById(R.id.cardCamping);
        cardCamping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThingsTodo.this,Camping.class));
            }
        });
    }
}
