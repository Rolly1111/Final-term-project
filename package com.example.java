package com.example.tapthedot;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    Button startButton;
    View circle;
    int score = 0;
    Random random = new Random();
    CountDownTimer gameTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        startButton = findViewById(R.id.startButton);
        circle = findViewById(R.id.circle);

        circle.setOnClickListener(v -> {
            score++;
            scoreText.setText("Score: " + score);
            moveCircle();
        });

        startButton.setOnClickListener(v -> startGame());
    }

    void startGame() {
        score = 0;
        scoreText.setText("Score: 0");

        startButton.setEnabled(false);

        circle.setVisibility(View.VISIBLE);

        circle.post(() -> moveCircle()); // Ensure layout is measured

        gameTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                circle.setVisibility(View.INVISIBLE);
                startButton.setEnabled(true);
                scoreText.setText("Final Score: " + score);
            }
        }.start();
    }

    void moveCircle() {
        RelativeLayout layout = (RelativeLayout) circle.getParent();
        int maxX = layout.getWidth() - circle.getWidth();
        int maxY = layout.getHeight() - circle.getHeight();

        if (maxX < 1 || maxY < 1)
            return; // Prevent crash on invalid layout size

        int x = random.nextInt(maxX);
        int y = random.nextInt(maxY);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) circle.getLayoutParams();
        params.leftMargin = x;
        params.topMargin = y;
        circle.setLayoutParams(params);
    }
}
