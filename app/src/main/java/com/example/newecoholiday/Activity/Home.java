package com.example.newecoholiday.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newecoholiday.Item.ParkItems;
import com.example.newecoholiday.R;
import com.example.newecoholiday.adapter.ParksAdapter;
import com.example.newecoholiday.adapter.ViewPageAdapter;
import com.example.newecoholiday.databinding.ActivityHomeBinding;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.round;

public class Home extends AppCompatActivity implements LocationListener{
    LocationManager locationManager;
    boolean firstTimeLocding = true;

    SQLiteDatabase mDatabase;
    TextView distanceCount;
    SeekBar seekDistance;
    ListView viewData;
    int DistCount = 1000;
    SharedPreferences sharedpreferences;
    //private ProgressDialog pDialog;
    public static ArrayList<ParkItems> ParksList;

    // search bar
    AutoCompleteTextView txtHomeSearch;
    ArrayAdapter NPAdapter;
    String selectNPName;
    List<Integer> NPIDonSearch = new ArrayList<Integer>();
    List<String> NPSearch = new ArrayList<String>();
    //search bar

    // image slider
    public ActivityHomeBinding mBinding;
    ViewPager viewPager;
    LinearLayout sliderDots;
    public int dotCounts;
    public ImageView[] dots;
    //image slider

    public static final String DATABASE_NAME = "myecodatabase";
    String z;
    List<String> NationalParks = new ArrayList<String>();
    List<String> Latitude = new ArrayList<String>();
    List<String> Longitude = new ArrayList<String>();
    List<String> Latitude1 = new ArrayList<String>();
    List<String> Longitude1 = new ArrayList<String>();
    List<String> Distance1 = new ArrayList<String>();


    public static final String MyPREFERENCES = "MyPrefs";
    //SharedPreferences sharedpreferences;

    private static final int START_PLACE_PICKER_REQUEST = 1;

    Double la, ln;
    String Lat, lon;
    String current_address;
    boolean doubleBackToExitPressedOnce = false;
    //GoogleApiClient mGoogleApiClient;



    // current changes
    private PopupWindow popupFilter;
    private RelativeLayout posPopup;

    ImageButton imgStats;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        NationalParks = Arrays.asList(getResources().getStringArray(R.array.NationalParks));

        //imageslider
        startImageSlider();

        // popup filter
        imgStats = (ImageButton) findViewById(R.id.imgStats);
        imgStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, statistics.class));
            }
        });

        ImageButton imgfilter = (ImageButton) findViewById(R.id.imgfilter);
        posPopup = (RelativeLayout) findViewById(R.id.relHome);

        imgfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.popup_filter, null);

                popupFilter = new PopupWindow(customView,
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                posPopup.setEnabled(false);

                popupFilter.showAtLocation(posPopup, Gravity.CENTER, 0, 0);


                distanceCount = (TextView) customView.findViewById(R.id.count);
                seekDistance = (SeekBar) customView.findViewById(R.id.seek_distance);
                seekDistance.setProgress(DistCount);
                //distanceCount.setText(DistCount);
                distanceCount.setText(String.valueOf(DistCount));

                seekDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        distanceCount.setText(progress + "");
                        DistCount = progress;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                });


                CardView btnFilterApply = (CardView) customView.findViewById(R.id.btnFilterApply);
                btnFilterApply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        popupFilter.dismiss();
                        //Toast.makeText(getApplicationContext(),"yes "+DistCount + " got" ,Toast.LENGTH_LONG).show();
                        new GetParksData().execute();
                    }
                });
                CardView btnFilterCancel = (CardView) customView.findViewById(R.id.btnFilterCancel);
                btnFilterCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupFilter.dismiss();
                    }
                });

            }
        });
        // popup filter

        // list is updated
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        distanceCount = (TextView) findViewById(R.id.count);
        seekDistance = (SeekBar) findViewById(R.id.seek_distance);
        viewData = (ListView) findViewById(R.id.view_list);
        ParksList = new ArrayList<ParkItems>();
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);


        new GetParksData().execute();
        Latitude = Arrays.asList(getResources().getStringArray(R.array.Latitude));
        Longitude = Arrays.asList(getResources().getStringArray(R.array.Longitude));


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(firstTimeLocding){
            getLoc();
        }else {
            startLocation();
        }

        RelativeLayout relChangeLoc = (RelativeLayout) findViewById(R.id.relChangeLoc);
        relChangeLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstTimeLocding = false;
                startLocation();
            }
        });

        //enabling text search keypad
        txtHomeSearch = (AutoCompleteTextView) findViewById(R.id.txtHomeSearch);
        txtHomeSearch.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                return false;
            }
        });

        NPAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, NationalParks);

        txtHomeSearch.setAdapter(NPAdapter);

        txtHomeSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectNPName = (String) parent.getItemAtPosition(position);
                checkForTheNP(selectNPName);


            }
        });

        progressDialog = ProgressDialog.show(Home.this,
                "Fetching Your Location","Please Wait...");
        progressDialog.setCancelable(true);

    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (requestCode == START_PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(this, data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            String attributions = (String) place.getAttributions();
            final LatLng latLng = place.getLatLng();

            la = latLng.latitude;
            Lat = la.toString();
            //Lat = Lat.substring(0,10);
            ln = latLng.longitude;
            lon = ln.toString();
            //lon=lon.substring(0,10);
            //Toast.makeText(getApplicationContext(),""+la+" "+ln,Toast.LENGTH_LONG).show();

            current_address = (String) address;
            Distance1.clear();
            Latitude1.clear();
            Longitude1.clear();
            if (Latitude.size() > 0 && Longitude.size() > 0 && Latitude.size() == Longitude.size()) {

                for (int i = 0; i < Longitude.size(); i++) {
                    Location locationA = new Location("point A");

                    locationA.setLatitude(la);
                    locationA.setLongitude(ln);

                    Location locationB = new Location("point B");

                    locationB.setLatitude(Double.parseDouble(Latitude.get(i)));
                    locationB.setLongitude(Double.parseDouble(Longitude.get(i)));

                    float dist = locationA.distanceTo(locationB);
                    int ditKM = (int) round(dist / 1000.0);
                    Distance1.add(ditKM + "");
                    Latitude1.add(Latitude.get(i));
                    Longitude1.add(Longitude.get(i));
                }


                if (Distance1.size() == Latitude.size()) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Latitude", la.toString());
                    editor.putString("Longitude", ln.toString());
                    editor.putString("Address", current_address);
                    editor.commit();
                    new Home.UpdateDistance().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Problem in Uploading Distance. Contact to our Support Team", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Problem in Uploading Distance. Contact to our Support Team", Toast.LENGTH_LONG).show();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public static float distance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    @Override
    public void onLocationChanged(Location location) {

        //Toast.makeText(this, " "+String.valueOf(location.getLongitude())+" "+String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
        la = location.getLatitude();
        ln = location.getLongitude();

        Distance1.clear();
        Latitude1.clear();
        Longitude1.clear();
        if (Latitude.size() > 0 && Longitude.size() > 0 && Latitude.size() == Longitude.size()) {

            for (int i = 0; i < Longitude.size(); i++) {
                Location locationA = new Location("point A");

                locationA.setLatitude(la);
                locationA.setLongitude(ln);

                Location locationB = new Location("point B");

                locationB.setLatitude(Double.parseDouble(Latitude.get(i)));
                locationB.setLongitude(Double.parseDouble(Longitude.get(i)));

                float dist = locationA.distanceTo(locationB);
                int ditKM = (int) round(dist / 1000.0);
                Distance1.add(ditKM + "");
                Latitude1.add(Latitude.get(i));
                Longitude1.add(Longitude.get(i));
            }


            if (Distance1.size() == Latitude.size()) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Latitude", la.toString());
                editor.putString("Longitude", ln.toString());
                editor.putString("Address", current_address);
                editor.commit();
                new Home.UpdateDistance().execute();
            } else {
                Toast.makeText(getApplicationContext(), "Problem in Uploading Distance. Contact to our Support Team", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Problem in Uploading Distance. Contact to our Support Team", Toast.LENGTH_LONG).show();
        }

        locationManager.removeUpdates(this);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private class UpdateDistance extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(MainActivity.this," Data is Loading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected String doInBackground(String... params) {
            //mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            if (Distance1.size() > 0) {
                z = "SUCCESS";
                for (int i = 0; i < Distance1.size(); i++) {
                    if (LandingPage.CheckIsDataAlreadyInDBorNot1("tbl_NationalParksList",
                            "Latitude", Latitude1.get(i),
                            "Longitude", Longitude1.get(i),
                            "Distance", Double.parseDouble(Distance1.get(i)))) {
                        try {
                            z = "SUCCESS";

                            String updateQuery = "UPDATE tbl_NationalParksList " +
                                    "set Distance = " + Double.parseDouble(Distance1.get(i)) + " " +
                                    "Where Latitude = '" + Latitude1.get(i) + "' and Longitude = '" + Longitude1.get(i) + "'";
                            mDatabase.execSQL(updateQuery);


                        } catch (SQLException se) {
                            Toast.makeText(getApplicationContext(), "National Parks Loading Problem :" + se.getMessage(), Toast.LENGTH_LONG).show();
                            z = "FAIL";
                        }
                    }
                }
            }

            progressDialog.dismiss();

            return z;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (z == "SUCCESS") {
                //Toast.makeText(getApplicationContext(),"Distance Updated.",Toast.LENGTH_LONG).show();
                new GetParksData().execute();
                //startActivity( new Intent(Home.this,Home.class));
            } else {
                Toast.makeText(getApplicationContext(), "Distance Update Fail", Toast.LENGTH_LONG).show();
            }

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press quick back again to exit", Toast.LENGTH_SHORT).show();

    }

    private class GetParksData extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Data Loading...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();*/
            ParksList.clear();
        }

        @Override
        protected String doInBackground(String... params) {

            String z = "SUCCESS";
            try {

                String query = "select * from tbl_NationalParksList where Distance <=" + DistCount + " order by Distance ASC";
                Cursor cursorRoom = mDatabase.rawQuery(query, null);

                if (cursorRoom.moveToFirst()) {
                    do {
                        final ParkItems item = new ParkItems();
                        item.setNationalParks(cursorRoom.getString(cursorRoom.getColumnIndex("NationalPark")));
                        item.setArea(cursorRoom.getString(cursorRoom.getColumnIndex("Area")));
                        item.setLatitude(cursorRoom.getString(cursorRoom.getColumnIndex("Latitude")));
                        item.setLongitude(cursorRoom.getString(cursorRoom.getColumnIndex("Longitude")));
                        item.setDistance(cursorRoom.getInt(cursorRoom.getColumnIndex("Distance")));
                        item.setNPID(cursorRoom.getInt(cursorRoom.getColumnIndex("NPID")));
                        NPIDonSearch.add(cursorRoom.getInt(cursorRoom.getColumnIndex("NPID")));
                        NPSearch.add(cursorRoom.getString(cursorRoom.getColumnIndex("NationalPark")));
                        ParksList.add(item);
                    } while (cursorRoom.moveToNext());
                }
                cursorRoom.close();
            } catch (Exception e) {
                z = "FAIL";
            }
            //mDatabase.close();
            return z;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //pDialog.hide();

            if (result.equals("SUCCESS")) {
                if (ParksList.size() > 0) {
                    ParksAdapter parkListAdapter = new ParksAdapter(Home.this, ParksList);
                    viewData.setAdapter(parkListAdapter);
                    /*viewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_LONG).show();
                            ParkItems parkListItem = ParksList.get(position);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("ParkLatitude",parkListItem.getLatitude());
                            editor.putString("ParkLongitude", parkListItem.getLongitude());
                            editor.putString("ParkName",parkListItem.getNationalParks());
                            editor.putString("ParkArea",parkListItem.getArea());
                            editor.putString("ParkDistance",parkListItem.getDistance()+"");
                            editor.commit();
                            startActivity(new Intent(Home.this,MapsActivity.class));
                        }
                    });*/

                } else {
                    viewData.setAdapter(null);
                    Toast.makeText(getApplicationContext(), "No Parks Found to this Distance", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    public void startImageSlider() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewPager = mBinding.viewPager;
        sliderDots = mBinding.SliderDots;
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(Home.this);
        viewPager.setAdapter(viewPageAdapter);
        dotCounts = viewPageAdapter.getCount();
        dots = new ImageView[dotCounts];

        for (int i = 0; i < dotCounts; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.not_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotCounts; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.not_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new Home.myTimerTask(), 4000, 4000);
    }

    public class myTimerTask extends TimerTask {
        @Override
        public void run() {

            Home.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });
        }
    }

    public void checkForTheNP(String selectNPName) {
        int pos = -1;
        for (int i = 0; i < NationalParks.size(); i++) {
            if (NationalParks.get(i).equals(selectNPName)) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ParkLatitude", Latitude.get(i));
                editor.putString("ParkLongitude", Longitude.get(i));
                editor.putString("ParkName", NationalParks.get(i));
                editor.putString("ParkDistance", Distance1.get(i));
                editor.putInt("NPID", NPIDonSearch.get(NPSearch.indexOf(selectNPName)));
                editor.apply();
                startActivity(new Intent(Home.this, ParkPage.class));
                break;

            }
        }
    }

    public void startLocation() {
        try {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(Home.this);
            startActivityForResult(intent, START_PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException
                | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }
    public void getLoc(){

        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 5000, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

}
