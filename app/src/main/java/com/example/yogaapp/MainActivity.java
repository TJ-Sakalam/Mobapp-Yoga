package com.example.yogaapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2;
    private TextView tvDaysBefore, tvDaysAfter;
    private ImageView btnIncreaseBefore, btnDecreaseBefore;
    private ImageView btnIncreaseAfter, btnDecreaseAfter;
    private int daysBefore = 1;
    private int daysAfter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize day counter views
        tvDaysBefore = findViewById(R.id.tvDaysBefore);
        tvDaysAfter = findViewById(R.id.tvDaysAfter);
        btnIncreaseBefore = findViewById(R.id.btnIncreaseBefore);
        btnDecreaseBefore = findViewById(R.id.btnDecreaseBefore);
        btnIncreaseAfter = findViewById(R.id.btnIncreaseAfter);
        btnDecreaseAfter = findViewById(R.id.btnDecreaseAfter);

        // Set initial values
        updateDayCounters();

        // Set click listeners for day counters
        btnIncreaseBefore.setOnClickListener(v -> {
            if (daysBefore < 365) {
                daysBefore++;
                updateDayCounters();
            } else {
                showMaxDaysToast();
            }
        });

        btnDecreaseBefore.setOnClickListener(v -> {
            if (daysBefore > 1) {
                daysBefore--;
                updateDayCounters();
            } else {
                showMinDaysToast();
            }
        });

        btnIncreaseAfter.setOnClickListener(v -> {
            if (daysAfter < 365) {
                daysAfter++;
                updateDayCounters();
            } else {
                showMaxDaysToast();
            }
        });

        btnDecreaseAfter.setOnClickListener(v -> {
            if (daysAfter > 1) {
                daysAfter--;
                updateDayCounters();
            } else {
                showMinDaysToast();
            }
        });

        // Initialize buttons
        button1 = findViewById(R.id.startyoga1);
        button2 = findViewById(R.id.startyoga2);

        button1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("days", daysBefore); // Pass days to next activity
            startActivity(intent);
        });

        button2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
            intent.putExtra("days", daysAfter); // Pass days to next activity
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void updateDayCounters() {
        tvDaysBefore.setText(String.valueOf(daysBefore));
        tvDaysAfter.setText(String.valueOf(daysAfter));
    }

    private void showMaxDaysToast() {
        Toast.makeText(this, "Maximum 365 days", Toast.LENGTH_SHORT).show();
    }

    private void showMinDaysToast() {
        Toast.makeText(this, "Minimum 1 day", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_privacy) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://iotexpert1.blogspot.com/2020/10/weight-loss-privacy-ploicy-page.html"));
            startActivity(intent);
            return true;
        }
        if (id == R.id.id_term) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://iotexpert1.blogspot.com/2020/10/weight-loss-terms-and-conditions-page.html"));
            startActivity(intent);
            return true;
        }
        if (id == R.id.rate) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (Exception ex) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
            return true;
        }
        if (id == R.id.more) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=women.workout.female.fitness&hl=en"));
            startActivity(intent);
            return true;
        }
        if (id == R.id.share) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String sharebody = "This is the best yoga \n By this app you stretch your body \n Free Download Now\n" + "https://play.google.com/store/apps/details?id=com.example.yogaapp&h1=en";
            String sharehub = "Yoga App";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, sharehub);
            myIntent.putExtra(Intent.EXTRA_TEXT, sharebody);
            startActivity(Intent.createChooser(myIntent, "share using"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void beforeage18(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("days", daysBefore);
        startActivity(intent);
    }

    public void afterage18(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
        intent.putExtra("days", daysAfter);
        startActivity(intent);
    }

    public void food(View view) {
        startActivity(new Intent(MainActivity.this, FoodActivity.class));
    }
}