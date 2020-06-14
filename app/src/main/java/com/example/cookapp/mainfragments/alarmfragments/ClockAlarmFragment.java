package com.example.cookapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

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
    boolean running;
    AutoCompleteTextView time_text_getter;
    View view;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clock_alarm, container, false);

        time_text_getter = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView4);
        Button submit_time_button = (Button) view.findViewById(R.id.submit_time_button);

        clock_text = (TextView) view.findViewById(R.id.clock);

        reset_time_button = (ImageButton) view.findViewById(R.id.stop_button);

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
                        time_text_getter.setHint("");
                        final long mili = (Long.parseLong(not_formatted_time.substring(0, 2)) * 60 + Long.parseLong(not_formatted_time.substring(3, 5))) * 1000;
                        timer = new CountDownTimer(mili, 1000) {
                            @Override
                            public void onTick(long value) {
                                //String current_time = Long.toString(millisUntilFinished / 60000) + ":" + Long.toString((millisUntilFinished - ((millisUntilFinished / 60000) * 60000)) / 1000);
                                //String dupa = Long.toString(mili);

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
                                running = true;
                                time_text_getter.setText("");
                            }

                            @Override
                            public void onFinish() {
                                showAlertDialog(view);
                                running = false;
                                clock_text.setText("00:00");
                                time_text_getter.setText("");
                                time_text_getter.setHint("");
                            }

                        }.start();
                    }

                }
            }
        });
        reset_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                clock_text.setText("00:00");
                time_text_getter.setText("");
                running = false;
            }
        });


        return view;
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
}
