package com.example.newecoholiday.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.newecoholiday.R;

public class statistics extends AppCompatActivity {
    FrameLayout worldFrame;
    FrameLayout ausFrame;
    FrameLayout vicFrame;
    CardView cardWorld,cardAus,cardVic ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        worldFrame = (FrameLayout) findViewById(R.id.worldFrame);
        ausFrame = (FrameLayout) findViewById(R.id.ausFrame);
        vicFrame = (FrameLayout) findViewById(R.id.vicFrame);
        cardWorld = (CardView)findViewById(R.id.cardWorld);
        cardAus = (CardView)findViewById(R.id.cardAus);
        cardVic = (CardView)findViewById(R.id.cardVic);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        ChartFragment frag = new ChartFragment();
        Bundle toFragment = new Bundle();

        toFragment.putString("type","world");
        frag.setArguments(toFragment);
        trans.add(R.id.worldFrame,frag).commit();


        // pie cahrt
        manager = getSupportFragmentManager();
        trans = manager.beginTransaction();
        frag = new ChartFragment();
        toFragment = new Bundle();

        toFragment.putString("type","piechart");
        frag.setArguments(toFragment);
        trans.add(R.id.vicFrame,frag).commit();

        // Aus emissions
        manager = getSupportFragmentManager();
        trans = manager.beginTransaction();
        frag = new ChartFragment();
        toFragment = new Bundle();

        toFragment.putString("type","ausemissions");
        frag.setArguments(toFragment);
        trans.add(R.id.ausFrame,frag).commit();

        // visible the frame
        worldFrame.setVisibility(View.VISIBLE);
        cardWorld.setBackgroundColor(Color.parseColor("#FFAE76"));

        cardWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onChartChange();
                cardWorld.setBackgroundColor(Color.parseColor("#FFAE76"));
                cardWorld.setCardElevation(12);
                worldFrame.setVisibility(View.VISIBLE);

            }
        });

        cardAus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onChartChange();
                cardAus.setBackgroundColor(Color.parseColor("#FFAE76"));
                cardAus.setCardElevation(12);
                ausFrame.setVisibility(View.VISIBLE);
            }
        });

        // on clicking victoria card

        cardVic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onChartChange();
                cardVic.setBackgroundColor(Color.parseColor("#FFAE76"));
                cardVic.setCardElevation(12);
                vicFrame.setVisibility(View.VISIBLE);

            }
        });

    }


    public void onChartChange(){

        cardVic.setBackgroundColor(Color.parseColor("#ffffff"));
        cardAus.setBackgroundColor(Color.parseColor("#ffffff"));
        cardWorld.setBackgroundColor(Color.parseColor("#ffffff"));

        vicFrame.setVisibility(View.INVISIBLE);
        ausFrame.setVisibility(View.INVISIBLE);
        worldFrame.setVisibility(View.INVISIBLE);

    }

    public void progressDialogueShowClose(){
        final ProgressDialog progressDialog = ProgressDialog.show(statistics.this,
                "Loading","Please Wait...");
        progressDialog.setCancelable(true);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();

            }
        }, 500);

    }


}
