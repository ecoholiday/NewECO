package com.example.newecoholiday.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.newecoholiday.Activity.Home;
import com.example.newecoholiday.Activity.MapsActivity;
import com.example.newecoholiday.Activity.ParkPage;
import com.example.newecoholiday.Item.ParkItems;
import com.example.newecoholiday.R;

import java.util.ArrayList;

public class ParksAdapter extends BaseAdapter {

    Context context;
    private ArrayList<ParkItems> parksList;
    LayoutInflater inflter;
    TextView nationalParkName,area,distance;
    ImageButton park_Navigation;
    CardView listParkDetails;

    SharedPreferences sharedpreferences;

    public ParksAdapter(Context applicationContext, ArrayList<ParkItems> parks) {
        sharedpreferences = applicationContext.getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);

        this.context = applicationContext;
        this.parksList = parks;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return parksList.size();
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

        convertView = inflter.inflate(R.layout.list_parks, null);
        nationalParkName = (TextView) convertView.findViewById(R.id.NParkName);
        area = (TextView) convertView.findViewById(R.id.Area);
        distance = (TextView) convertView.findViewById(R.id.ParkDistance);
        park_Navigation = (ImageButton) convertView.findViewById(R.id.btnNavigation);
        listParkDetails = (CardView) convertView.findViewById(R.id.listParkDetails);

        final ParkItems item = parksList.get(position);

        nationalParkName.setText(item.getNationalParks());
        area.setText(" "+item.getArea()+" ft2");
        distance.setText(" "+item.getDistance() + " KMs");

        park_Navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ParkLatitude",item.getLatitude());
                editor.putString("ParkLongitude", item.getLongitude());
                editor.putString("ParkName",item.getNationalParks());
                editor.putString("ParkArea",item.getArea());
                editor.putString("ParkDistance",item.getDistance()+"");
                editor.commit();
                context.startActivity(new Intent(context, MapsActivity.class));
            }
        });

        listParkDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("ParkLatitude",item.getLatitude());
                editor.putString("ParkLongitude", item.getLongitude());
                editor.putString("ParkName",item.getNationalParks());
                editor.putString("ParkArea",item.getArea());
                editor.putString("ParkDistance",item.getDistance()+"");
                editor.putInt("NPID",item.getNPID());
                editor.commit();
                context.startActivity(new Intent(context, ParkPage.class));
            }
        });

        return convertView;
    }
}
