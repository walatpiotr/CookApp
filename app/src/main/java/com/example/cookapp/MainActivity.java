package com.example.cookapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.cookapp.database.DatabaseHelper;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cookapp.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    //private ArrayList<String> id, name, type, cookware, device, power, minutes, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager.setOffscreenPageLimit(3);         /* limit is a fixed integer*/
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);
        //myDB.addFormula();
        //myDB.addRecord("nowa nazwa", "nowy cookware", "nowy device", "nowy power", "12:34", 2.5);
        //Cursor cursor = myDB.readAllData();
        //String tablica=DatabaseUtils.dumpCursorToString(cursor);
        //System.out.print(tablica);
//        id = new ArrayList<>();
//        name = new ArrayList<>();
//        type = new ArrayList<>();
//        cookware = new ArrayList<>();
//        device = new ArrayList<>();
//        power = new ArrayList<>();
//        minutes = new ArrayList<>();
//        rating = new ArrayList<>();
//        storeDataInArrays();


    }




        /*
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    void storeDataInArrays(){
        DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);
        Cursor cursor = myDB.readAllData();

            while (cursor.moveToNext()){
//                id.add(cursor.getString(0));
//                name.add(cursor.getString(1));
//                type.add(cursor.getString(2));
//                cookware.add(cursor.getString(3));
//                device.add(cursor.getString(4));
//                power.add(cursor.getString(5));
//                minutes.add(cursor.getString(6));
//                rating.add(cursor.getString(7));
            }
    }
}