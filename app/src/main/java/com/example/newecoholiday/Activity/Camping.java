package com.example.newecoholiday.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newecoholiday.Item.ParkFacilityListItem;
import com.example.newecoholiday.R;
import com.example.newecoholiday.adapter.ParkFacilityAdapter;

import java.util.ArrayList;

public class Camping extends AppCompatActivity {

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
    String facilities;

    Boolean firstTimeLoading = true;
    SQLiteDatabase mDatabase;

    SharedPreferences sharedpreferences;
    int NPID;
    String parkName;
    String area;
    String parkDistance;
    String latitude;
    String longitude;
    ListView lstActivity;

    ProgressDialog pDialog;
    ArrayList<String> trekking = new ArrayList<String>();
    ParkFacilityAdapter parkFacilityAdapter;
    public static ArrayList<ParkFacilityListItem> ParkCampingFacilityList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping);

        sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);
        NPID = sharedpreferences.getInt("NPID", 0);
        parkName = sharedpreferences.getString("ParkName","");



        setTitle(parkName);
        ParkCampingFacilityList = new ArrayList<ParkFacilityListItem>();
        mDatabase = openOrCreateDatabase(Home.DATABASE_NAME, MODE_PRIVATE, null);

        lstActivity = (ListView) findViewById(R.id.lstCampActivity);
        TextView txtCampPage = (TextView)findViewById(R.id.txtCampPage) ;
        txtCampPage.setText(parkName);

        new GetParksFacilityData().execute();





    }
    private class GetParksFacilityData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Camping.this);
            pDialog.setMessage("Data Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show(); */
            ParkCampingFacilityList.clear();
        }

        @Override
        protected String doInBackground(String... params) {

            String z = "SUCCESS";
            if (firstTimeLoading) {
                firstTimeLoading = false;
                try {

                    String query1 = "select * from tbl_Camping_Sites where NPID =" + NPID + " order by CampingName ASC";
                    Cursor cursorCamping = mDatabase.rawQuery(query1, null);

                    if (cursorCamping.moveToFirst()) {
                        do {
                            final ParkFacilityListItem campItem = new ParkFacilityListItem();
                            campItem.setDescription(cursorCamping.getString(cursorCamping.getColumnIndex("CampingDescription")));
                            campItem.setParkFacilityName(cursorCamping.getString(cursorCamping.getColumnIndex("CampingName")));
                            campItem.setParkFacilityLatitude(cursorCamping.getString(cursorCamping.getColumnIndex("Latitude")));
                            campItem.setParkFacilityLongitude(cursorCamping.getString(cursorCamping.getColumnIndex("Longitude")));
                            campItem.setParkFacilityID(cursorCamping.getInt(cursorCamping.getColumnIndex("CampID")));
                            campItem.setParkFacilitySourceType("Camping");
                            ParkCampingFacilityList.add(campItem);
                        } while (cursorCamping.moveToNext());
                    }

                    cursorCamping.close();
                } catch (Exception e) {
                    z = "FAIL";
                }
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

            }

            return z;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //pDialog.hide();

            if(result.equals("SUCCESS")){

                if(ParkCampingFacilityList.size()>0){
                    //Toast.makeText(getApplicationContext(),""+ NPID,Toast.LENGTH_LONG).show();
                    lstActivity.setVisibility(View.VISIBLE);
                    ParkFacilityAdapter parkListAdapter = new ParkFacilityAdapter(Camping.this,ParkCampingFacilityList);
                    lstActivity.setAdapter(parkListAdapter);
                    lstActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ParkFacilityListItem parkListItem = ParkCampingFacilityList.get(position);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("FacilityNPID",NPID);
                            editor.putString("ParkName",parkName);
                            editor.apply();

                            startActivity(new Intent(Camping.this,FacilityListActivity.class));
                        }
                    });
                }else{
                    lstActivity.setAdapter(null);
                    lstActivity.setVisibility(View.GONE);
                    TextView txtNoData = (TextView)findViewById(R.id.txtNoData);
                    txtNoData.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(),"No Data Available for "+parkName,Toast.LENGTH_LONG).show();
                }

                if(NPCamping ==1){ facilities = facilities + "Camping | ";}
                if(NPCanoeing ==1){ facilities = facilities + "Canoeing | ";}
                if(NPFishing ==1){ facilities = facilities + "Fishing | ";}
                if(NPScienceDrive ==1){ facilities = facilities + "ScienceDrive | ";}
                if(NPHorseRiding ==1){ facilities = facilities + "HorseRiding | ";}
                if(NPHunting ==1){ facilities = facilities + "Hunting | ";}
                if(NPTrekking ==1){ facilities = facilities + "Trekking | ";}
                if(NPCycling ==1){ facilities = facilities + "Cycling | ";}
                if(NPPicnicking ==1){ facilities = facilities + "Picnicking | ";}
                if(NPSightSeeing ==1){ facilities = facilities + "SightSeeing | ";}
                if(NPSkiing ==1){ facilities = facilities + "Skiing | ";}
                if(NPWhiteWaterRafting ==1){ facilities = facilities + "WhiteWaterRafting | ";}
                if(NPCampFire ==1){ facilities = facilities + "CampFire | ";}
                if(NPSwimming ==1){ facilities = facilities + "Swimming | ";}
                if(NPYachtingSailing ==1){ facilities = facilities + "YachtingSailing | ";}
                if(NPBBQ ==1){ facilities = facilities + "BBQ | ";}
                if(NPBirdWatching ==1){ facilities = facilities + "BirdWatching | ";}
                if(NPPlayground ==1){ facilities = facilities + "Playground | ";}

                facilities = facilities.replace("null", "");

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("CampFacilities", facilities);
                editor.apply();


            }else{
                Toast.makeText(getApplicationContext(),"Loading Problem",Toast.LENGTH_LONG).show();
            }
        }
    }


    /*@Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getBaseContext());
    }*/


}
