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
    OnClick onClick ;

    public Adapter(Context context, ArrayList<Modaldata> list,OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick=onClick ;
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
            }
        });

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


    interface OnClick
    {

        void onItemClick(String pos);
    }
}
