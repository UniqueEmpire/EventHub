package com.example.eventhubapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotelRVAdapter extends RecyclerView.Adapter<HotelRVAdapter.ViewHolder> {
    private ArrayList<HotelRVModal> hotelRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private HotelClickInterface hotelClickInterface;

    private BottomSheetListener mListener;


    public HotelRVAdapter(ArrayList<HotelRVModal> hotelRVModalArrayList, Context context, HotelClickInterface hotelClickInterface) {
        this.hotelRVModalArrayList = hotelRVModalArrayList;
        this.context = context;
        this.hotelClickInterface = hotelClickInterface;
    }

    @NonNull
    @Override
    public HotelRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_rv_item,parent,false);
        return new ViewHolder(view);
        //return null;
    }


    public interface BottomSheetListener{
        void openViewHotelActivity();

    }

    @Override
    public void onBindViewHolder(@NonNull HotelRVAdapter.ViewHolder holder, int position) {
        HotelRVModal hotelRVModal = hotelRVModalArrayList.get(position);
        holder.hotelNameTV.setText(hotelRVModal.getHotelName());
        //holder.hotelAddressTV.setText(hotelRVModal.getHotelAddress());
        Picasso.get().load(hotelRVModal.getHotelImage()).into(holder.hotelIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotelClickInterface.onHotelClick(position);
            }
        });
    }

    private void setAnimation(View itemView,int position){
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }
    @Override
    public int getItemCount() {
        return hotelRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hotelNameTV;
        private ImageView hotelIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelNameTV = itemView.findViewById(R.id.idTVHotelName);
            //hotelAddressTV = itemView.findViewById(R.id.idTVAddress);
            hotelIV = itemView.findViewById(R.id.idIVHotel);
        }
    }

    public interface HotelClickInterface{
        void onHotelClick(int position);
    }
}
