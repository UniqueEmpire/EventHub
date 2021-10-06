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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<modelcatering> caterarraylist;
    private ArrayList<String> mImage = new ArrayList<>();
    private Context mContext;
    int lastPos = -1;
    private CaterClickInterface caterClickInterface;

    public RecyclerViewAdapter(ArrayList<modelcatering> caterarraylist, Context mContext, CaterClickInterface caterClickInterface) {
        this.caterarraylist = caterarraylist;
        this.mContext = mContext;
        this.caterClickInterface = caterClickInterface;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        modelcatering caterModl = caterarraylist.get(position);
        holder.name.setText(caterModl.getComname());
        Picasso.get().load(caterModl.getLogourl()).into(holder.image);
        holder.viewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caterClickInterface.onCaterClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return caterarraylist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;
        private TextView viewmore;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.image_name);
            viewmore = itemView.findViewById(R.id.textView13);
            //parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
    public interface CaterClickInterface{
        void onCaterClick(int position);
    }
}
