package com.example.yogaapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {

    int intvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLeftInMillis;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        // Get intent extra
        Intent intent = getIntent();
        intvalue = intent.getIntExtra("value", 0);

        // Load appropriate layout
        switch (intvalue) {
            case 1:
                setContentView(R.layout.activity_bow);
                break;
            case 2:
                setContentView(R.layout.activity_bridge);
                break;
            case 3:
                setContentView(R.layout.activity_chair);
                break;
            case 4:
                setContentView(R.layout.activity_child);
                break;
            case 5:
                setContentView(R.layout.activity_cobbler);
                break;
            case 6:
                setContentView(R.layout.activity_cow);
                break;
            case 7:
                setContentView(R.layout.activity_playji);
                break;
            case 8:
                setContentView(R.layout.activity_pauseji);
                break;
            case 9:
                setContentView(R.layout.activity_plankji);
                break;
            case 10:
                setContentView(R.layout.activity_crunches);
                break;
            case 11:
                setContentView(R.layout.activity_situp);
                break;
            case 12:
                setContentView(R.layout.activity_russia);
                break;
            case 13:
                setContentView(R.layout.activity_rotation);
                break;
            case 14:
                setContentView(R.layout.activity_twist);
                break;
            default:
                setContentView(R.layout.activity_third);
                break;
        }

        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.time);

        // Default time (if needed)
        MTimeLeftInMillis = 60000; // 1 minute default
        updateTimer();

        // Start/Pause Timer
        startBtn.setOnClickListener(v -> {
            if (MTimeRunning) {
                stopTimer();
            } else {
                startTimer();
            }
        });

        // Open time picker dialog when time is clicked
        mtextview.setOnClickListener(v -> {
            if (!MTimeRunning) {
                showTimePickerDialog();
            }
        });

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }

    private void stopTimer() {
        countDownTimer.cancel();
        MTimeRunning = false;
        startBtn.setText("START");
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(MTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                MTimeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                int newValue = intvalue + 1;
                if (newValue <= 14) {
                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", newValue);
                    startActivity(intent);
                } else {
                    newValue = 1;
                    Intent intent = new Intent(ThirdActivity.this, ThirdActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", newValue);
                    startActivity(intent);
                }
            }
        }.start();

        startBtn.setText("PAUSE");
        MTimeRunning = true;
    }

    private void updateTimer() {
        int minutes = (int) MTimeLeftInMillis / 60000;
        int seconds = (int) (MTimeLeftInMillis % 60000) / 1000;

        String timeLeftText = "";
        if (minutes < 10) timeLeftText += "0";
        timeLeftText += minutes + ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        mtextview.setText(timeLeftText);
    }

    private void showTimePickerDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_timer_picker, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        NumberPicker minPicker = dialogView.findViewById(R.id.minPicker);
        NumberPicker secPicker = dialogView.findViewById(R.id.secPicker);
        Button setTimeButton = dialogView.findViewById(R.id.setTimeButton);

        minPicker.setMinValue(0);
        minPicker.setMaxValue(59);
        secPicker.setMinValue(0);
        secPicker.setMaxValue(59);

        // Set current time as default
        String[] timeParts = mtextview.getText().toString().split(":");
        minPicker.setValue(Integer.parseInt(timeParts[0]));
        secPicker.setValue(Integer.parseInt(timeParts[1]));

        setTimeButton.setOnClickListener(v -> {
            int minutes = minPicker.getValue();
            int seconds = secPicker.getValue();
            MTimeLeftInMillis = (minutes * 60 + seconds) * 1000;
            updateTimer();
            dialog.dismiss();
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
