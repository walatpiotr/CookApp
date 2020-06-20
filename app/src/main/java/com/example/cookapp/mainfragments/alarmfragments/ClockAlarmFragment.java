package com.example.cookapp.mainfragments.alarmfragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookapp.R;
import com.example.cookapp.mainfragments.FormFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClockAlarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClockAlarmFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public TextView clock_text;
    public CountDownTimer timer;
    public ImageButton reset_time_button;
    private boolean running = false;
    AutoCompleteTextView time_text_getter;
    View view;
    private long mili;

    public static final String ACTION_NEW_MSG1 = "com.example.cookapp.mainfragments.alarmfragments.NEW_MSG";
    public static final String MSG_FIELD1 = "message";
    private ClockAlarmFragment.MyReceiver1 myReceiver;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClockAlarmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClockAlarmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClockAlarmFragment newInstance(String param1, String param2) {
        ClockAlarmFragment fragment = new ClockAlarmFragment();
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
        initReceiver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_clock_alarm, container, false);
        time_text_getter = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView4);
        Button submit_time_button = (Button) view.findViewById(R.id.submit_time_button);
        clock_text = (TextView) view.findViewById(R.id.clock);
        reset_time_button = (ImageButton) view.findViewById(R.id.stop_button);

        if(clock_text.getText().toString().equals("00:00")){
            showAlertDialog(view);
        }
        submit_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time_text_getter.getText().toString().matches("\\d{2}:\\d{2}") && !running) {
                    String pattern = time_text_getter.getText().toString();
                    if(Integer.parseInt(pattern.substring(3))>59){
                        time_text_getter.setText("");
                        time_text_getter.setHint("Type real time");
                        time_text_getter.setHintTextColor(getResources().getColor(R.color.error_red));
                    }
                    else {
                        String not_formatted_time = time_text_getter.getText().toString();

                        mili = (Long.parseLong(not_formatted_time.substring(0, 2)) * 60 + Long.parseLong(not_formatted_time.substring(3, 5))) * 1000;
                        startTimer(mili);
                        running=true;
                    }
                }
            }
        });




        reset_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                clock_text.setText("--:--");
                time_text_getter.setText("");
                running = false;
            }
        });


        return view;
    }

    private void startTimer(Long value){
        timer = new CountDownTimer(value, 1000) {
            @Override
            public void onTick(long value) {
                updateCountDownTimer(value);
            }

            @Override
            public void onFinish() {
                running = false;

            }

        }.start();
    }


    private void updateCountDownTimer(Long value){
        mili = value;
        String beforetime;
        String aftertime;
        if(value/60000 < 10){
            beforetime =  "0"+Long.toString(value / 60000);
            if( ((value-((value / 60000)*60000)) / 1000)<10){
                aftertime = "0"+ Long.toString((value-((value / 60000)*60000)) / 1000);
            }
            else{
                aftertime = Long.toString((value-((value / 60000)*60000)) / 1000);
            }
        }
        else{
            beforetime =  Long.toString(value / 60000);
            if( ((value-((value / 60000)*60000)) / 1000)<10){
                aftertime = "0"+ Long.toString((value-((value / 60000)*60000)) / 1000);
            }
            else{
                aftertime = Long.toString((value-((value / 60000)*60000)) / 1000);
            }
        }
        String current_time =  beforetime + ":" + aftertime;
        clock_text.setText(current_time);

    }

    public void showAlertDialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Your dish is finished");
            alert.setMessage("Your dish is finished");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  Toast.makeText(getContext(), "Bon appetit!", Toast.LENGTH_SHORT).show();
                }
            });
            alert.create().show();
    }

    private void initReceiver() {
        myReceiver = new MyReceiver1();
        IntentFilter filter = new IntentFilter(ACTION_NEW_MSG1);
        getActivity().registerReceiver(myReceiver, filter);
    }

    private void finishReceiver() {
        getActivity().unregisterReceiver(myReceiver);
    }

    public class MyReceiver1 extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(ClockAlarmFragment.ACTION_NEW_MSG1)) {
                String message = intent.getStringExtra(MSG_FIELD1);

                if (message != null) {
                    time_text_getter.setText(message);
                    clock_text.setText(message);
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("clock_value", clock_text.getText().toString());
        outState.putBoolean("isRunning", running);
        outState.putLong("milis",mili);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState!=null) {
            if(!savedInstanceState.getString("clock_value").equals("--:--")) {
                running = savedInstanceState.getBoolean("isRunning");
                mili = savedInstanceState.getLong("milis");

                updateCountDownTimer(mili);
                if (running) {
                    startTimer(mili);
                }
            }
            else {
                clock_text.setText("--:--");
                running = false;
            }
        }
    }
}
