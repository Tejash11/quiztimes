package com.example.quiztime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class FinalResult extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView textView;
    int correct,wrong, Correct;
    LinearLayout scoreshare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        Correct = correct + 1;
        correct = getIntent().getIntExtra("correct",0);//default value is here bcs if no value is stored in correct so default value will be stored for correct
        wrong = getIntent().getIntExtra("wrong",0);


        circularProgressBar =  findViewById(R.id.circularProgressBar);
        textView = findViewById(R.id.scores);
        scoreshare = findViewById(R.id.sharescore);
        circularProgressBar.setProgress(Correct);
        textView.setText(Correct +"/10");

        scoreshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage= "\nI scored " + Correct + " out of 10 marks.\n You May also try\n";
                    shareMessage.toUpperCase();
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }
            }
        });
    }
}