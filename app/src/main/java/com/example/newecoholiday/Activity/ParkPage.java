package com.example.newecoholiday.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newecoholiday.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ParkPage extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    TextView cDate,cWeather;

    //calendar
    EditText sdate,edate;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dateTime;
    Calendar dateSelected = Calendar.getInstance();
    private DatePickerDialog datePickerDialog;
    final Calendar startCalendar = Calendar.getInstance();
    final Calendar endCalendar = Calendar.getInstance();
    String myFormat = "MM/dd/yy"; //In which you need put here

    //calendar

    Boolean firstTimeLoading = true;
    SharedPreferences sharedpreferences;
    int NPID;
    String parkName;
    String area;
    String parkDistance;
    String latitude;
    String longitude;
    ProgressDialog pDialog;

    //CardView clicks
    CardView cardTodo,cardSavings,cardHelp,cardPrepare;

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

        final TextView ParkDistance = (TextView)findViewById(R.id.ParkDistance);
        TextView Area = (TextView)findViewById(R.id.Area);
        ImageButton btnNavigation = (ImageButton) findViewById(R.id.btnNavigation);
        //Toast.makeText(getApplicationContext()," "+ NPID +"",Toast.LENGTH_LONG).show();

        //set the image nd name of national park
        TextView txtPark = (TextView)findViewById(R.id.txtPark);
        txtPark.setText(parkName);
        setNPImage(parkName);


        btnNavigation.setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(ParkPage.this,MapsActivity.class));
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
                startActivity(new Intent(ParkPage.this,MapsActivity.class));
            }
        });

    }

    public void setNPImage(String name){
        ImageView imgPark = (ImageView)findViewById(R.id.imgPark);
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
}
