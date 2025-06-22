package com.example.yogaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    int[] newArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_activity);

        // Fix: use correct Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Do not override this method

        newArray = new int[]{
                R.id.bow_pose, R.id.bridge_pose, R.id.child_pose, R.id.cobbler_pose, R.id.cow_pose, R.id.playji_pose,
                R.id.pauseji_pose, R.id.plankji_pose, R.id.crunches_pose, R.id.situp_pose, R.id.russia_pose,
                R.id.rotation_pose, R.id.twist_pose, R.id.chair_pose
        };

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Click handler for yoga poses
    public void Imagebuttonclicked(View view) {
        for (int i = 0; i < newArray.length; i++) {
            if (view.getId() == newArray[i]) {
                int value = i + 1;
                Log.i("FIRST", "Pose clicked: " + value);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("pose_id", value); // Optional: pass data
                startActivity(intent);
                break;
            }
        }
    }
}
