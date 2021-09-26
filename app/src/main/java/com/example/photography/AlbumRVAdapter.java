package com.example.photography;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumRVAdapter extends RecyclerView.Adapter<AlbumRVAdapter.ViewHolder> {
    private ArrayList<AlbumRVModal> albumRVModalArrayList;
    private Context context;
    int lastPos = -1;
    private AlbumClickInterface albumClickInterface;

    public AlbumRVAdapter(ArrayList<AlbumRVModal> albumRVModalArrayList, Context context, AlbumClickInterface albumClickInterface) {
        this.albumRVModalArrayList = albumRVModalArrayList;
        this.context = context;
        this.albumClickInterface = albumClickInterface;
    }

    @NonNull
    @Override
    public AlbumRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumRVAdapter.ViewHolder holder, int position) {
        AlbumRVModal albumRVModal = albumRVModalArrayList.get(position);
        holder.albumNameTV.setText(albumRVModal.getAlbumName());
        holder.albumAddressTV.setText(albumRVModal.getAlbumAddress());
        Picasso.get().load(albumRVModal.getAlbumImage()).into(holder.albumIV);
        setAnimation(holder.itemView,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                albumClickInterface.onAlbumClick(position);
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
        return albumRVModalArrayList.size();
    }

    public interface AlbumClickInterface{
        void onAlbumClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView albumNameTV,albumAddressTV;
        private ImageView albumIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumNameTV = itemView.findViewById(R.id.idTVAlbumName);
            albumAddressTV = itemView.findViewById(R.id.idTVAddress);
            albumIV = itemView.findViewById(R.id.idIVAlbum);
        }
    }

}
