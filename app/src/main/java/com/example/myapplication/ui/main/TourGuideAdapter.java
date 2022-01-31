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
import com.example.myapplication.data.model.ModelTourGuide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TourGuideAdapter extends RecyclerView.Adapter<TourGuideAdapter.ViewHolder> {
    private Context context;
    private List<ModelTourGuide> list;

    public TourGuideAdapter(Context context, List<ModelTourGuide> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view5, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.setText1(list.get(position).getName());
        holder.setText2(list.get(position).getLanguage());
        holder.setImage(list.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Text1;
        TextView Text2;
        ImageView Fiber;
        ImageView image;
        Button Order;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            Text1 = itemView.findViewById(R.id.text_1);
            Text2 = itemView.findViewById(R.id.text_2);
            Fiber = itemView.findViewById(R.id.fiber);
            image = itemView.findViewById(R.id.image);
            Order = itemView.findViewById(R.id.order);
        }

        void setText1(String text1) {
            Text1.setText("Name: " + text1);
        }

        void setText2(String text2) {
            Text2.setText("Language: " + text2);
        }

        void setImage(String url) {
            Glide.with(context)
                    .load(url)
                    .placeholder(R.drawable.gamer)
                    .into(image);
        }

    }
}
