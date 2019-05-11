package com.example.newecoholiday.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newecoholiday.R;


public class FacilityListActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;

    TextView parkName;
    CheckBox Camping,
            Canoeing,
            Fishing,
            ScienceDrive,
            HorseRiding,
            Hunting,
            Trekking,
            Cycling,
            Picnicking,
            SightSeeing,
            Skiing,
            WhiteWaterRafting,
            CampFire,
            Swimming,
            YachtingSailing,
            BBQ,
            BirdWatching,
            Playground;

    int NPCamping,
            NPCanoeing,
            NPFishing,
            NPScienceDrive,
            NPHorseRiding,
            NPHunting,
            NPTrekking,
            NPCycling,
            NPPicnicking,
            NPSightSeeing,
            NPSkiing,
            NPWhiteWaterRafting,
            NPCampFire,
            NPSwimming,
            NPYachtingSailing,
            NPBBQ,
            NPBirdWatching,
            NPPlayground;

    ProgressDialog pDialog;

    SharedPreferences sharedpreferences;
    int NPID;
    String park;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_list);

        mDatabase = openOrCreateDatabase(Home.DATABASE_NAME, MODE_PRIVATE, null);

        Camping = (CheckBox) findViewById(R.id.chkCamping);
        Canoeing = (CheckBox) findViewById(R.id.chkCanoeing);
        Fishing = (CheckBox) findViewById(R.id.chkFishing);
        ScienceDrive = (CheckBox) findViewById(R.id.chkScienceDrive);
        HorseRiding = (CheckBox) findViewById(R.id.chkHorseRiding);
        Hunting = (CheckBox) findViewById(R.id.chkHunting);
        Trekking = (CheckBox) findViewById(R.id.chkTrekking);
        Cycling = (CheckBox) findViewById(R.id.chkCycling);
        Picnicking = (CheckBox) findViewById(R.id.chkPicnicking);
        SightSeeing = (CheckBox) findViewById(R.id.chkSightSeeing);
        Skiing = (CheckBox) findViewById(R.id.chkSkiing);
        WhiteWaterRafting = (CheckBox) findViewById(R.id.chkWhiteWaterRafting);
        CampFire = (CheckBox) findViewById(R.id.chkCampfire);
        Swimming = (CheckBox) findViewById(R.id.chkSwimming);
        YachtingSailing = (CheckBox) findViewById(R.id.chkSailing);
        BBQ = (CheckBox) findViewById(R.id.chkBBQ);
        BirdWatching = (CheckBox) findViewById(R.id.chkBirdWatching);
        Playground = (CheckBox) findViewById(R.id.chkPlayground);

        sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);
        NPID = sharedpreferences.getInt("FacilityNPID", 0);
        park = sharedpreferences.getString("ParkName","");

        new GetParksFacilityData().execute();




    }

    private class GetParksFacilityData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FacilityListActivity.this);
            pDialog.setMessage("Data Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            String z = "SUCCESS";
                try {

                    String query = "select * from tbl_NationalPark_Facilities where NPID =" + NPID ;
                    Cursor cursorFacility = mDatabase.rawQuery(query, null);

                    if (cursorFacility.moveToFirst()) {
                        do {

                            NPCamping = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Camping")));
                            NPCanoeing = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Canoeing")));
                            NPFishing = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Fishing")));
                            NPScienceDrive = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("ScienicDrive")));
                            NPHorseRiding = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("HorseRiding")));
                            NPHunting = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Hunting")));
                            NPTrekking = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Trekking")));
                            NPCycling = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Cycling")));
                            NPPicnicking = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Picknicking")));
                            NPSightSeeing = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("SightSeeing")));
                            NPSkiing = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Skiing")));
                            NPWhiteWaterRafting = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("whiteWaterRafting")));
                            NPCampFire = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("CampFire")));
                            NPSwimming = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Swimming")));
                            NPYachtingSailing = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("YachtingSailing")));
                            NPBBQ = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("BBQ")));
                            NPBirdWatching = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("BirdWatching")));
                            NPPlayground = Integer.parseInt(cursorFacility.getString(cursorFacility.getColumnIndex("Playground")));

                        } while (cursorFacility.moveToNext());
                    }

                    cursorFacility.close();
                } catch (Exception e) {
                    z = "FAIL";
                }

        return z;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.hide();

            if(result.equals("SUCCESS")){
                if(NPCamping ==1){
                    Camping.setChecked(true);
                }else{
                    Camping.setChecked(false);
                }
                if(NPCanoeing ==1){
                    Canoeing.setChecked(true);
                }else{
                    Canoeing.setChecked(false);
                }
                if(NPFishing ==1){
                    Fishing.setChecked(true);
                }else{
                    Fishing.setChecked(false);
                }
                if(NPScienceDrive ==1){
                    ScienceDrive.setChecked(true);
                }else{
                    ScienceDrive.setChecked(false);
                }
                if(NPHorseRiding ==1){
                    HorseRiding.setChecked(true);
                }else{
                    HorseRiding.setChecked(false);
                }
                if(NPHunting ==1){
                    Hunting.setChecked(true);
                }else{
                    Hunting.setChecked(false);
                }
                if(NPTrekking ==1){
                    Trekking.setChecked(true);
                }else{
                    Trekking.setChecked(false);
                }
                if(NPCycling ==1){
                    Cycling.setChecked(true);
                }else{
                    Cycling.setChecked(false);
                }
                if(NPPicnicking ==1){
                    Picnicking.setChecked(true);
                }else{
                    Picnicking.setChecked(false);
                }
                if(NPSightSeeing ==1){
                    SightSeeing.setChecked(true);
                }else{
                    SightSeeing.setChecked(false);
                }
                if(NPSkiing ==1){
                    Skiing.setChecked(true);
                }else{
                    Skiing.setChecked(false);
                }
                if(NPWhiteWaterRafting ==1){
                    WhiteWaterRafting.setChecked(true);
                }else{
                    WhiteWaterRafting.setChecked(false);
                }
                if(NPCampFire ==1){
                    CampFire.setChecked(true);
                }else{
                    CampFire.setChecked(false);
                }
                if(NPSwimming ==1){
                    Swimming.setChecked(true);
                }else{
                    Swimming.setChecked(false);
                }
                if(NPYachtingSailing ==1){
                    YachtingSailing.setChecked(true);
                }else{
                    YachtingSailing.setChecked(false);
                }
                if(NPBBQ ==1){
                    BBQ.setChecked(true);
                }else{
                    BBQ.setChecked(false);
                }
                if(NPBirdWatching ==1){
                    BirdWatching.setChecked(true);
                }else{
                    BirdWatching.setChecked(false);
                }
                if(NPPlayground ==1){
                    Playground.setChecked(true);
                }else{
                    Playground.setChecked(false);
                }

            }else{
                Toast.makeText(getApplicationContext(),"Loading Problem",Toast.LENGTH_LONG).show();
            }
        }
    }
}
