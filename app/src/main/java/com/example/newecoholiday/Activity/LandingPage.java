package com.example.newecoholiday.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newecoholiday.Item.ParkItems;
import com.example.newecoholiday.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LandingPage extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    public static final String DATABASE_NAME = "myecodatabase";
    private Handler mhandler = new Handler();
    private LocationManager locationManager ;


    //Data loading variables
    static SQLiteDatabase mDatabase;
    SharedPreferences sharedpreferences;
    String z;
    List<String> NationalParks = new ArrayList<String>();
    List<String> Area = new ArrayList<String>();
    List<String> Latitude = new ArrayList<String>();
    List<String> Longitude = new ArrayList<String>();
    List<String> Distance = new ArrayList<String>();
    List<String> Latitude1 = new ArrayList<String>();
    List<String> Longitude1 = new ArrayList<String>();
    List<String> Distance1 = new ArrayList<String>();
    //String[] showdistance = new String[20];

    // camping and treking tables
    List<String> CampingName = new ArrayList<String>();
    List<String> CNPID = new ArrayList<String>();
    List<String> CampDesc = new ArrayList<String>();
    List<String> CampLatitude = new ArrayList<String>();
    List<String> CampLongitude= new ArrayList<String>();

    List<String> TNPID = new ArrayList<String>();
    List<String> TrackName = new ArrayList<String>();
    List<String> TrackDesc = new ArrayList<String>();
    List<String> TrackLength = new ArrayList<String>();
    List<String> TrackTime = new ArrayList<String>();
    List<String> TrackLat = new ArrayList<String>();
    List<String> TrackLong= new ArrayList<String>();
    //

    //lookouts table
    List<String> LookoutSite = new ArrayList<String>();
    List<String> LookLatitude = new ArrayList<String>();
    List<String> LookLongitude = new ArrayList<String>();
    List<String> LNPID = new ArrayList<String>();
    List<String> LookDesc = new ArrayList<String>();
    //
    //Facilities Data
    List<String> NPCamping = new ArrayList<String>();
    List<String> NPCanoeing = new ArrayList<String>();
    List<String> NPFishing = new ArrayList<String>();
    List<String> NPScienceDrive = new ArrayList<String>();
    List<String> NPHorseRiding = new ArrayList<String>();
    List<String> NPHunting = new ArrayList<String>();
    List<String> NPTrekking = new ArrayList<String>();
    List<String> NPCycling = new ArrayList<String>();
    List<String> NPPicnicking = new ArrayList<String>();
    List<String> NPSightSeeing = new ArrayList<String>();
    List<String> NPSkiing = new ArrayList<String>();
    List<String> NPWhiteWaterRafting = new ArrayList<String>();
    List<String> NPCampFire = new ArrayList<String>();
    List<String> NPSwimming = new ArrayList<String>();
    List<String> NPSailing = new ArrayList<String>();
    List<String> NPBBQ = new ArrayList<String>();
    List<String> NPBirdWatching = new ArrayList<String>();
    List<String> NPPlayGround = new ArrayList<String>();
    List<String> NPID = new ArrayList<String>();
    //Facilities Data


    public static final String MyPREFERENCES = "MyPrefs" ;
    public static ArrayList<ParkItems> ParksList ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        getApplicationContext().deleteDatabase(DATABASE_NAME);

        NationalParks = Arrays.asList(getResources().getStringArray(R.array.NationalParks));
        Area = Arrays.asList(getResources().getStringArray(R.array.Area));
        Latitude = Arrays.asList(getResources().getStringArray(R.array.Latitude));
        Longitude = Arrays.asList(getResources().getStringArray(R.array.Longitude));
        Distance = Arrays.asList(getResources().getStringArray(R.array.Distance));

        // camping and treking data
        CampingName = Arrays.asList(getResources().getStringArray(R.array.Camping));
        CNPID = Arrays.asList(getResources().getStringArray(R.array.CNPID));
        CampDesc = Arrays.asList(getResources().getStringArray(R.array.CampDesc));
        CampLatitude = Arrays.asList(getResources().getStringArray(R.array.CampLat));
        CampLongitude = Arrays.asList(getResources().getStringArray(R.array.CampLong));

        TrackName = Arrays.asList(getResources().getStringArray(R.array.TrackName));
        TNPID = Arrays.asList(getResources().getStringArray(R.array.TNPID));
        TrackDesc = Arrays.asList(getResources().getStringArray(R.array.TrackDesc));
        TrackLength = Arrays.asList(getResources().getStringArray(R.array.TrackLength));
        TrackTime = Arrays.asList(getResources().getStringArray(R.array.TrackTime));
        TrackLat = Arrays.asList(getResources().getStringArray(R.array.TrackkLat));
        TrackLong = Arrays.asList(getResources().getStringArray(R.array.TrackLong));
        //
        // LookOut Data
        LookoutSite = Arrays.asList(getResources().getStringArray(R.array.LookoutSite));
        LookLatitude = Arrays.asList(getResources().getStringArray(R.array.LookLatitude));
        LookLongitude = Arrays.asList(getResources().getStringArray(R.array.LookLongitude));
        LNPID = Arrays.asList(getResources().getStringArray(R.array.LNPID));
        LookDesc=Arrays.asList(getResources().getStringArray(R.array.LookDesc));
        //
        NPCamping = Arrays.asList(getResources().getStringArray(R.array.NPCamping));
        NPCanoeing = Arrays.asList(getResources().getStringArray(R.array.NPCanoeing));
        NPFishing = Arrays.asList(getResources().getStringArray(R.array.NPFishing));
        NPScienceDrive = Arrays.asList(getResources().getStringArray(R.array.NPScienceDrive));
        NPHorseRiding = Arrays.asList(getResources().getStringArray(R.array.NPHorseRiding));
        NPHunting = Arrays.asList(getResources().getStringArray(R.array.NPHunting));
        NPTrekking = Arrays.asList(getResources().getStringArray(R.array.NPTrekking));
        NPCycling = Arrays.asList(getResources().getStringArray(R.array.NPCycling));
        NPPicnicking = Arrays.asList(getResources().getStringArray(R.array.NPPicnicking));
        NPSightSeeing = Arrays.asList(getResources().getStringArray(R.array.NPSightSeeing));
        NPSkiing = Arrays.asList(getResources().getStringArray(R.array.NPSkiing));
        NPWhiteWaterRafting = Arrays.asList(getResources().getStringArray(R.array.NPWhiteWaterRafting));
        NPCampFire = Arrays.asList(getResources().getStringArray(R.array.NPCampFire));
        NPSwimming = Arrays.asList(getResources().getStringArray(R.array.NPSwimming));
        NPSailing = Arrays.asList(getResources().getStringArray(R.array.NPSailing));
        NPBBQ = Arrays.asList(getResources().getStringArray(R.array.NPBBQ));
        NPBirdWatching = Arrays.asList(getResources().getStringArray(R.array.NPBirdWatching));
        NPPlayGround = Arrays.asList(getResources().getStringArray(R.array.NPPlayGround));
        NPID = Arrays.asList(getResources().getStringArray(R.array.NPID));

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (!isLocationEnabled()) {
            new AlertDialog.Builder(this)
                    .setTitle("Please enable location access for better results")
                    .setMessage("Click ok to enable or cancel and enable manually later")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                            finish();
                            startActivity(getIntent());
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .show();

        }else{
            Thread mythead = new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(1000);
                        mhandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(25);


                                if (createEcoParkTables()) {
                                    new UploadData().execute();
                                    //Toast.makeText(getApplicationContext(), "Tables Created", Toast.LENGTH_LONG).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Error has occured. Please try again", Toast.LENGTH_LONG).show();

                                }

                                progressBar.setProgress(75);
                            }
                        });

                        sleep(1000);
                        mhandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(1000);
                            }
                        });

                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            mythead.start();
        }



    }
    protected boolean isLocationEnabled(){
        String le = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(le);
        if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            return false;
        } else {
            return true;
        }
    }

    private boolean createEcoParkTables() {
        boolean isCreated = false;
        //mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        try{
            mDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS tbl_NationalParksList (\n" +
                            "   [NPID] INTEGER NOT NULL CONSTRAINT PK_tbl_NationalParksList PRIMARY KEY AUTOINCREMENT,\n" +
                            "   [NationalPark] [varchar](1000),\n" +
                            "   [Area] [varchar](100),\n" +
                            "   [Latitude] [varchar](500),\n" +
                            "   [Longitude] [varchar](500),\n" +
                            "   [Distance] [REAL] \n" +
                            ");"
            );
            mDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS tbl_Camping_Sites (\n" +
                            "   [CampID] INTEGER NOT NULL CONSTRAINT PK_tbl_Camping_Sites PRIMARY KEY AUTOINCREMENT,\n" +
                            "   [NPID] [int],\n" +
                            "   [CampingName] [varchar](100),\n" +
                            "   [CampingDescription] [varchar](1000),\n" +
                            "   [Latitude] [varchar](500),\n" +
                            "   [Longitude] [varchar](500)\n" +
                            ");"
            );
            mDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS tbl_Treck_Sites (\n" +
                            "   [TID] INTEGER NOT NULL CONSTRAINT PK_tbl_Treck_Sites PRIMARY KEY AUTOINCREMENT,\n" +
                            "   [NPID] [int],\n" +
                            "   [TrackName] [varchar](1000),\n" +
                            "   [Description] [varchar](1000) ,\n" +
                            "   [Length] [REAL],\n" +
                            "   [Time] [REAL],\n" +
                            "   [Latitude] [varchar](500),\n" +
                            "   [Longitude] [varchar](500)\n" +
                            ");"
            );
            mDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS tbl_LookOut_Sites (\n" +
                            "   [LookID] INTEGER NOT NULL CONSTRAINT PK_tbl_LookOut_Sites PRIMARY KEY AUTOINCREMENT,\n" +
                            "   [NPID] [int],\n" +
                            "   [LookOutSite] [varchar](1000),\n" +
                            "   [Description] [varchar](1000) ,\n" +
                            "   [Latitude] [varchar](500),\n" +
                            "   [Longitude] [varchar](500)\n" +
                            ");"
            );
            mDatabase.execSQL(
                    "CREATE TABLE IF NOT EXISTS tbl_NationalPark_Facilities (\n" +
                            "   [NPFID] INTEGER NOT NULL CONSTRAINT PK_tbl_NationalPark_Facilities PRIMARY KEY AUTOINCREMENT,\n" +
                            "   [NPID] [int],\n" +
                            "   [Camping] [varchar] (10),\n" +
                            "   [Canoeing] [varchar] (10) ,\n" +
                            "   [Fishing] [varchar] (10),\n" +
                            "   [ScienicDrive] [varchar] (10),\n" +
                            "   [HorseRiding] [varchar] (10),\n" +
                            "   [Hunting] [varchar] (10),\n" +
                            "   [Trekking] [varchar] (10),\n" +
                            "   [Cycling] [varchar] (10),\n" +
                            "   [Picknicking] [varchar] (10),\n" +
                            "   [SightSeeing] [varchar] (10),\n" +
                            "   [Skiing] [varchar] (10),\n" +
                            "   [whiteWaterRafting] [varchar] (10),\n" +
                            "   [CampFire] [varchar] (10),\n" +
                            "   [Swimming] [varchar] (10),\n" +
                            "   [YachtingSailing] [varchar] (10),\n" +
                            "   [BBQ] [varchar] (10),\n" +
                            "   [BirdWatching] [varchar] (10),\n" +
                            "   [Playground] [varchar] (10)\n" +
                            ");"
            );
//            pDialog.hide();
            isCreated =true;

        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage()+"",Toast.LENGTH_LONG).show();
            isCreated= false;
        }
        finally {
            //mDatabase.close();
            return isCreated;
        }


    }
    private class UploadData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(MainActivity.this," Data is Loading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected String doInBackground(String... params) {

            if(NationalParks.size()>0){
                z="SUCCESS";
                for(int i=0;i<NationalParks.size();i++){
                    if(CheckIsDataAlreadyInDBorNot("tbl_NationalParksList",
                            "NationalPark",NationalParks.get(i),
                            "Area",Area.get(i))){
                        try{
                            String insertSQL = "INSERT INTO tbl_NationalParksList \n" +
                                    "(NationalPark, Area, Latitude, Longitude,Distance)\n" +
                                    "VALUES \n" +
                                    "('"+NationalParks.get(i)+"'," +
                                    " '"+Area.get(i)+"'," +
                                    " '"+Latitude.get(i)+"'," +
                                    " '"+Longitude.get(i)+"'," +
                                    " "+Double.parseDouble(Distance.get(i)) +
                                    ");";


                            mDatabase.execSQL(insertSQL);
                        }catch (SQLException se){
                            Toast.makeText(getApplicationContext(),"National Parks Loading Problem :"+se.getMessage(),Toast.LENGTH_LONG).show();
                            z="FAIL";
                        }
                    }
                }
                for(int i=0;i<CampingName.size();i++){
                    if(CheckIsDataAlreadyInDBorNotInt("tbl_Camping_Sites",
                            "CampingDescription",CampingName.get(i),
                            "NPID",Integer.parseInt(CNPID.get(i)))){
                        try{
                            String insertSQL = "INSERT INTO tbl_Camping_Sites \n" +
                                    "(NPID, CampingName,CampingDescription,Latitude,Longitude)\n" +
                                    "VALUES \n" +
                                    "("+Integer.parseInt(CNPID.get(i))+"," +
                                    " '"+CampingName.get(i)+"'," +
                                    " '"+CampDesc.get(i)+"'," +
                                    " '"+CampLatitude.get(i)+"'," +
                                    " '"+CampLongitude.get(i) + "'"+
                                    ");";


                            mDatabase.execSQL(insertSQL);
                        }catch (SQLException se){
                            Toast.makeText(getApplicationContext(),"Camping Table Loading Problem :"+se.getMessage(),Toast.LENGTH_LONG).show();
                            z="FAIL";
                        }
                    }
                }
                for(int i=0;i<TrackName.size();i++){
                    if(CheckIsDataAlreadyInDBorNotInt("tbl_Treck_Sites",
                            "TrackName",TrackName.get(i),
                            "NPID",Integer.parseInt(TNPID.get(i)))){
                        try{
                            String insertSQL = "INSERT INTO tbl_Treck_Sites \n" +
                                    "(NPID, TrackName,Description,Length,Time,Latitude,Longitude)\n" +
                                    "VALUES \n" +
                                    "("+Integer.parseInt(TNPID.get(i))+"," +
                                    " '"+TrackName.get(i)+"'," +
                                    " '"+TrackDesc.get(i)+"'," +
                                    " "+Double.parseDouble(TrackLength.get(i))+"," +
                                    " "+Double.parseDouble(TrackTime.get(i))+ ","+
                                    " '"+TrackLat.get(i)+"'," +
                                    " '"+TrackLong.get(i) + "'"+
                                    ");";


                            mDatabase.execSQL(insertSQL);
                        }catch (SQLException se){
                            Toast.makeText(getApplicationContext(),"Track Table Loading Problem :"+se.getMessage(),Toast.LENGTH_LONG).show();
                            z="FAIL";
                        }
                    }
                }
                for(int i=0;i<LookoutSite.size();i++){
                    if(CheckIsDataAlreadyInDBorNotInt("tbl_LookOut_Sites",
                            "LookOutSite",LookoutSite.get(i),
                            "NPID",Integer.parseInt(LNPID.get(i)))){
                        try{
                            String insertSQL = "INSERT INTO tbl_LookOut_Sites \n" +
                                    "(NPID, LookOutSite,Description,Latitude,Longitude)\n" +
                                    "VALUES \n" +
                                    "("+Integer.parseInt(LNPID.get(i))+"," +
                                    " '"+LookoutSite.get(i)+"'," +
                                    " '"+LookDesc.get(i)+"'," +
                                    " '"+LookLatitude.get(i)+"'," +
                                    " '"+LookLongitude.get(i)+ "'"+
                                    ");";

                            mDatabase.execSQL(insertSQL);
                        }catch (SQLException se){
                            Toast.makeText(getApplicationContext(),"Lookouts Table Loading Problem :"+se.getMessage(),Toast.LENGTH_LONG).show();
                            z="FAIL";
                        }
                    }
                }
                for(int i=0;i<NPCamping.size();i++){
                    if(CheckIsDataAlreadyInDBorNotIntiger("tbl_NationalPark_Facilities",
                            "NPID",Integer.parseInt(NPID.get(i)))){
                        try{
                            String insertSQL = "INSERT INTO tbl_NationalPark_Facilities \n" +
                                    "(NPID, " +
                                    "Camping," +
                                    "Canoeing," +
                                    "Fishing," +
                                    "ScienicDrive," +
                                    "HorseRiding," +
                                    "Hunting," +
                                    "Trekking," +
                                    "Cycling," +
                                    "Picknicking," +
                                    "SightSeeing," +
                                    "Skiing," +
                                    "whiteWaterRafting," +
                                    "CampFire," +
                                    "Swimming," +
                                    "YachtingSailing," +
                                    "BBQ," +
                                    "BirdWatching," +
                                    "Playground )"+
                                    "VALUES \n" +
                                    "("+Integer.parseInt(NPID.get(i))+"," +
                                    " '"+NPCamping.get(i)+"'," +
                                    " '"+NPCanoeing.get(i)+"'," +
                                    " '"+NPFishing.get(i)+"'," +
                                    " '"+NPScienceDrive.get(i)+"'," +
                                    " '"+NPHorseRiding.get(i)+"'," +
                                    " '"+NPHunting.get(i)+"'," +
                                    " '"+NPTrekking.get(i)+"'," +
                                    " '"+NPCycling.get(i)+"'," +
                                    " '"+NPPicnicking.get(i)+"'," +
                                    " '"+NPSightSeeing.get(i)+"'," +
                                    " '"+NPSkiing.get(i)+"'," +
                                    " '"+NPWhiteWaterRafting.get(i)+"'," +
                                    " '"+NPCampFire.get(i)+"'," +
                                    " '"+NPSwimming.get(i)+"'," +
                                    " '"+NPSailing.get(i)+"'," +
                                    " '"+NPBBQ.get(i)+"'," +
                                    " '"+NPBirdWatching.get(i)+"'," +
                                    " '"+NPPlayGround.get(i)+"'" +
                                    ");";


                            mDatabase.execSQL(insertSQL);
                        }catch (SQLException se){
                            Toast.makeText(getApplicationContext(),"Track Table Loading Problem :"+se.getMessage(),Toast.LENGTH_LONG).show();
                            z="FAIL";
                        }
                    }
                }

            }


            return z;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(z=="SUCCESS"){
                //Toast.makeText(getApplicationContext(),"Data Loading Completed.",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Data Loading Fail. Please try again",Toast.LENGTH_LONG).show();
            }



        }
    }

    public boolean CheckIsDataAlreadyInDBorNot(String TableName,
                                               String dbfield,
                                               String fieldValue,
                                               String dbfield1,
                                               String fieldValue1) {
        //mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue+"' and " + dbfield1 + " = '" + fieldValue1+"'";
        Cursor cursor = mDatabase.rawQuery(Query, null);
        if(cursor.getCount() > 0){
            cursor.close();
            return false;
        }
        cursor.close();
        //mDatabase.close();
        return true;
    }
    public boolean CheckIsDataAlreadyInDBorNotIntiger(String TableName,
                                                      String dbfield,
                                                      int fieldValue)
    {
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = mDatabase.rawQuery(Query, null);
        if(cursor.getCount() > 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean CheckIsDataAlreadyInDBorNotInt(String TableName,
                                                  String dbfield,
                                                  String fieldValue,
                                                  String dbfield1,
                                                  int fieldValue1) {
        //mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue+"' and " + dbfield1 + " = '" + fieldValue1+"'";
        Cursor cursor = mDatabase.rawQuery(Query, null);
        if(cursor.getCount() > 0){
            cursor.close();
            return false;
        }
        cursor.close();
        //mDatabase.close();
        return true;
    }

    public static boolean CheckIsDataAlreadyInDBorNot1(String TableName,
                                                String dbfield,
                                                String fieldValue,
                                                String dbfield1,
                                                String fieldValue1,
                                                String dbfield2,
                                                double fieldValue2) {
        //mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String Query = "Select * from " + TableName + " where " + dbfield + " = '" + fieldValue+"' and " + dbfield1 + " = '" + fieldValue1+"' and " + dbfield2 + " = " + fieldValue2+"";
        Cursor cursor = mDatabase.rawQuery(Query, null);
        if(cursor.getCount() > 0){
            cursor.close();
            return false;
        }
        cursor.close();
        //mDatabase.close();
        return true;
    }

}
