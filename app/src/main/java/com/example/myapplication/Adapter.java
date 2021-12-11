package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter  extends RecyclerView.Adapter<Adapter.za>{
    Context context;
    ArrayList<Modaldata> list;

    public Adapter(Context context, ArrayList<Modaldata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.za onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View b= LayoutInflater.from(context).inflate(R.layout.item_view,parent,false);
        za za=new za(b);
        return za;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.za holder, int position) {
        holder.Title.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() { return list.size();}

    public class za extends RecyclerView.ViewHolder {
        TextView Title;
        ImageView Image;

        public za(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.title);
           Image =itemView.findViewById(R.id.iamge);
        }
    }
}
