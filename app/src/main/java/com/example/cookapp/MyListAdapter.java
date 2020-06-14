package com.example.cookapp;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private MyListData[] listdata;

    // RecyclerView recyclerView;
    public MyListAdapter(MyListData[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyListData myListData = listdata[position];
        holder.name.setText(listdata[position].getName());
        holder.cookware.setText(listdata[position].getCookware());
        holder.device.setText(listdata[position].getDevice());
        holder.power.setText(listdata[position].getPower());
        holder.minutes.setText(listdata[position].getMinutes());
        holder.rating.setText(listdata[position].getRating());
        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });*/
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


        //public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.cookware = (TextView) itemView.findViewById(R.id.cookware);
            this.device = (TextView) itemView.findViewById(R.id.device);
            this.power = (TextView) itemView.findViewById(R.id.power);
            this.minutes = (TextView) itemView.findViewById(R.id.minutes);
            this.rating = (TextView) itemView.findViewById(R.id.rating);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
