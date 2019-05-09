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

        TextView NParkName = (TextView)findViewById(R.id.NParkName);
        final TextView ParkDistance = (TextView)findViewById(R.id.ParkDistance);
        TextView Area = (TextView)findViewById(R.id.Area);
        ImageButton btnNavigation = (ImageButton) findViewById(R.id.btnNavigation);
        NParkName.setText(parkName);
        ParkDistance.setText("" + parkDistance + "KMs");
        Area.setText(""+area+ "FT2");
        //Toast.makeText(getApplicationContext()," "+ NPID +"",Toast.LENGTH_LONG).show();


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
                startActivity(new Intent(ParkPage.this,MapsActivity.class));
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
}
