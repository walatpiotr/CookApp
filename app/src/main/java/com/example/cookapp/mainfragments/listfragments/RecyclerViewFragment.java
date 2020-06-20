package com.example.cookapp.mainfragments.listfragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cookapp.database.DatabaseHelper;
import com.example.cookapp.recyclerview.MyListAdapter;
import com.example.cookapp.recyclerview.MyListData;
import com.example.cookapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<String> id, name, type, cookware, device, power_db, minutes_db, rating;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewFragment newInstance(String param1, String param2) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
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
        View view = inflater.inflate(R.layout.recyclerview, container, false);


        storeDataInArrays();

        MyListData[] argumentToMyListData = dataToMyListData();
        MyListData[] myListData = argumentToMyListData;

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

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
}
