package com.example.tapthedot;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.View;
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
        moveCircle();
        circle.setVisibility(View.VISIBLE);
        startButton.setEnabled(false);

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
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.circle).getParent();
        int maxX = layout.getWidth() - circle.getWidth();
        int maxY = layout.getHeight() - circle.getHeight();

        int x = random.nextInt(Math.max(maxX, 1));
        int y = random.nextInt(Math.max(maxY, 1));

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) circle.getLayoutParams();
        params.leftMargin = x;
        params.topMargin = y;
        circle.setLayoutParams(params);
    }
}