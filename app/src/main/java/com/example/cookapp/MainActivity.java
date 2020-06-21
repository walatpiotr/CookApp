package com.example.cookapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.cookapp.database.DatabaseChecker;
import com.example.cookapp.database.DatabaseHelper;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cookapp.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setOffscreenPageLimit(3);         /* limit is a fixed integer*/
        viewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        new DatabaseChecker();

    }
}