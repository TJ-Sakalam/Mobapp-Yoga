package com.example.yogaapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity2 extends AppCompatActivity {

    int intvalue;
    Button startBtn;
    private CountDownTimer countDownTimer;
    TextView mtextview;
    private boolean MTimeRunning;
    private long MTimeLeftInMillis;
    private MediaPlayer mediaPlayer;

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
                setContentView(R.layout.activity_bow2);
                break;
            case 2:
                setContentView(R.layout.activity_bridge2);
                break;
            case 3:
                setContentView(R.layout.activity_chair2);
                break;
            case 4:
                setContentView(R.layout.activity_child2);
                break;
            case 5:
                setContentView(R.layout.activity_cobbler2);
                break;
            case 6:
                setContentView(R.layout.activity_cow2);
                break;
            case 7:
                setContentView(R.layout.activity_playji2);
                break;
            case 8:
                setContentView(R.layout.activity_pauseji2);
                break;
            case 9:
                setContentView(R.layout.activity_plankji2);
                break;
            case 10:
                setContentView(R.layout.activity_crunches2);
                break;
            case 11:
                setContentView(R.layout.activity_situp2);
                break;
            case 12:
                setContentView(R.layout.activity_russia2);
                break;
            case 13:
                setContentView(R.layout.activity_rotation2);
                break;
            case 14:
                setContentView(R.layout.activity_twist2);
                break;
            default:
                setContentView(R.layout.activity_third2);
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
                MTimeRunning = false;
                startBtn.setText("START");
                mtextview.setText("00:00");

                // Play completion sound for exactly 5 seconds
                try {
                    mediaPlayer = MediaPlayer.create(ThirdActivity2.this, R.raw.completion_sound);
                    mediaPlayer.start();

                    // Stop after exactly 5 seconds (5000 milliseconds)
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }
                    }, 10000);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Vibrate on completion
                try {
                    Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                    if (vibrator != null && vibrator.hasVibrator()) {
                        vibrator.vibrate(500); // 500ms vibration
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Show completion message
                Toast.makeText(ThirdActivity2.this,
                        "Exercise completed!",
                        Toast.LENGTH_SHORT).show();
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
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onBackPressed() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onBackPressed();
    }
}