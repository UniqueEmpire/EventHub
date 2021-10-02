package com.eventhub.eventhub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eventhub.eventhub.databinding.MusicBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicRVAdapter extends RecyclerView.Adapter<MusicRVAdapter.ViewHolder>{
    private ArrayList<MusicModel> musicarrayList;
    private Context mContext;
    int lastPos = -1;
    private MusicClickInterface musicClickInterface;
    private static final String TAG = "com.eventhub.eventhub.MusicRVAdapter";

    public MusicRVAdapter(ArrayList<MusicModel> musicarrayList, Context mContext, MusicClickInterface musicClickInterface) {
        this.musicarrayList = musicarrayList;
        this.mContext = mContext;
        this.musicClickInterface = musicClickInterface;
    }

    @NonNull
    @Override
    public MusicRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sin_music2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicRVAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MusicModel musicModl = musicarrayList.get(position);
        holder.name.setText(musicModl.getComname());
        Picasso.get().load(musicModl.getLogourl()).into(holder.image);
        setAnimation(holder.itemView,position);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicClickInterface.onMusicClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicarrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private  ImageView image;
        private TextView name;
        //RelativeLayout parentlay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.musicid);
            name= itemView.findViewById(R.id.musicname);
            //parentlay=itemView.findViewById(R.id.parent_layout);
        }
    }
    private void setAnimation(View itemView,int position){
        if(position>lastPos){
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }
    public interface MusicClickInterface{
        void onMusicClick(int position);
    }
}