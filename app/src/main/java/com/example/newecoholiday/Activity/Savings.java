package com.example.newecoholiday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newecoholiday.R;

import static java.lang.Math.round;

public class Savings extends AppCompatActivity {

    String days,nationalPark,distance;
    int diffDays,dist;
    //    double moneySaved;
//    double carbonSaved;
    TextView count1,day_count1;
    TextView TDays,
            TotalDistance,
            txtParkReport,
            EUsed,
            MSaved,
            CSaved,
            FConsumed,
            MConsumed,
            CCreated,
            TMSaving,
            TCSaving;
            //positiveDays;
    int TravelDistance;
    //TableRow positiveRow;
    long  ElectricityUsed,
            MoneySaved,
            CarbonSaved,
            FuelConsumed,
            MoneyConsumed,
            CarbonConsumed,
            TotalMoneySaving,
            TotalCarbonSaving;
    long takePositive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);


        Intent fromParkActivity = getIntent();
        days = fromParkActivity.getStringExtra("days");
        days = days.substring(0,1);
        distance = fromParkActivity.getStringExtra("distance");
        nationalPark = fromParkActivity.getStringExtra("NPname");
        //Toast.makeText(getApplicationContext(),distance+" : Distance : "+ distance.substring(15) , Toast.LENGTH_LONG).show();

        //distance = distance.substring(0,(distance.length()-3));

        TDays = (TextView)findViewById(R.id.txtDaysReport);
        TotalDistance = (TextView)findViewById(R.id.txtDistReport);
        txtParkReport = (TextView)findViewById(R.id.txtParkReport);
        EUsed = (TextView)findViewById(R.id.txtElecSaved);
        MSaved = (TextView)findViewById(R.id.txtMoneySaved);
        CSaved = (TextView)findViewById(R.id.txtCarbonSaved);
        FConsumed = (TextView)findViewById(R.id.txtFuelSpent);
        MConsumed = (TextView)findViewById(R.id.txtMoneySpent);
        CCreated = (TextView)findViewById(R.id.txtCarbonSpent);
        TMSaving = (TextView)findViewById(R.id.txtMoneyFinal);
        TCSaving = (TextView)findViewById(R.id.txtCarbonFinal);
        //positiveDays = (TextView)findViewById(R.id.PositiveCarbonDaysCount);
        //positiveRow = (TableRow) findViewById(R.id.positiveRow);
        //positiveRow.setVisibility(View.GONE);



         //long difference = Math.abs(StartDate.getTime() - EndDate.getTime());
        int intDays = Integer.parseInt(days);
        diffDays = (int) (intDays / (1000 * 60 * 60 * 24))+1;
        dist = Integer.parseInt(distance);

        //Start calculations

        TravelDistance =(dist * 2);
        ElectricityUsed = round(7.4*diffDays);
        MoneySaved = round(6.0*diffDays);
        CarbonSaved = round(8115.0*diffDays);
        FuelConsumed = round(dist*2*0.1);
        MoneyConsumed = round(dist*2*0.1*1.4);
        CarbonConsumed = round(dist*2*0.1*1954);
        TotalMoneySaving = round(MoneySaved-MoneyConsumed);
        TotalCarbonSaving = round(CarbonSaved-CarbonConsumed);
        if(TotalCarbonSaving<0){
            //positiveRow.setVisibility(View.VISIBLE);
            takePositive = round((195.4*dist*2)/8115)-diffDays;
            //positiveDays.setText(takePositive+"");
        }

        txtParkReport.setText(nationalPark);

        TDays.setText(intDays+" days");
        //TDistance.setText(TravelDistance+" KM");
        TotalDistance.setText(dist+" KMs");
        EUsed.setText(ElectricityUsed+" KWh");
        MSaved.setText(MoneySaved+"");
        CSaved.setText(CarbonSaved+" g");
        FConsumed.setText(FuelConsumed+" L");
        MConsumed.setText(MoneyConsumed+"");
        CCreated.setText(CarbonConsumed+" g");
        TextView txtNegative = (TextView)findViewById(R.id.txtNegative);
        TextView txtNetSavings = (TextView)findViewById(R.id.txtNetSavings);

        if (TotalMoneySaving<0){
            TMSaving.setTextColor(getColor(R.color.carbonSpent));
            txtNetSavings.setTextColor(getColor(R.color.carbonSpent));
            TotalMoneySaving = TotalMoneySaving * (-1);
            txtNegative.setVisibility(View.VISIBLE);
            ImageView imgTotalMoney = (ImageView)findViewById(R.id.imgTotalMoney);
            imgTotalMoney.setImageResource(R.drawable.ic_dollar_spent);
        }
        if (TotalCarbonSaving<0){
            TCSaving.setTextColor(getColor(R.color.carbonSpent));
            txtNetSavings.setTextColor(getColor(R.color.carbonSpent));
            TotalCarbonSaving = TotalCarbonSaving*(-1);
            txtNegative.setVisibility(View.VISIBLE);
            ImageView imgTotalCarbon = (ImageView)findViewById(R.id.imgTotalCarbon);
            imgTotalCarbon.setImageResource(R.drawable.ic_carbon_spent);
        }

        TMSaving.setText(TotalMoneySaving+"");
        TCSaving.setText(TotalCarbonSaving+" g");

    }
}
