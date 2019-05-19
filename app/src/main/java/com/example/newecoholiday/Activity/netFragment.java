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
 * {@link netFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link netFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class netFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public netFragment() {
        // Required empty public constructor
    }

    public static netFragment newInstance(String param1, String param2) {
        netFragment fragment = new netFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_net, container, false);

        Bundle fromReport = getArguments();
        Long fuel = fromReport.getLong("Fuel");
        Long money = fromReport.getLong("Money");
        Long carbon = fromReport.getLong("Carbon");


        TextView txtFuelSpent = (TextView)v.findViewById(R.id.txtFuelSpent);
        TextView txtMoneySpent = (TextView)v.findViewById(R.id.txtMoneySpent);
        TextView txtCarbonSpent = (TextView)v.findViewById(R.id.txtCarbonSpent);

        txtFuelSpent.setText(String.valueOf(fuel)+"L");
        txtMoneySpent.setText(String.valueOf(money));
        txtCarbonSpent.setText(String.valueOf(carbon)+"g");
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
