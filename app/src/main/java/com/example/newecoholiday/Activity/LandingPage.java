package com.example.newecoholiday.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.newecoholiday.R;


public class LandingPage extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    public static final String DATABASE_NAME = "myecodatabase";
    private Handler mhandler = new Handler();
    private LocationManager locationManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        getApplicationContext().deleteDatabase(DATABASE_NAME);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (!isLocationEnabled()) {
            new AlertDialog.Builder(this)
                    .setTitle("Please activate location")
                    .setMessage("Click ok to goto settings else exit.")
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
                        mhandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(25);
                            }
                        });
                        sleep(750);
                        mhandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(75);
                            }
                        });
                        sleep(750);
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
}
