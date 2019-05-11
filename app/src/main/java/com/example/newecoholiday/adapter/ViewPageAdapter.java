package com.example.newecoholiday.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newecoholiday.Activity.Home;
import com.example.newecoholiday.R;


public class ViewPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private  int []images={R.drawable.alpine,R.drawable.grampians,R.drawable.wilsons,R.drawable.murray};
    private String []parkNames = {"Alpine National Park","Grampians National Park","Wilsons Promontory National Park","Murray - Sunset National Park"};

    public ViewPageAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position){

        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.viewpager_imageview,null);
        TextView txtImageSlider = (TextView)view.findViewById(R.id.txtImageSlider) ;
        ImageView imageView=(ImageView)view.findViewById(R.id.pageImageViewer);
        imageView.setImageResource(images[position]);
        txtImageSlider.setText(parkNames[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position == 0) {
                    ((Home)context).checkForTheNP(parkNames[position]);

                }else if(position == 1) {
                    ((Home)context).checkForTheNP(parkNames[position]);
                }else if(position == 2) {
                    ((Home)context).checkForTheNP(parkNames[position]);
                }else {
                    ((Home)context).checkForTheNP(parkNames[position]);
                }
            }
        });

        ViewPager vp=(ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}