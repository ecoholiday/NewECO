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
import android.widget.Toast;

import com.example.newecoholiday.Item.ParkFacilityListItem;
import com.example.newecoholiday.R;
import com.example.newecoholiday.adapter.ParkFacilityAdapter;

import java.util.ArrayList;

public class Camping extends AppCompatActivity {

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
        parkDistance = sharedpreferences.getString("ParkDistance","");
        area = sharedpreferences.getString("ParkArea","");
        latitude = sharedpreferences.getString("ParkLatitude","");
        longitude = sharedpreferences.getString("ParkLongitude","");

        setTitle(parkName);
        ParkCampingFacilityList = new ArrayList<ParkFacilityListItem>();
        mDatabase = openOrCreateDatabase(Home.DATABASE_NAME, MODE_PRIVATE, null);

        lstActivity = (ListView) findViewById(R.id.lstCampActivity);


    }
    private class GetParksFacilityData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Camping.this);
            pDialog.setMessage("Data Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
            ParkCampingFacilityList.clear();
        }

        @Override
        protected String doInBackground(String... params) {

            String z = "SUCCESS";
            if (firstTimeLoading) {
                firstTimeLoading = false;
                try {

                    String query1 = "select * from tbl_Camping_Sites where NPID =" + NPID + " order by CampingDescription ASC";
                    Cursor cursorCamping = mDatabase.rawQuery(query1, null);

                    if (cursorCamping.moveToFirst()) {
                        do {
                            final ParkFacilityListItem campItem = new ParkFacilityListItem();
                            campItem.setParkFacilityName(cursorCamping.getString(cursorCamping.getColumnIndex("CampingDescription")));
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
            }

            return z;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.hide();

            if(result.equals("SUCCESS")){

                if(ParkCampingFacilityList.size()>0){
                    lstActivity.setVisibility(View.VISIBLE);
                    ParkFacilityAdapter parkListAdapter = new ParkFacilityAdapter(Camping.this,ParkCampingFacilityList);
                    lstActivity.setAdapter(parkListAdapter);
                    lstActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ParkFacilityListItem parkListItem = ParkCampingFacilityList.get(position);
                            if(parkListItem.getParkFacilitySourceType().equals("Camping")){
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putInt("FacilityNPID",NPID);
                                editor.putString("ParkName",parkName);
                                editor.apply();
                                startActivity(new Intent(Camping.this,FacilityListActivity.class));
                                //Toast.makeText(getApplicationContext(),"Camping Under Development",Toast.LENGTH_LONG).show();
                            }else if(parkListItem.getParkFacilitySourceType().equals("Trekking")){
                                Toast.makeText(getApplicationContext(),"Trekking Under Development",Toast.LENGTH_LONG).show();
                            }else if(parkListItem.getParkFacilitySourceType().equals("Lookout")){
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("ParkLatitude",parkListItem.getParkFacilityLatitude());
                                editor.putString("ParkLongitude", parkListItem.getParkFacilityLongitude());
                                editor.putString("ParkName",parkName+" ( "+parkListItem.getParkFacilityName()+" ) ");
                                editor.putString("ParkArea",area);
                                editor.putString("ParkDistance",parkDistance);
                                editor.apply();
                                startActivity(new Intent(Camping.this,MapsActivity.class));
                            }
                        }
                    });
                }else{
                    lstActivity.setAdapter(null);
                    lstActivity.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"No Data Available for "+parkName,Toast.LENGTH_LONG).show();
                }


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
