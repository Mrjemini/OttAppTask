package com.example.ottapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ottapp.R;
import com.example.ottapp.models.CarouselType;
import com.example.ottapp.models.MediaItem;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {

    private final List<MediaItem> items;
    private final CarouselType type;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(MediaItem item);
    }

    public CarouselAdapter(List<MediaItem> items, CarouselType type, OnItemClickListener listener) {
        this.items = items;
        this.type = type;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId;
        switch (type) {
            case BANNER:
                layoutId = R.layout.item_banner;
                break;
            case SQUARE:
                layoutId = R.layout.item_square;
                break;
            case ROUND:
                layoutId = R.layout.item_round;
                break;
            case FEATURE:
                layoutId = R.layout.item_feature;
                break;
            default:
                layoutId = R.layout.item_poster;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MediaItem item = items.get(position);

        holder.itemView.setScaleX(1.0f);
        holder.itemView.setScaleY(1.0f);
        holder.itemView.setRotationY(0f);
        holder.itemView.setAlpha(1.0f);
        holder.itemView.setTranslationX(0f);
        
        holder.bind(item, listener);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            titleView = itemView.findViewById(R.id.itemTitle);
        }

        public void bind(MediaItem item, OnItemClickListener listener) {
            if (titleView != null) {
                titleView.setText(item.getTitle());
            }
            Glide.with(itemView.getContext())
                    .load(item.getImageUrl())
                    .placeholder(android.R.drawable.progress_horizontal)
                    .error(android.R.drawable.stat_notify_error)
                    .into(imageView);

            itemView.setOnClickListener(v -> listener.onItemClick(item));

            View playFab = itemView.findViewById(R.id.playFab);
            if (playFab != null) {
                playFab.setOnClickListener(v -> listener.onItemClick(item));
            }


            TextView metadataView = itemView.findViewById(R.id.itemMetadata);
            if (metadataView != null) {
                metadataView.setText(R.string.tata_ipl_2026_10m_cricket);
            }
        }
    }
}
