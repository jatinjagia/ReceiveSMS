package com.example.jatin.receivesms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jatin on 29/9/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<Details> details;


    public RecyclerViewAdapter(List<Details> details) {
        this.details = details;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            Details mDetails= details.get(position);
            holder.carrierName.setText(mDetails.getCarrierName());
            holder.phoneNumber.setText(mDetails.getPhoneNumber());
            holder.messsage.setText(mDetails.getMessage());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView carrierName,messsage,phoneNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            carrierName= itemView.findViewById(R.id.sim_carrier);
            messsage=itemView.findViewById(R.id.message);
            phoneNumber=itemView.findViewById(R.id.phone_number);
        }


    }


}
