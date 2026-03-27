package com.example.ottapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.media3.common.util.UnstableApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ottapp.adapters.HomeAdapter;
import com.example.ottapp.utils.NetworkUtils;
import com.example.ottapp.viewmodels.HomeViewModel;

import androidx.appcompat.app.AlertDialog;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    @OptIn(markerClass = UnstableApi.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        
        setContentView(R.layout.activity_main);

        View statusBarBg = findViewById(R.id.statusBarBg);
        View headerContainer = findViewById(R.id.headerContainer);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            int top = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            statusBarBg.getLayoutParams().height = top;
            statusBarBg.requestLayout();

            headerContainer.setPadding(0, top, 0, 0);
            return insets;
        });

        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setAppearanceLightStatusBars(false);



        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getCarousels().observe(this, carousels -> {
            adapter = new HomeAdapter(carousels, item -> {
                if (item.getVideoUrl() != null && !item.getVideoUrl().isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                    intent.putExtra("video_url", item.getVideoUrl());
                    intent.putExtra("title", item.getTitle());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtils.isNetworkAvailable(this)) {
            showNoInternetDialog();
        }
    }

    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_no_internet, null);
        builder.setView(view);
        builder.setCancelable(false);
        
        AlertDialog dialog = builder.create();
        dialog.show();

        Button retryButton = view.findViewById(R.id.retryButton);
        retryButton.setOnClickListener(v -> {
            if (NetworkUtils.isNetworkAvailable(MainActivity.this)) {
                dialog.dismiss();
            } else {
                Toast.makeText(MainActivity.this, "Still no internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}