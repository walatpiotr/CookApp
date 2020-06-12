package com.example.cookapp;

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
        View view = inflater.inflate(R.layout.fragment_clock_alarm, container, false);

        final AutoCompleteTextView time_text_getter = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView4);
        Button submit_time_button = (Button) view.findViewById(R.id.submit_time_button);

        clock_text = (TextView) view.findViewById(R.id.clock);

        reset_time_button = (ImageButton) view.findViewById(R.id.stop_button);

        submit_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("dupaaaa");
                if(time_text_getter.getText().toString().matches("\\d{2}:\\d{2}")) {
                    String not_formatted_time = time_text_getter.getText().toString();

                    final long mili = (Long.parseLong(not_formatted_time.substring(0, 2)) * 60 + Long.parseLong(not_formatted_time.substring(3, 5)))*1000;
                    timer = new CountDownTimer(mili, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            String current_time =  Long.toString(millisUntilFinished / 60000) + ":" + Long.toString((millisUntilFinished-((millisUntilFinished / 60000)*60000)) / 1000);

                            String dupa = Long.toString(mili);
                            clock_text.setText(current_time);
                        }

                        @Override
                        public void onFinish() {

                        }
                    }.start();
                }
            }
        });
        reset_time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                clock_text.setText("00:00");
            }
        });


        return view;
    }
}
