package com.example.ottapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ottapp.data.MediaRepository;
import com.example.ottapp.models.Carousel;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MediaRepository repository;
    private final MutableLiveData<List<Carousel>> carousels = new MutableLiveData<>();

    public HomeViewModel() {
        this.repository = new MediaRepository();
        fetchHomeData();
    }

    public LiveData<List<Carousel>> getCarousels() {
        return carousels;
    }

    private void fetchHomeData() {
        carousels.setValue(repository.getHomeData());
    }
}
