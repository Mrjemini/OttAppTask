package com.example.ottapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ottapp.utils.NetworkUtils;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AlertDialog;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        setContentView(R.layout.activity_login);

        View statusBarBg = findViewById(R.id.statusBarBg);
        findViewById(R.id.loginLogo).getRootView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginLogo), (v, insets) -> {
            statusBarBg.getLayoutParams().height = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            statusBarBg.requestLayout();
            return insets;
        });

        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setAppearanceLightStatusBars(false);



        TextInputEditText mobileInput = findViewById(R.id.mobileNumber);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            if (!NetworkUtils.isNetworkAvailable(LoginActivity.this)) {
                showNoInternetDialog();
                return;
            }
            String number = Objects.requireNonNull(mobileInput.getText()).toString();

            if (number.length() == 10) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Please enter a valid 10-digit number", Toast.LENGTH_SHORT).show();
            }
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

        view.findViewById(R.id.retryButton).setOnClickListener(v -> {
            if (NetworkUtils.isNetworkAvailable(LoginActivity.this)) {
                dialog.dismiss();
            } else {
                Toast.makeText(LoginActivity.this, "Still no internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

