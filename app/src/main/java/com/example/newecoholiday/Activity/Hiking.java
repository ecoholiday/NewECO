package com.example.newecoholiday.Activity;

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

public class Hiking extends AppCompatActivity {

    Boolean firstTimeLoading = true;
    SQLiteDatabase mDatabase;

    SharedPreferences sharedpreferences;
    int NPID;
    String parkName;
    ListView lstActivity;

    public static ArrayList<ParkFacilityListItem> ParkCampingFacilityList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiking);

        sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);
        NPID = sharedpreferences.getInt("NPID", 0);
        parkName = sharedpreferences.getString("ParkName","");

        setTitle(parkName);
        ParkCampingFacilityList = new ArrayList<ParkFacilityListItem>();
        mDatabase = openOrCreateDatabase(Home.DATABASE_NAME, MODE_PRIVATE, null);

        lstActivity = (ListView) findViewById(R.id.lstHikeActivity);
        TextView txtCampPage = (TextView)findViewById(R.id.txtHikePage);
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

                    String query1 = "select * from tbl_Treck_Sites where NPID =" + NPID + " order by TrackName ASC";
                    Cursor cursorCamping = mDatabase.rawQuery(query1, null);

                    if (cursorCamping.moveToFirst()) {
                        do {
                            final ParkFacilityListItem lookItem = new ParkFacilityListItem();
                            lookItem.setDescription(cursorCamping.getString(cursorCamping.getColumnIndex("Description")));
                            lookItem.setParkFacilityName(cursorCamping.getString(cursorCamping.getColumnIndex("TrackName")));
                            lookItem.setParkFacilityLatitude(cursorCamping.getString(cursorCamping.getColumnIndex("Latitude")));
                            lookItem.setParkFacilityLongitude(cursorCamping.getString(cursorCamping.getColumnIndex("Longitude")));
                            lookItem.setTrackTime(cursorCamping.getFloat(cursorCamping.getColumnIndex("Time")));
                            lookItem.setTrackLength(cursorCamping.getFloat(cursorCamping.getColumnIndex("Length")));
                            lookItem.setParkFacilitySourceType("Hiking");
                            ParkCampingFacilityList.add(lookItem);
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
            //pDialog.hide();

            if(result.equals("SUCCESS")){

                if(ParkCampingFacilityList.size()>0){
                    //Toast.makeText(getApplicationContext(),""+ NPID,Toast.LENGTH_LONG).show();
                    lstActivity.setVisibility(View.VISIBLE);
                    ParkFacilityAdapter parkListAdapter = new ParkFacilityAdapter(Hiking.this,ParkCampingFacilityList);
                    lstActivity.setAdapter(parkListAdapter);
                    lstActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ParkFacilityListItem parkListItem = ParkCampingFacilityList.get(position);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("FacilityNPID",NPID);
                            editor.putString("ParkName",parkName);
                            editor.apply();

                            startActivity(new Intent(Hiking.this,FacilityListActivity.class));
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
