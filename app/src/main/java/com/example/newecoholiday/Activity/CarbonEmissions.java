package com.example.newecoholiday.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newecoholiday.R;

import java.util.Arrays;

import static java.lang.Math.round;

public class CarbonEmissions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CardView cardNet,cardSaved,cardSpent;
    String traveller="couple";
    String house = "unit";
    String others = "0";
    String days,distance,nationalPark;
    FrameLayout frameSaved,frameSpent;
    long  ElectricityUsed,
            MoneySaved,
            CarbonSaved,
            FuelConsumed,
            MoneyConsumed,
            CarbonConsumed,
            TotalMoneySaving,
            TotalCarbonSaving;
    int dist,intDays;
    TextView txtElecTotal,txtMoneyFinal,txtCarbonFinal,txtParkReport,txtDistReport,txtDaysReport;

    long carbonHotel;
    long moneyHotel;
    long alcohol;
    long mobile;
    long otherElectronics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_emissions);

       final LinearLayout linInvisible = findViewById(R.id.linInvisible);


        Intent fromParkActivity = getIntent();
        days = fromParkActivity.getStringExtra("days");
        distance = fromParkActivity.getStringExtra("distance");
        nationalPark = fromParkActivity.getStringExtra("NPname");

        txtParkReport = findViewById(R.id.txtParkReport);
        txtDistReport = findViewById(R.id.txtDistReport);
        txtDaysReport = findViewById(R.id.txtDaysReport);

        txtParkReport.setText(nationalPark);
        txtDistReport.setText(distance);
        txtDaysReport.setText(days);

        intDays = Integer.parseInt(days);
        dist = Integer.parseInt(distance);


        frameSaved = findViewById(R.id.frameSaved);
        frameSpent = findViewById(R.id.frameSpent);
        txtCarbonFinal = findViewById(R.id.txtCarbonFinal);
        txtMoneyFinal = findViewById(R.id.txtMoneyFinal);
        txtElecTotal = findViewById(R.id.txtElecTotal);
        txtParkReport = findViewById(R.id.txtParkReport);
        txtDistReport = findViewById(R.id.txtDistReport);
        txtDaysReport = findViewById(R.id.txtDaysReport);

        Spinner spinnerUser = findViewById(R.id.spinnerUser);
        String[] users = new String[]{"couple","single"};
        ArrayAdapter<String> adapterUser = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, users);
        spinnerUser.setAdapter(adapterUser);
        spinnerUser.setOnItemSelectedListener(this);

        Spinner spinnerHouse = findViewById(R.id.spinnerHouse);
        String[] houses = new String[]{"unit","apartment"};
        ArrayAdapter<String> adapterHouses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, houses);
        spinnerHouse.setAdapter(adapterHouses);
        spinnerHouse.setOnItemSelectedListener(this);

        Spinner spinnerKids = findViewById(R.id.spinnerKids);
        String[] kids = new String[]{"0","1","2","3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kids);
        spinnerKids.setAdapter(adapter);
        spinnerKids.setOnItemSelectedListener(this);


        cardNet = findViewById(R.id.cardNet);
        cardSaved = findViewById(R.id.cardSaved);
        cardSpent = findViewById(R.id.cardSpent);
        cardNet.setBackgroundColor(Color.parseColor("#FFAE76"));

        dropDownChanged();


        cardNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                carbonTabChanged();
                cardNet.setBackgroundColor(Color.parseColor("#FFAE76"));

                frameSpent.setVisibility(View.GONE);
                frameSaved.setVisibility(View.GONE);
                linInvisible.setVisibility(View.VISIBLE);
                //vicFrame.setVisibility(View.VISIBLE);

            }
        });
        cardSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                carbonTabChanged();
                cardSaved.setBackgroundColor(Color.parseColor("#FFAE76"));
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                Saved frag = new Saved();
                Bundle toFragment = new Bundle();

                toFragment.putLong("HotelMoney",moneyHotel);
                toFragment.putLong("HotelCarbon",carbonHotel);
                toFragment.putLong("Electricity",ElectricityUsed);
                toFragment.putLong("ElecCarbon",CarbonSaved);
                toFragment.putLong("ElecMoney",MoneySaved);
                toFragment.putLong("Mobile",mobile);
                toFragment.putLong("Electronics",otherElectronics);
                toFragment.putLong("Beer",alcohol);

                frag.setArguments(toFragment);
                trans.add(R.id.frameSaved,frag).commit();


                frameSaved.setVisibility(View.VISIBLE);
                frameSpent.setVisibility(View.INVISIBLE);
                linInvisible.setVisibility(View.INVISIBLE);

            }
        });
        cardSpent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                carbonTabChanged();
                cardSpent.setBackgroundColor(Color.parseColor("#FFAE76"));
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                netFragment frag = new netFragment();
                Bundle toFragment = new Bundle();

                toFragment.putLong("Fuel",FuelConsumed);
                toFragment.putLong("Money",MoneyConsumed);
                toFragment.putLong("Carbon",CarbonConsumed);

                frag.setArguments(toFragment);
                trans.add(R.id.frameSpent,frag).commit();

                frameSpent.setVisibility(View.VISIBLE);
                frameSaved.setVisibility(View.GONE);
                linInvisible.setVisibility(View.INVISIBLE);
            }
        });

    }

    public void carbonTabChanged(){
        cardNet.setBackgroundColor(Color.parseColor("#ffffff"));
        cardSaved.setBackgroundColor(Color.parseColor("#ffffff"));
        cardSpent.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String value = parent.getItemAtPosition(position).toString();
        if( Arrays.asList("couple","single").contains(value) ){
            traveller = value;
        }
        else if( Arrays.asList("unit","apartment").contains(value) ){
            house = value;
        } else{
            others = value;
        }
        dropDownChanged();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void dropDownChanged(){

        carbonHotel = 3500*intDays;
        moneyHotel=120* intDays;
        alcohol = 500*intDays;
        mobile = 3500*intDays;
        otherElectronics = 4000*intDays;


        FuelConsumed = round(dist*2*0.1);
        MoneyConsumed = round(dist*2*0.1*1.4);
        CarbonConsumed = round(dist*2*0.1*1954);


        ElectricityUsed = round(7.4*intDays);
        MoneySaved = round(6.0*intDays);
        CarbonSaved = round(8115.0*intDays);


        if(traveller == "couple"){
            alcohol = alcohol *2;

            if (others == "2"){
                ElectricityUsed = round(41.2*intDays);
                MoneySaved = round(9*intDays);
                mobile = mobile*3;
            }
            else if (others == "0"){
                ElectricityUsed = round(22.2*intDays);
                MoneySaved = round(5*intDays);
                mobile = mobile*2;
            }else if (others == "1"){
                ElectricityUsed = round(34.5*intDays);
                MoneySaved = round(7*intDays);
            }else if (others == "3"){
                ElectricityUsed = round(49*intDays);
                MoneySaved = round(10*intDays);
                mobile = mobile*4;
            }

        }else{
            int otherSingles = Integer.parseInt(others)+1;
            ElectricityUsed = round(19.5*intDays)*otherSingles;
            MoneySaved = round(7*intDays)*otherSingles;
            mobile = mobile*otherSingles;
            alcohol = alcohol*otherSingles;
            otherElectronics = otherElectronics*otherSingles;
        }
        if(house == "apartment"){
            ElectricityUsed = round(ElectricityUsed/2.5);
            MoneySaved = round(MoneySaved/2.5);
        }
        CarbonSaved = round(512*ElectricityUsed);

        TotalMoneySaving = round(MoneySaved-MoneyConsumed+moneyHotel);
        TotalCarbonSaving = round(CarbonSaved-CarbonConsumed+carbonHotel+mobile+otherElectronics+alcohol);

        txtCarbonFinal.setText(String.valueOf(TotalCarbonSaving)+ "g");
        txtElecTotal.setText(String.valueOf(ElectricityUsed) + "KWh");
        txtMoneyFinal.setText(String.valueOf(TotalMoneySaving));

        if(TotalCarbonSaving<0){
            txtCarbonFinal.setTextColor(getColor(R.color.carbonSpent));
            TextView txtNegative = findViewById(R.id.txtNegative);
            txtNegative.setVisibility(View.VISIBLE);
            ImageView imgTotalCarbon = (ImageView)findViewById(R.id.imgTotalCarbon);
            imgTotalCarbon.setImageResource(R.drawable.ic_carbon_spent);
        }else{
            txtCarbonFinal.setTextColor(getColor(R.color.carbonSaved));
            TextView txtNegative = findViewById(R.id.txtNegative);
            txtNegative.setVisibility(View.INVISIBLE);
            ImageView imgTotalCarbon = (ImageView)findViewById(R.id.imgTotalCarbon);
            imgTotalCarbon.setImageResource(R.drawable.ic_carbon);

        }

    }
}
