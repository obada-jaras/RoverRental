package com.example.android_projects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    Context context;
    ArrayList<Review> list;

    public ReviewAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.car_review_item, parent, false);
        return new ReviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        Review review = list.get(position);
        holder.userName.setText(review.getUserName());
        holder.rating.setText(review.getRating() + "/5");
        holder.comment.setText(review.getComment());
        holder.userImage.setImageBitmap(review.getBmpUserImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ReviewHolder extends RecyclerView.ViewHolder {
        TextView userName, rating, comment;
        ImageView userImage;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.feedbackUser_img);
            userName = itemView.findViewById(R.id.feedbackUsername_txt);
            rating = itemView.findViewById(R.id.feedbackRating_txt);
            comment = itemView.findViewById(R.id.comment_txt);
        }
    }
}
