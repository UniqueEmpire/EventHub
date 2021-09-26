package com.eventhub.eventhub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MusicRVAdapter extends RecyclerView.Adapter<MusicRVAdapter.ViewHolder>{
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private static final String TAG = "test.sliit.recyclerview.RecyclerViewAdapter";
    private Context mContext;
    public MusicRVAdapter(ArrayList<String> image,ArrayList<String>name,Context context){
        this.mImage=image;
        this.mImageNames=name;
        this.mContext=context;
    }
    @NonNull
    @Override

    public MusicRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sin_music,parent,false);
        MusicRVAdapter.ViewHolder viewHolder = new MusicRVAdapter.ViewHolder(view);
        return viewHolder;
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull MusicRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext).asBitmap().load(mImage.get(position)).into(holder.image);
        holder.name.setText(mImageNames.get(position));
        holder.parentlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on"+mImageNames.get(position));
                Toast.makeText(mContext,mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        RelativeLayout parentlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.musicid);
            name= itemView.findViewById(R.id.musicid);
            parentlay=itemView.findViewById(R.id.parent_layout);
        }
    }
}
