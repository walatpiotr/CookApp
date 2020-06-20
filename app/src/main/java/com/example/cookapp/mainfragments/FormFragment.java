package com.example.cookapp.mainfragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.cookapp.R;
import com.example.cookapp.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private ArrayList<String> id, name, type, cookware, device, power_db, minutes_db, rating;
    private AutoCompleteTextView autoName ;
    private AutoCompleteTextView autoCookware ;
    private AutoCompleteTextView autoDevice;
    private EditText power;
    private EditText minutes;
    private RatingBar ratingBar;

    public static final String ACTION_NEW_MSG = "com.example.cookapp.mainfragments.NEW_MSG";
    public static final String MSG_FIELD = "message";
    private MyReceiver myReceiver;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    private static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
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
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }

        initReceiver();

        id = new ArrayList<>();
        name = new ArrayList<>();
        type = new ArrayList<>();
        cookware = new ArrayList<>();
        device = new ArrayList<>();
        power_db = new ArrayList<>();
        minutes_db = new ArrayList<>();
        rating = new ArrayList<>();
        storeDataInArrays();





    }

    private void storeDataInArrays() {
        DatabaseHelper myDB = new DatabaseHelper(getContext());
        Cursor cursor = myDB.readAllData();

        while (cursor.moveToNext()){
            id.add(cursor.getString(0));
            name.add(cursor.getString(1));
            type.add(cursor.getString(2));
            cookware.add(cursor.getString(3));
            device.add(cursor.getString(4));
            power_db.add(cursor.getString(5));
            minutes_db.add(cursor.getString(6));
            rating.add(cursor.getString(7));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);
        autoName = view.findViewById(R.id.autoCompleteTextView);
        autoCookware = view.findViewById(R.id.autoCompleteTextView2);
        autoDevice = view.findViewById(R.id.autoCompleteTextView3);
        power = view.findViewById(R.id.editText_power);
        minutes = view.findViewById(R.id.edit_minutes);
        ratingBar = view.findViewById(R.id.ratingBar);
        Button clear = view.findViewById(R.id.clear_button);
        Button submit = view.findViewById(R.id.submit_button);

        if(savedInstanceState==null){
            autoName.setText("");
            autoCookware.setText("");
            autoDevice.setText("");
            power.setText("");
            minutes.setText("");
            ratingBar.setRating((float)2.5);
        }
        if(savedInstanceState!=null){
            autoName.setText(savedInstanceState.getString("name_key"));
            autoCookware.setText(savedInstanceState.getString("cookware_key"));
            autoDevice.setText(savedInstanceState.getString("device_key"));
            power.setText(savedInstanceState.getString("power_key"));
            minutes.setText(savedInstanceState.getString("minutes_key"));
            ratingBar.setRating(savedInstanceState.getFloat("rating_key"));
        }

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                autoName.setText("");
                autoName.setHint("");
                autoCookware.setText("");
                autoCookware.setHint("");
                autoDevice.setText("");
                autoDevice.setHint("");
                power.setText("");
                power.setHint(getResources().getString(R.string.hint_text));
                power.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                minutes.setText("");
                minutes.setHint(getResources().getString(R.string.hint_clock));
                minutes.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                ratingBar.setRating((float) 2.5);
            }// end onClick
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String name_string;
                String cookware_string;
                String device_string;
                String power_string;
                String minutes_string;
                float rating_value;
                if(
                        !autoName.getText().toString().equals("") &&
                        !autoCookware.getText().toString().equals("") &&
                        !autoDevice.getText().toString().equals("") &&
                        !power.getText().toString().equals("") &&
                        !minutes.getText().toString().equals(""))
                {
                    if(!minutes.getText().toString().matches("\\d{2}:\\d{2}")) {
                        minutes.setText("");
                        minutes.setHint(getResources().getString(R.string.hint_clock));
                        minutes.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                    else {
                        name_string = autoName.getText().toString();
                        cookware_string = autoCookware.getText().toString();
                        device_string = autoDevice.getText().toString();
                        power_string = power.getText().toString();
                        minutes_string = minutes.getText().toString();
                        rating_value = ratingBar.getRating();

                        DatabaseHelper db = new DatabaseHelper(getContext());
                        db.addRecord(name_string, cookware_string, device_string, power_string, minutes_string, rating_value);

                        autoName.setText("");
                        autoName.setHint("");
                        autoCookware.setText("");
                        autoCookware.setHint("");
                        autoDevice.setText("");
                        autoDevice.setHint("");
                        power.setText("");
                        power.setHint(getResources().getString(R.string.hint_text));
                        power.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                        minutes.setText("");
                        minutes.setHint(getResources().getString(R.string.hint_clock));
                        minutes.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                        ratingBar.setRating((float) 2.5);
                    }
                }
                else
                {
                    if(autoName.getText().toString().equals("")){
                        autoName.setHint(getResources().getString(R.string.error_hint_name));
                        autoName.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                    if(autoCookware.getText().toString().equals("")){
                        autoCookware.setHint(getResources().getString(R.string.error_hint_cookware));
                        autoCookware.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                    if(autoDevice.getText().toString().equals("")){
                        autoDevice.setHint(getResources().getString(R.string.error_hint_device));
                        autoDevice.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                    if(power.getText().toString().equals("")){
                        power.setHint(getResources().getString(R.string.error_hint_power));
                        power.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                    if(minutes.getText().toString().equals("")){
                        minutes.setHint(getResources().getString(R.string.error_hint_minutes));
                        minutes.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                    if(!minutes.getText().toString().matches("\\d{2}:\\d{2}")) {
                        minutes.setText("");
                        minutes.setHint(getResources().getString(R.string.hint_clock));
                        minutes.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                }
            }
        });

        return view;
    }
    public void initReceiver() {
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter(ACTION_NEW_MSG);
        getActivity().registerReceiver(myReceiver, filter);
    }

    private void finishReceiver() {
        getActivity().unregisterReceiver(myReceiver);
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(FormFragment.ACTION_NEW_MSG)) {
                String message = intent.getStringExtra(MSG_FIELD);
                if(message!=null){
                    long value = Long.parseLong(message);
                    String beforeTime;
                    String afterTime;
                    if(value/60000 < 10){
                        beforeTime =  "0"+Long.toString(value / 60000);
                        if( ((value-((value / 60000)*60000)) / 1000)<10){
                            afterTime = "0"+ Long.toString((value-((value / 60000)*60000)) / 1000);
                        }
                        else{
                            afterTime = Long.toString((value-((value / 60000)*60000)) / 1000);
                        }
                    }
                    else{
                        beforeTime =  Long.toString(value / 60000);
                        if( ((value-((value / 60000)*60000)) / 1000)<10){
                            afterTime = "0"+ Long.toString((value-((value / 60000)*60000)) / 1000);
                        }
                        else{
                            afterTime = Long.toString((value-((value / 60000)*60000)) / 1000);
                        }
                    }
                    String current_time =  beforeTime + ":" + afterTime;
                    minutes.setText(current_time);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name_key", autoName.getText().toString());
        outState.putString("cookware_key", autoCookware.getText().toString());
        outState.putString("device_key", autoDevice.getText().toString());
        outState.putString("power_key", power.getText().toString());
        outState.putString("minutes_key", minutes.getText().toString());
        outState.putFloat("rating_key", ratingBar.getRating());
    }
}
