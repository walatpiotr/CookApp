package com.example.cookapp.mainfragments.alarmfragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;

import com.example.cookapp.R;
import com.example.cookapp.mainfragments.FormFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClockSimpleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClockSimpleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Chronometer chronometer;
    private boolean running=false;
    private long pauseOffset;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClockSimpleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClockSimple.
     */
    // TODO: Rename and change types and number of parameters
    public static ClockSimpleFragment newInstance(String param1, String param2) {
        ClockSimpleFragment fragment = new ClockSimpleFragment();
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
        View view = inflater.inflate(R.layout.fragment_clock_simple, container, false);
        ImageButton start = (ImageButton) view.findViewById(R.id.start_button);
        final ImageButton pause = (ImageButton) view.findViewById(R.id.pause_button);
        ImageButton reset = (ImageButton) view.findViewById(R.id.stop_button);
        chronometer = view.findViewById(R.id.chronometer);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running) {
                    startTimer();
                }
            }// end onClick
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(running) {
                    pauseTimer();
                }
            }// end onClick
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        if(savedInstanceState==null){

            running= false;
        }
        if(savedInstanceState!=null) {
            running = savedInstanceState.getBoolean("isRunning");
            pauseOffset = savedInstanceState.getLong("offset");
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            if(running){
                startTimer();
            }
            else{
                pauseTimer();
            }
        }

        return view;
    }

    private void sendMessageToActivity(Long cos) {
        Intent intent = new Intent(FormFragment.ACTION_NEW_MSG);
        intent.putExtra(FormFragment.MSG_FIELD, cos.toString());
        getActivity().sendBroadcast(intent);
    }

    private void startTimer(){

            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
    }

    private void pauseTimer(){

            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            sendMessageToActivity(pauseOffset);
            running = false;
    }

    private void resetTimer(){
        chronometer.stop();
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        running = false;
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


        outState.putString("clock_value", chronometer.getText().toString());
        outState.putBoolean("isRunning", running);
        outState.putLong("offset", pauseOffset);

    }
}
