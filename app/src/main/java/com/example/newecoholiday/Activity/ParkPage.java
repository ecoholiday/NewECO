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
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newecoholiday.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ParkPage extends AppCompatActivity {

    SQLiteDatabase mDatabase;


    //calendar
    TextView cDate,cWeather;
    CardView cardStartDate,cardEndDate;
    TextView sdate,edate;
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


        ImageButton btnNavigation = (ImageButton) findViewById(R.id.btnNavigation);
        //Toast.makeText(getApplicationContext()," "+ NPID +"",Toast.LENGTH_LONG).show();

        //set the image nd name of national park
        TextView txtPark = (TextView)findViewById(R.id.txtPark);
        TextView txtParkDistance = (TextView)findViewById(R.id.txtParkDistance);
        txtPark.setText(parkName);
        setNPImage(parkName);
        txtParkDistance.setText(" " + parkDistance +"KMs");


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

                Intent intentReport = new Intent(ParkPage.this, Savings.class);

                intentReport.putExtra("startDate", sdate.getText().toString());
                intentReport.putExtra("endDate", edate.getText().toString());
                intentReport.putExtra("distance", parkDistance);
                intentReport.putExtra("NPname", parkName);

                startActivity(intentReport);
            }
        });


        // dates code
        sdate =(TextView) findViewById(R.id.sdate);
        edate =(TextView) findViewById(R.id.edate);
        cardStartDate = (CardView)findViewById(R.id.cardStartDate);
        cardEndDate= (CardView)findViewById(R.id.cardEndDate);



        final DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                startCalendar.set(Calendar.YEAR, year);
                startCalendar.set(Calendar.MONTH, monthOfYear);
                startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateStartDate();
            }

        };

        final DatePickerDialog.OnDateSetListener endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                endCalendar.set(Calendar.YEAR, year);
                endCalendar.set(Calendar.MONTH, monthOfYear);
                endCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndDate();
            }

        };

        cardEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(sdate.getText().toString().equals("Start Date")){
                    Toast.makeText(getApplicationContext(),"Please Select Start Date",Toast.LENGTH_LONG).show();
                }else{
                    DatePickerDialog datePickerDialog = new DatePickerDialog(ParkPage.this, endDate, endCalendar
                            .get(Calendar.YEAR), endCalendar.get(Calendar.MONTH),
                            endCalendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.getDatePicker().setMinDate(startCalendar.getTimeInMillis());
                    datePickerDialog.show();
                }
            }
        });
        cardStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ParkPage.this, startDate, startCalendar
                        .get(Calendar.YEAR), startCalendar.get(Calendar.MONTH),
                        startCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        ImageView imgWeather = (ImageView)findViewById(R.id.imgWeather);
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

    private void updateStartDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        sdate.setText(sdf.format(startCalendar.getTime()));
    }
    private void updateEndDate() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edate.setText(sdf.format(endCalendar.getTime()));
    }

}
