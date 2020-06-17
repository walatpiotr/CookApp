package com.example.cookapp.mainfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.cookapp.ClockSimpleFragment;
import com.example.cookapp.R;
import com.example.cookapp.mainfragments.alarmfragments.ClockAlarmFragment;
import com.google.android.material.tabs.TabLayout;

public class AlarmLandscapeFragment extends androidx.fragment.app.Fragment {

    public static int int_items = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View v = inflater.inflate(R.layout.fragment_alarm_landscape, container, false);



        /**
         *Set an Apater for the View Pager
         */

        FrameLayout frag1 = v.findViewById(R.id.fragment1);
        FrameLayout frag2 = v.findViewById(R.id.fragment2);

        addFragment1();
        addFragment2();



        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */



        return v;

    }
    public void addFragment2(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ClockAlarmFragment clockAlarmFragment = new ClockAlarmFragment();
        fragmentTransaction.add(R.id.fragment2, clockAlarmFragment);

    }
    public void addFragment1(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ClockSimpleFragment clockAlarmFragment = new ClockSimpleFragment();
        fragmentTransaction.add(R.id.fragment1, clockAlarmFragment);

    }

    static class AlarmsPagerAdapterLandscape extends FragmentPagerAdapter {

        //private final int[] TAB_TITLES = new int[]{R.string.simple_clock_title, R.string.dish_clock_title};

        public AlarmsPagerAdapterLandscape( FragmentManager fm) {
            super(fm);

        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
                case 0:
                    fragment = new ClockSimpleFragment();
                    break;
                case 1:
                    fragment = new ClockAlarmFragment();
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {

            return int_items;

        }


    }
}
