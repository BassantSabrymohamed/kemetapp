package com.example.myapplication.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.model.ModelDriver;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> {
    private Context context;
    private List<ModelDriver>list;


    public DriverAdapter(Context context, List<ModelDriver> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view4, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        holder.setname(list.get(position).getName());
        holder.setcarmodel(list.get(position).getCarModel());
        holder.setImage(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView carmodel;
        TextView name;
        ImageView image;
        Button orderdriver;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            carmodel = itemView.findViewById(R.id.CarModel);
            name = itemView.findViewById(R.id.Name);

            image = itemView.findViewById(R.id.Image);
            orderdriver = itemView.findViewById(R.id.order);
        }

        void setname(String Name) {
            name.setText("Name: " + Name);
        }

        void setcarmodel(String CarModel) {
            carmodel.setText("CarModel: " + CarModel);
        }

        void setImage(String url) {

            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.lod)
                    .into(image);
        }

    }}


