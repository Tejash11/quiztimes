package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class FinalResult extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView textView;
    int correct,wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);

        circularProgressBar =  findViewById(R.id.circularProgressBar);

        circularProgressBar.setProgress(correct);
    }
}