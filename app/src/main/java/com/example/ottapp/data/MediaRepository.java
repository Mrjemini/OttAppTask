package com.example.ottapp.data;

import com.example.ottapp.models.Carousel;
import com.example.ottapp.models.CarouselType;
import com.example.ottapp.models.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class MediaRepository {

    public List<Carousel> getHomeData() {
        List<Carousel> carousels = new ArrayList<>();

        // 0. Feature Carousel (Auto-scroll)
        List<MediaItem> features = new ArrayList<>();
        features.add(new MediaItem("0", "Winning Gives You the Respect", "https://picsum.photos/seed/100/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        features.add(new MediaItem("0.1", "Passion in Red", "https://picsum.photos/seed/101/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        features.add(new MediaItem("0.2", "Cricket Season 2026", "https://picsum.photos/seed/102/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));
        features.add(new MediaItem("0.3", "Beyond the Horizon", "https://picsum.photos/seed/103/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        features.add(new MediaItem("0.4", "Urban Jungle", "https://picsum.photos/seed/104/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"));
        features.add(new MediaItem("0.5", "Silence of Snow", "https://picsum.photos/seed/105/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"));
        features.add(new MediaItem("0.6", "The Last Stand", "https://picsum.photos/seed/106/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"));
        features.add(new MediaItem("0.7", "Neon Dreams", "https://picsum.photos/seed/107/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"));
        features.add(new MediaItem("0.8", "Deep Blue", "https://picsum.photos/seed/108/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        features.add(new MediaItem("0.9", "Sky High", "https://picsum.photos/seed/109/320/480", "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        carousels.add(new Carousel("Specials", features, CarouselType.FEATURE));

        // 1. Banner Carousel
        List<MediaItem> banners = new ArrayList<>();

        banners.add(new MediaItem("1", "Epic Journey", "https://picsum.photos/seed/1/320/180", "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));
        banners.add(new MediaItem("2", "Ocean Mystery", "https://picsum.photos/seed/2/320/180", "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
        banners.add(new MediaItem("3", "Mountain Peak", "https://picsum.photos/seed/3/320/180", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"));
        carousels.add(new Carousel("Featured Movies", banners, CarouselType.BANNER));

        // 2. Vertical Poster Carousel
        List<MediaItem> posters = new ArrayList<>();
        posters.add(new MediaItem("4", "The Forest", "https://picsum.photos/seed/4/120/180", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        posters.add(new MediaItem("5", "City Lights", "https://picsum.photos/seed/5/120/180", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"));
        posters.add(new MediaItem("6", "Desert Storm", "https://picsum.photos/seed/6/120/180", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"));
        posters.add(new MediaItem("7", "Ancient Ruins", "https://picsum.photos/seed/7/120/180", "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"));
        carousels.add(new Carousel("Trending Now", posters, CarouselType.VERTICAL_POSTER));

        // 3. Square Carousel
        List<MediaItem> squares = new ArrayList<>();
        squares.add(new MediaItem("8", "Action", "https://picsum.photos/seed/8/140/140", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        squares.add(new MediaItem("9", "Comedy", "https://picsum.photos/seed/9/140/140", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        squares.add(new MediaItem("10", "Drama", "https://picsum.photos/seed/10/140/140", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        squares.add(new MediaItem("11", "Sci-Fi", "https://picsum.photos/seed/11/140/140", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        carousels.add(new Carousel("Genres", squares, CarouselType.SQUARE));

        // 4. Round Carousel
        List<MediaItem> rounds = new ArrayList<>();
        rounds.add(new MediaItem("12", "Hero", "https://picsum.photos/seed/12/80/80", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        rounds.add(new MediaItem("13", "Villain", "https://picsum.photos/seed/13/80/80", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        rounds.add(new MediaItem("14", "Sidekick", "https://picsum.photos/seed/14/80/80", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        rounds.add(new MediaItem("15", "Guide", "https://picsum.photos/seed/15/80/80", "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"));
        carousels.add(new Carousel("New Releases", rounds, CarouselType.ROUND));

        return carousels;
    }
}
