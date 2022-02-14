package com.example.myapplication.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.model.ModelCivilization;
import com.example.myapplication.data.model.ModelHotile;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HotileAdapter extends RecyclerView.Adapter<HotileAdapter.ViewHolder> {
    private Context context;
    private List<ModelHotile>list;
    private OnClick onClick ;


    public HotileAdapter(Context context, List<ModelHotile> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick = onClick;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View b= LayoutInflater.from(context).inflate(R.layout.item_view3,parent,false);
        ViewHolder ViewHolder=new ViewHolder(b);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setname(list.get(position).getName());
        holder.setImage(list.get(position).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0)
                {
                    onClick.onItemClick("0");
                }
                else if(position ==1)
                {
                    onClick.onItemClick("1");
                }
                else if(position ==2)
                {
                    onClick.onItemClick("2");
                }
                else if(position ==3)
                {
                    onClick.onItemClick("3");
                }
                else if(position ==4)
                {
                    onClick.onItemClick("4");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView Image;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.text_item);
            Image =itemView.findViewById(R.id.image);
        }
        void setname(String Name) {
            name.setText(" " + Name);
        }

        void setImage(String url) {

            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.d)
                    .into(Image);
        }
    }
    interface OnClick
    {

        void onItemClick(String pos);
    }

}
