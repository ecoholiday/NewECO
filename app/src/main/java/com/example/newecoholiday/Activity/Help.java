package com.example.newecoholiday.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.newecoholiday.R;

import java.util.Arrays;
import java.util.List;

public class Help extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    String parkName;
    TextView txtParkName,call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        sharedpreferences = getSharedPreferences(Home.MyPREFERENCES, Context.MODE_PRIVATE);
        parkName = sharedpreferences.getString("ParkName","");

        call = (TextView) findViewById(R.id.call);
        txtParkName = (TextView)findViewById(R.id.parkName);
        txtParkName.setText(parkName);


        List<String> nationalPark = Arrays.asList("Alpine National Park",
                "Barmah National Park",
                "Baw Baw National Park",
                "Burrowa - Pine Mountain National Park",
                "Croajingolong National Park",
                "Dandenong Ranges National Park",
                "Errinundra National Park",
                "Grampians National Park",
                "Hattah - Kulkyne National Park",
                "Kara Kara National Park",
                "Kinglake National Park",
                "Lake Eildon National Park",
                "Little Desert National Park",
                "Lower Glenelg National Park",
                "Mitchell River National Park",
                "Mornington Peninsula National Park",
                "Mount Eccles National Park",
                "Murray - Sunset National Park",
                "Organ Pipes National Park",
                "Port Campbell National Park",
                "Tarra-Bulga National Park",
                "Wilsons Promontory National Park",
                "Wyperfeld National Park",
                "Yarra Ranges National Park");

        List<String> contact = Arrays.asList("13 1963",
                "(03) 8627 4700",
                "13 1963",
                "13 1963",
                "13 19 63",
                "13 19 63",
                "13 19 63/ (03) 5161 1222/ (02) 6459 0500 / (03) 5154 2424",
                "(03) 5361 4000/ 1800 657 158/ 03 5355 0281 / 1800 065 599/ 03 5361 4444/ 1800 807 056/ 03 5572 3746/ 1800 633 218/  03 5382 1832 / 1800 330 080/ 03 5355 0281/ 03 5577 2558",
                "13 19 63",
                "13 19 63",
                "13 19 63",
                "(03) 5774 2585",
                "13 19 63",
                "13 19 63",
                "13 19 63",
                "13 19 63",
                "13 1963",
                "13 1963/ (03) 5480 7110",
                "13 19 63/(0) 3-9016-7590",
                "13 19 63/1800 505 466",
                "13 19 63/(03) 8427 2001 ",
                "(03) 8427 2694",
                "13 19 63/ (03) 8427 2001",
                "13 19 63/(03) 8427 2001 ");

        int pos=-1;
        for (int i = 0; i < nationalPark.size(); i++) {
            if (nationalPark.get(i).equals(parkName)) {
                call.setText(contact.get(i));
                break;
            }
        }

    }
}
