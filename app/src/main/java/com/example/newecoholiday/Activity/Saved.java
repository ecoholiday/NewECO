package com.example.newecoholiday.Activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newecoholiday.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Saved.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Saved#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Saved extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Saved() {

    }

    public static Saved newInstance(String param1, String param2) {
        Saved fragment = new Saved();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_saved, container, false);
        Bundle fromReport = getArguments();
        Long hotelMoney = fromReport.getLong("HotelMoney");
        Long hotelCarbon = fromReport.getLong("HotelCarbon");
        Long electricity = fromReport.getLong("Electricity");
        Long elecCarbon = fromReport.getLong("ElecCarbon");
        Long elecMoney = fromReport.getLong("ElecMoney");
        Long mobile = fromReport.getLong("Mobile");
        Long electronics = fromReport.getLong("Electronics");
        Long beer = fromReport.getLong("Beer");


        TextView txtElecSaved = (TextView)v.findViewById(R.id.txtElecSaved);
        TextView txtMoneySaved = (TextView)v.findViewById(R.id.txtMoneySaved);
        TextView txtCarbonSaved = (TextView)v.findViewById(R.id.txtCarbonSaved);
        TextView txtHotelMoney = (TextView)v.findViewById(R.id.txtHotelMoney);
        TextView txtHotelCarbon = (TextView)v.findViewById(R.id.txtHotelCarbon);
        TextView txtMobile = (TextView)v.findViewById(R.id.txtMobile);
        TextView txtOtherElectronics = (TextView)v.findViewById(R.id.txtOtherElectronics);
        TextView txtBeer = (TextView)v.findViewById(R.id.txtBeer);

        txtHotelMoney.setText(String.valueOf(hotelMoney));
        txtHotelCarbon.setText(String.valueOf(hotelCarbon) + "g");
        txtElecSaved.setText(String.valueOf(electricity)+"KWh");
        txtMoneySaved.setText(String.valueOf(elecMoney));
        txtCarbonSaved.setText(String.valueOf(elecCarbon)+"g");
        txtMobile.setText(String.valueOf(mobile)+"g");
        txtOtherElectronics.setText(String.valueOf(electronics)+"g");
        txtBeer.setText(String.valueOf(beer)+"g");
        return v;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
