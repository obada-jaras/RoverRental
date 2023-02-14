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

public class AvailableCardAdapter extends RecyclerView.Adapter<AvailableCardAdapter.AvailableCardHolder> {

    Context context;
    ArrayList<AvailabeCar> list;

    public AvailableCardAdapter(Context context, ArrayList<AvailabeCar> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public AvailableCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cars_available_car_card, parent, false);
        return new AvailableCardHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableCardHolder holder, int position) {
        AvailabeCar card = list.get(position);
        holder.brand_txt.setText(card.getBrand());
        holder.price_txt.setText(card.getPrice() + "$/day");
        holder.seats_txt.setText(card.getSeats());
        holder.transmission_txt.setText(card.getTransmission());

        holder.img.setImageBitmap(card.getBtmImg());
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AvailableCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView brand_txt, price_txt, seats_txt, transmission_txt;
        ImageView img;
        private AvailabeCar carCard;

        public AvailableCardHolder(@NonNull View itemView) {
            super(itemView);

            brand_txt = itemView.findViewById(R.id.brand_txt);
            price_txt = itemView.findViewById(R.id.price_txt);
            seats_txt = itemView.findViewById(R.id.seats_txt);
            transmission_txt = itemView.findViewById(R.id.transmission_txt);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(this);
        }

        public void bind(AvailabeCar carCard) {
            this.carCard = carCard;
            img.setImageBitmap(carCard.getBtmImg());
            brand_txt.setText(carCard.getBrand());
            price_txt.setText(String.valueOf(carCard.getPrice()));
            seats_txt.setText(String.valueOf(carCard.getSeats()));
            transmission_txt.setText(String.valueOf(carCard.getTransmission()));
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), CarInformation.class);
            intent.putExtra("carID", carCard.getId());
            view.getContext().startActivity(intent);
        }
    }
}
