package com.example.nu_mad_sp2023_final_project_15;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UploadPictureAdapter extends RecyclerView.Adapter<UploadPictureAdapter.UploadPictureViewHolder> {

    private List<Uri> images;

    public UploadPictureAdapter(List<Uri> images) {
        this.images = images;
    }

    public static class UploadPictureViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUploadPicture;

        public UploadPictureViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUploadPicture = itemView.findViewById(R.id.imgUploadPicture);
        }
    }

    @NonNull
    @Override
    public UploadPictureAdapter.UploadPictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pictures_list, parent, false);
        return new UploadPictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadPictureAdapter.UploadPictureViewHolder holder, int position) {
        Uri imageUri = images.get(position);
        Glide.with(holder.itemView.getContext())
                .load(imageUri)
                .centerCrop()
                .into(holder.imgUploadPicture);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

}
