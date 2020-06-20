package com.example.cookapp.recyclerview;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cookapp.MainActivity;
import com.example.cookapp.R;
import com.example.cookapp.mainfragments.alarmfragments.ClockAlarmFragment;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private MyListData[] listdata;

    public MyListAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(listItem);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MyListData myListData = listdata[position];
        String ratingText ="rating: "+listdata[position].getRating();
        String nameText ="name: " + listdata[position].getName();
        String cookwareText ="cookware: " +listdata[position].getCookware();
        String deviceText ="device: "+ listdata[position].getDevice();
        String minutesText ="minutes: "+ listdata[position].getMinutes();
        String powerText ="power: "+ listdata[position].getPower();
        holder.name.setText(nameText);
        holder.cookware.setText(cookwareText);
        holder.device.setText(deviceText);
        holder.power.setText(powerText);
        holder.minutes.setText(minutesText);
        holder.rating.setText(ratingText);
        holder.startCooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String minutesValue = listdata[position].getMinutes();
                Context context = v.getContext();
                sendMessageToActivity(context, minutesValue);
                Toast.makeText(context, "Your desired time has been set in Dish Clock. Let's cook!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void sendMessageToActivity(Context context, String message) {
        Intent intent = new Intent(ClockAlarmFragment.ACTION_NEW_MSG1);
        intent.putExtra(ClockAlarmFragment.MSG_FIELD1, message);

        context.sendBroadcast(intent);

    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView cookware;
        public TextView device;
        public TextView power;
        public TextView minutes;
        public TextView rating;
        public Button startCooking;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.cookware = (TextView) itemView.findViewById(R.id.cookware);
            this.device = (TextView) itemView.findViewById(R.id.device);
            this.power = (TextView) itemView.findViewById(R.id.power);
            this.minutes = (TextView) itemView.findViewById(R.id.minutes);
            this.rating = (TextView) itemView.findViewById(R.id.rating);
            this.startCooking = itemView.findViewById(R.id.try_list_button);

        }
    }
}
