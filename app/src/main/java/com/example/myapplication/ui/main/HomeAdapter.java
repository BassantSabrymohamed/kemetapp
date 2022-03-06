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
import com.example.myapplication.data.model.Modaldata;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    Context context;
    List<Modaldata> list;
    OnClick onClick ;

    public HomeAdapter(Context context, List<Modaldata> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View b= LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        ViewHolder ViewHolder=new ViewHolder(b);
        return ViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
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
            }
        });

    }

    @Override
    public int getItemCount() { return list.size();}

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView Image;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.text_item);
            Image =itemView.findViewById(R.id.image_item);
        }
        void setname(String Name) {
            name.setText(" " + Name);
        }

        void setImage(String url) {

            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.lod)
                   .into(Image);
        }
    }


    public interface OnClick
    {

        void onItemClick(String pos);
    }

}
