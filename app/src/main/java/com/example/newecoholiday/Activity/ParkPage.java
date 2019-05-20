package com.example.newecoholiday.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newecoholiday.R;

public class ParkPage extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    SharedPreferences sharedpreferences;
    int NPID;
    String parkName,area,parkDistance;
    String latitude,longitude,days;
    ProgressDialog pDialog;
    ImageView imgPark;

    //CardView clicks
    CardView cardTodo,cardSavings,cardHelp,cardPrepare;
    CardView cardDay1,cardDay2,cardDay3,cardDay4,cardDay5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_page);

        sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);
        NPID = sharedpreferences.getInt("NPID", 0);
        parkName = sharedpreferences.getString("ParkName","");
        parkDistance = sharedpreferences.getString("ParkDistance","");
        area = sharedpreferences.getString("ParkArea","");
        latitude = sharedpreferences.getString("ParkLatitude","");
        longitude = sharedpreferences.getString("ParkLongitude","");


        //ImageButton btnNavigation = (ImageButton) findViewById(R.id.btnNavigation);
        imgPark = (ImageView)findViewById(R.id.imgPark);

        //set the image nd name of national park
        TextView txtPark = (TextView)findViewById(R.id.txtPark);
        TextView txtParkDistance = (TextView)findViewById(R.id.txtParkDistance);
        //final SeekBar seekBarDays = findViewById(R.id.seekBarDays);
        //final TextView txtSeekDays = findViewById(R.id.txtSeekDays);

        txtPark.setText(parkName);
        setNPImage(parkName);
        txtParkDistance.setText(" " + parkDistance +" KMs");


        imgPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkPage.this,MapsActivity.class));
            }
        });


        cardPrepare = (CardView) findViewById(R.id.cardPrepare);
        cardHelp = (CardView) findViewById(R.id.cardHelp);
        cardSavings = (CardView) findViewById(R.id.cardSavings);
        cardTodo = (CardView) findViewById(R.id.cardTodo);

        cardPrepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkPage.this,CheckList.class));
            }
        });

        cardHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkPage.this,Help.class));
            }
        });

        cardTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkPage.this,ThingsTodo.class));
            }
        });

        cardSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentReport = new Intent(ParkPage.this, CarbonEmissions.class);
                //Toast.makeText(getApplicationContext(),""+txtSeekDays.getText(),Toast.LENGTH_LONG).show();
                intentReport.putExtra("days", days);
                intentReport.putExtra("distance", parkDistance);
                intentReport.putExtra("NPname", parkName);

                startActivity(intentReport);
            }
        });

        cardDay1 = findViewById(R.id.cardDay1);
        cardDay2 = findViewById(R.id.cardDay2);
        cardDay3 = findViewById(R.id.cardDay3);
        cardDay4 = findViewById(R.id.cardDay4);
        cardDay5 = findViewById(R.id.cardDay5);
        days="1";
        cardDay1.setBackgroundColor(Color.parseColor("#FFAE76"));


        cardDay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = "1";
                changeDaysCards();
                cardDay1.setBackgroundColor(Color.parseColor("#FFAE76"));

            }
        });
        cardDay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = "2";
                changeDaysCards();
                cardDay2.setBackgroundColor(Color.parseColor("#FFAE76"));
            }
        });
        cardDay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = "3";
                changeDaysCards();
                cardDay3.setBackgroundColor(Color.parseColor("#FFAE76"));
            }
        });
        cardDay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = "4";
                changeDaysCards();
                cardDay4.setBackgroundColor(Color.parseColor("#FFAE76"));
            }
        });
        cardDay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days = "5";
                changeDaysCards();
                cardDay5.setBackgroundColor(Color.parseColor("#FFAE76"));
            }
        });

        /*if (seekBarDays != null) {
            seekBarDays.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (seekBarDays.getProgress() ==0){
                        seekBarDays.setProgress(1);
                    }
                    days = String.valueOf(seekBarDays.getProgress());
                    if (seekBarDays.getProgress() ==1){
                        txtSeekDays.setText(days+" Day");
                    }else{
                        txtSeekDays.setText(days+" Days");
                    }


                }
            });
        }*/


        RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(2000);
        final ImageView imgWeather = (ImageView) findViewById(R.id.imgWeather);
        imgWeather.startAnimation(anim);

        imgWeather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ParkLatitude",latitude);
                editor.putString("ParkLongitude", longitude);
                editor.putString("ParkName",parkName);
                editor.putString("ParkArea",area);
                editor.putString("ParkDistance",parkDistance);
                editor.apply();
                startActivity(new Intent(ParkPage.this,WeatherActivity.class));

            }
        });

    }

    public void setNPImage(String name){

        if(name.contains("lpine")){
            imgPark.setImageResource(R.drawable.alpine);
        }else if(name.contains("armah")){
            imgPark.setImageResource(R.drawable.barmah);
        }else if(name.contains("aw")){
            imgPark.setImageResource(R.drawable.bawbaw);
        }else if(name.contains("urrowa")){
            imgPark.setImageResource(R.drawable.burrowa);
        }else if(name.contains("roajin")){
            imgPark.setImageResource(R.drawable.croajingolong);
        }else if(name.contains("andeno")){
            imgPark.setImageResource(R.drawable.dandenong);
        }else if(name.contains("rrin")){
            imgPark.setImageResource(R.drawable.errinundra);
        }else if(name.contains("rampi")){
            imgPark.setImageResource(R.drawable.grampians);
        }else if(name.contains("attah")){
            imgPark.setImageResource(R.drawable.hattah);
        }else if(name.contains("ara")){
            imgPark.setImageResource(R.drawable.karakara);
        }else if(name.contains("inglake")){
            imgPark.setImageResource(R.drawable.kinglake);
        }else if(name.contains("ildon")){
            imgPark.setImageResource(R.drawable.lake);
        }else if(name.contains("esert")){
            imgPark.setImageResource(R.drawable.little);
        }else if(name.contains("lenelg")){
            imgPark.setImageResource(R.drawable.lower);
        }else if(name.contains("itchell")){
            imgPark.setImageResource(R.drawable.mitchell);
        }else if(name.contains("ornington")){
            imgPark.setImageResource(R.drawable.mornington);
        }else if(name.contains("ccles")){
            imgPark.setImageResource(R.drawable.eccles);
        }else if(name.contains("ichmond")){
            imgPark.setImageResource(R.drawable.richmond);
        }else if(name.contains("urray")){
            imgPark.setImageResource(R.drawable.murray);
        }else if(name.contains("rgan")){
            imgPark.setImageResource(R.drawable.organ);
        }else if(name.contains("ampbell")){
            imgPark.setImageResource(R.drawable.port);
        }else if(name.contains("ulga")){
            imgPark.setImageResource(R.drawable.tarra);
        }else if(name.contains("ilsons")){
            imgPark.setImageResource(R.drawable.wilsons);
        }else if(name.contains("yperf")){
            imgPark.setImageResource(R.drawable.wyperfield);
        }else{
            imgPark.setImageResource(R.drawable.yarra);
        }


    }

    public void changeDaysCards(){
        cardDay1.setBackgroundColor(Color.parseColor("#ffffff"));
        cardDay2.setBackgroundColor(Color.parseColor("#ffffff"));
        cardDay3.setBackgroundColor(Color.parseColor("#ffffff"));
        cardDay4.setBackgroundColor(Color.parseColor("#ffffff"));
        cardDay5.setBackgroundColor(Color.parseColor("#ffffff"));

    }

}
