package com.example.yogaapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third2);

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
                setContentView(R.layout.activity_third2); // fallback
                break;
        }

        // Find views AFTER layout is set
        startBtn = findViewById(R.id.startbutton);
        mtextview = findViewById(R.id.time);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MTimeRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
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
        String time = mtextview.getText().toString();
        String minStr = time.substring(0, 2);
        String secStr = time.substring(3, 5);

        int minutes = Integer.valueOf(minStr);
        int seconds = Integer.valueOf(secStr);
        int totalSeconds = minutes * 60 + seconds;
        MTimeLeftInMillis = totalSeconds * 1000;

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
                    Intent intent = new Intent(ThirdActivity2.this, ThirdActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("value", newValue);
                    startActivity(intent);
                } else {
                    newValue = 1;
                    Intent intent = new Intent(ThirdActivity2.this, ThirdActivity2.class);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
