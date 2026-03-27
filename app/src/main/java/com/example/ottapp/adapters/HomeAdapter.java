package com.example.ottapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ottapp.R;
import com.example.ottapp.models.Carousel;
import com.example.ottapp.models.CarouselType;

import java.util.List;
import android.os.Handler;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final List<Carousel> carousels;

    private final CarouselAdapter.OnItemClickListener listener;

    public HomeAdapter(List<Carousel> carousels, CarouselAdapter.OnItemClickListener listener) {
        this.carousels = carousels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_carousel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Carousel carousel = carousels.get(position);
        holder.bind(carousel, listener);
    }

    @Override
    public int getItemCount() {
        return carousels.size();
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.stopAutoScroll();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;
        RecyclerView recyclerView;
        android.widget.LinearLayout dotContainer;

        private final Handler handler = new Handler();
        private Runnable runnable;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.carouselTitle);
            recyclerView = itemView.findViewById(R.id.innerRecyclerView);
            dotContainer = itemView.findViewById(R.id.dotIndicatorContainer);
        }


        public void bind(Carousel carousel, CarouselAdapter.OnItemClickListener listener) {
            titleView.setText(carousel.getTitle());
            boolean isFeature = carousel.getType() == CarouselType.FEATURE;
            
            if (isFeature) {
                titleView.setVisibility(View.GONE);
                float density = itemView.getContext().getResources().getDisplayMetrics().density;
                int padding = (int) (16 * density);
                recyclerView.setPadding(padding, 0, padding, 0);

                recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setOnFlingListener(null);
                
                new androidx.recyclerview.widget.PagerSnapHelper().attachToRecyclerView(recyclerView);
                
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        int centerX = recyclerView.getWidth() / 2;
                        int closestPos = -1;
                        float minDistance = Float.MAX_VALUE;
                        for (int i = 0; i < recyclerView.getChildCount(); i++) {
                            View child = recyclerView.getChildAt(i);
                            int childCenterX = (child.getLeft() + child.getRight()) / 2;
                            float distance = Math.abs(centerX - childCenterX);
                            if (distance < minDistance) {
                                minDistance = distance;
                                closestPos = recyclerView.getChildAdapterPosition(child);
                            }
                        }
                        if (closestPos != -1) {
                            updateDots(closestPos, carousel.getItems().size());
                        }
                    }
                });

                setupDots(carousel.getItems().size());
                startAutoScroll(carousel.getItems().size());
            } else {


                dotContainer.setVisibility(View.GONE);
                titleView.setVisibility(View.VISIBLE);

                float density = itemView.getContext().getResources().getDisplayMetrics().density;
                int padding = (int) (16 * density);
                recyclerView.setPadding(padding, 0, padding, 0);
                
                recyclerView.clearOnScrollListeners();
                recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setOnFlingListener(null);


                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    View child = recyclerView.getChildAt(i);
                    child.setScaleX(1.0f);
                    child.setScaleY(1.0f);
                    child.setRotationY(0f);
                    child.setAlpha(1.0f);
                    child.setTranslationX(0f);
                }

                stopAutoScroll();
            }



            CarouselAdapter adapter = new CarouselAdapter(carousel.getItems(), carousel.getType(), listener);
            recyclerView.setAdapter(adapter);
        }

        private void setupDots(int size) {
            dotContainer.removeAllViews();
            dotContainer.setVisibility(View.VISIBLE);
            float density = itemView.getContext().getResources().getDisplayMetrics().density;
            int margin = (int) (4 * density);
            int dotSize = (int) (6 * density);

            for (int i = 0; i < size; i++) {
                View dot = new View(itemView.getContext());
                android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(dotSize, dotSize);
                params.setMargins(margin, 0, margin, 0);
                dot.setLayoutParams(params);
                dot.setBackgroundResource(R.drawable.dot_indicator);
                dot.setAlpha(0.2f);
                dot.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.GRAY));
                dotContainer.addView(dot);
            }
            updateDots(0, size);
        }

        private void updateDots(int currentPos, int size) {
            for (int i = 0; i < dotContainer.getChildCount(); i++) {
                View dot = dotContainer.getChildAt(i);
                if (i == currentPos % size) {
                    dot.setAlpha(1.0f);
                    dot.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#2196F3"))); // Blue
                    dot.setScaleX(1.3f);
                    dot.setScaleY(1.3f);
                } else {
                    dot.setAlpha(0.2f);
                    dot.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.GRAY));
                    dot.setScaleX(1.0f);
                    dot.setScaleY(1.0f);
                }
            }
        }


        private void startAutoScroll(int size) {

            stopAutoScroll();
            runnable = new Runnable() {
                @Override
                public void run() {
                    LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (lm != null) {
                        int currentPos = lm.findFirstCompletelyVisibleItemPosition();
                        if (currentPos == RecyclerView.NO_POSITION) currentPos = lm.findFirstVisibleItemPosition();
                        int nextPos = (currentPos + 1) % size;
                        recyclerView.smoothScrollToPosition(nextPos);
                    }
                    handler.postDelayed(this, 5000);
                }
            };
            handler.postDelayed(runnable, 5000);
        }

        private void stopAutoScroll() {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
                runnable = null;
            }
        }
    }
}
