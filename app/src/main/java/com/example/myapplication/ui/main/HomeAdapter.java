package com.example.myapplication.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CivilizationActivity;
import com.example.myapplication.data.model.Modaldata;
import com.example.myapplication.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    Context context;
    ArrayList<Modaldata> list;
    OnClick onClick ;

    public HomeAdapter(Context context, ArrayList<Modaldata> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick=onClick ;
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
        holder.Title.setText(list.get(position).getTitle());
        holder.Image.setImageResource(list.get(position).getIamge());

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Title;
        ImageView Image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.text_item);
           Image =itemView.findViewById(R.id.image);

        }
    }


    interface OnClick
    {

        void onItemClick(String pos);
    }

}
