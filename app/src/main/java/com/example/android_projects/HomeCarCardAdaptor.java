package com.example.android_projects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeCarCardAdaptor extends RecyclerView.Adapter<HomeCarCardAdaptor.HomeCarCardHolder> {

    Context context;
    ArrayList<HomeCarCard> list;

    public HomeCarCardAdaptor(Context context, ArrayList<HomeCarCard> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public HomeCarCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.home_car_card, parent, false);
        return new HomeCarCardHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCarCardHolder holder, int position) {
        HomeCarCard card = list.get(position);
        holder.carName_txt.setText(card.getBrand());
        holder.carPrice_txt.setText(card.getPrice() + "");
        holder.car_img.setImageBitmap(card.getImage());
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HomeCarCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView carName_txt, carPrice_txt;
        ImageView car_img;
        private HomeCarCard carCard;

        public HomeCarCardHolder(@NonNull View itemView) {
            super(itemView);

            car_img = itemView.findViewById(R.id.car_img);
            carName_txt = itemView.findViewById(R.id.carName_txt);
            carPrice_txt = itemView.findViewById(R.id.carPrice_txt);

            itemView.setOnClickListener(this);
        }

        public void bind(HomeCarCard carCard) {
            this.carCard = carCard;
            car_img.setImageBitmap(carCard.getImage());
            carName_txt.setText(carCard.getBrand());
            carPrice_txt.setText(String.valueOf(carCard.getPrice()));
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), CarInformation.class);
            intent.putExtra("carID", carCard.getID());
            view.getContext().startActivity(intent);
        }
    }
}
