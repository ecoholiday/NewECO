package com.example.newecoholiday.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.newecoholiday.Activity.CampSite;
import com.example.newecoholiday.Activity.Home;
import com.example.newecoholiday.Item.ParkFacilityListItem;
import com.example.newecoholiday.R;

import java.util.ArrayList;


public class ParkFacilityAdapter extends BaseAdapter {
    Context context;
    private ArrayList<ParkFacilityListItem> parksfacilityList;
    LayoutInflater inflter;
    TextView parkFacilityName;

    SharedPreferences sharedpreferences;

    public ParkFacilityAdapter(Context applicationContext, ArrayList<ParkFacilityListItem> parksList) {
        sharedpreferences = applicationContext.getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);
        this.context = applicationContext;
        this.parksfacilityList = parksList;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return parksfacilityList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.list_parkfacility, null);
        parkFacilityName = (TextView) convertView.findViewById(R.id.NParkFacilityName);


        final ParkFacilityListItem item = parksfacilityList.get(position);

        parkFacilityName.setText(item.getParkFacilityName());

        CardView cardCampMain = (CardView)convertView.findViewById(R.id.cardCampMain);

        cardCampMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("FacilityLatitude",item.getParkFacilityLatitude());
                editor.putString("FacilityLongitude", item.getParkFacilityLongitude());
                editor.putString("FacilityName",item.getParkFacilityName());
                editor.putString("FacilityDesc",item.getDescription());
                editor.putString("FacilityType",item.getParkFacilitySourceType());
                editor.putFloat("TrackLength",item.getTrackLength());
                editor.putFloat("TrackTime",item.getTrackTime());

                editor.apply();


                context.startActivity(new Intent(context, CampSite.class));
            }
        });

        return convertView;
    }
}
