package com.example.cookapp.mainfragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cookapp.R;
import com.example.cookapp.database.DatabaseHelper;
import com.example.cookapp.mainfragments.listfragments.RecyclerViewFragment;
import com.example.cookapp.recyclerview.MyListAdapter;
import com.example.cookapp.recyclerview.MyListData;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<String> id, name, type, cookware, device, power_db, minutes_db, rating;

    public static ViewPager viewPager;
    public static int int_items = 1;


    public EditText what_search;
    public CheckBox name_check;
    public CheckBox cookware_check;
    public CheckBox device_check;
    public Button search_button;
    public RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //public android.database.Cursor cursor;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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

        id = new ArrayList<>();
        name = new ArrayList<>();
        type = new ArrayList<>();
        cookware = new ArrayList<>();
        device = new ArrayList<>();
        power_db = new ArrayList<>();
        minutes_db = new ArrayList<>();
        rating = new ArrayList<>();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */
        what_search = view.findViewById(R.id.search_text);
        name_check = view.findViewById(R.id.checkBoxName);
        cookware_check = view.findViewById(R.id.checkBoxCookware);
        device_check = view.findViewById(R.id.checkBoxDevice);
        search_button = view.findViewById(R.id.search_button);

        name_check.setChecked(true);


        storeDataInArrays();

        MyListData[] argumentToMyListData = dataToMyListData();
        MyListData[] myListData = argumentToMyListData;

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });

        return view;
    }

    private void searchData(){
        recyclerView.setAdapter(new MyListAdapter(searchDataBase(what_search.getText().toString(),name_check.isChecked(),cookware_check.isChecked(),device_check.isChecked())));

    }
    //ArrayList<MyListData> nowa = new ArrayList<MyListData()>;

    public MyListData[] dataToMyListData(){
        MyListData[] result = new MyListData[id.size()];

        for (int i=0; i<id.size(); i++){
            result[i] = new MyListData(name.get(i),cookware.get(i),device.get(i), power_db.get(i), minutes_db.get(i), rating.get(i));

        }
        return result;

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

    MyListData[] searchDataBase(String searching_text, boolean name_check, boolean cookware_check, boolean device_check){

        DatabaseHelper db = new DatabaseHelper(getContext());
        Cursor cursor = db.searchData(searching_text,name_check,cookware_check,device_check);
        id.clear();
        name.clear();
        type.clear();
        cookware.clear();
        device.clear();
        power_db.clear();
        minutes_db.clear();
        rating.clear();


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
        MyListData[] result = new MyListData[id.size()];
        for (int i=0; i<id.size(); i++){
            result[i] = new MyListData(name.get(i),cookware.get(i),device.get(i), power_db.get(i), minutes_db.get(i), rating.get(i));

        }
        return result;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.getString("searched_text", what_search.getText().toString());
        outState.getBoolean("name_check", name_check.isChecked());
        outState.getBoolean("cookware_check", cookware_check.isChecked());
        outState.getBoolean("device_check", device_check.isChecked());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null){
            what_search.setText(savedInstanceState.getString("searched_text"));
            boolean name = savedInstanceState.getBoolean("name_check");
            name_check.setChecked(name);
            boolean cookware = savedInstanceState.getBoolean("cookware_check");
            cookware_check.setChecked(cookware);
            boolean device = savedInstanceState.getBoolean("device_check");
            device_check.setChecked(device);


            //recyclerView.setAdapter(new MyListAdapter(searchDataBase(what_search.getText().toString(),name_check.isChecked(),cookware_check.isChecked(),device_check.isChecked())));
        }

    }

    class RecyclerViewPagerAdapter extends FragmentPagerAdapter {

        //private final int[] TAB_TITLES = new int[]{R.string.simple_clock_title, R.string.dish_clock_title};

        public RecyclerViewPagerAdapter( FragmentManager fm) {
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
                    fragment = new RecyclerViewFragment();
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



    }
}
