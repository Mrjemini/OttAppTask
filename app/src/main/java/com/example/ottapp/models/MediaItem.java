package com.example.ottapp.models;

public class MediaItem {
    private final String id;
    private final String title;
    private final String imageUrl;
    private final String videoUrl;

    public MediaItem(String id, String title, String imageUrl, String videoUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getVideoUrl() { return videoUrl; }
}
