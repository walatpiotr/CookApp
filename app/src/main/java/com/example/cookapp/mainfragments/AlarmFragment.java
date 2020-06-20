package com.example.cookapp.mainfragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookapp.ClockSimpleFragment;
import com.example.cookapp.mainfragments.alarmfragments.ClockAlarmFragment;
import com.example.cookapp.R;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static ViewPager viewpager2;
    public static int int_items = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);


        /**
         *Set an Apater for the View Pager
         */

        viewPager.setAdapter(new AlarmsPagerAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return v;

    }

    class AlarmsPagerAdapter extends FragmentPagerAdapter {

        private final int[] TAB_TITLES = new int[]{R.string.simple_clock_title, R.string.dish_clock_title};

        public AlarmsPagerAdapter( FragmentManager fm) {
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

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {


            CharSequence title = null;
            if (position == 0) {
                title = getString(TAB_TITLES[0]);
            } else if (position == 1) {
                title = getString(TAB_TITLES[1]);

            }
            return title;
        }
    }

}
