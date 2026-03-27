package com.example.ottapp.models;

import java.util.List;

public class Carousel {
    private final String title;
    private final List<MediaItem> items;
    private final CarouselType type;

    public Carousel(String title, List<MediaItem> items, CarouselType type) {
        this.title = title;
        this.items = items;
        this.type = type;
    }

    public String getTitle() { return title; }
    public List<MediaItem> getItems() { return items; }
    public CarouselType getType() { return type; }
}
